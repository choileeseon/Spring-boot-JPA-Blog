package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data // @Getter,@Setter 한번에
//@AllArgsConstructor // 전체 생성자
//@RequiredArgsConstructor // final 붙은 변수 생성자를 만들어줌
@NoArgsConstructor // 빈 생성자
public class Member {
 
	//final 불변성 (변경할 이유가 없어서)
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	
}
