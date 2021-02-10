package artfire.entity;

import lombok.Data;
/**
 * @Description: 用户实体
 * @Author: 周启宇
 * @CreateDate: 2020/12/17
 */
@Data
public class User {
    private int userid;
    private String username;
    private String password;
    private String permission;
    private String phone;
}
