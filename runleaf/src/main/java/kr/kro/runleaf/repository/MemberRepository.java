package kr.kro.runleaf.repository;

import kr.kro.runleaf.member.domain.dto.MemberFindDto;

import org.apache.ibatis.annotations.Mapper;

import kr.kro.runleaf.domain.MemberFile;
import kr.kro.runleaf.member.domain.dto.MemberDto;


public interface MemberRepository {
	
	void insertMember(MemberDto memberDto);
	
	void insertMemberFile(MemberFile memberFile);

	MemberFindDto selectMember(int id);
	
	int getMemberId(String username);

	void updateMember(MemberDto memberDto);

	void deleteMember(int id);
}
