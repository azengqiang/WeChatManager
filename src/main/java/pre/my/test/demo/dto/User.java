package pre.my.test.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by win on 2016/11/16.
 */

public class User {
    @Id
    @GeneratedValue()
    private Long userId;

    private String userName;

    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
