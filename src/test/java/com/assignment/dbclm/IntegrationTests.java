package com.assignment.dbclm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import com.assignment.dbclm.entities.NACEDao;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTests {
    @LocalServerPort
    private int port;

    private String url = "http://localhost";
    NACEDao naceDao;

    private static TestRestTemplate restTemplate;

    @Autowired
    TestH2NACERepository testh2naceRepository;

    @BeforeAll
    static void init() {
        restTemplate = new TestRestTemplate();
    }

    @BeforeEach
    void setUp() {
        url = url.concat(":").concat(port + "/");
        naceDao = new NACEDao(110l, 2, "test", "test", "test", "test", "test", "test", "test", "test");
    }

    @Test
    @Sql(statements = " INSERT INTO nace (id,order_no,code) values (100,555,'A')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = " DELETE from nace where order_no = 555", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getNaceDetails() {
        NACEDao naceDao = restTemplate.getForObject(url + "/getNaceDetails/{order}", NACEDao.class, 555);
        assertEquals(naceDao.getCode(), testh2naceRepository.findByOrder(555l).get().getCode());
    }


    @Test
    @Sql(statements = " INSERT INTO nace (id,order_no,code) values (100,555,'A')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = " DELETE from nace where order_no = 555", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getNaceDetailsOfWrongOrder() {
        String res = restTemplate.getForObject(url + "/getNaceDetails/{order}", String.class, 556);
        assertEquals(res, "Order 556 not found");
    }
}
