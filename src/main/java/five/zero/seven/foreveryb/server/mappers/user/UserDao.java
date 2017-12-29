/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	UserMapperDao.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.server.mappers.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import five.zero.seven.foreveryb.server.api.user.User;


/**
 * User 类与SQL的映射关系   （目前最简单的使用）
 * 以面向对象的方式对类User的操作映射成为对应的持久化操作
 * @author wangyibo
 *
 */
public interface UserDao {

  /**     
   * @description 根据指定的name，passwd查询用户
   * @param name
   * @param passwd
   * @return     
   */
  @Select("select * from idear_yb_user where uname = #{name} and passwd = #{passwd}")
  public User findUserByUnameAndPasswd(@Param("name") String uname,
          @Param("passwd") String passwd);

    
  /**     
   * @description 持久化用户，向数据库插入一条用户记录
   * @param user     
   */
  @Insert("insert into idear_yb_user(uuid, version, code, name, passwd, gentle, email, city) values (#{uuid},#{version},#{code},#{name},#{passwd},#{gentle},#{email},#{city})")
  public void createUser(User user);
  
  /**     
   * @description 持久化用户，向数据库插入一条用户记录
   * @param user     
   */
  @Insert("update idear_yb_user set code=#{code}, name=#{name}, passwd=#{passwd}, gentle=#{gentle}, email=#{email}, city=#{city} where uuid=#{uuid}")
  public void saveUser(User user);
  
    
  /**     
   * @description 根据指定的name查询用户
   * @param name
   * @return     
   */
  @Select("select * from idear_yb_user where name = #{name}")
  public User findUserByUname(@Param("name") String name);
  
    
  /**     
   * @description 根据指定的uuid查询用户
   * @param id
   * @return     
   */
  @Select("select * from idear_yb_user where uuid = #{uuid}")
  public User findUserById(@Param("uuid") String uuid);
  
}
