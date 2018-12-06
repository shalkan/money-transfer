package io.swagger.api;

import io.swagger.api.controller.CurrencyApi;
import io.swagger.model.Currency;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyApiControllerIntegrationTest {

  @Autowired
  private CurrencyApi api;

  @Test
  public void currencyGetTest() throws Exception {
    ResponseEntity<List<Currency>> responseEntity = api.currencyGet();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void currencyPostTest() throws Exception {
    Currency body = new Currency();
    try {
      api.currencyPost(body);
    }
    catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
  }

  @Test
  public void currencyPutTest() throws Exception {
    Currency body = new Currency();
    try {
      api.currencyPost(body);
    }
    catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
  }

}
