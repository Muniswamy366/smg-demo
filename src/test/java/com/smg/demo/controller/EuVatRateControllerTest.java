package com.smg.demo.controller;

import com.smg.demo.pojo.Rate;
import com.smg.demo.service.EuVatRateService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EuVatRateController.class)
class EuVatRateControllerTest {

    @MockBean
    EuVatRateService euVatRateService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testHighestStandardVAT() throws Exception {
        Rate rate1 = new Rate();
        rate1.setStandard_rate(10.0);

        Rate rate2 = new Rate();
        rate2.setStandard_rate(5.0);

        Rate rate3 = new Rate();
        rate3.setStandard_rate(2.0);

        Optional<List<Rate>> optionalRates = Optional.of(Arrays.asList(rate1, rate2));

        Mockito.when(euVatRateService.getHighestStandardVAT(2)).thenReturn(optionalRates);

        mockMvc.perform(get("/euRates/TOP/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void testNotFound() throws Exception {
        Rate rate1 = new Rate();
        rate1.setReduced_rate("10.0");

        Rate rate2 = new Rate();
        rate2.setReduced_rate("5.0");

        Rate rate3 = new Rate();
        rate3.setReduced_rate("2.0");

        Optional<List<Rate>> optionalRates = Optional.of(Arrays.asList(rate1, rate2));

        Mockito.when(euVatRateService.getLowestReducedVAT(2)).thenReturn(optionalRates);

        mockMvc.perform(get("/euRates/test/2"))
                .andExpect(status().isNotFound());
    }

}