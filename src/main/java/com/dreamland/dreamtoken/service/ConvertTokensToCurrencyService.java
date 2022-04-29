package com.dreamland.dreamtoken.service;

import com.dreamland.dreamtoken.entity.*;
import com.dreamland.dreamtoken.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.dreamland.dreamtoken.entity.LedgerUserEntity.EntryType.CONVERT_TO_CURRENCY;
import static com.dreamland.dreamtoken.entity.LedgerUserEntity.EntryType.FEE_AFTER_CONVERT;

@Service
public class ConvertTokensToCurrencyService {

    @Autowired
    private LedgerDreamtokenRepository ledgerDreamtokenRepository;

    @Autowired
    private LedgerUserRepository ledgerUserRepository;

    @Autowired
    private LedgerDreamlandRepository ledgerDreamlandRepository;

    @Autowired
    private DreamtokenRateRepository dreamtokenRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Scheduled(cron = "0 0 * * * ?") //Every hour at hh:00:00
    public void convertToCurrency() {

        Iterable<CurrencyEntity> currencyEntities = currencyRepository.findAll();

        for (CurrencyEntity currencyEntity: currencyEntities){
            //List of the tokens to be converted
            List<LedgerDreamtokenEntity> ledgerDreamtokenEntityList = ledgerDreamtokenRepository.findByConversionToCurrencyDateIsNull();

            for(LedgerDreamtokenEntity ledgerDreamtoken: ledgerDreamtokenEntityList) {
                //rates token/currency and fee to apply
                Optional<DreamtokenRateEntity> dreamtokenRateEntity = dreamtokenRateRepository.findByCurrencyIdAndStartDateBeforeAndEndDateAfter(currencyEntity.getId(), new Date(), new Date());

                if (dreamtokenRateEntity.isPresent()) {
                    populateLedgerTables(ledgerDreamtoken, dreamtokenRateEntity);
                }
                else {
                    // log error here
                    // configuration issue no rates are configured for the currency
                }
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void populateLedgerTables(LedgerDreamtokenEntity ledgerDreamtoken, Optional<DreamtokenRateEntity> dreamtokenRateEntity) {
        DreamtokenRateEntity rate = dreamtokenRateEntity.get();

        LedgerUserEntity ledgerUserEntityCredit = new LedgerUserEntity();
        ledgerUserEntityCredit.setUser(ledgerDreamtoken.getUser());
        ledgerUserEntityCredit.setInsertDate(new Date());
        ledgerUserEntityCredit.setEntryType(CONVERT_TO_CURRENCY);
        ledgerUserEntityCredit.setDreamtokenRate(rate);
        ledgerUserEntityCredit.setCurrencyAmount(rate.getExchangeRateToCurrency().multiply(ledgerDreamtoken.getQuantity()));
        ledgerUserEntityCredit.setTokenAmount(ledgerDreamtoken.getQuantity());
        ledgerUserRepository.save(ledgerUserEntityCredit);


        LedgerUserEntity ledgerUserEntityDebit = new LedgerUserEntity();
        ledgerUserEntityDebit.setUser(ledgerDreamtoken.getUser());
        ledgerUserEntityDebit.setInsertDate(new Date());
        ledgerUserEntityDebit.setEntryType(FEE_AFTER_CONVERT);
        ledgerUserEntityDebit.setDreamtokenRate(rate);
        ledgerUserEntityDebit.setCurrencyAmount(rate.getDreamlandFee().negate());
        ledgerUserRepository.save(ledgerUserEntityDebit);


        LedgerDreamlandEntity ledgerDreamlandEntity = new LedgerDreamlandEntity();
        ledgerDreamlandEntity.setLedgerDreamtoken(ledgerDreamtoken);
        ledgerDreamlandEntity.setFee(rate.getDreamlandFee());
        ledgerDreamlandEntity.setInsertDate(new Date());

        ledgerDreamlandRepository.save(ledgerDreamlandEntity);

        //set conversionToCurrencyDate
        ledgerDreamtoken.setConversionToCurrencyDate(new Date());
        ledgerDreamlandRepository.save(ledgerDreamlandEntity);
    }
}
