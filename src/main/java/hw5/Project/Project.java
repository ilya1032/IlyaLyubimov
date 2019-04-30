package hw5.Project;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {

    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String DESCRIPTION_KEY = "description";
    public static final String STATUS_KEY = "status";

    private final int id;
    private final String name;
    private final String description;
    private final Status status;

    @JsonCreator
    public Project(
            @JsonProperty(ID_KEY) final int id,
            @JsonProperty(NAME_KEY) final String name,
            @JsonProperty(DESCRIPTION_KEY) final String description,
            @JsonProperty(STATUS_KEY) final Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonProperty(NAME_KEY)
    public String getName() {
        return name;
    }

    @JsonProperty(DESCRIPTION_KEY)
    public String getDescription() {
        return description;
    }

    @JsonProperty(STATUS_KEY)
    public Status getStatus() {
        return status;
    }
}
