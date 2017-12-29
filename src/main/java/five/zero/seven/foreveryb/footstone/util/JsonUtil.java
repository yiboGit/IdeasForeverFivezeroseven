/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	JsonUtil.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月28日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.util;

import com.fasterxml.jackson.databind.DeserializationFeature;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import five.zero.seven.foreveryb.footstone.format.DateTimeFormat;

/**
 * json <---> object 转换的工具类
 * 
 * 主要功能包括三部分： 1.对象和json字符串的转换 2.json字符串转对象 
 * 3.json字符串对特定对象list集合的转换 。 个人认为这个工具还是很必要的。
 * 
 * @author wangyibo
 *
 */
public class JsonUtil {

   private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

  private static ThreadLocal<ObjectMapper> threadLocal = new ThreadLocal<ObjectMapper>() {
    @Override
    protected ObjectMapper initialValue() {
      ObjectMapper mapper = new ObjectMapper();
       mapper.setDateFormat(new DateTimeFormat(DATE_FORMAT));
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return mapper;
    }
  };

  public static String objectToJson(Object value) throws RuntimeException {
    if (value == null)
      return null;

    try {
      return threadLocal.get().writeValueAsString(value);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public static <T> T jsonToObject(String json, Class<T> valueType) throws RuntimeException {
    if (StringUtils.isBlank(json))
      return null;

    try {
      return threadLocal.get().readValue(json, valueType);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public static <T> List<T> jsonToArrayList(String json, Class<T> elementType)
      throws RuntimeException {
    if (StringUtils.isBlank(json)) {
      return new ArrayList<T>();
    }

    try {
      return threadLocal.get().readValue(json,
          threadLocal.get().getTypeFactory().constructParametricType(ArrayList.class, elementType));
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
