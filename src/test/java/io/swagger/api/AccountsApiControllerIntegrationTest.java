package io.swagger.api;

import io.swagger.api.controller.AccountsApi;
import io.swagger.model.Account;
import io.swagger.model.Transaction;

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
public class AccountsApiControllerIntegrationTest {

    @Autowired
    private AccountsApi api;

    @Test
    public void accountsAccountIdGetTest() throws Exception {
        String accountId = UUID.randomUUID().toString();
        ResponseEntity<Account> responseEntity = api.accountsAccountIdGet(accountId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void accountsAccountIdTransfersGetTest() throws Exception {
        String accountId = UUID.randomUUID().toString();
        ResponseEntity<List<Transaction>> responseEntity = api.accountsAccountIdTransfersGet(accountId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void accountsGetTest() throws Exception {
        ResponseEntity<Collection<Account>> responseEntity = api.accountsGet();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void accountsPostTest() throws Exception {
        Account body = new Account();
        try {
            api.accountsPost(body);
        }
        catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

}
