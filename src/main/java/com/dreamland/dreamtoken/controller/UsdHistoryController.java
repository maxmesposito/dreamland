package com.dreamland.dreamtoken.controller;

import com.dreamland.dreamtoken.dto.LedgerDreamtokenResponseDto;
import com.dreamland.dreamtoken.dto.LedgerUserResponseDto;
import com.dreamland.dreamtoken.service.LedgerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsdHistoryController {

    @Autowired
    private LedgerUserService ledgerUserService;

    @GetMapping(path = "/usds/{userId}")
    public @ResponseBody
    ResponseEntity<List<LedgerDreamtokenResponseDto>> usds(@PathVariable Integer userId) {

        List<LedgerUserResponseDto> ledgerDreamtokenDtoList = ledgerUserService.historyUntilYesterday(userId);
        return new ResponseEntity(ledgerDreamtokenDtoList, HttpStatus.OK);
    }

}