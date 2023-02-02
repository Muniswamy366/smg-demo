package com.smg.demo.controller;

import java.util.List;

import com.smg.demo.exception.NoRecordFound;
import com.smg.demo.pojo.Rate;
import com.smg.demo.service.EuVatRateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("euRates")
public class EuVatRateController {
    private final EuVatRateService euVatRateService;

    /**
     * @param type
     * @param limit
     * @return
     */
    @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Top n EU Rates, Where n is number", notes = "Returns top n EU countries based on n")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The data was not found")
    })
    public List<Rate> getCountriesByLimit(@ApiParam(value = "TOP,LOW", required = true) @RequestParam String type,
                                          @ApiParam(value = "1,2,3", required = true) @RequestParam int limit) {
        if("TOP".equalsIgnoreCase(type)) {
            return euVatRateService.getHighestStandardVAT(limit).orElseThrow(() -> new NoRecordFound("Record not found."));
        } else if ("LOW".equalsIgnoreCase(type)) {
            return euVatRateService.getLowestReducedVAT(limit).orElseThrow(() -> new NoRecordFound("Record not found."));
        } else {
            throw new NoRecordFound("Record not found.");
        }
    }

}
