package com.apply.mapper;

import com.apply.entity.Gov;
import com.apply.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface GovMapper {

    @Select("select * from gov where date < #{now} and" +
            " date >=DATE_SUB(#{now}, INTERVAL 1 YEAR)")
    List<Gov> getOneYearNews(LocalDate now);

    @Select("select * from gov where date between #{begin} and #{end}")
    List<Gov> getSpecifyDateNews(LocalDate begin, LocalDate end);

    @Select("select * from gov where date between #{begin} and #{end} and ref=#{region}")
    List<Gov> getSpecifyDateAndRegionNews(LocalDate begin, LocalDate end, String region);

    @Select("select * from user where username=#{username} and password=#{password}")
    User login(String username, String password);
}
