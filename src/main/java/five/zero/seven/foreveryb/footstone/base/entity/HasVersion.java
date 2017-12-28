/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	HasVersion.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.entity;

/**
 * **指示 HasVersion版本号**
 * 指示类拥有描述数据实体版本信息的属性。
 * 
 * @author wangyibo
 *
 */
public interface HasVersion {

  /** 约定的起始版本号。 */
  public static final long START_VERSION = 0L;

  /**
   * 版本号。
   */
  long getVersion();

  /**
   * @throws UnsupportedOperationException
   *           当实现类不提供修改版本号功能时抛出。
   */
  void setVersion(long version) throws UnsupportedOperationException;
}
