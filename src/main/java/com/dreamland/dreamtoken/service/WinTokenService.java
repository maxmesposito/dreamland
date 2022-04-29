package com.dreamland.dreamtoken.service;

import com.dreamland.dreamtoken.dto.WonAmountRequestDto;
import com.dreamland.dreamtoken.entity.LedgerDreamtokenEntity;
import com.dreamland.dreamtoken.entity.UserEntity;
import com.dreamland.dreamtoken.repository.LedgerDreamtokenRepository;
import com.dreamland.dreamtoken.repository.UserRepository;
import com.dreamland.dreamtoken.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WinTokenService {

    @Autowired
    private LedgerDreamtokenRepository ledgerDreamtokenRepository;

    @Autowired
    private UserRepository userRepository;

    public void winNewToken(WonAmountRequestDto wonAmount) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(wonAmount.getIdUser());
        if (optionalUserEntity.isPresent()) {
            BigDecimal totalTokenWonToday = calculateTotalTokenWonTodayByUserId(wonAmount.getIdUser());
            //if the limit of 5-tokens-per-day is already reached, skip
            if (new BigDecimal(5).compareTo(totalTokenWonToday) > 0) {
                LedgerDreamtokenEntity ledgerDreamtoken = new LedgerDreamtokenEntity();
                ledgerDreamtoken.setUser(optionalUserEntity.get());
                ledgerDreamtoken.setIssueDate(new Date());

                //going to trim to 5 max token per day
                BigDecimal partialTotalTokenWonToday = totalTokenWonToday.add(wonAmount.getTokenAmount());
                if (new BigDecimal(5).compareTo(partialTotalTokenWonToday) < 0) {
                    ledgerDreamtoken.setQuantity(new BigDecimal(5).subtract(totalTokenWonToday));
                } else {
                    ledgerDreamtoken.setQuantity(wonAmount.getTokenAmount());
                }
                ledgerDreamtokenRepository.save(ledgerDreamtoken);
            }
        }
    }

    private BigDecimal calculateTotalTokenWonTodayByUserId(Integer idUser) {
        BigDecimal total = BigDecimal.ZERO;
        Date midnightToday = DateUtils.getStartOfDay();
        Date nextMidnight = DateUtils.getEndOfDay();
        List<LedgerDreamtokenEntity> ledgerDreamtokenEntityList = ledgerDreamtokenRepository.findByUserIdAndIssueDateBetween(idUser, midnightToday, nextMidnight);
        for (LedgerDreamtokenEntity ledgerDreamtoken : ledgerDreamtokenEntityList) {
            total = total.add(ledgerDreamtoken.getQuantity());
        }
        return total;
    }


}
