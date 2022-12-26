package com.mobicast.telegram4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectGitlab {
    public String name;
    public String path_with_namespace;
}
