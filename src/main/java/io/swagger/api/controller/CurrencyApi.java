/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api.controller;

import io.swagger.model.Currency;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-06T19:09:28.930Z[GMT]")

@Api(value = "currency", description = "the currency API")
public interface CurrencyApi {

    @ApiOperation(value = "existing currencies", nickname = "currencyGet", notes = "get list of existing currencies", response = Currency.class, responseContainer = "List", tags={ "currency", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "currencies list", response = Currency.class, responseContainer = "List") })
    @RequestMapping(value = "/currency",
        produces = { "application/json" }, 
        method = RequestMethod.GET, headers = "Accept=application/json")
    ResponseEntity<List<Currency>> currencyGet();


    @ApiOperation(value = "create new currency item", nickname = "currencyPost", notes = "method for adding new currencies. Currency's 'ratio' field is ratio with reference constant value", response = Currency.class, tags={ "currency", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "created currency item", response = Currency.class) })
    @RequestMapping(value = "/currency",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Currency> currencyPost(@ApiParam(value = ""  )  @Valid @RequestBody Currency body);


    @ApiOperation(value = "update specified currency item", nickname = "currencyPut", notes = "only update of currency's 'ratio' allowed", response = Currency.class, tags={ "currency", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "updated currency item", response = Currency.class) })
    @RequestMapping(value = "/currency",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Currency> currencyPut(@ApiParam(value = ""  )  @Valid @RequestBody Currency body);

}