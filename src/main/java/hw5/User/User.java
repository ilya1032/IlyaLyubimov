package hw5.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String REAL_NAME_KEY = "real_name";
    public static final String EMAIL_KEY = "email";

    private int id;
    private String name;
    private String realName;
    private String email;

    @JsonCreator
    public User(
            @JsonProperty(ID_KEY) final int id,
            @JsonProperty(NAME_KEY) final String name,
            @JsonProperty(REAL_NAME_KEY) final String realName,
            @JsonProperty(EMAIL_KEY) final String email
    ) {
        this.id = id;
        this.name = name;
        this.realName = realName;
        this.email = email;
    }

    @JsonProperty(ID_KEY)
    public int getId() {
        return id;
    }

    @JsonProperty(NAME_KEY)
    public String getName() {
        return name;
    }

    @JsonProperty(REAL_NAME_KEY)
    public String getRealName() {
        return realName;
    }

    @JsonProperty(EMAIL_KEY)
    public String getEmail() {
        return email;
    }
}
