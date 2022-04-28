package com.dreamland.dreamtoken.controller;

import com.dreamland.dreamtoken.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class WinTokenController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/win")
    public @ResponseBody
    ResponseEntity<Void> addNewUser(@RequestBody WonAmount wonAmount) {
        //TODO
        return new ResponseEntity(HttpStatus.OK);
    }

    static class WonAmount {
        @Getter
        @Setter
        Integer idUser;

        @Getter
        @Setter
        BigDecimal amount;
    }
}