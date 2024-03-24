package com.developer.tracker.issuemanagmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "issue")
public class Issue {

    @Id
    private String id;

    private int gitHubId;

    private String assignee;

    private String state;

    private boolean locked;

    private String issueMessage;
}
