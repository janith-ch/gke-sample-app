package com.developer.tracker.issuemanagmentservice.service;


import com.developer.tracker.issuemanagmentservice.dto.IssueDetailsDto;
import com.developer.tracker.issuemanagmentservice.model.Issue;

import java.util.List;

public interface IssueService {


    List<Issue> syncIssueDetailsFromGithub();

    IssueDetailsDto getAlIssueByAssignee(String assignee);
}
