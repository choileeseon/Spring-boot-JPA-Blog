package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ORM -> Java(그 외 다른언어 포함) Object -> 테이블로 매핑해주는 기술 

//@Data  무한참조 StackOverflowError 오류나서 겟터,셋터로 대체
@Getter
@Setter
@NoArgsConstructor //빈 생성자
@AllArgsConstructor //전체 생성자
@Builder //빌더 패턴!
@Entity // User 클래스가 MySQL 에 테이블이 생성된다.
//@DynamicInsert insert할때 null 인 필드 제외
public class User {

	@Id // Primary key 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스(오라클) , auto_increment(MYSQL)
	
	@Column(nullable = false, length = 100, unique=true) // null이 될 수 없고 100자까지
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 12345 => 해쉬(비밀번호 암호화) 해서 넉넉하게 100까지 잡아줌.
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//	@ColumnDefault("'user'") // 'user' 문자형태 
	@Enumerated(EnumType.STRING) // DB에는 RoleType이라는게 없으니 
	private RoleType role; // Enum 을 쓰는게 좋다. (도메인을 만들어줌.) // ADMIN,USER 권한 
	
	private String oauth; //카카오, 구글 사용자 구분
	
	@CreationTimestamp // 시간이 자동 입력 (비워놔도 됨)
	private Timestamp createDate; // 가입시간
}
