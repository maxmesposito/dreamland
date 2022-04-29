package com.dreamland.dreamtoken.controller;

import com.dreamland.dreamtoken.service.ConvertTokensToCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConvertTokensToCurrencyController {

    @Autowired
    private ConvertTokensToCurrencyService convertTokensToCurrencyService;

    @PostMapping(path = "/convert-tokens")
    public @ResponseBody
    ResponseEntity<Void> convertToCurrency() {

        convertTokensToCurrencyService.convertToCurrency();
        return new ResponseEntity(HttpStatus.OK);
    }

}