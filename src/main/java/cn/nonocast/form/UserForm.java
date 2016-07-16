package cn.nonocast.form;

import cn.nonocast.model.User;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Size;

// new everytime request if needed
public class UserForm {
    private static final Logger logger = LoggerFactory.getLogger(UserForm.class);

    private String op = "create";

    public Boolean op_create() {return "create" == op; }

    private Long id = 0L;
    @Size(min=5, max=30, message="邮箱地址不正确")
    private String email;
    private String password;
    @Size(min=2, max=30, message="用户名不少于2个字符")
    private String name;
    private String wechatid;
    private String avatar;
    private String mobile;
    private String location;
    private User.Role role = User.Role.USER;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid;
    }

    public String getAvatar() {
        return avatar;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void pull() { this.op = "edit"; }

    public void pull(User user) {
        this.pull();

        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.role = user.getRole();
        this.mobile = user.getMobile();
        this.wechatid = user.getWechatid();
        this.location = user.getLocation();
    }

    public User push(User user, PasswordEncoder encoder) {
        user.setEmail(this.email);
        user.setName(this.name);
        user.setRole(this.role);
        user.setMobile(this.mobile);
        user.setWechatid(this.wechatid);
        user.setLocation(this.location);

        if(!Strings.isNullOrEmpty(this.password)) {
            user.setPassword(encoder.encode(this.password));
        }
        return user;
    }

    public User build(PasswordEncoder encoder) {
        return push(new User(), encoder);
    }
}
