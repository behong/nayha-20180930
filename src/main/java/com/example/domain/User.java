package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//Entity 데이터 베이스와 연동 맵핑 어노테이션
@Entity
public class User extends AbstractEntity {
	
	@Column(nullable=false,length=20,unique=true)
	@JsonProperty
	private String userid;
	
	@JsonIgnore
	private String password;
	
	@JsonProperty
	private String name;
	@JsonProperty
	private String email;

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}


	public String getPassword() {
		return password;
	}
	
	public boolean matchId(Long newID) {
		
		if( newID == null) {
			return false;
		}
		
		return newID.equals(getId());
	}		

	public boolean matchPassword(String newPassword) {
		
		if( newPassword == null) {
			return false;
		}
		
		return newPassword.equals(password);
	}


	public void update(User newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
	}



	@Override
	public String toString() {
		return "User [id=" + getId() + ", userid=" + userid + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
	}

}
