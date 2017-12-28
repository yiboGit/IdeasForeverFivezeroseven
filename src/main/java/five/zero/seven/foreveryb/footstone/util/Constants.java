/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	Constants.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.util;

/**
  * Title: 登录的相关常量  id,token相关信息   
 * @author wangyibo
 *
 */
public class Constants {

  /**
   * 存储当前登录用户id的字段名
   */
  public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

  /**
   * token有效期（小时）
   */
  public static final int TOKEN_EXPIRES_HOUR = 72;

  /**  存放Token的header字段  (@author: rico) */      
  public static final String DEFAULT_TOKEN_NAME = "X-Token";
  
}
