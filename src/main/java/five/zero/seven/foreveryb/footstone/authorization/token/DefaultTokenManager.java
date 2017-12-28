/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	DefaultTokenManager.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月25日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.authorization.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import five.zero.seven.foreveryb.footstone.util.CodeUuidUtil;

/**
 * Title: TokenManager的默认实现 Description: 管理 Token
 * 
 * @author wangyibo
 *
 */
@Service(value = TokenManager.DEFAULT_CONTEXT_ID)
public class DefaultTokenManager implements TokenManager {
  
  /** 将token存储到JVM内存(ConcurrentHashMap)中  */      
  private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

  /**
   *  利用UUID创建Token(用户登录时，创建Token)
   * */
  public String createToken(String username) {
    String token = CodeUuidUtil.createUUID();
    tokenMap.put(token, username);
    return token;
  }

  /**
   * Token验证(用户登录验证)
   * */
  public boolean checkToken(String token) {
    return StringUtils.isNotEmpty(token) && tokenMap.containsKey(token);
  }

  /**
   * Token删除(用户登出时，删除Token)
   * */
  public void deleteToken(String token) {
    tokenMap.remove(token);
  }

}
