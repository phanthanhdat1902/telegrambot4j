package com.mobicast.telegram4j.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reviewers {
    public String name;
    public String username;
}
