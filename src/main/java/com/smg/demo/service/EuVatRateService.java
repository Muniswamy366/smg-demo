package com.smg.demo.service;

import com.smg.demo.pojo.Rate;

import java.util.List;
import java.util.Optional;

public interface EuVatRateService {
  Optional<List<Rate>> getHighestStandardVAT(int limit);

  Optional<List<Rate>> getLowestReducedVAT(int limit);

}
