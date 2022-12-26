package com.mobicast.telegram4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectAttributes {
    public String merge_status;
    public String source_branch;
    public String target_branch;
}
