package com.napmkmk.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.napmkmk.home.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	public Member findByMid(String mid); // 이미 있는 아이디 찾아줘 하나니까 그냥 맴버로..  나오거나 안나오거나.

}
