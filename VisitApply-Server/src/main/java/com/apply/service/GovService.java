package com.apply.service;

import com.apply.entity.Gov;
import com.apply.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface GovService {

    List<Gov> getOneYearNews();

    List<Gov> getSpecifyDateNews(LocalDate begin, LocalDate end);

    List<Gov> getSpecifyDateAndRegionNews(LocalDate beginDate, LocalDate endDate, String region);

    String login(String username, String password);

    Map<String, Integer> getSpecifyDateAndRegionTop(LocalDate beginDate, LocalDate endDate, String region);
}
