/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2017，所有权利保留。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	UCN.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月28日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.entity;

/**
 * 包含关键信息的对象（UCN）
 * 
 * 简单介绍一下： 这是我最喜欢的一种对象设计思想，当我们需要在A中引入B对象作为变量时，直接保存B类型往往会保存很多不必要信息
 * 但是使用UCN结构可以很好的保存B对象的关键信息,减少冗余信息；同理，在页面中展示信息时UCN结构，可以很好地展示关键信息，避免
 * 其他不必要的信息泄露。
 * 
 * @author wangyibo
 *
 */
public class UCN extends Entity implements HasUCN {

  /**
   * 
   */
  private static final long serialVersionUID = -8417374807517178175L;
  
  private String code;
  private String name;

  /**
   * 构造对象。
   */
  public UCN() {
    // Do Nothing
  }

  /**
   * 指定全局唯一标识、代码和名称构造对象。
   * 
   * @param code
   * @param name
   */
  public UCN(String uuid, String code, String name) {
    setUuid(uuid);
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) throws UnsupportedOperationException {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) throws UnsupportedOperationException {
    this.name = name;
  }

}
