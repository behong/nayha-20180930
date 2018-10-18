package com.example.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_answer_writer"))
	@JsonProperty
	private User writer;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_answer_to_question"))
	@JsonProperty
	private Question question;
	
	@Lob
	@JsonProperty
	private String contents;
	
	public Answer() {}
	
	public Answer(User wirter,Question question ,String contents) {
		this.writer = wirter;
		this.question = question;
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
	}

	public boolean isSameWriter(User loginUser) {
		return this.writer.equals(loginUser);
	}
	
	

}
