package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController //html파일이 아니라 데이터를 응답(리턴)
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	//update
	//email, password 만 수정.
	//save 함수는 id를 전달하지 않으면 insert를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 udpate를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다.
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { // 최고 부모인 Exception 으로 설정해두면 모든 예외가 자식으로 걸린다.
			return "삭제 실패 되었습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id :  " + id;
	}
	
	
	@Transactional //함수 종료시 자동 commit 이 됨. 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { 
		// json데이터를 요청 -> Java Object(MessageConverter의 Jackson 라이브러리가 변환해서 받아준다.)
		//System.out.println("id : " + id);
		//System.out.println("password : " + requestUser.getPassword());
		//System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
	
		//트랜잭션이 끝나는 시점에 변화가 있는 모든 엔티티 객체를 데이터베이스 반영해서 save 하지 않더라도 DB에 반영된다.
		// userRepository.save(user);
		
		//더티 체킹
		return user;
	}
	
	//전체 유저
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건의 데이터를 리턴받기
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id",direction = Direction.DESC)Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//{id} 주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4 을 찾는다면 내가 DB에서 못찾아올때 user가 null이 될것.
		// 그럼 return할때 null이 리턴이 되니 프로그램에 문제가 생길수도
		// Optional 로 너의 User객체를 감싸서 가져올테니 null인지 판단해서 return 한다!
		
//		람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다.");
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		// 요청 : 웹브라우저 는 html만 이해함. 자바 오브젝트 이해못함.
		// user 객체는 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json
		// 스프링부트 = MessageConverter 라는 애가 응답시 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리 호출해서 
		// user오브젝트를 json으로 변환해서 브라우저에게 전달해준다.
		return user;
	}
	
	//insert
	// http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username,password,email 데이터를 가지고(요청)
	@PostMapping("/dummy/join")
	public String join(User user) { // key=value (약속된 규칙)
		//System.out.println("id : " + user.getId());
		//System.out.println("username : " + user.getUsername());
		//System.out.println("password : " + user.getPassword());
		//System.out.println("email : " + user.getEmail());
		//System.out.println("role: " + user.getRole());
		//System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 되었습니다.";
	}
}
