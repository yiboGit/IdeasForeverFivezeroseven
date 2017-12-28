/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	LoginParam.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.login.param;

import java.io.Serializable;

/**
 * 登录参数 
 * 
 * @author wangyibo
 *
 */
public class LoginParameter implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 6168434383473860320L;
  
  
  private String uname;
  private String passwd;

  /** ：用户名 */
  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  /** 密码： */
  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

}
