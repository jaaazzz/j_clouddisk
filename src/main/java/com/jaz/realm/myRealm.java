package com.jaz.realm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class myRealm extends AuthorizingRealm implements Serializable {
    private static final long serialVersionUID = 1L;

    //一、认证 （自定义认证）
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken arg0) throws AuthenticationException {
        //强转为UsernamePasswordToken类型
        UsernamePasswordToken token=(UsernamePasswordToken)arg0;

        //获取用户名和密码(密码要转为字符串类型)
        String username = token.getUsername();
        String password = new String(token.getPassword());

        //测试一下，看是否得到了用户名和密码
        System.out.println("username: " + username + ", password: " + password);

        //模拟查询数据库进行登录操作
        if("a".equals(username)){
            throw new UnknownAccountException("没有这个账号");
        }
        if("a".equals(password)){
            throw new IncorrectCredentialsException("密码错误");
        }
        if("b".equals(username)){
            throw new LockedAccountException("账号被锁定!");
        }
        //利用新建的类来创建对象
        ShiroUser user=new ShiroUser();
        user.setUsername(username); //将页面中的username值设置进去

        //模拟设置权限部分:要分别来判断
        if("admin".equals(username)){
            //如果用户名为：admin，则为其增加2个角色 admin和user
            user.getRoles().add("admin");
            user.getRoles().add("user");
        }else if("user".equals(username)){
            //如果用户名为：user,则为其增加user角色
            user.getRoles().add("user");
        }
        return new SimpleAuthenticationInfo(user, password,getName());
    }

    //二、授权(自定义)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        //arg0.getPrimaryPrincipal(): 实际上是在认证时返回的 SimpleAuthenticationInfo 的第一个参数!
        Object principal = arg0.getPrimaryPrincipal();
        ShiroUser user = (ShiroUser) principal;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(user.getRoles());
        return info;
    }


    //新建一个类定义用户角色和权限
    class ShiroUser implements Serializable{
        private static final long serialVersionUID = 1L;
        private String username;
        private Set<String>roles=new HashSet<String>();

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public Set<String> getRoles() {
            return roles;
        }
        public void setRoles(Set<String> roles) {
            this.roles = roles;
        }
    }

}
