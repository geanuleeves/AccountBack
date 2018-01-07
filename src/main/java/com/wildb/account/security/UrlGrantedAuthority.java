package com.wildb.account.security;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * Created by yangyibo on 17/2/15.
 */
public class UrlGrantedAuthority implements GrantedAuthority,Serializable{
    private static final long serialVersionUID = 1L;
    private String permissionUrl;
    private String method;
    private String authority;
    private String role;

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public UrlGrantedAuthority (String permissionUrl, String method) {
        this.permissionUrl = permissionUrl;
        this.method = method;
    }
    public UrlGrantedAuthority(String authority,String permissionUrl,String method){
        this.authority = authority;
        this.permissionUrl = permissionUrl;
        this.method = method;
    }

    public UrlGrantedAuthority(String permissionUrl, String method, String authority, String role) {
        this.permissionUrl = permissionUrl;
        this.method = method;
        this.authority = authority;
        this.role = role;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

//        @Override
//    public String getAuthority() {
//        return this.permissionUrl + ";"+this.method;
//    }

    public String getAll() {
        return this.authority + " ：" + this.permissionUrl + "--" + this.method;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
