package io.swagger.api.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.dao.CurrencyRepository;
import io.swagger.api.entity.CurrencyEntity;
import io.swagger.model.Currency;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public Currency saveOrUpdate(Currency currency) {
    return modelMapper.map(currencyRepository.save(modelMapper.map(currency, CurrencyEntity.class)),
        Currency.class);
  }
}
