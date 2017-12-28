/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	WebContextUtil.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Web上下文工具类
 * @author wangyibo
 *
 */
public class WebContextUtil {

  
  /**     
   * @description 获取HTTP请求    
   * @return HttpServletRequest request
   */
  public static HttpServletRequest getRequest() {
      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
              .getRequestAttributes()).getRequest();
      return request;
  }
  
}
