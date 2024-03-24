package com.developer.tracker.issuemanagmentservice.controller;

import com.developer.tracker.issuemanagmentservice.dto.IssueDetailsDto;
import com.developer.tracker.issuemanagmentservice.model.Issue;
import com.developer.tracker.issuemanagmentservice.service.IssueService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/issue")
public class IssueController {

    private IssueService issueService;

    @GetMapping("/sync-from-git-hub")
    public ResponseEntity<List<Issue>> syncIssueDetailsFromGithub() {
        return ResponseEntity.ok(this.issueService.syncIssueDetailsFromGithub());
    }

    @GetMapping("/get-by-assignee")
    public ResponseEntity<IssueDetailsDto> getAlIssueByAssignee(@RequestParam String assigneeName) {
        return ResponseEntity.ok(this.issueService.getAlIssueByAssignee(assigneeName));
    }
}
