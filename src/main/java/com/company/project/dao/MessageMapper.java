package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Message;
import com.company.project.vo.MessageVo;

public interface MessageMapper extends Mapper<Message> {


	List<Message> findHistory(@Param("fromid")Integer fromid,@Param("toid") Integer toid);

	List<Integer> findToIdsById(@Param("fromid")Integer fromid);

	List<Integer> findFromIdsById(@Param("fromid")Integer fromid);

	Message findFirst(@Param("fromid")Integer fromid,@Param("toid") Integer toid);

	String findRealnameByToid(@Param("toid")Integer toid);

	
}