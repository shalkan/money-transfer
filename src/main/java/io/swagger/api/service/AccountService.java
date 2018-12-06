package io.swagger.api.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import io.swagger.api.dao.AccountRepository;
import io.swagger.api.dao.CurrencyRepository;
import io.swagger.api.entity.AccountEntity;
import io.swagger.api.entity.CurrencyEntity;
import io.swagger.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gilfanovrenat
 */

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private CurrencyRepository currencyRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Transactional
  public List<Account> getAccounts() {
    return StreamSupport.stream(accountRepository.findAll().spliterator(), false)
        .map(entity -> {
          Account acc = new Account();
          acc.setId(entity.getId());
          acc.setAmount(entity.getAmount());
          acc.setCurrencyId(entity.getCurrencyEntity().getId());
          acc.setCurrencyName(entity.getCurrencyEntity().getName());
          return acc;
        }).collect(Collectors.toList());
  }

  public Account getAccount(Long accountId) {
    AccountEntity entity = accountRepository.findById(accountId).orElse(null);
    return entity == null ? null : modelMapper.map(entity,Account.class);
  }

  @Transactional
  public Account saveOrUpdate(Account account) {
    AccountEntity entity = modelMapper.map(account, AccountEntity.class);
    CurrencyEntity currencyEntity = currencyRepository.findById(account.getCurrencyId()).orElse(null);
    if(currencyEntity == null) {
      throw new IllegalArgumentException("currency does not exist");
    }
    entity.setCurrencyEntity(currencyEntity);
    return modelMapper.map(accountRepository.save(entity),Account.class);
  }
}
