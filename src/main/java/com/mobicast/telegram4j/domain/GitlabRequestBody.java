package com.mobicast.telegram4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitlabRequestBody {
    public String object_kind;
    public String event_name;
    public String user_name;
    public String user_username;
    public String before;
    public String after;
    public String ref;
    public ProjectGitlab project;
    public ObjectAttributes object_attributes;
    public List<Commit> commits;
    public List<Assignees> assignees;
    public List<Reviewers> reviewers;

}
