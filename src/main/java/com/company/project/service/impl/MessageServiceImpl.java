package com.company.project.service.impl;

import com.company.project.dao.MessageMapper;
import com.company.project.model.Message;
import com.company.project.service.MessageService;
import com.company.project.vo.MessageVo;
import com.company.project.core.AbstractService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class MessageServiceImpl extends AbstractService<Message> implements MessageService {
    @Resource
    private MessageMapper messageMapper;
    
    private final static Logger LOG  = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Override
	public List<Message> findHistory(Integer fromid,Integer toid) {
		List<Message> messageList = messageMapper.findHistory(fromid,toid);
		return messageList;
	}

	@Override
	public List<MessageVo> findUsersById(Integer fromid) {
		
		LOG.info("根据fromid查询与之联系过的人 fromid={}",fromid);
		List<MessageVo> users = new ArrayList<>();
				
		List<Integer> toids  = messageMapper.findToIdsById(fromid);
		List<Integer> fromId = messageMapper.findFromIdsById(fromid);
		
		Set<Integer>  aa = new HashSet<>(toids);
		aa.addAll(fromId);
		
		for (Integer toid : aa) {
			MessageVo mv = new MessageVo();
			String realname = messageMapper.findRealnameByToid(toid);
			
			Message message = messageMapper.findFirst(fromid,toid);
			BeanUtils.copyProperties(message, mv);
			
			mv.setRealname(realname);
			
			users.add(mv);
		}
		
		return users;
	}

}
