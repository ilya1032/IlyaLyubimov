package hw5.Issue;

import com.fasterxml.jackson.annotation.*;
import hw5.Project.Project;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Issue {

    public static final String ID_KEY = "id";
    public static final String SUMMARY_KEY = "summary";
    public static final String DESCRIPTION_KEY = "description";
    public static final String PROJECT_KEY = "project";
    public static final String CATEGORY_KEY = "category";
    public static final String STATUS_KEY = "status";
    public static final String SEVERITY_KEY = "severity";
    public static final String PRIORITY_KEY = "priority";

    private final int id;
    private final String summary;
    private final String description;
    private final Project project;
    private final IssueField category;
    private final IssueFieldWithLabel severity;
    private final IssueFieldWithLabel priority;
    private final Status status;

    @JsonCreator
    public Issue(@JsonProperty(ID_KEY) final int id,
                 @JsonProperty(SUMMARY_KEY) final String summary,
                 @JsonProperty(DESCRIPTION_KEY) final String description,
                 @JsonProperty(PROJECT_KEY) final Project project,
                 @JsonProperty(CATEGORY_KEY) final IssueField category,
                 @JsonProperty(SEVERITY_KEY) final IssueFieldWithLabel severity,
                 @JsonProperty(PRIORITY_KEY) final IssueFieldWithLabel priority,
                 @JsonProperty(STATUS_KEY) final Status status
    )
    {
        this.id = id;
        this.summary = summary;
        this.description = description;
        this.project = project;
        this.category = category;
        this.severity = severity;
        this.priority = priority;
        this.status = status;
    }

    public Issue(Issue other) {
        this(
                other.id,
                other.summary,
                other.description,
                other.project,
                other.category,
                other.severity,
                other.priority,
                other.status);
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonProperty(SUMMARY_KEY)
    public String getSummary() {
        return summary;
    }

    @JsonProperty(DESCRIPTION_KEY)
    public String getDescription() {
        return description;
    }

    @JsonProperty(PROJECT_KEY)
    public Project getProject() {
        return project;
    }

    @JsonProperty(CATEGORY_KEY)
    public IssueField getCategory() {
        return category;
    }

    @JsonProperty(STATUS_KEY)
    public Status getStatus() {
        return status;
    }

    @JsonProperty(SEVERITY_KEY)
    public IssueField getSeverity() {
        return severity;
    }

    @JsonProperty(PRIORITY_KEY)
    public IssueFieldWithLabel getPriority() {
        return priority;
    }

}
