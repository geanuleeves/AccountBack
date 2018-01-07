package com.wildb.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wildb.account.security.UrlGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

/**
 * thymeleaf可以通过两种方式获取user数据：
 * 1、 <span sec:authentication="principal.email"></span>
 * 2、<ul th:each="authorityName:${#authorization.authentication.principal.authorityNames}">
        <li th:text="${authorityName}"></li>
      </ul>
 */
public class User implements UserDetails,Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String cnname;
    private String username;
    @JsonIgnore
    private String password;
    private String rePassword;
    private String historyPassword;
    private String email;
    @JsonIgnore
    private String telephone;
    private String mobilePhone;
    private List<? extends GrantedAuthority> authorities;  //此处authorities由UrlGrantedAuthority定义
    private Role role;
    private Integer roleId;

    private List<UrlGrantedAuthority> authorityList;
    private List<String> authorityNames;

    private Set<String> roleNames;

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //添加用户权限、用户角色信息
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        this.authorityList = (List<UrlGrantedAuthority>) authorities;
        this.authorityNames = new ArrayList<>(authorities.size());
        this.roleNames = new HashSet<>(authorities.size());

        for (int i = 0; i < authorityList.size(); i++) {
            this.authorityNames.add(authorityList.get(i).getAuthority());
            this.roleNames.add(authorityList.get(i).getRole());
        }
        return authorities;
    }

    public void setGrantedAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getUsername() {
        return username;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getHistoryPassword() {
        return historyPassword;
    }

    public void setHistoryPassword(String historyPassword) {
        this.historyPassword = historyPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cnname=" + cnname +
                ", username=" + username +
                ", password=" + password +
                ", email=" + email +
                ", telephone=" + telephone +
                ", mobilePhone=" + mobilePhone +
                '}';
    }

    public List<UrlGrantedAuthority> getAuthorityList() {
        return authorityList;
    }

    public List<String> getAuthorityNames() {
        return authorityNames;
    }

    public Set<String> getRoleNames() {
        return roleNames;
    }
}