package kr.kro.runleaf.member.repository;

import kr.kro.runleaf.member.domain.dto.MemberFindDto;

import org.apache.ibatis.annotations.Mapper;

import kr.kro.runleaf.member.domain.dto.MemberDto;

@Mapper
public interface MemberRepository {
	
	void insertMember(MemberDto memberDto);
	
	MemberFindDto selectMember(int id);

	void updateMember(MemberDto memberDto);

	void deleteMember(int id);
}
