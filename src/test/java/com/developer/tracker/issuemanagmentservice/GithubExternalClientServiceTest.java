package com.developer.tracker.issuemanagmentservice;

import com.developer.tracker.issuemanagmentservice.configuration.properties.GitHubClientProperties;
import com.developer.tracker.issuemanagmentservice.dto.IssueDto;
import com.developer.tracker.issuemanagmentservice.service.external.GithubExternalClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubExternalClientServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private GitHubClientProperties clientProperties;

    @InjectMocks
    private GithubExternalClientService githubExternalClientService;

    @BeforeEach
    void setUp() {
        when(clientProperties.getIssueDetailUrl()).thenReturn("http://api.github.com/issues");
    }

    @Test
    void testGetIssueDetailsWhenApiCallIsSuccessfulThenReturnIssueDetails() {
        // Arrange
        List<IssueDto> expectedIssues = Arrays.asList(
                new IssueDto(1, "open", false, null, "Issue 1"),
                new IssueDto(2, "closed", false, null, "Issue 2")
        );
        ResponseEntity<List<IssueDto>> responseEntity = new ResponseEntity<>(expectedIssues, HttpStatus.OK);
        when(restTemplate.exchange(
                "http://api.github.com/issues",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IssueDto>>() {
                }
        )).thenReturn(responseEntity);

        // Act
        List<IssueDto> actualIssues = githubExternalClientService.getIssueDetails();

        // Assert
        assertNotNull(actualIssues, "The returned issue details should not be null");
        assertEquals(expectedIssues.size(), actualIssues.size(), "The number of issues returned was not as expected");
        assertEquals(expectedIssues, actualIssues, "The issues returned were not as expected");
    }

    @Test
    void testGetIssueDetailsWhenApiCallFailsThenReturnNull() {
        // Arrange
        when(restTemplate.exchange(
                "http://api.github.com/issues",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IssueDto>>() {
                }
        )).thenThrow(new RuntimeException("API call failed"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            githubExternalClientService.getIssueDetails();
        }, "A RuntimeException should be thrown when the API call fails");

        assertEquals("API call failed", exception.getMessage(), "The exception message was not as expected");
    }
}