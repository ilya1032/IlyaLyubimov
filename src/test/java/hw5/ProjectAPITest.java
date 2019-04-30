package hw5;

import hw5.Project.Project;
import hw5.Project.ProjectResponse;
import hw5.Project.Status;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ProjectAPITest {

    private String baseUrl;
    private Project project;
    private Project subProject;
    private Header header;


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        FileInputStream propertiesFile = new FileInputStream("src/test/resources/hw5.properties");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        baseUrl = properties.getProperty("mantis.url");
        String token = properties.getProperty("mantis.token");
        header = new Header("Authorization", token);

        Status projectStatus = new Status(
                0,
                properties.getProperty("project.status"),
                properties.getProperty("project.status"));

        project = new Project(
                0,
                properties.getProperty("project.name"),
                properties.getProperty("project.description"),
                projectStatus);

        Status subProjectStatus = new Status(
                0,
                properties.getProperty("subproject.status"),
                properties.getProperty("subproject.status"));

        subProject = new Project(
                0,
                properties.getProperty("subproject.name"),
                properties.getProperty("subproject.description"),
                subProjectStatus);
    }

    @Test
    public void createProjectTest() {

        JSONObject subProjectJson = new JSONObject();
        subProjectJson.put(Project.NAME_KEY, subProject.getName());

        JSONObject updSubJson = new JSONObject();
        updSubJson.put("project", subProjectJson);
        updSubJson.put("inherit_parent", true);

        ProjectResponse response = given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .body(project)
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().response().as(ProjectResponse.class);

        project = response.getProject();

        response = given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .body(subProject)
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().response().as(ProjectResponse.class);

        subProject = response.getProject();

        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .body(updSubJson.toString())
                .when().
                post("/projects/" + project.getId() + "/subprojects")
                .then().
                statusCode(204);

        //delete created projects to keep Mantis Clean
        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + project.getId())
                .then().
                statusCode(200);

        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + subProject.getId())
                .then().
                statusCode(200);
    }

    @Test
    public void deleteNonExistingProject() {
        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/9000")
                .then().
                //MantisBT returns status code 403 for Non-existing projects
                        statusCode(403).extract().statusLine();
    }

}
