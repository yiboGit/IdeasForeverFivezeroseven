/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	VersionUtil.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.util;

import five.zero.seven.foreveryb.footstone.base.entity.HasVersion;
import five.zero.seven.foreveryb.server.service.user.message.UserMessage;

/**
 * 
 * 版本号验证的工具类
 * 
 * @author wangyibo
 *
 */
public class VersionUtil {

  public static void checkVersion(long version, HasVersion poVersion)
      throws Exception {
    if (poVersion != null && version != poVersion.getVersion()) {
      throw new Exception(UserMessage.noVersion);
    }
  }
  
}
