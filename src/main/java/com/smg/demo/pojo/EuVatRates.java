package com.smg.demo.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class EuVatRates {
  private String last_updated;
  private String disclaimer;

  private Map<String, Rate> rates;

}
