package com.dreamland.dreamtoken.controller;

import com.dreamland.dreamtoken.dto.StatsResponseDto;
import com.dreamland.dreamtoken.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping(path = "/stats/{userId}")
    public @ResponseBody
    ResponseEntity<StatsResponseDto> stats(@PathVariable Integer userId) {

        StatsResponseDto statsResponseDto = statsService.getStatsByUserId(userId);
        return new ResponseEntity(statsResponseDto, HttpStatus.OK);
    }

}