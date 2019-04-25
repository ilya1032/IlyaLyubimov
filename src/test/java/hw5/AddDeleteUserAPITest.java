package hw5;

import static io.restassured.RestAssured.given;

import hw5.User.FullUser;
import hw5.User.MantisResponce;
import hw5.User.User;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AddDeleteUserAPITest {

    private Properties properties;
    private String baseUrl;
    private String token;
    private FullUser user;

    @BeforeSuite
    public void setUp() throws IOException {
        FileInputStream propertiesFile = new FileInputStream("src/test/resources/hw5.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        baseUrl = properties.getProperty("mantis.url");
        token = properties.getProperty("mantis.token");
    }

    @Test
    public void createUserTest() {
        FullUser user = new FullUser(
                new User(
                        0,
                        properties.getProperty("user.username"),
                        properties.getProperty("user.realname"),
                        properties.getProperty("user.email")),
                properties.getProperty("user.password"));

        JSONObject userParams = new JSONObject();
        userParams.put("username", user.getUser().getName());
        userParams.put(User.REAL_NAME_KEY, user.getUser().getRealName());
        userParams.put(User.EMAIL_KEY, user.getUser().getEmail());
        userParams.put("password", user.getPassword());

        MantisResponce createUser = given()
                .baseUri(properties.getProperty("mantis.url"))
                .header(new Header("Authorization", token))
                .contentType(ContentType.JSON)
                .body(userParams.toString())
                .when()
                .post("/users/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().
                        response().
                        as(MantisResponce.class);

        user = new FullUser(createUser.getUser(), user.getPassword());

        //delete created user to keep Mantis Clean
        given().
                baseUri(baseUrl).
                header(new Header("Authorization", properties.getProperty("mantis.token"))).
                contentType(ContentType.JSON)
                .when()
                .delete("/users/" + user.getUser().getId())
                .then().
                statusCode(204);
    }

    @Test
    public void deleteNonExistingUserTest() {
        given().
                baseUri(baseUrl).
                header(new Header("Authorization", properties.getProperty("mantis.token"))).
                contentType(ContentType.JSON)
                .when()
                .delete("/users/9000")
                .then().
                statusCode(404);
    }
}
