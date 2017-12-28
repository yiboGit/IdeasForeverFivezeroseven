/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	UserService.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.server.service.user;

import five.zero.seven.foreveryb.server.api.user.User;

/**
 * UserService 用户
 * 对用户相关的业务逻辑的抽象(面向接口编程)
 * 
 * @author wangyibo
 *
 */
public interface UserService {

  /** 用于在Spring容器的默认ID。 */
  public static final String DEFAULT_CONTEXT_ID = "server-service-user.deviceService";
  
  /**     
   * @description 用户登录逻辑
   * @param name
   * @param passwd
   * @return     
   */
  public boolean login(String name, String passwd);
  
  /**     
   * @description 用户注册逻辑
   * @param User
   * @return     
   */
  public void saveUser(User user) throws Exception;
  
  /**     
   * @description  用户获取逻辑
   * @param id
   * @return     
   */
  public Object getUser(String uuid); 
  
}
