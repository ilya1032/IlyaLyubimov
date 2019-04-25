package hw5.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class MantisResponce {

    public static final String USER_KEY = "user";

    private final User user;

    @JsonCreator
    public MantisResponce(@JsonProperty(USER_KEY) final User user) {
        this.user = user;
    }

    @JsonProperty(USER_KEY)
    public User getUser() {
        return user;
    }
}
