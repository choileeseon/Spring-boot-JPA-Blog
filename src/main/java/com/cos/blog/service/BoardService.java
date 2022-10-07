package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 = loC (메모리를 대신 띄어준다)
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository ;
	
	@Transactional
	public void 글쓰기(Board board,User user) { //title,content
		board.setCount(0); //조회수 직접넣기
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true) //select만 하는 것이라서
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true) 
	public Board 글상세보기(int id){
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, Board requestBody) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
				}); //영속화 완료
		board.setTitle(requestBody.getTitle());
		board.setContent(requestBody.getContent());
		// 해당 함수 종료시 (Service가 종료될때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 된다.
	}
	
}
