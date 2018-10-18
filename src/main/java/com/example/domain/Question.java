package com.example.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

//Entity 데이터 베이스와 연동 맵핑 어노테이션
@Entity
public class Question extends AbstractEntity {

	//회원은 여러개 질문
	//질문은 회원 한개 매핑
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_question_writer"))
	@JsonProperty
	private User writer;
	
	@JsonProperty
	private String title;
	
	@Lob
	@JsonProperty
	private String contents;
	
	@JsonProperty
	private Integer countOfAnswer =0;
	
	@OneToMany(mappedBy="question")
	@OrderBy("id DESC")
	private List<Answer> answers;

	public Question() {}
	
	public Question(User writer, String title, String contents) {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	
	public void update(String title, String contents) {
			this.title = title;
			this.contents = contents;
	}

	public boolean isSameWriter(User loginUser) {
		//인스턴스 는 다르지만 값은 비교 가능
		return this.writer.equals(loginUser);
	}

	public void addAnswer() {
			this.countOfAnswer += 1;
	}
	
	public void delAnswer() {
		this.countOfAnswer -= 1;
}
	
	
	
	

}
