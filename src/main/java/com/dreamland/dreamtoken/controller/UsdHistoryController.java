package com.dreamland.dreamtoken.controller;

import com.dreamland.dreamtoken.dto.LedgerDreamtokenDto;
import com.dreamland.dreamtoken.dto.LedgerUserDto;
import com.dreamland.dreamtoken.service.DreamtokenService;
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
    ResponseEntity<List<LedgerDreamtokenDto>> winNewToken(@PathVariable Integer userId) {

        List<LedgerUserDto> ledgerDreamtokenDtoList = ledgerUserService.historyUntilYesterday(userId);
        return new ResponseEntity(ledgerDreamtokenDtoList, HttpStatus.OK);
    }

}