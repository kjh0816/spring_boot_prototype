package com.kjh.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kjh.dto.Member;

@Mapper
public interface MemberDao {

	Member getMemberByLoginId(@Param("loginId") String loginId);

	Member getMemberByNameEmail(@Param("name") String name, @Param("email") String email); 

}
