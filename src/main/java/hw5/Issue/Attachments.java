package hw5.Issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attachments {

    public static final String NAME_KEY = "name";
    public static final String CONTENT_KEY = "content";

    private final String name;
    private final String content;

    @JsonCreator
    public Attachments(
            @JsonProperty(NAME_KEY) final String name,
            @JsonProperty(CONTENT_KEY) final String content)
    {
        this.name = name;
        this.content = content;
    }

    @JsonProperty(NAME_KEY)
    public String getName() {
        return name;
    }

    @JsonProperty(CONTENT_KEY)
    public String getContent() {
        return content;
    }
}
