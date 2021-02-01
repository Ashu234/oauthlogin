package com.oauth.login.controller;

import com.oauth.login.domain.DateRange;
import com.oauth.login.domain.UserSession;
import com.oauth.login.exception.BadRequestException;
import com.oauth.login.exception.BusinessException;
import com.oauth.login.exception.ErrorCodes;
import com.oauth.login.service.DateRangeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
/*
   DateRangeController accepts string dates from and to
   It returns all the valid User Session between those dates

 */
@RestController
@RequestMapping("/date/range")
public class DateRangeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateRangeController.class);

    private final DateRangeService dateRangeService;

    public DateRangeController(DateRangeService dateRangeService) {
        this.dateRangeService = dateRangeService;
    }


    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "dateRange", response = UserSession.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden Access or Wrong Password"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "System Error")
    })
    public ResponseEntity<List<UserSession>> getUserSessionsInDateRange(@Valid @RequestBody DateRange dateRange) throws BusinessException {
        LOGGER.debug("Date Range Session Query: {}", dateRange);
        //For Unit Testing
        if(StringUtils.isEmpty(dateRange.getFromDate())){
            throw new BadRequestException("Please Provide From Date", ErrorCodes.BAD_REQUEST_EXCEPTION);
        }

        if(StringUtils.isEmpty(dateRange.getToDate())){
            throw new BadRequestException("Please Provide To Date ", ErrorCodes.BAD_REQUEST_EXCEPTION);
        }

        List<UserSession> userSessionEntityList = dateRangeService.getUserSessionsWithinDateRange(dateRange.getFromDate(),
                dateRange.getToDate());

        return new ResponseEntity<>(userSessionEntityList, HttpStatus.OK);
    }
}
