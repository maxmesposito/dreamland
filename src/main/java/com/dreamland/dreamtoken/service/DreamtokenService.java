package com.dreamland.dreamtoken.service;

import com.dreamland.dreamtoken.dto.LedgerDreamtokenDto;
import com.dreamland.dreamtoken.entity.LedgerDreamtokenEntity;
import com.dreamland.dreamtoken.repository.LedgerDreamtokenRepository;
import com.dreamland.dreamtoken.repository.UserRepository;
import com.dreamland.dreamtoken.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DreamtokenService {

    @Autowired
    private LedgerDreamtokenRepository ledgerDreamtokenRepository;

    @Autowired
    private UserRepository userRepository;


    public List<LedgerDreamtokenDto> historyOfToday(Integer userId) {
        List<LedgerDreamtokenDto> historyLedgerDreamtoken = new ArrayList<>();

        Date midnightToday = DateUtils.getStartOfDay();
        List<LedgerDreamtokenEntity> ledgerDreamtokenEntities = ledgerDreamtokenRepository.findByUserIdAndIssueDateBetween(userId, midnightToday, new Date());

        for(LedgerDreamtokenEntity ledgerDreamtoken: ledgerDreamtokenEntities){
            LedgerDreamtokenDto ledgerDreamtokenDto = new LedgerDreamtokenDto();
            ledgerDreamtokenDto.setId(ledgerDreamtoken.getId());
            ledgerDreamtokenDto.setIssueDate(ledgerDreamtoken.getIssueDate());
            ledgerDreamtokenDto.setQuantity(ledgerDreamtoken.getQuantity());
            ledgerDreamtokenDto.setConversionToCurrencyDate(ledgerDreamtoken.getConversionToCurrencyDate());
            historyLedgerDreamtoken.add(ledgerDreamtokenDto);
        }
        return historyLedgerDreamtoken;
    }
}
