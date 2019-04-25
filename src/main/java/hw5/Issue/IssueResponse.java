package hw5.Issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueResponse {

    public static final String ISSUE_KEY = "issue";

    @JsonProperty(ISSUE_KEY)
    private final Issue issue;

    @JsonCreator
    public IssueResponse(
            @JsonProperty(ISSUE_KEY) Issue issue)
    {
        this.issue = issue;
    }

    @JsonProperty(ISSUE_KEY)
    public Issue getIssue() {
        return issue;
    }
}
