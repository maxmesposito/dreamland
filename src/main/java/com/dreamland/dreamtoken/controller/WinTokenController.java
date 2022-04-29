package com.dreamland.dreamtoken.controller;

import com.dreamland.dreamtoken.dto.WonAmount;
import com.dreamland.dreamtoken.service.WinTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WinTokenController {

    @Autowired
    private WinTokenService winTokenService;

    @PostMapping(path = "/win")
    public @ResponseBody
    ResponseEntity<Void> winNewToken(@RequestBody WonAmount wonAmount) {

        winTokenService.winNewToken(wonAmount);
        return new ResponseEntity(HttpStatus.OK);
    }

}