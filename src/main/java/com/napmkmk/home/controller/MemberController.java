package com.napmkmk.home.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.napmkmk.home.dto.MemberDto;
import com.napmkmk.home.entity.Member;
import com.napmkmk.home.repository.MemberRepository;
import com.napmkmk.home.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor //오토와이어드 대신 써줌 final로 불러아햠 
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/join")
	public String join(Model model) {
		
		MemberDto memberDto = new MemberDto(); //빈  memberDto를 보냄
		model.addAttribute("memberDto",memberDto);
		
		return "joinForm";
		
	}
	//오버로딩 해줌.
	@RequestMapping(value = "/joinOk")
	public String joinOk(MemberDto memberDto) {
		
		Member member =Member.createMember(memberDto, passwordEncoder);
		memberService.saveMember(member);
		
		return "index";
		
	}
	
}
