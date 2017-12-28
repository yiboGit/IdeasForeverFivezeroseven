/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	TokenManager.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月25日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.authorization.token;

/**
 * Title: REST 鉴权   
 * Description: 登录用户的身份鉴权
 * @author wangyibo
 *
 */
public interface TokenManager {
  
  /** 用于在Spring容器的默认ID。 */
  public static final String DEFAULT_CONTEXT_ID = "footstone-authorization-token.tokenManager";
  
  /**
   * 创建
   * */
  String createToken(String username);  

  /**
   * 检查
   * */
  boolean checkToken(String token); 
  
  /**
   * 注销
   * */
  void deleteToken(String token);
  
}
