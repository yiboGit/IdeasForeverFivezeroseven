/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	UserServiceImpl.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.server.service.user.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import five.zero.seven.foreveryb.footstone.util.CodeUuidUtil;
import five.zero.seven.foreveryb.footstone.util.VersionUtil;
import five.zero.seven.foreveryb.server.api.user.User;
import five.zero.seven.foreveryb.server.mappers.user.UserDao;
import five.zero.seven.foreveryb.server.service.user.UserService;
import five.zero.seven.foreveryb.server.service.user.message.UserMessage;

/**
 * @author wangyibo
 *
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service(value = UserService.DEFAULT_CONTEXT_ID)
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  /*
   * public UserDao getUserDao() { return userDao; }
   * 
   * @Resource // 默认按名称(userMapper)注入,若名称匹配失败，则按照类型匹配 public void
   * setUserDao(UserDao UserDao) { this.userDao = UserDao; }
   */

  /**
   * @description 登录逻辑的具体实现，对应事务包括用户的查询和日志的插入两部分
   * @param uname
   * @param passwd
   * @return 用户名、密码匹配成功，返回true；否则，返回false;
   */
  @Transactional(readOnly = false) // 必须设置为false,因为此处切入了日志的保存逻辑
  public boolean login(String name, String passwd) {
    User user = userDao.findUserByUnameAndPasswd(name, passwd);
    return user == null ? false : true;
  }

  /**
   * @description 用户获取逻辑的具体实现
   * @param id
   * @return
   */
  public Object getUser(String uuid) {
    User user = userDao.findUserById(uuid);
    if (user == null) {
      user = userDao.findUserByUname(uuid);
    }
    return user;
  }

  public void saveUser(User user) throws Exception {

    if (StringUtils.isNotBlank(user.getUuid())) { // 编辑
      
      //验证版本号
      safeGet(user.getUuid(), user.getVersion());
      long version = user.getVersion() + 1;
      user.setVersion(version);
      userDao.saveUser(user);
    } else {
      String uuid = CodeUuidUtil.createEntityId();// 新建
      //验证用户名的唯一性
      if(!ValidateUser(user)) {
        throw new Exception(UserMessage.onlyOneUser);
      }
      user.setUuid(uuid);
      userDao.createUser(user);
    }
    
  }

  private User safeGet(String uuid, long verstion) throws Exception {
    User entity = userDao.findUserById(uuid);
    if (entity == null) {
      throw new Exception(UserMessage.noUser);
    }
    VersionUtil.checkVersion(verstion, entity);

    return entity;
  }
  
  private boolean ValidateUser (User user) throws Exception {
    if(StringUtils.isBlank(user.getName())) {
      throw new Exception(UserMessage.noName);
      }
    return userDao.findUserByUname(user.getName()) == null ? true : false;
    
  }

}
