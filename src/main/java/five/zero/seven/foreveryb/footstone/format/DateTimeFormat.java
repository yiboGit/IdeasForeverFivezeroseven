/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	DateFormat.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月29日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.format;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对日期的格式 进行标准化限定
 * 
 * 默认模板为："yyyy-MM-dd'T'HH:mm:ss.SSSZ" 其中后面的三个SSS指的是毫秒，Z代表的时区，中间的T代表可替换的任意字符。
 * 
 * @author wangyibo
 *
 */
public class DateTimeFormat extends DateFormat {

  /**
   * 
   */
  private static final long serialVersionUID = -3572909426478890757L;

  /** 默认日期格式模板。 */
  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

  /** 日期匹配格式数组。 */
  public static final String[] DATE_FORMATS = new String[] {
      "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };

  private String dateFormat;
  private SimpleDateFormat simpleFormat;
  private List<SimpleDateFormat> listFormat = new ArrayList<SimpleDateFormat>();

  /**
   * 构造函数
   */
  public DateTimeFormat(String dateFormate) {
    this.dateFormat = dateFormate;
    for (String string : DATE_FORMATS) {
      SimpleDateFormat simpleFormat = new SimpleDateFormat(string);
      listFormat.add(simpleFormat);
    }
    simpleFormat = new SimpleDateFormat(dateFormate);
  }
  
  /**
   * 构造函数
   */
  public DateTimeFormat() {
    this(DEFAULT_DATE_FORMAT);
  }

  @Override
  public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
    return simpleFormat.format(date, toAppendTo, fieldPosition);
  }

  @Override
  public Date parse(String source, ParsePosition pos) {
    RuntimeException pe = null;
    for (SimpleDateFormat sdf : listFormat) {
      try {
        Date date = sdf.parse(source.trim(), pos);
        if (date != null)
          return date;
      } catch (RuntimeException e) {
        pe = e;
      }
    }
    throw pe;
  }
  
  /**
   * Creates a copy of this <code>SimpleDateFormat</code>. This also clones the
   * format's date format symbols.
   *
   * @return a clone of this <code>SimpleDateFormat</code>
   */
  public Object clone() {
    return new DateTimeFormat(dateFormat);
  }

}
