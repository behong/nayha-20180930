package com.example.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * 도메인 클래스들 중복 제거 
 * */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;
	
	// 시작 클래스에 @EnableJpaAuditing 추가해야함 
	
	//자동 수정일 (스프링에서 지원)
	@CreatedDate
	private LocalDateTime createDate;

	//자동 수정일 (스프링에서 지원)
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormatCreateDate() {
		if ( createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	public String getFormatModifiedDate() {
		if ( modifiedDate == null) {
			return "";
		}
		return modifiedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
