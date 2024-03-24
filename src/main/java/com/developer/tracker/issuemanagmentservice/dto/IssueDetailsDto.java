package com.developer.tracker.issuemanagmentservice.dto;

import com.developer.tracker.issuemanagmentservice.model.Issue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueDetailsDto {

    private int issueCount;
    private List<Issue> issues;
}
