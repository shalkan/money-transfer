package io.swagger.api.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import io.swagger.api.dao.CurrencyRepository;
import io.swagger.api.entity.CurrencyEntity;
import io.swagger.model.Currency;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gilfanovrenat
 */
@Service
public class CurrencyService {

  @Autowired
  private CurrencyRepository currencyRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<Currency> getCurrencies() {
    return StreamSupport.stream(currencyRepository.findAll().spliterator(), false)
        .map(entity -> {
          Currency currency = new Currency();
          currency.setId(entity.getId());
          currency.setName(entity.getName());
          currency.setRatio(entity.getRatio());
          return currency;
        }).collect(Collectors.toList());
  }

  public Currency save(Currency currency) {
    return modelMapper.map(currencyRepository.save(modelMapper.map(currency, CurrencyEntity.class)),
        Currency.class);
  }

  @Transactional
  public Currency update(Currency currency) {
    CurrencyEntity entity = currencyRepository.findById(currency.getId()).orElse(null);
    if (entity == null) {
      throw new IllegalArgumentException("Currency does not exist");
    }
    if (!entity.getName().equals(currency.getName())) {
      throw new UnsupportedOperationException("Currency name can not be changed");
    }
    entity.setRatio(currency.getRatio());
    return modelMapper.map(currencyRepository.save(entity), Currency.class);
  }
}
