package io.swagger.api.controller;

import io.swagger.api.service.CurrencyService;
import io.swagger.model.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-06T19:09:28.930Z[GMT]")

@Controller
public class CurrencyApiController implements CurrencyApi {

    private static final Logger log = LoggerFactory.getLogger(CurrencyApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private CurrencyService currencyService;

    @org.springframework.beans.factory.annotation.Autowired
    public CurrencyApiController(ObjectMapper objectMapper, HttpServletRequest request, CurrencyService currencyService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.currencyService = currencyService;
    }

    public ResponseEntity<List<Currency>> currencyGet() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Currency>>(currencyService.getCurrencies(), HttpStatus.OK);
    }

    public ResponseEntity<Currency> currencyPost(@ApiParam(value = ""  )  @Valid @RequestBody Currency body) {
        String accept = request.getHeader("Accept");
        if (body.getId() != null) {
            throw new IllegalArgumentException("Id should not be set");
        }
        return new ResponseEntity<Currency>(currencyService.saveOrUpdate(body), HttpStatus.OK);
    }

    public ResponseEntity<Currency> currencyPut(@ApiParam(value = ""  )  @Valid @RequestBody Currency body) {
        String accept = request.getHeader("Accept");
        if (body.getId() == null || body.getId() <= 0L) {
            throw new IllegalArgumentException("Non negative Id should be set");
        }
        return new ResponseEntity<Currency>(currencyService.saveOrUpdate(body), HttpStatus.OK);
    }

}
