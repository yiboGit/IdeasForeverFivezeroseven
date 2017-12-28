/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	SecurityAspect.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月25日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.aspect;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import five.zero.seven.foreveryb.footstone.authorization.token.TokenManager;
import five.zero.seven.foreveryb.footstone.base.annotation.IgnoreSecurity;
import five.zero.seven.foreveryb.footstone.util.Constants;
import five.zero.seven.foreveryb.footstone.util.WebContextUtil;

/**
 * Title:安全检查切面(是否登录检查) Description: 通过验证Token维持登录状态
 * 
 * @author wangyibo
 *
 */
@Component
@Aspect
public class SecurityAspect {

  /** Log4j日志处理 */
  private static final Logger log = Logger.getLogger(SecurityAspect.class);

  @Autowired
  private TokenManager tokenManager;

  @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public Object execute(ProceedingJoinPoint pjp) throws Throwable {
    // 从切点上获取目标方法
    MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
    log.debug("methodSignature : " + methodSignature);
    Method method = methodSignature.getMethod();
    log.debug(
        "Method : " + method.getName() + " : " + method.isAnnotationPresent(IgnoreSecurity.class));
    // 若目标方法忽略了安全性检查,则直接调用目标方法
    if (method.isAnnotationPresent(IgnoreSecurity.class)) {
      return pjp.proceed();
    }

    // 从 request header 中获取当前 token
    String token = WebContextUtil.getRequest().getHeader(Constants.DEFAULT_TOKEN_NAME);
    // 检查 token 有效性
    if (!tokenManager.checkToken(token)) {
      String message = String.format("token [%s] is invalid", token);
      log.debug("message : " + message);
      throw new Exception(message);
    }
    // 调用目标方法
    return pjp.proceed();
  }
}
