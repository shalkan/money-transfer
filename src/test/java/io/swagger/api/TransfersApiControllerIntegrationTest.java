package io.swagger.api;

import io.swagger.api.controller.TransfersApi;
import io.swagger.model.Transaction;
import io.swagger.model.Transfer;

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
public class TransfersApiControllerIntegrationTest {

  @Autowired
  private TransfersApi api;

  @Test
  public void transfersGetTest() throws Exception {
    ResponseEntity<List<Transaction>> responseEntity = api.transfersGet();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void transfersPostTest() throws Exception {
    Transfer body = new Transfer();
    try {
      api.transfersPost(body);
    }
    catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
  }

}
