package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java(그 외 다른언어 포함) Object -> 테이블로 매핑해주는 기술 

@Data
@NoArgsConstructor //빈 생성자
@AllArgsConstructor //전체 생성자
@Builder //빌더 패턴!
@Entity // User 클래스가 MySQL 에 테이블이 생성된다.
public class User {

	@Id // Primary key 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스(오라클) , auto_increment(MYSQL)
	
	@Column(nullable = false, length = 30) // null이 될 수 없고 30자까지
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 12345 => 해쉬(비밀번호 암호화) 해서 넉넉하게 100까지 잡아줌.
	private String passsword;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'") // 'user' 문자형태 
	private String role; // Enum 을 쓰는게 좋다. (도메인을 만들어줌.) // admin,user,manager 권한 
	
	@CreationTimestamp // 시간이 자동 입력 (비워놔도 됨)
	private Timestamp createDate; // 가입시간
}
