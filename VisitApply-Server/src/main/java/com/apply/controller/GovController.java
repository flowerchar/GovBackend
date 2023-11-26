package com.apply.controller;

import com.apply.entity.Gov;
import com.apply.entity.User;
import com.apply.result.LoginResult;
import com.apply.result.Result;
import com.apply.service.GovService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController()

@CrossOrigin
public class GovController {

    @Autowired
    private GovService govService;

    @GetMapping("/getOneYearNews")
    public Result<List<Gov>> getOneYearNews(){
        List<Gov> oneYearNews = govService.getOneYearNews();
        return Result.success(oneYearNews);
    }

//    @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam
    @GetMapping("/getSpecifyDateNews")
    public Result<List<Gov>> getSpecifyDateNews( String begin, String end){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate beginDate = LocalDate.parse(begin, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        List<Gov> SpecifyDateNews = govService.getSpecifyDateNews(beginDate, endDate);
        return Result.success(SpecifyDateNews);
    }

    @GetMapping("/getSpecifyDateAndRegionNews")
    public Result<List<Gov>> getSpecifyDateAndRegionNews( String begin, String end, String region) throws UnsupportedEncodingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate beginDate = LocalDate.parse(begin, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        region = URLDecoder.decode(region, "UTF-8");
        System.out.println(region);
        List<Gov> SpecifyDateAndRegionNews = govService.getSpecifyDateAndRegionNews(beginDate, endDate, region);
        return Result.success(SpecifyDateAndRegionNews);
    }
    @GetMapping("/getSpecifyDateAndRegionTop")
    public Result<Map<String, Integer>> getSpecifyDateAndRegionTop( String begin, String end, String region) throws UnsupportedEncodingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate beginDate = LocalDate.parse(begin, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        region = URLDecoder.decode(region, "UTF-8");
        System.out.println(region);
        Map<String, Integer> SpecifyDateAndRegionNews = govService.getSpecifyDateAndRegionTop(beginDate, endDate, region);
        return Result.success(SpecifyDateAndRegionNews);
    }
    @PostMapping(value="/api/login")
    public LoginResult login(@RequestBody User user){
        String token= govService.login(user.getUsername(), user.getPassword());
        return LoginResult.success(token);
    }
}
