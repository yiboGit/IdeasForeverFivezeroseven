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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import five.zero.seven.foreveryb.footstone.base.query.QueryFilter;
import five.zero.seven.foreveryb.footstone.util.CodeUuidUtil;
import five.zero.seven.foreveryb.footstone.util.VersionUtil;
import five.zero.seven.foreveryb.server.mappers.user.UserMapper;
import five.zero.seven.foreveryb.server.pojo.user.User;
import five.zero.seven.foreveryb.server.service.user.UserService;
import five.zero.seven.foreveryb.server.service.user.message.UserMessage;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.orderbyhelper.OrderByHelper;

/**
 * @author wangyibo
 *
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service(value = UserService.DEFAULT_CONTEXT_ID)
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

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
    Example example = new Example(User.class);
    example.createCriteria().andCondition("name =", name).andCondition("passwd =", passwd);
    User user = userMapper.selectOneByExample(example);
    return (user != null && user.getName().equals(name)) ? true : false;
  }

  /**
   * @description 用户获取逻辑的具体实现
   * @param uuid
   * @return
   */
  public User getUser(String uuid) {
    Example example = new Example(User.class);
    example.createCriteria().andCondition("uuid =", uuid);
    User user = userMapper.selectOneByExample(example);
    if (user == null) {
      example.createCriteria().andCondition("name =", uuid);
      user = userMapper.selectOneByExample(example);
    }
    return user;
  }

  public void saveUser(User user) throws Exception {

    if (StringUtils.isNotBlank(user.getUuid())) { // 编辑

      // 验证版本号
      safeGet(user.getId(), user.getUuid(), user.getVersion());
      // 数据库设置一下 版本号为自增
      long version = user.getVersion() + 1;
      user.setVersion(version);
      userMapper.updateByPrimaryKey(user);
    } else {
      String uuid = CodeUuidUtil.createEntityId();// 新建
      // 验证用户名的唯一性
      if (!ValidateUser(user)) {
        throw new Exception(UserMessage.onlyOneUser);
      }
      user.setCreateTime(new Date());
      user.setUuid(uuid);
      userMapper.insert(user);
    }

  }

  private User safeGet(int id, String uuid, long verstion) throws Exception {
    User entity = userMapper.selectByPrimaryKey(id);
    if (entity == null) {
      throw new Exception(UserMessage.noUser);
    }
    if (!entity.getUuid().equals(uuid)) {
      throw new Exception(UserMessage.uuidNotSame);
    }
    VersionUtil.checkVersion(verstion, entity);

    return entity;
  }

  private boolean ValidateUser(User user) throws Exception {
    if (StringUtils.isBlank(user.getName())) {
      throw new Exception(UserMessage.noName);
    }
    Example example = new Example(User.class);
    example.createCriteria().andCondition("name =", user.getName());
    user = userMapper.selectOneByExample(example);
    return user == null ? true : false;

  }

  public PageInfo<User> queryUser(QueryFilter filter) throws Exception {
    PageHelper.startPage(filter.getPageNum(), filter.getPageSize());
    List<User> users = userMapper.query(filter);
    PageInfo queryResult = new PageInfo(users);
    return queryResult;
  }

  public PageInfo<User> selectAllUsers(QueryFilter filter) throws Exception {
    if (filter == null) {
      List<User> users = userMapper.selectAll();
      return new PageInfo(users);
    }
    Example queryExample = new Example(User.class);
    Criteria criteria = queryExample.createCriteria();
    if (StringUtils.isBlank(filter.getKeyWord())) {
      criteria.orLike("name", "%" + filter.getKeyWord() + "%").orLike("code",
          "%" + filter.getKeyWord() + "%");
    } else if (filter.getFilter() != null) {
      Map<String, Object> queryFilter = filter.getFilter();
      // if (queryFilter.get("code") != null) {
      // criteria.andCondition("code =", queryFilter.get("code"));
      // }
      // if (queryFilter.get("name") != null) {
      // criteria.andCondition("name =", queryFilter.get("name"));
      // }
      // if(queryFilter.get("gentle") != null) {
      // criteria.andCondition("gentle =", queryFilter.get("gentle"));
      // }
      // if(queryFilter.get("email") != null) {
      // criteria.andCondition("email =", queryFilter.get("email"));
      // }
      // if(queryFilter.get("city") != null) {
      // criteria.andCondition("city =", queryFilter.get("city"));
      // } 还可以写成，有特殊再进行调整
      for (String key : queryFilter.keySet()) {
        if (queryFilter.get(key) != null) {
          criteria.andCondition(key + " =", queryFilter.get(key));
        }
      }
    }
    String sortPropety = "";
    if (filter.getSorts() != null) {
      // queryExample.orderBy(property)
      Map<String, String> sorts = filter.getSorts();
      for (Iterator<String> key = sorts.keySet().iterator(); key.hasNext();) {
        if (StringUtils.isNotBlank(sorts.get(key))) {
          if (key.hasNext()) {
            sortPropety += key + " " + sorts.get(key) + ",";
          } else {
            sortPropety += key + " " + sorts.get(key);
          }

        }
      }
    }

    List<User> users = new ArrayList<User>();
    if (StringUtils.isBlank(sortPropety)) {
      PageHelper.startPage(filter.getPageNum(), filter.getPageSize());
      OrderByHelper.orderBy("id desc");
      users = userMapper.selectByExample(queryExample);
    } else {
      PageHelper.startPage(filter.getPageNum(), filter.getPageSize());
      OrderByHelper.orderBy(sortPropety);
      users = userMapper.selectByExample(queryExample);
    }

    PageInfo<User> result = new PageInfo<User>(users);
    return result;
  }

}
