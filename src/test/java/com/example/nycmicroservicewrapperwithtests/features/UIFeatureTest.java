package com.example.nycmicroservicewrapperwithtests.features;

import com.example.nycmicroservicewrapperwithtests.models.User;
import com.example.nycmicroservicewrapperwithtests.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UIFeatureTest {
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudFunctionalityForAUser() throws Exception {

        User firstUser = new User(
                "someone",
                "Ima",
                "Person"
        );
        firstUser = userRepository.save(firstUser);

        User secondUser = new User(
                "someone_else",
                "Someone",
                "Else"
        );
        secondUser = userRepository.save(secondUser);
        System.setProperty("selenide.browser", "Chrome");

//         Visit the UI in a browser
        open("http://localhost:3000/");

        // There should only be two users
        $(".column").should(appear);

        open("http://localhost:3000/myusers");
        $$(".users").shouldHave(size(2));

        $("#new-user-link").click();
        $(".column").should(appear);

        $("#view-users").click();
        $("body").shouldHave(text("someone_else"));
    }
}
