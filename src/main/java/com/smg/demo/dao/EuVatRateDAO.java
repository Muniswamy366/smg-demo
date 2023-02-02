package com.smg.demo.dao;

import com.smg.demo.pojo.EuVatRates;

import java.util.Optional;

public interface EuVatRateDAO {

    Optional<EuVatRates> getCountriesByLimit(int limit);

}
