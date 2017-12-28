/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	CorsFilter.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import five.zero.seven.foreveryb.footstone.util.CollectionUtil;

/**
 * Title: 跨域访问处理(跨域资源共享)    
 * Description: 解决前后端分离架构中的跨域问题
 * @author wangyibo
 *
 */
public class CorsFilter implements Filter {

  /** Log4j日志处理 */
  private static final Logger log = Logger.getLogger(CorsFilter.class);
  
  private String allowOrigin;
  private String allowMethods;
  private String allowCredentials;
  private String allowHeaders;
  private String exposeHeaders;

  public void init(FilterConfig filterConfig) throws ServletException {
    allowOrigin = filterConfig.getInitParameter("allowOrigin");
    allowMethods = filterConfig.getInitParameter("allowMethods");
    allowCredentials = filterConfig.getInitParameter("allowCredentials");
    allowHeaders = filterConfig.getInitParameter("allowHeaders");
    exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
  }

  /** 
   * @description 通过CORS技术实现AJAX跨域访问,只要将CORS响应头写入response对象中即可
   * @param req
   * @param res
   * @param chain
   * @throws IOException
   * @throws ServletException     
   */  
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    String currentOrigin = request.getHeader("Origin");
    log.debug("currentOrigin : " + currentOrigin);
    if (StringUtils.isNotEmpty(allowOrigin)) {
        List<String> allowOriginList = Arrays
                .asList(allowOrigin.split(","));
        log.debug("allowOriginList : " + allowOrigin);
        if (CollectionUtil.isNotEmpty(allowOriginList)) {
            if (allowOriginList.contains(currentOrigin)) {
                response.setHeader("Access-Control-Allow-Origin",
                        currentOrigin);
            }
        }
    }
    if (StringUtils.isNotEmpty(allowMethods)) {
        response.setHeader("Access-Control-Allow-Methods", allowMethods);
    }
    if (StringUtils.isNotEmpty(allowCredentials)) {
        response.setHeader("Access-Control-Allow-Credentials",
                allowCredentials);
    }
    if (StringUtils.isNotEmpty(allowHeaders)) {
        response.setHeader("Access-Control-Allow-Headers", allowHeaders);
    }
    if (StringUtils.isNotEmpty(exposeHeaders)) {
        response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
    }
    chain.doFilter(req, res);
  }

  public void destroy() {
    
  }
}
