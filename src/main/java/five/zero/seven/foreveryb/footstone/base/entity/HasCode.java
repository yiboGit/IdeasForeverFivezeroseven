/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	HasCode.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.entity;

/**
 * **指示HasCode** 
 * 指示类拥有code属性，代码可以作为业务层面数据实体标识(例如用户的账户等...)。
 * 
 * @author wangyibo
 *
 */
public interface HasCode {

  /**
   * 代码。
   */
  String getCode();

  /**
   * @see #getCode()
   * @throws UnsupportedOperationException
   *           当实现类不支持修改代码时抛出。
   */
  void setCode(String code) throws UnsupportedOperationException;
}
