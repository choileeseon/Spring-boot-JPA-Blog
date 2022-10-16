package com.cos.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	//인터페이스에 public 꼭 안써도 됨
	@Modifying
	@Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
	int mSave(int userId, int boardId, String content); // update된 행의 개수를 리턴해줌 
	// 1이 리턴 = 1개 세이브 , 0 = 세이브 안됨, -1 = 오류
}
