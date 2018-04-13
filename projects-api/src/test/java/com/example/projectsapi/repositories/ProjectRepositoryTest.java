package com.example.projectsapi.repositories;


import com.example.projectsapi.models.Project;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProjectRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Before
    public void setUp() {

        Project firstProject = new Project(
                "agency_name",
                "some_contact_name",
                "some_summary",
                "df@adf.com",
                "454545454",
                "12 12 18"
        );

        Project secondProject = new Project(
                "second_agency_name",
                "second_contact_name",
                "second_summary",
                "secondemail@adf.com",
                "1234567859",
                "12 12 19"
        );

        entityManager.persist(firstProject);
        entityManager.persist(secondProject);
        entityManager.flush();

    }

    @Test
    public void findAll_returnsAllProjectsFromDb() {
        Iterable<Project> projectsFromDb =  projectRepository.findAll();
        long size = projectsFromDb.spliterator().getExactSizeIfKnown();
        assertThat(size, is(2L));
    }

    @Test
    public void findAll_returnsProjectName() {
        Iterable<Project> projectsFromDb = projectRepository.findAll();
        String agencyname = Iterables.get(projectsFromDb,0).getAgencyName();
        assertThat(agencyname,is("agency_name" ));
    }

    @Test
    public void findAll_returnsFirstName() {
        Iterable<Project> projectsFromDb = projectRepository.findAll();
        String contactname = Iterables.get(projectsFromDb,0).getContactName();
        assertThat(contactname,is("some_contact_name" ));
    }

    @Test
    public void findAll_returnsLastName() {
        Iterable<Project> projectsFromDb = projectRepository.findAll();
        String email = Iterables.get(projectsFromDb,1).getEmail();
        assertThat(email,is("secondemail@adf.com" ));
    }
}
