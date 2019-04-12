package com.tconnect.entity;

import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "users")
@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class UserEntity {
	@Id
	@JsonIgnore
	private ObjectId _id;
	private String email;
	private String password;
	private boolean active;
	private Set<String> roles;
	
	@Override
	public String toString() {
		return "UserEntity [_id=" + _id + ", email=" + email + ", password=" + password + ", active=" + active
				+ ", roles=" + roles + "]";
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
