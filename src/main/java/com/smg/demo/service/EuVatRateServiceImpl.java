package com.smg.demo.service;

import com.smg.demo.dao.EuVatRateDAO;
import com.smg.demo.pojo.EuVatRates;
import com.smg.demo.pojo.Rate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EuVatRateServiceImpl implements EuVatRateService {

    private EuVatRateDAO euVatRateDAO;

    /**
     * @param limit
     * @return
     */
    @Override
    public Optional<List<Rate>> getHighestStandardVAT(int limit) {
        Optional<EuVatRates> countriesByLimit = euVatRateDAO.getCountriesByLimit(limit);

        if (countriesByLimit.isPresent()) {
            Map<String, Rate> rates = countriesByLimit.get().getRates();
            return Optional.of(rates.values().stream()
                    .sorted(Comparator.comparing(Rate::getStandard_rate, Comparator.reverseOrder()))
                    .limit(limit)
                    .collect(Collectors.toList()));
        }

        return Optional.empty();
    }

    /**
     * @param limit
     * @return
     */
    @Override
    public Optional<List<Rate>> getLowestReducedVAT( int limit) {
        Optional<EuVatRates> countriesByLimit = euVatRateDAO.getCountriesByLimit(limit);

        if (countriesByLimit.isPresent()) {
            Map<String, Rate> rates = countriesByLimit.get().getRates();
            return Optional.of(rates.values().stream()
                    .filter(rate -> this.isNumeric(rate.getReduced_rate()))
                    .sorted(Comparator.comparing(rate -> Double.parseDouble(rate.getReduced_rate()), Comparator.naturalOrder()))
                    .limit(limit)
                    .collect(Collectors.toList()));
        }

        return Optional.empty();
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}