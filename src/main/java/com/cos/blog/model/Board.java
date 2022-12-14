package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@Data 무한참조 StackOverflowError 오류나서 겟터,셋터로 대체
@Getter
@Setter
@NoArgsConstructor //빈 생성자
@AllArgsConstructor //전체 생성자
@Builder //빌더 패턴! 
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length=100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; 	// 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.

	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // Many=Board , One=User (한명의 유저는 여러개 게시글을 쓸수있다.)
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수없다. FK,자바는 오브젝트를 저장할 수 있다.
	
	// 무조건 들고와야하면 EAGER , 댓글펼치기 기능처럼 필요하지 않을 수도 있다면 LAZY전략
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //mappedBy 연관관계의 주인이 아니다(난 FK가 아니다 ,DB에 칼럼을 만들지 마세요)
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp // insert,update 될 때 시간이 자동 입력 (비워놔도 됨)
	private Timestamp createDate; 
}
