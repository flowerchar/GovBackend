package com.apply.mapper;

import com.apply.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VisitorMapper {


    @Select("select * from visitor where id=#{id}")
    Visitor findInfoByVisitor(Visitor visitor);
}
