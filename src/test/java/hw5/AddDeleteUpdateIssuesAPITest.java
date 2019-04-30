package hw5;

import hw5.Issue.*;
import hw5.Project.Project;
import hw5.Project.ProjectResponce;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class AddDeleteUpdateIssuesAPITest {

    private Properties properties;
    private String baseUrl;
    private Header header;
    private Project project;
    private Attachments firstFile;
    private Attachments secondFile;
    private IssueField category;
    private IssueFieldWithLabel severity;
    private IssueFieldWithLabel priority;
    private Status status;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        FileInputStream propertiesFile = new FileInputStream("src/test/resources/hw5.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        baseUrl = properties.getProperty("mantis.url");
        String token = properties.getProperty("mantis.token");
        header = new Header("Authorization", token);

        hw5.Project.Status projectStatus = new hw5.Project.Status(
                0,
                properties.getProperty("project.status"),
                properties.getProperty("project.status"));

        project = new Project(
                0,
                properties.getProperty("project.name"),
                properties.getProperty("project.description"),
                projectStatus);

        firstFile =
                new Attachments(properties.getProperty("attachments.first_name"),
                        properties.getProperty("attachments.first_content"));
        secondFile =
                new Attachments(properties.getProperty("attachments.second_name"),
                        properties.getProperty("attachments.second_content"));

        category =
                new IssueField(Integer.parseInt(properties.getProperty("category.id")),
                        properties.getProperty("category.name"));

        severity =
                new IssueFieldWithLabel(Integer.parseInt(properties.getProperty("severity.id")),
                        properties.getProperty("severity.name"),
                        properties.getProperty("severity.name"));

        priority =
                new IssueFieldWithLabel(Integer.parseInt(properties.getProperty("priority.id")),
                        properties.getProperty("priority.name"),
                        properties.getProperty("priority.name"));

        status =
                new Status(Integer.parseInt(properties.getProperty("status.id")),
                        properties.getProperty("status.name"),
                        properties.getProperty("status.name"),
                        properties.getProperty("status.color"));
    }

    @Test
    public void createUpdateDeleteIssue() {

        ProjectResponce projectResponce = given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .body(project)
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().response().as(ProjectResponce.class);

        project = projectResponce.getProject();

        Issue issue =
                new Issue(
                        0,
                        properties.getProperty("issue.summary"),
                        properties.getProperty("issue.description"),
                        project,
                        category,
                        severity,
                        priority,
                        status
                );
        IssueWithAttachments issueWithAttachments = new IssueWithAttachments(
                issue,
                new Attachments[]{firstFile, secondFile}
        );


        IssueResponse issueResponse =
                given().
                        baseUri(baseUrl).
                        header(header).
                        contentType(ContentType.JSON).
                        body(issueWithAttachments)
                        .when().
                        post("/issues/")
                        .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                        .extract().response().as(IssueResponse.class);

        issue = issueResponse.getIssue();

        JSONObject issueJson = new JSONObject();
        issueJson.put(Issue.SUMMARY_KEY, properties.getProperty("issue.updated_summary"));

        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON).
                body(issueJson.toString())
                .when().
                patch("/issues/" + issue.getId())
                .then().
                contentType(ContentType.JSON).
                statusCode(200);

        //delete created project and issues to keep Mantis Clean
        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + project.getId())
                .then().
                statusCode(200);
    }

    @Test
    public void updateNonExistingIssue() {

        JSONObject issueJson = new JSONObject();
        issueJson.put(Issue.SUMMARY_KEY, properties.getProperty("issue.updated_summary"));

        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON).
                body(issueJson.toString())
                .when().
                patch("/issues/90000")
                .then().
                contentType(ContentType.JSON).
                statusCode(404);
    }

}
