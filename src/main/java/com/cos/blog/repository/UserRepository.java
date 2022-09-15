package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// JpaRepository 는 User 테이블을 관리하고 User의 pk는 Integer이다.
// 자동으로 bean 등록이 된다.
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{

	// 1번째 방법 JPA Naming 쿼리 자동실행
	// SELECT * FROM user WHERE username = ? AND password = ?
	User findByUsernameAndPassword(String username, String password); //User 객체로 리턴
	
	//2번째방법
	//	@Query(value = "SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
	//	User login(String username, String password);
}
