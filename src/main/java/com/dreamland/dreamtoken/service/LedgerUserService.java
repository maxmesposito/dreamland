package com.dreamland.dreamtoken.service;

import com.dreamland.dreamtoken.dto.LedgerUserResponseDto;
import com.dreamland.dreamtoken.entity.LedgerUserEntity;
import com.dreamland.dreamtoken.repository.LedgerUserRepository;
import com.dreamland.dreamtoken.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.dreamland.dreamtoken.entity.LedgerUserEntity.EntryType.CONVERT_TO_CURRENCY;

@Service
public class LedgerUserService {

    @Autowired
    private LedgerUserRepository ledgerUserRepository;

    public List<LedgerUserResponseDto> historyUntilYesterday(Integer userId) {
        List<LedgerUserResponseDto> historyUntilYesterdayUsds = new ArrayList<>();
        Date midnightToday = DateUtils.getStartOfDay();

        List<LedgerUserEntity> ledgerUserEntityList = ledgerUserRepository.findByUserIdAndEntryTypeAndInsertDateBefore(userId, CONVERT_TO_CURRENCY, midnightToday);
        for (LedgerUserEntity ledgerUserEntity: ledgerUserEntityList){
            LedgerUserResponseDto ledgerUserDto = new LedgerUserResponseDto();

            ledgerUserDto.setId(ledgerUserEntity.getId());
            ledgerUserDto.setInsertDate(ledgerUserEntity.getInsertDate());
            ledgerUserDto.setEntryType(ledgerUserEntity.getEntryType());
            ledgerUserDto.setTokenAmount(ledgerUserEntity.getTokenAmount());
            ledgerUserDto.setCurrencyAmount(ledgerUserEntity.getCurrencyAmount());
            historyUntilYesterdayUsds.add(ledgerUserDto);
        }
        return historyUntilYesterdayUsds;
    }
}
