package com.company.project.service;
import com.company.project.model.Message;
import com.company.project.vo.MessageVo;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
public interface MessageService extends Service<Message> {

	List<Message> findHistory(Integer fromid,Integer toid);

	List<MessageVo> findUsersById(Integer fromid);

}
