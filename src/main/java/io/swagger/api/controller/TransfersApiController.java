package io.swagger.api.controller;

import io.swagger.api.storage.Storage;
import io.swagger.model.Transaction;
import io.swagger.model.Transfer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
                            date = "2018-12-06T19:09:28.930Z[GMT]")

@Controller
public class TransfersApiController implements TransfersApi {

  private static final Logger log = LoggerFactory.getLogger(TransfersApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  private final Storage storage;

  @org.springframework.beans.factory.annotation.Autowired
  public TransfersApiController(ObjectMapper objectMapper, HttpServletRequest request,
                                Storage storage) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.storage = storage;
  }

  public ResponseEntity<List<Transaction>> transfersGet() {
    String accept = request.getHeader("Accept");
    return new ResponseEntity<List<Transaction>>(storage.getTransactionList(), HttpStatus.OK);
  }

  public ResponseEntity<Transaction> transfersPost(
      @ApiParam(value = "") @Valid @RequestBody Transfer body) {
    String accept = request.getHeader("Accept");
    if (body.getDestAccId() == null || body.getSourceAccId() == null) {
      throw new IllegalArgumentException("accounts are not set");
    }
    if (body.getAmount().compareTo(BigDecimal.ZERO) < 1) {
      throw new IllegalArgumentException("amount must be positive");
    }
    return new ResponseEntity<Transaction>(storage.createTransaction(body), HttpStatus.OK);
  }

//  public ResponseEntity<Transaction> transfersTransferIdGet(
//      @ApiParam(value = "id of transfer to return", required = true) @PathVariable("transferId")
//          Long transferId) {
//    String accept = request.getHeader("Accept");
//    return new ResponseEntity<Transaction>(transferService.getTransaction(transferId),
//        HttpStatus.OK);
//  }

}
