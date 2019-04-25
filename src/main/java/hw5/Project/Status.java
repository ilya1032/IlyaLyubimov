package hw5.Project;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {

    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String LABEL_KEY = "label";

    private final int id;
    private final String name;
    private final String label;

    @JsonCreator
    public Status(
            @JsonProperty(ID_KEY) final int id,
            @JsonProperty(NAME_KEY) final String name,
            @JsonProperty(LABEL_KEY) final String label
    ) {
        this.id = id;
        this.name = name;
        this.label = label;
    }

    @JsonProperty(ID_KEY)
    public int getId() {
        return id;
    }

    @JsonProperty(NAME_KEY)
    public String getName() {
        return name;
    }

    @JsonProperty(LABEL_KEY)
    public String getLabel() {
        return label;
    }
}
