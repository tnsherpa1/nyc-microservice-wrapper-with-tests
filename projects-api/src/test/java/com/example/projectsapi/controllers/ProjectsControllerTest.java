package com.example.projectsapi.controllers;

import com.example.projectsapi.models.Project;
import com.example.projectsapi.repositories.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectsController.class)
public class ProjectsControllerTest {
    @Autowired
    private ObjectMapper jsonObjectMapper;

    @MockBean
    private ProjectRepository mockProjectRepository;

    @Autowired
    private MockMvc mockMvc;

    private Project newProject;

    @Before
    public void setUp() {
        Project firstProject = new Project(
                "water agency",
                "Karen1",
                "water work",
                "karen@wa.com",
                "1193393233",
                "aug 1st 2019"
        );
        Project secondProject = new Project(
                "fire agency",
                "Karen2",
                "fire work",
                "karen@wa.com",
                "1193393233",
                "aug 1st 2020"
        );
        Iterable<Project> mockProjects = Stream.of(firstProject, secondProject).collect(Collectors.toList());

        given(mockProjectRepository.findAll()).willReturn(mockProjects);
        given(mockProjectRepository.findOne(1L)).willReturn(firstProject);
        given(mockProjectRepository.findOne(4L)).willReturn(null);
        given(mockProjectRepository.save(newProject)).willReturn(newProject);
        newProject = new Project(
                "Forest agency",
                "sam",
                "forest work",
                "sam@np.com",
                "2393393233",
                "aug 1st 2021"
        );
        given(mockProjectRepository.save(newProject)).willReturn(newProject);
    }

    @Test
    public void findAllProjects_success_returnsStatusOK() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllProjects_success_returnAllProjectsAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllProjects_success_returnAgencyNameForEachProject() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].agencyName", is("water agency")));
    }

    @Test
    public void findAllProjects_success_returnContactNameForEachProject() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].contactName", is("Karen1") ));
    }

    @Test
    public void findProjectById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findProjectById_success_returnAgencyName() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.agencyName", is("water agency")));
    }

    @Test
    public void findProjectById_success_returnContactName() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.contactName", is("Karen1")));
    }

    @Test
    public void findProjectById_success_returnContactEmail() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.email", is("karen@wa.com")));
    }

    @Test
    public void findProjectById_failure_projectNotFoundReturnsErrorMessage() throws Exception {

        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().reason(containsString("Project with ID of 4 was not found!")));
    }

    @Test
    public void deleteProjectById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(delete("/1"))
                .andExpect(status().isOk());
        verify(mockProjectRepository, times(1)).delete(1L);

    }

    @Test
    public void createProject_success_returnsStatusOk() throws Exception {
        this.mockMvc
            .perform(
                    post("/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonObjectMapper.writeValueAsString(newProject))
            )
            .andExpect(status().isOk());
    }

    @Test
    public void createProject_success_returnsAgencyName() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newProject))
                )
                .andExpect(jsonPath("$.agencyName", is("Forest agency")));
    }


}
