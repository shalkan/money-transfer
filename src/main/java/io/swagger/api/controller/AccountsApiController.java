package io.swagger.api.controller;

import io.swagger.api.service.AccountService;
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
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-06T19:09:28.930Z[GMT]")

@Controller
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final AccountService accountService;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request, AccountService accountService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.accountService = accountService;
    }

    public ResponseEntity<Account> accountsAccountIdGet(@ApiParam(value = "ID of account to return",required=true) @PathVariable("accountId") Long accountId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Account>(accountService.getAccount(accountId), HttpStatus.OK);
    }

    public ResponseEntity<List<Transaction>> accountsAccountIdTransfersGet(@ApiParam(value = "account ID",required=true) @PathVariable("accountId") Long accountId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Account>> accountsGet() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Account>>(accountService.getAccounts(), HttpStatus.OK);
    }

    public ResponseEntity<Account> accountsPost(@ApiParam(value = ""  )  @Valid @RequestBody Account body) {
        String accept = request.getHeader("Accept");
        if (body.getId() != null) {
            throw new IllegalArgumentException("Id must not be set");
        }
        if(body.getCurrencyId() == null) {

            throw new IllegalArgumentException("currency required");
        }
        return new ResponseEntity<Account>(accountService.saveOrUpdate(body), HttpStatus.OK);
    }

}
