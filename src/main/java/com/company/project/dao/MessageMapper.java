package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Message;

public interface MessageMapper extends Mapper<Message> {


	List<Message> findHistory(@Param("fromid")Integer fromid,@Param("toid") Integer toid);
}