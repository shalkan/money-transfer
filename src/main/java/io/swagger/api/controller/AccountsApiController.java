package io.swagger.api.controller;

import io.swagger.api.storage.Storage;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
                            date = "2018-12-06T19:09:28.930Z[GMT]")

@Controller
public class AccountsApiController implements AccountsApi {

  private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  private final Storage storage;

  @org.springframework.beans.factory.annotation.Autowired
  public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request,
                               Storage storage) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.storage = storage;
  }

  public ResponseEntity<Account> accountsAccountIdGet(
      @ApiParam(value = "ID of account to return", required = true) @PathVariable("accountId")
          String accountId) {
    String accept = request.getHeader("Accept");
    return new ResponseEntity<Account>(storage.getAccount(accountId), HttpStatus.OK);
  }

  public ResponseEntity<List<Transaction>> accountsAccountIdTransfersGet(
      @ApiParam(value = "account ID", required = true) @PathVariable("accountId") String accountId) {
    String accept = request.getHeader("Accept");
    return new ResponseEntity<List<Transaction>>(storage.getAccountTransactions(accountId),
        HttpStatus.OK);
  }

  public ResponseEntity<Collection<Account>> accountsGet() {
    String accept = request.getHeader("Accept");
    return new ResponseEntity<Collection<Account>>(storage.getAccounts(), HttpStatus.OK);
  }

  public ResponseEntity<Account> accountsPost(
      @ApiParam(value = "") @Valid @RequestBody Account body) {
    String accept = request.getHeader("Accept");
    if (body.getId() != null) {
      throw new IllegalArgumentException("Id must not be set");
    }
    body.setId(UUID.randomUUID().toString());
    return new ResponseEntity<Account>(storage.addAccount(body), HttpStatus.OK);
  }

}
