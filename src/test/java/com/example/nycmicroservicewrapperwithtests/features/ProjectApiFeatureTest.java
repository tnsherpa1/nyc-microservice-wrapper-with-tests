package com.example.nycmicroservicewrapperwithtests.features;

import com.example.nycmicroservicewrapperwithtests.models.Project;
import com.example.nycmicroservicewrapperwithtests.repositories.ProjectRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProjectApiFeatureTest {
    @Autowired
    private ProjectRepository projectRepository;

    @Before
    public void setUp(){
        projectRepository.deleteAll();
    }

    @After
    public void tearDown(){
        projectRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudForAProject() throws Exception {
        Project p1 = new Project(
                "housing agency",
                "Jen",
                "roof work",
                "jen@ha.com",
                "5551112224",
                "aug 1st 2018");

        Project p2 = new Project(
                "landscape agency",
                "Karen",
                "Garden work",
                "karen@la.com",
                "9993393233",
                "aug 1st 2012");

        Stream.of(p1, p2)
                .forEach(project -> {
                    projectRepository.save(project);
                });

        // Test get all Projects
        when()
                .get("http://localhost:8080/projects/")
                .then()
                .statusCode(is(200))
                .and().body(containsString("roof"))
                .and().body(containsString("Jen"));

        given()
                .contentType(JSON)
                .and().body(p2)
                .when()
                .post("http://localhost:8080/projects")
                .then()
                .statusCode(is(200))
                .and().body(containsString("karen"));

        // Test finding one project by ID
        when()
                .get("http://localhost:8080/projects/" + p2.getId())
                .then()
                .statusCode(is(200))
                .and().body(containsString("Garden"))
                .and().body(containsString("Karen"));

        // Test updating a project
        p2.setAgencyName("changed_agency");

        given()
                .contentType(JSON)
                .and().body(p2)
                .when()
                .patch("http://localhost:8080/projects/" + p2.getId())
                .then()
                .statusCode(is(200))
                .and().body(containsString("changed_name"));

        // Test deleting a project
        when()
                .delete("http://localhost:8080/projects/" + p2.getId())
                .then()
                .statusCode(is(200));
    }

}
