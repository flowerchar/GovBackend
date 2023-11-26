package com.apply.service.impl;

import com.apply.entity.Visitor;
import com.apply.mapper.VisitorMapper;
import com.apply.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorMapper visitorMapper;



    @Override
    public Visitor getVisitorInfo(Visitor visitor) {
        return visitorMapper.findInfoByVisitor(visitor);
    }
}
