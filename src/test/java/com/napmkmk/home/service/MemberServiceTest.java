package com.napmkmk.home.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.napmkmk.home.dto.MemberDto;
import com.napmkmk.home.entity.Member;

@SpringBootTest
//@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")

public class MemberServiceTest {
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Member createMember() {
		
		MemberDto memberDto = new MemberDto();
			memberDto.setMid("tiger222");
			memberDto.setMname("홍길동");
			memberDto.setMpw("12345");
			memberDto.setMemail("ddd@ddd.com");
		
		return Member.createMember(memberDto, passwordEncoder);	
   }
	public Member createMember2() {
		
		MemberDto memberDto = new MemberDto();
			memberDto.setMid("fire111");
			memberDto.setMname("김유신");
			memberDto.setMpw("12345");
			memberDto.setMemail("kys@ddd.com");
		
		return Member.createMember(memberDto, passwordEncoder);	
   }
	
		
	@Test
	@DisplayName("회원가입 테스트")
	public void saveMemberTest() {
		Member member1 = createMember();
		
		Member savedMember = memberService.saveMember(member1);
		assertEquals(member1.getMid(),savedMember.getMid());
	}
	
	@Test
	@DisplayName("중복 회원 가입 테스트")
	public void duplicateMemberTest() {
		Member member1 =createMember2();
		Member member2 =createMember2();
		
		memberService.saveMember(member1); //fire 가입시킴
		
		Throwable e = assertThrows(IllegalStateException.class, () -> {  //테스트의 예외처리.
		memberService.saveMember(member2);});//fire 중복가입
		
		System.out.println(e.getMessage());
		assertEquals("이미가입된 회원 입니다.",e.getMessage()); //에러까지도 예외처리 되서 테스트 가능
		}
		
	}
	

