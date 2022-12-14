package com.cos.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id; //auto_increment
	
	@Column(nullable = false, length=200)
	private String content; // 답변 내용
	
	@ManyToOne // 여러개의 답변들에 하나의 게시글
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne // 여러개의 답변들에 하나의 유저
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private LocalDateTime createDate;

	@Override
	public String toString() {
		return "Reply [id=" + id + ", content=" + content + ", board=" + board + ", user=" + user + ", createDate="
				+ createDate + "]";
	}
}







