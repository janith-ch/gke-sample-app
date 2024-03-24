package com.developer.tracker.issuemanagmentservice.service.external;

import com.developer.tracker.issuemanagmentservice.configuration.properties.GitHubClientProperties;
import com.developer.tracker.issuemanagmentservice.dto.IssueDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@AllArgsConstructor
public class GithubExternalClientService {

    private final RestTemplate restTemplate;
    private final GitHubClientProperties clientProperties;

    public List<IssueDto> getIssueDetails() {
        ResponseEntity<List<IssueDto>> response = restTemplate.exchange(clientProperties.getIssueDetailUrl(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return response.getBody();
    }
}
