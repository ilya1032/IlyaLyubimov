package hw5.Issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status extends IssueFieldWithLabel{

    public static final String COLOR_KEY = "color";

    private final String color;

    @JsonCreator
    public Status(
            @JsonProperty(ID_KEY) final int id,
            @JsonProperty(NAME_KEY) final String name,
            @JsonProperty(LABALE_KEY) final String label,
            @JsonProperty(COLOR_KEY) final String color)
    {
        super(id, name, label);
        this.color = color;
    }

    @JsonProperty(COLOR_KEY)
    public String getColor() {
        return color;
    }
}
