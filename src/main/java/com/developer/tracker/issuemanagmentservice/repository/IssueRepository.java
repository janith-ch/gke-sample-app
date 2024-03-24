package com.developer.tracker.issuemanagmentservice.repository;

import com.developer.tracker.issuemanagmentservice.model.Issue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends MongoRepository<Issue, String> {
    Optional<Issue> findByGitHubId(int gitHubId);

    List<Issue> findAllByAssignee(String assignee);
}
