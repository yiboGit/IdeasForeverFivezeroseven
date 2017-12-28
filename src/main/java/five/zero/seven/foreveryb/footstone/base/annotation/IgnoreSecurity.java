/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	IgnoreSecurity.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月25日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title:自定义注解     
 * Description: 标识是否忽略REST安全性检查
 * @author wangyibo
 *
 */    
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented
public @interface IgnoreSecurity {

}
