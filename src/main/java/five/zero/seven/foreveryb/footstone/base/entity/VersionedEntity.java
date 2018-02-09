/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	VersionedEntity.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.entity;

import java.util.Date;

import tk.mybatis.mapper.annotation.Version;

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
  
  @Version
  private long version = HasVersion.START_VERSION;
  
  private Date versionTime;
  private Date createTime;

  /** 版本号。从0开始计数，默认为{HasVersion.DEFAULT_VERSION}. */
  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  /** 版本修改时间，默认系统当前时间 */
  public Date getVersionTime() {
    return versionTime;
  }

  public void setVersionTime(Date versionTime) {
    this.versionTime = versionTime;
  }

  /** 创建时间 */
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  
  
  
}
