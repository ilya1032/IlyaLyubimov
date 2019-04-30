package hw5.Issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueFieldWithLabel extends IssueField{

    public static final String LABALE_KEY = "label";

    private final String label;

    @JsonCreator
    public IssueFieldWithLabel(
            @JsonProperty(ID_KEY) final int id,
            @JsonProperty(NAME_KEY) final String name,
            @JsonProperty(LABALE_KEY) final String label)
    {
        super(id, name);
        this.label = label;
    }

    @JsonProperty(LABALE_KEY)
    public String getLabel() {
        return label;
    }
}
