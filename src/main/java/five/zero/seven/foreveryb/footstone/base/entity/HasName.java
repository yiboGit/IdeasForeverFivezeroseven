/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	HasName.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.entity;

/**
 * **指示HasName**
 * 指示类具有名称属性，名称可以被看作是实体对象摘要(例如用户名)。
 * @author wangyibo
 *
 */
public interface HasName {

  /**
   * 名称
   */
  String getName();

  /**
   * @see #getName()
   * @throws UnsupportedOperationException
   *           当实现类不支持修改名称时抛出。
   */
  void setName(String name) throws UnsupportedOperationException;
}
