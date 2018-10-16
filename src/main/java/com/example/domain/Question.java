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
	
	@Lob
	private String contents;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy="question")
	@OrderBy("id ASC")
	private List<Answer> answers;

	public Question() {}
	
	public Question(User writer, String title, String contents) {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		//this.createDate = createDate.now();
		this.createDate = LocalDateTime.now();
	}


	public String getFormatCreateDate() {
		if ( createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	
	public void update(String title, String contents) {
			this.title = title;
			this.contents = contents;
	}

	public boolean isSameWriter(User loginUser) {
		//인스턴스 는 다르지만 값은 비교 가능
		return this.writer.equals(loginUser);
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
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
