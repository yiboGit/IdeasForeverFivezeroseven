/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	QueryFilter.java
 * 模块说明：	
 * 修改历史：
 * 2018年2月6日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.base.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyibo
 *
 */
public class QueryFilter implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = -7139536938760113205L;
  public static final String ASC = "asc";
  public static final String DESC = "desc";

  private String keyWord;
  private Integer pageNum;
  private Integer pageSize;
  private Map<String, Object> filter = new HashMap<String, Object>();
  private Map<String, String> sorts = new HashMap<String, String>();

  /** keyWord：关键字，查询条件 */
  public String getKeyWord() {
    return keyWord;
  }

  public void setKeyWord(String keyWord) {
    this.keyWord = keyWord;
  }

  /** pageNum： 查询时的当前页 */
  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  /** pageSize： 每页的数量 */
  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  /** filter： 进行查询的过滤条件*/
  public Map<String, Object> getFilter() {
    return filter;
  }

  public void setFilter(Map<String, Object> filter) {
    this.filter = filter;
  }

  /** sorts：查询时候指定的排序 */
  public Map<String, String> getSorts() {
    return sorts;
  }

  public void setSorts(Map<String, String> sorts) {
    this.sorts = sorts;
  }

}
