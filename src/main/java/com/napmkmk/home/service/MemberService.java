package com.napmkmk.home.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.napmkmk.home.entity.Member;
import com.napmkmk.home.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional //springframework.transaction 여기 꺼 부르기
@RequiredArgsConstructor //final 이 붙은 생성자를 생성해 줌.
public class MemberService {
	
	private final MemberRepository memberRepository; //값을 못바꾸게 final로
	
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member); 
		
	}
	
	private void validateDuplicateMember(Member member) {
	
		Member resultMember = memberRepository.findByMid(member.getMid());
		
		if(resultMember !=null) {
			throw new IllegalStateException("이미 가입된 회원입니다!") ; //일부러 에러 내서 테스트하기
		}
	
	}
	
	
}
