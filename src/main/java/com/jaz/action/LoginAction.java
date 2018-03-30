package com.jaz.action;

import java.io.Serializable;

import org.apache.struts2.ServletActionContext;
import com.jaz.dao.po.User;
import com.jaz.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

public class LoginAction extends ActionSupport implements Serializable{

	private String username;
	private String password;
	private UserService service; 
	private User user;
	
	public void setUser(User user) {
		this.user = user;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		
		return SUCCESS;
	}
	
	public void validateLogin(){
		
	    if("".equals(username) || "".equals(password)){
			ServletActionContext.getRequest().setAttribute("error", "用户名或密码错误!!");
            addFieldError("", "");
		}
	}
	
	public String login(){
		//1.获取当前的用户
		Subject currentUser = SecurityUtils.getSubject();
		//2.把登录信息封装为一个 UsernamePasswordToken 对象
		UsernamePasswordToken token=new UsernamePasswordToken(this.username,this.password);
		//3.设置"记住我"功能
		token.setRememberMe(true);
		try {
			// *登录操作!
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			System.out.println("用户名不存在: " + uae);
			return "input";
		} catch (IncorrectCredentialsException ice) {
			System.out.println("用户名存在,但密码和用户名不匹配: " + ice);
			return "input";
		} catch (LockedAccountException lae) {
			System.out.println("用户被锁定: " + lae);
			return "input";
		} catch (AuthenticationException ae) {
			System.out.println("其他异常: " + ae);
			return "input";
		}
		ActionContext.getContext().getSession().put("user_name", this.username);
		return SUCCESS;
		//return "success";

//		user.setUsername(username);
//		user.setPassword(password);
//		try {
//			String user_name = service.checkUser(user);
//			if( user_name != null && (!"".equals(user_name)) ){
//				//如果登陆成功 把用户名放到session域
//				ActionContext.getContext().getSession().put("user_name", user_name);
//			    return SUCCESS;
//			}
//			ServletActionContext.getRequest().setAttribute("error", "用户名或密码错误!!");
//			return INPUT;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ERROR;
//		}
	}

}
