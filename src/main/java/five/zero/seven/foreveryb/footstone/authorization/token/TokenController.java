/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	TokenController.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.authorization.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import five.zero.seven.foreveryb.footstone.base.annotation.IgnoreSecurity;
import five.zero.seven.foreveryb.footstone.base.login.param.LoginParameter;
import five.zero.seven.foreveryb.footstone.base.login.param.Response;
import five.zero.seven.foreveryb.footstone.util.Constants;
import five.zero.seven.foreveryb.server.service.user.UserService;

/**
 * token的管理 处理用户的登录、登出操作
 * 
 * @author wangyibo
 *
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

  private static final Logger log = Logger.getLogger(TokenController.class);

  @Autowired
  private TokenManager tokenManager;
  @Autowired
  private UserService userService;

  /*
   * public UserService getUserService() { return userService; }
   * 
   * @Resource(name = "userService") public void setUserService(UserService
   * userService) { this.userService = userService; }
   * 
   * public TokenManager getTokenManager() { return tokenManager; }
   * 
   * @Resource(name = "tokenManager") public void setTokenManager(TokenManager
   * tokenManager) { this.tokenManager = tokenManager; }
   */

  /**
   * @description 登录处理
   * @author rico
   * @created 2017年7月4日 下午4:53:58
   */
  @RequestMapping(method = RequestMethod.POST)
  @IgnoreSecurity
  public Response login(@RequestBody LoginParameter loginUser, HttpServletResponse response) {
    if (loginUser == null)
      return new Response().failure("缺少必要参数");

    String uname = loginUser.getUname();
    String passwd = loginUser.getPasswd();

    boolean flag = userService.login(uname, passwd);
    if (flag) {
      String token = tokenManager.createToken(uname);
      log.debug("**** Generate Token **** : " + token);
      Cookie cookie = new Cookie(Constants.DEFAULT_TOKEN_NAME, token);
      log.debug("Write Token to Cookie and return to the Client : " + cookie.toString());
      response.addCookie(cookie);
      return new Response().success("Login Success...");
    }
    return new Response().failure("Login Failure...");
  }

  /**
   * @description 登出处理
   * @author rico
   * @created 2017年7月4日 下午4:53:58
   */
  @RequestMapping(method = RequestMethod.DELETE)
  @IgnoreSecurity
  public Response logout(HttpServletRequest request) {
    String token = request.getHeader(Constants.DEFAULT_TOKEN_NAME);
    tokenManager.deleteToken(token);
    log.debug("Logout Success...");
    return new Response().success("Logout Success...");
  }

}
