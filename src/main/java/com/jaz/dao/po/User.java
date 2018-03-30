package com.jaz.dao.po;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("user")
@Scope("prototype")
public class User  {
	private int id;
	private int group_id;
	private String password;
	private String username;
	private String salt;
	private String role_id;
	private int locked;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getRoleId() {
		return role_id;
	}
	public void setRoleId(String role_id) {
		this.role_id = role_id;
	}
	public int getIslocked() {
		return locked;
	}
	public void setIslocked(int locked) {
		this.locked = locked;
	}
	public int getGroupId() {
		return group_id;
	}
	public void setGroupId(int group_id) {
		this.group_id = group_id;
	}
    public String getCredentialsSalt() {
        return username + salt;
    }
}
