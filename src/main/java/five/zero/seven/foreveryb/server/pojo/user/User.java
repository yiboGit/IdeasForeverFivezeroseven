/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	User.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.server.pojo.user;

import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import five.zero.seven.foreveryb.footstone.base.entity.HasUCN;
import five.zero.seven.foreveryb.footstone.base.entity.VersionedEntity;

/**
 * 用户
 * @author wangyibo
 *
 */
@Table(name = "user")
public class User extends VersionedEntity implements HasUCN{

  /**
   * 
   */
  private static final long serialVersionUID = -1307311052825409599L;
  
  @NotEmpty
  private String code;
  @NotEmpty
  private String name;
  @NotEmpty
  private String passwd;
  private String gentle;
  private String email;
  private String city;

  /**
   * 账户
   * */
  public String getCode() {
    return code;
  }

  public void setCode(String code) throws UnsupportedOperationException {
    this.code = code;
  }

  /**
   * 用户名
   * */
  public String getName() {
    return name;
  }

  public void setName(String name) throws UnsupportedOperationException {
    this.name = name;
  }

  /** 密码 */
  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  /** 性别 */
  public String getGentle() {
    return gentle;
  }

  public void setGentle(String gentle) {
    this.gentle = gentle;
  }

  /** email： */
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /** 城市： */
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
