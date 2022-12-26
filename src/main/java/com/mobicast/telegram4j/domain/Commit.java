package com.mobicast.telegram4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {
    public String message;
    public String title;
    public String timestamp;
    public Author author;
}
