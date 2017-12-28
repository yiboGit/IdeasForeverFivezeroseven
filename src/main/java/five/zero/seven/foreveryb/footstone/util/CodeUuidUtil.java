/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	CodeUuidUtil.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.util;

import java.util.UUID;

/**
 * Title: 生成UUID （特殊标识） 未来也可作为数据唯一标识
 *
 * @author wangyibo
 */
public class CodeUuidUtil {

  /**
   * 生成唯一的token标识
   * 
   * @return uuid
   */
  public static String createUUID() {
    return UUID.randomUUID().toString();
  }

  /**
   * 生成唯一属于数据的id标识（主键）
   * 
   * @return uuid
   */
  public static String createEntityId() {
    return UUID.randomUUID().toString().replaceAll("-", " ");
  }

}
