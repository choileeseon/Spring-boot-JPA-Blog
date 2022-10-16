package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 = loC (메모리를 대신 띄어준다)
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
	
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User(); // 빈 객체 리턴 (null일수가 없음)
		});
		
		return user;
	}
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //1234 
		String encPassword = encoder.encode(rawPassword); //해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER); // 시간은 알아서 넣어지니까 제외
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고 영속화된 User오브젝트를 수정
		// select해서 User오브젝트를 DB로부터 가져오는 이유는 영속화 하기 위해서
		// 영속화 된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		
		// Validate 체크
		// 일반유저(oauth에 값이 없으면)일때만 수정가능
//		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
//			String rawPassword = user.getPassword(); //패스워드를 받아서 
//			String encPassword = encoder.encode(rawPassword); //암호화
//			persistance.setPassword(encPassword); //변경 된 패스워드를 암호화
//			persistance.setEmail(user.getEmail()); // 변경 된 이메일			
//		}
		
		// 회원 수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 자동으로 된다.
		// 영속화 된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려준다.
	}
	
}
