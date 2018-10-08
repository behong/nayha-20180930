package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Entity 데이터 베이스와 연동 맵핑 어노테이션
@Entity
public class User {

	
	@Id
	@GeneratedValue  //자동으로 1씩 증가
	private Long id;
	
	@Column(nullable=false,length=20)
	private String userid;
	
	private String password;
	private String name;
	private String email;
	
	

	public void setId(Long id) {
		this.id = id;
	}



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

	


	public Long getId() {
		return id;
	}
	

	public String getUserid() {
		return userid;
	}


	public String getPassword() {
		return password;
	}



	public void update(User newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", userid=" + userid + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
	}
	




	
	
	

}
