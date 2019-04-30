package hw5;

import static io.restassured.RestAssured.given;

import hw5.User.MantisResponce;
import hw5.User.User;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AddDeleteUserAPITest {

    private Properties properties;
    private String baseUrl;
    private Header header;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        FileInputStream propertiesFile = new FileInputStream("src/test/resources/hw5.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        baseUrl = properties.getProperty("mantis.url");
        String token = properties.getProperty("mantis.token");
        header = new Header("Authorization", token);
    }

    @Test
    public void createUserTest() {
        User user = new User(
                        0,
                        properties.getProperty("user.username"),
                        properties.getProperty("user.realname"),
                        properties.getProperty("user.email"));

        MantisResponce createUser = given()
                .baseUri(properties.getProperty("mantis.url"))
                .header(header)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().
                        response().
                        as(MantisResponce.class);

        user = createUser.getUser();

        //delete created user to keep Mantis Clean
        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .when()
                .delete("/users/" + user.getId())
                .then().
                statusCode(204);
    }

    @Test
    public void deleteNonExistingUserTest() {
        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .when()
                .delete("/users/9000")
                .then().
                statusCode(404);
    }
}
