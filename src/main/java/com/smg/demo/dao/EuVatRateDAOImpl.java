package com.smg.demo.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smg.demo.pojo.EuVatRates;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Component
public class EuVatRateDAOImpl implements EuVatRateDAO{

    ObjectMapper mapper = new ObjectMapper();

    /**
     * @param limit
     * @return
     */
    @Override
    public Optional<EuVatRates> getCountriesByLimit(int limit) {

        try {
            return Optional.ofNullable(mapper.readValue(new URL("https://euvatrates.com/rates.json"), EuVatRates.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
