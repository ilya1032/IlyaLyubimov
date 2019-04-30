package hw5.Issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueField {

    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";

    private final int id;
    private final String name;

    @JsonCreator
    public IssueField(
            @JsonProperty(ID_KEY) final int id,
            @JsonProperty(NAME_KEY) final String name)
    {
        this.id = id;
        this.name = name;
    }

    @JsonProperty(ID_KEY)
    public int getId() {
        return id;
    }

    @JsonProperty(NAME_KEY)
    public String getName() {
        return name;
    }
}
