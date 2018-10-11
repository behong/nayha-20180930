package com.example.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//Entity 데이터 베이스와 연동 맵핑 어노테이션
@Entity
public class Question {
	
	@Id
	@GeneratedValue  //자동으로 1씩 증가
	private Long id;
	//회원은 여러개 질문
	//질문은 회원 한개 매핑
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_question_writer"))
	private User writer;
	
	private String title;
	private String contents;
	
	private LocalDateTime createDate;

	public Question() {}
	
	public Question(User writer, String title, String contents) {
		super();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = createDate.now();
	}


	
	
	

}
