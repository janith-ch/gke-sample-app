package com.developer.tracker.issuemanagmentservice.service;

import com.developer.tracker.issuemanagmentservice.dto.IssueDetailsDto;
import com.developer.tracker.issuemanagmentservice.dto.IssueDto;
import com.developer.tracker.issuemanagmentservice.model.Issue;
import com.developer.tracker.issuemanagmentservice.repository.IssueRepository;
import com.developer.tracker.issuemanagmentservice.service.external.GithubExternalClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final GithubExternalClientService githubExternalClientService;
    private final IssueRepository issueRepository;

    @Override
    public List<Issue> syncIssueDetailsFromGithub() {
        List<Issue> issues = new ArrayList<>();
        log.info("sync Issue Details From Github | process Started");
        List<IssueDto> issueDtoList = this.githubExternalClientService.getIssueDetails();

        issueDtoList.forEach(issueDto -> {
            if (issueDto.getAssigneeDto() != null) {
                Issue issue = this.generateIssueObject(issueDto);
                log.info("sync Issue Details From Github  | save new record | {}", issue);
                this.checkAndRemoveOldRecords(issue);
                issues.add(issue);
            }
        });

        this.issueRepository.saveAll(issues);
        log.info("sync Issue Details From Github  | process end");
        return issues;

    }

    @Override
    public IssueDetailsDto getAlIssueByAssignee(String assignee) {
        List<Issue> issues = this.issueRepository.findAllByAssignee(assignee);
        return IssueDetailsDto.builder().issueCount(issues.size()).issues(issues).build();
    }

    private void checkAndRemoveOldRecords(Issue issue) {
        Optional<Issue> oldIssue = this.issueRepository.findByGitHubId(issue.getGitHubId());
        oldIssue.ifPresent(this.issueRepository::delete);
    }

    private Issue generateIssueObject(IssueDto issueDto) {
        return Issue.builder()
                .gitHubId(issueDto.getId())
                .assignee(issueDto.getAssigneeDto().getLogin())
                .issueMessage(issueDto.getIssueMessage())
                .state(issueDto.getState())
                .locked(issueDto.isLocked())
                .build();
    }
}
