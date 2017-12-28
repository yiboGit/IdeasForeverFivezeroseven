/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	HasUCN.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.entity;

/**
 * 指示类同时拥有全局唯一标识uuid、code和名称name属性。
 * @author wangyibo
 *
 */
public interface HasUCN extends HasUuid,HasCode,HasName{
  //作为标识
}
