package hw5.Issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueWithAttachments extends Issue {

    public static final String FILES_KEY = "files";

    private final Attachments files[];

    @JsonCreator
    public IssueWithAttachments(
            Issue issue,
            @JsonProperty(FILES_KEY) Attachments[] files)
    {
        super(issue);
        this.files = files;
    }

    @JsonProperty(FILES_KEY)
    public Attachments[] getFiles() {
        return files;
    }
}
