/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	CollectionUtil.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import five.zero.seven.foreveryb.footstone.base.entity.HasUuid;

/**
 * Title: Collection 工具类
 * 
 * @author wangyibo
 *
 */
public class CollectionUtil {

  /**
   * collection 不为空
   * 
   * @param Collection<?>
   *          集合
   * @return boolean 判断结果
   * 
   */
  public static boolean isNotEmpty(Collection<?> collection) {
    if (collection != null && !collection.isEmpty() && collection.size() != 0) {
      return true;
    }
    return false;
  }

  /**
   * collection 转 list<String>
   * 
   * @param Collection<?>
   *          集合
   * @return list<String> uuids
   * 
   */
  public static List<String> toUuids(Collection<? extends HasUuid> objectList) {
    List<String> uuids = new ArrayList<String>();
    if (objectList == null)
      return uuids;

    for (HasUuid object : objectList) {
      if (StringUtils.isNotBlank(object.getUuid()) && !uuids.contains(object.getUuid())) {
        uuids.add(object.getUuid());
      }
    }
    return uuids;
  }

  /**
   * collection 转 Map<(String)uuid, object> 键值对
   * 将过滤uuid为空的数据
   * @param Collection<?>
   *          集合
   * @return Map<String, object> 
   * 
   */
  public static <T extends HasUuid>Map<String, T> toMap(Collection<T> objectList) {
    Map<String, T> map = new HashMap<String, T>();
    if (objectList == null)
      return map;

    for (T object : objectList) {
      if (StringUtils.isNotBlank(object.getUuid()) && !map.containsKey(object.getUuid())) {
        map.put(object.getUuid(), object);
      }
    }
    return map;
  }

}
