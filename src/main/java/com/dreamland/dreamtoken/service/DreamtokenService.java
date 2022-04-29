package com.dreamland.dreamtoken.service;

import com.dreamland.dreamtoken.dto.LedgerDreamtokenResponseDto;
import com.dreamland.dreamtoken.entity.LedgerDreamtokenEntity;
import com.dreamland.dreamtoken.repository.LedgerDreamtokenRepository;
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

    public List<LedgerDreamtokenResponseDto> historyOfToday(Integer userId) {
        List<LedgerDreamtokenResponseDto> historyLedgerDreamtoken = new ArrayList<>();

        Date midnightToday = DateUtils.getStartOfDay();
        List<LedgerDreamtokenEntity> ledgerDreamtokenEntities = ledgerDreamtokenRepository.findByUserIdAndIssueDateBetween(userId, midnightToday, new Date());

        for(LedgerDreamtokenEntity ledgerDreamtoken: ledgerDreamtokenEntities){
            LedgerDreamtokenResponseDto ledgerDreamtokenDto = new LedgerDreamtokenResponseDto();
            ledgerDreamtokenDto.setId(ledgerDreamtoken.getId());
            ledgerDreamtokenDto.setIssueDate(ledgerDreamtoken.getIssueDate());
            ledgerDreamtokenDto.setQuantity(ledgerDreamtoken.getQuantity());
            ledgerDreamtokenDto.setConversionToCurrencyDate(ledgerDreamtoken.getConversionToCurrencyDate());
            historyLedgerDreamtoken.add(ledgerDreamtokenDto);
        }
        return historyLedgerDreamtoken;
    }
}
