package hw5;

import hw5.Project.Project;
import hw5.Project.ProjectResponce;
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

    private Properties properties;
    private String baseUrl;
    private String token;
    private Status projectStatus;
    private Project project;
    private Status subProjectStatus;
    private Project subProject;


    @BeforeSuite
    public void setUp() throws IOException {
        FileInputStream propertiesFile = new FileInputStream("src/test/resources/hw5.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        baseUrl = properties.getProperty("mantis.url");
        token = properties.getProperty("mantis.token");

        projectStatus = new Status(
                0,
                properties.getProperty("project.status"),
                properties.getProperty("project.status"));

        project = new Project(
                0,
                properties.getProperty("project.name"),
                properties.getProperty("project.description"),
                projectStatus);

        subProjectStatus = new Status(
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

        JSONObject statusJson = new JSONObject();
        statusJson.put(Status.LABEL_KEY, projectStatus.getLabel());
        statusJson.put(Status.NAME_KEY, projectStatus.getName());

        JSONObject projectJson = new JSONObject();
        projectJson.put(Project.NAME_KEY, project.getName());
        projectJson.put(Project.DESCRIPTION_KEY, project.getDescription());
        projectJson.put(Project.STATUS_KEY, statusJson.toString());

        JSONObject subStatusJson = new JSONObject();
        subStatusJson.put(Status.LABEL_KEY, subProjectStatus.getLabel());
        subStatusJson.put(Status.NAME_KEY, subProjectStatus.getName());

        JSONObject subProjectJson = new JSONObject();
        subProjectJson.put(Project.NAME_KEY, subProject.getName());

        JSONObject addSubJson = new JSONObject();
        addSubJson.put("project", subProjectJson);
        addSubJson.put("inherit_parent", true);

        subProjectJson.put(Project.DESCRIPTION_KEY, subProject.getDescription());
        subProjectJson.put(Project.STATUS_KEY, subStatusJson.toString());


        ProjectResponce responce = given().
                baseUri(baseUrl).
                header(new Header("Authorization", token)).
                contentType(ContentType.JSON)
                .body(projectJson.toString())
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().response().as(ProjectResponce.class);

        project = responce.getProject();

        responce = given().
                baseUri(baseUrl).
                header(new Header("Authorization", token)).
                contentType(ContentType.JSON)
                .body(subProjectJson.toString())
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().response().as(ProjectResponce.class);

        subProject = responce.getProject();

        given().
                baseUri(baseUrl).
                header(new Header("Authorization", token)).
                contentType(ContentType.JSON)
                .body(addSubJson.toString())
                .when().
                        post("/projects/" + project.getId() + "/subprojects")
                .then().
                        statusCode(204);

        System.out.println(responce);

        //delete created projects to keep Mantis Clean
        given().
                baseUri(baseUrl).
                header(new Header("Authorization", token)).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + project.getId())
                .then().
                statusCode(200);

        given().
                baseUri(baseUrl).
                header(new Header("Authorization", token)).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + subProject.getId())
                .then().
                statusCode(200);
    }

    @Test
    public void deleteNonExistingProject() {
        System.out.println(
                given().
                        baseUri(baseUrl).
                        header(new Header("Authorization", token)).
                        contentType(ContentType.JSON)
                        .when().
                        delete("/projects/9000")
                        .then().
                        //MantisBT returns status code 403 for Non-existing projects
                                statusCode(403).extract().statusLine());
    }

}
