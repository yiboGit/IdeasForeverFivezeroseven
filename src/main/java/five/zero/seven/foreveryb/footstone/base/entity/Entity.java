/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	Entity.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.entity;

import java.io.Serializable;

/**
 * 提供所有数据实体的基类
 * @author wangyibo
 *
 */
public abstract class Entity implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = -4277773014737849096L;

  private String uuid;

  /**
   * 全局唯一标识。
   *  */
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) throws UnsupportedOperationException{
    this.uuid = uuid;
  }
}
