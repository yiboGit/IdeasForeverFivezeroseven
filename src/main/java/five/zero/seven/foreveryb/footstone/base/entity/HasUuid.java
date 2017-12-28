/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	HasUuid.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.entity;

/**
 * **指示HasUuid**
 * 
 * 指示类拥有全局唯一标识属性，设计被用于实体数据对象。
 * 
 * @author wangyibo
 *
 */
public interface HasUuid {

  /**
   * 全局唯一标识。
   */
  String getUuid();

  /**
   * @see #getUuid()
   * @throws UnsupportedOperationException
   *           当实现类不支持修改全局唯一标识时抛出。
   */
  void setUuid(String uuid) throws UnsupportedOperationException;
}
