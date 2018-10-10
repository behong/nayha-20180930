package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Entity 데이터 베이스와 연동 맵핑 어노테이션
@Entity
public class Question {

	
	@Id
	@GeneratedValue  //자동으로 1씩 증가
	private Long id;
	
	private String writer;
	private String title;
	private String contents;

	public Question() {}
	
	public Question(String writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}


	
	
	

}
