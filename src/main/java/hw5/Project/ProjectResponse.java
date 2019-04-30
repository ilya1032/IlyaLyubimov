package hw5.Project;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectResponse {

    public static final String PROJECT_KEY = "project";

    @JsonProperty(PROJECT_KEY)
    private final Project project;

    @JsonCreator
    public ProjectResponse(
            @JsonProperty(PROJECT_KEY) Project project)
    {
        this.project = project;
    }

    @JsonProperty(PROJECT_KEY)
    public Project getProject() {
        return project;
    }
}
