package com.dreamland.dreamtoken.controller;

import com.dreamland.dreamtoken.dto.LedgerDreamtokenResponseDto;
import com.dreamland.dreamtoken.service.DreamtokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DreamtokenHistoryController {

    @Autowired
    private DreamtokenService dreamtokenService;

    @GetMapping(path = "/dreamtokens/{userId}")
    public @ResponseBody
    ResponseEntity<List<LedgerDreamtokenResponseDto>> dreamtokens(@PathVariable Integer userId) {

        List<LedgerDreamtokenResponseDto> ledgerDreamtokenDtoList = dreamtokenService.historyOfToday(userId);
        return new ResponseEntity(ledgerDreamtokenDtoList, HttpStatus.OK);
    }

}