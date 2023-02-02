package com.smg.demo.service;

import com.smg.demo.dao.EuVatRateDAO;
import com.smg.demo.pojo.EuVatRates;
import com.smg.demo.pojo.Rate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EuVatRateServiceImplTest {

    @InjectMocks
    EuVatRateServiceImpl euVatRateService;

    @Mock
    EuVatRateDAO euVatRateDAO;

    @Test
    void testHighestStandardVAT() {
        Rate rate1 = new Rate();
        rate1.setStandard_rate(10.0);

        Rate rate2 = new Rate();
        rate2.setStandard_rate(5.0);

        Rate rate3 = new Rate();
        rate3.setStandard_rate(2.0);

        Map<String, Rate> map = Map.ofEntries(Map.entry("A", rate1), Map.entry("B", rate2), Map.entry("C", rate3));

        EuVatRates euVatRates = new EuVatRates();
        euVatRates.setRates(map);

        Mockito.when(euVatRateDAO.getCountriesByLimit(2)).thenReturn(Optional.of(euVatRates));

        Optional<List<Rate>> highestStandardVAT = euVatRateService.getHighestStandardVAT(2);
        List<Rate> rates = highestStandardVAT.get();

        assertEquals(2, rates.size());
        assertEquals(10.0, rates.get(0).getStandard_rate());
        assertEquals(5.0, rates.get(1).getStandard_rate());
    }

    @Test
    void testLowestReducedVAT() {
        Rate rate1 = new Rate();
        rate1.setReduced_rate("10.0");

        Rate rate2 = new Rate();
        rate2.setReduced_rate("5.0");

        Rate rate3 = new Rate();
        rate3.setReduced_rate("2.0");

        Map<String, Rate> map = Map.ofEntries(Map.entry("A", rate1), Map.entry("B", rate2), Map.entry("C", rate3));

        EuVatRates euVatRates = new EuVatRates();
        euVatRates.setRates(map);

        Mockito.when(euVatRateDAO.getCountriesByLimit(2)).thenReturn(Optional.of(euVatRates));

        Optional<List<Rate>> lowestReducedVAT = euVatRateService.getLowestReducedVAT(2);
        List<Rate> rates = lowestReducedVAT.get();

        assertEquals(2, rates.size());
        assertEquals("2.0", rates.get(0).getReduced_rate());
        assertEquals("5.0", rates.get(1).getReduced_rate());
    }

    @Test
    void isNumeric() {
    }
}