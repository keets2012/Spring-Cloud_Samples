package com.blueskykong.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootTestApplicationTests {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void restExampleTest() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).isEqualTo("Hello World");
    }

    @Test
    public void exampleTest() {
        this.webClient.get().uri("/").exchange().expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello World");
    }

    @Test
    public void contextLoads() {
    }

}
