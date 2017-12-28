/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2017，所有权利保留。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	VersionedEntity.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.entity;

/**
 * 提供所有具有版本控制信息的数据实体的基类。
 * @author wangyibo
 *
 */
public abstract class VersionedEntity extends Entity implements HasVersion{

  /**
   * 
   */
  private static final long serialVersionUID = 2051990708382915648L;
  
  private long version = HasVersion.START_VERSION;

  /** 版本号。从0开始计数，默认为{HasVersion.DEFAULT_VERSION}. */
  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }
}
