package com.developer.tracker.issuemanagmentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("state")
    private String state;

    @JsonProperty("locked")
    private boolean locked;

    @JsonProperty("assignee")
    private AssigneeDto assigneeDto;

    @JsonProperty("title")
    private String issueMessage;

}
