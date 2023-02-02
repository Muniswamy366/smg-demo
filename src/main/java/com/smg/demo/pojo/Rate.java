package com.smg.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    @ApiModelProperty(notes = "Comment", example = "sample comment")
    private String _comment;
    private String iso_duplicate;
    private String iso_duplicate_of;
    private String country;
    private double standard_rate;
    private String reduced_rate;
    private String reduced_rate_alt; // double and boolean
    private String super_reduced_rate;
    private String parking_rate;

}
