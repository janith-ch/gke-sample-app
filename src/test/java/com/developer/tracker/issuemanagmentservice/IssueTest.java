package com.developer.tracker.issuemanagmentservice;

import com.developer.tracker.issuemanagmentservice.model.Issue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

class IssueTest {

    @Test
    void testIssueModel() {
        // Arrange
        String id = "123";
        int gitHubId = 1;
        String assignee = "testUser";
        String state = "open";
        boolean locked = true;
        String issueMessage = "Issue title";

        // Act
        Issue issue = new Issue();
        issue.setId(id);
        issue.setGitHubId(gitHubId);
        issue.setAssignee(assignee);
        issue.setState(state);
        issue.setLocked(locked);
        issue.setIssueMessage(issueMessage);

        // Assert
        assertThat(issue).isNotNull();
        assertThat(issue.getId()).isEqualTo(id);
        assertThat(issue.getGitHubId()).isEqualTo(gitHubId);
        assertThat(issue.getAssignee()).isEqualTo(assignee);
        assertThat(issue.getState()).isEqualTo(state);
        assertThat(issue.isLocked()).isEqualTo(locked);
        assertThat(issue.getIssueMessage()).isEqualTo(issueMessage);
    }
}