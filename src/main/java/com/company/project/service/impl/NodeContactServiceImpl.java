package com.company.project.service.impl;

import com.company.project.dao.NodeUserMapper;
import com.company.project.model.NodeUser;
import com.company.project.service.NodeContactService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class NodeContactServiceImpl extends AbstractService<NodeUser> implements NodeContactService {
    @Resource
    private NodeUserMapper nodeContactMapper;

}
