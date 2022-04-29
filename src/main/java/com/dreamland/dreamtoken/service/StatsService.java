package com.dreamland.dreamtoken.service;

import com.dreamland.dreamtoken.dto.StatsResponseDto;
import com.dreamland.dreamtoken.entity.LedgerDreamtokenEntity;
import com.dreamland.dreamtoken.entity.LedgerUserEntity;
import com.dreamland.dreamtoken.repository.LedgerDreamtokenRepository;
import com.dreamland.dreamtoken.repository.LedgerUserRepository;
import com.dreamland.dreamtoken.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class StatsService {

    @Autowired
    private LedgerDreamtokenRepository ledgerDreamtokenRepository;

    @Autowired
    private LedgerUserRepository ledgerUserRepository;

    public StatsResponseDto getStatsByUserId(Integer userId) {
        StatsResponseDto statsResponseDto = new StatsResponseDto();
        Date midnightToday = DateUtils.getStartOfDay();

        List<LedgerDreamtokenEntity> ledgerDreamtokenEntityList = ledgerDreamtokenRepository.findByUserIdAndIssueDateBetween(userId, midnightToday, new Date());
        BigDecimal tempSumToken = BigDecimal.ZERO;
        for (LedgerDreamtokenEntity ledgerDreamtoken : ledgerDreamtokenEntityList){
            tempSumToken.add(ledgerDreamtoken.getQuantity());
        }
        statsResponseDto.setTokenSumCurrentDay(tempSumToken);

        List<LedgerUserEntity> ledgerUserEntityList = ledgerUserRepository.findByUserId(userId);

        BigDecimal tempSumUsd = BigDecimal.ZERO;
        for (LedgerUserEntity ledgerUserEntity: ledgerUserEntityList){
            tempSumUsd = tempSumUsd.add(ledgerUserEntity.getCurrencyAmount());
        }
        statsResponseDto.setCurrenciesSum(tempSumUsd);

        return statsResponseDto;
    }
}
