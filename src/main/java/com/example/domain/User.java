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
	
	@Column(nullable=false,length=20,unique=true)
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
	

	public boolean matchId(Long newID) {
		
		if( newID == null) {
			return false;
		}
		
		return newID.equals(id);
	}	

	public String getUserid() {
		return userid;
	}


	public String getPassword() {
		return password;
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
		return "User [id=" + id + ", userid=" + userid + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	




	
	
	

}
