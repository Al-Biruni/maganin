package com.digitalraider.maganin.web.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import com.digitalraider.maganin.TestUtil;
import com.digitalraider.maganin.domain.Consultancy;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import liquibase.Liquibase;
import io.quarkus.liquibase.LiquibaseFactory;
import org.junit.jupiter.api.*;

import javax.inject.Inject;

import java.util.List;
    import java.time.Instant;
import java.time.temporal.ChronoUnit;

@QuarkusTest
public class ConsultancyResourceTest {

    private static final TypeRef<Consultancy> ENTITY_TYPE = new TypeRef<>() {
    };

    private static final TypeRef<List<Consultancy>> LIST_OF_ENTITY_TYPE = new TypeRef<>() {
    };

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochSecond(0L).truncatedTo(ChronoUnit.SECONDS);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    private static final String DEFAULT_AGE = "AAAAAAAAAA";
    private static final String UPDATED_AGE = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_RELIGION = "AAAAAAAAAA";
    private static final String UPDATED_RELIGION = "BBBBBBBBBB";

    private static final String DEFAULT_REL_2 = "AAAAAAAAAA";
    private static final String UPDATED_REL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_EDU = "AAAAAAAAAA";
    private static final String UPDATED_EDU = "BBBBBBBBBB";

    private static final String DEFAULT_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_ECON = "AAAAAAAAAA";
    private static final String UPDATED_ECON = "BBBBBBBBBB";

    private static final String DEFAULT_HOP = "AAAAAAAAAA";
    private static final String UPDATED_HOP = "BBBBBBBBBB";

    private static final String DEFAULT_JOB = "AAAAAAAAAA";
    private static final String UPDATED_JOB = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION = "BBBBBBBBBB";

    private static final String DEFAULT_ANSWER = "AAAAAAAAAA";
    private static final String UPDATED_ANSWER = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_CONSULTANT_ID = 1;
    private static final Integer UPDATED_CONSULTANT_ID = 2;

    private static final Boolean DEFAULT_SHOW = false;
    private static final Boolean UPDATED_SHOW = true;

    private static final Boolean DEFAULT_PAID = false;
    private static final Boolean UPDATED_PAID = true;

    private static final Integer DEFAULT_IMPRESSIONS = 1;
    private static final Integer UPDATED_IMPRESSIONS = 2;



    String adminToken;

    Consultancy consultancy;

    @Inject
    LiquibaseFactory liquibaseFactory;

    @BeforeAll
    static void jsonMapper() {
        RestAssured.config =
            RestAssured.config().objectMapperConfig(objectMapperConfig().defaultObjectMapper(TestUtil.jsonbObjectMapper()));
    }

    @BeforeEach
    public void authenticateAdmin() {
        this.adminToken = TestUtil.getAdminToken();
    }

    @BeforeEach
    public void databaseFixture() {
        try (Liquibase liquibase = liquibaseFactory.createLiquibase()) {
            liquibase.dropAll();
            liquibase.validate();
            liquibase.update(liquibaseFactory.createContexts(), liquibaseFactory.createLabels());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Consultancy createEntity() {
        var consultancy = new Consultancy();
        consultancy.userId = DEFAULT_USER_ID;
        consultancy.name = DEFAULT_NAME;
        consultancy.date = DEFAULT_DATE;
        consultancy.age = DEFAULT_AGE;
        consultancy.gender = DEFAULT_GENDER;
        consultancy.religion = DEFAULT_RELIGION;
        consultancy.rel2 = DEFAULT_REL_2;
        consultancy.edu = DEFAULT_EDU;
        consultancy.social = DEFAULT_SOCIAL;
        consultancy.econ = DEFAULT_ECON;
        consultancy.hop = DEFAULT_HOP;
        consultancy.job = DEFAULT_JOB;
        consultancy.country = DEFAULT_COUNTRY;
        consultancy.origin = DEFAULT_ORIGIN;
        consultancy.email = DEFAULT_EMAIL;
        consultancy.title = DEFAULT_TITLE;
        consultancy.question = DEFAULT_QUESTION;
        consultancy.answer = DEFAULT_ANSWER;
        consultancy.doctor = DEFAULT_DOCTOR;
        consultancy.consultantId = DEFAULT_CONSULTANT_ID;
        consultancy.show = DEFAULT_SHOW;
        consultancy.paid = DEFAULT_PAID;
        consultancy.impressions = DEFAULT_IMPRESSIONS;
        return consultancy;
    }

    @BeforeEach
    public void initTest() {
        consultancy = createEntity();
    }

    @Test
    public void createConsultancy() {
        var databaseSizeBeforeCreate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Create the Consultancy
        consultancy = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(consultancy)
            .when()
            .post("/api/consultancies")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        // Validate the Consultancy in the database
        var consultancyList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(consultancyList).hasSize(databaseSizeBeforeCreate + 1);
        var testConsultancy = consultancyList.stream().filter(it -> consultancy.id.equals(it.id)).findFirst().get();
        assertThat(testConsultancy.userId).isEqualTo(DEFAULT_USER_ID);
        assertThat(testConsultancy.name).isEqualTo(DEFAULT_NAME);
        assertThat(testConsultancy.date).isEqualTo(DEFAULT_DATE);
        assertThat(testConsultancy.age).isEqualTo(DEFAULT_AGE);
        assertThat(testConsultancy.gender).isEqualTo(DEFAULT_GENDER);
        assertThat(testConsultancy.religion).isEqualTo(DEFAULT_RELIGION);
        assertThat(testConsultancy.rel2).isEqualTo(DEFAULT_REL_2);
        assertThat(testConsultancy.edu).isEqualTo(DEFAULT_EDU);
        assertThat(testConsultancy.social).isEqualTo(DEFAULT_SOCIAL);
        assertThat(testConsultancy.econ).isEqualTo(DEFAULT_ECON);
        assertThat(testConsultancy.hop).isEqualTo(DEFAULT_HOP);
        assertThat(testConsultancy.job).isEqualTo(DEFAULT_JOB);
        assertThat(testConsultancy.country).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testConsultancy.origin).isEqualTo(DEFAULT_ORIGIN);
        assertThat(testConsultancy.email).isEqualTo(DEFAULT_EMAIL);
        assertThat(testConsultancy.title).isEqualTo(DEFAULT_TITLE);
        assertThat(testConsultancy.question).isEqualTo(DEFAULT_QUESTION);
        assertThat(testConsultancy.answer).isEqualTo(DEFAULT_ANSWER);
        assertThat(testConsultancy.doctor).isEqualTo(DEFAULT_DOCTOR);
        assertThat(testConsultancy.consultantId).isEqualTo(DEFAULT_CONSULTANT_ID);
        assertThat(testConsultancy.show).isEqualTo(DEFAULT_SHOW);
        assertThat(testConsultancy.paid).isEqualTo(DEFAULT_PAID);
        assertThat(testConsultancy.impressions).isEqualTo(DEFAULT_IMPRESSIONS);
    }

    @Test
    public void createConsultancyWithExistingId() {
        var databaseSizeBeforeCreate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Create the Consultancy with an existing ID
        consultancy.id = 1L;

        // An entity with an existing ID cannot be created, so this API call must fail
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(consultancy)
            .when()
            .post("/api/consultancies")
            .then()
            .statusCode(BAD_REQUEST.getStatusCode());

        // Validate the Consultancy in the database
        var consultancyList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(consultancyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void updateConsultancy() {
        // Initialize the database
        consultancy = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(consultancy)
            .when()
            .post("/api/consultancies")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var databaseSizeBeforeUpdate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Get the consultancy
        var updatedConsultancy = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies/{id}", consultancy.id)
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().body().as(ENTITY_TYPE);

        // Update the consultancy
        updatedConsultancy.userId = UPDATED_USER_ID;
        updatedConsultancy.name = UPDATED_NAME;
        updatedConsultancy.date = UPDATED_DATE;
        updatedConsultancy.age = UPDATED_AGE;
        updatedConsultancy.gender = UPDATED_GENDER;
        updatedConsultancy.religion = UPDATED_RELIGION;
        updatedConsultancy.rel2 = UPDATED_REL_2;
        updatedConsultancy.edu = UPDATED_EDU;
        updatedConsultancy.social = UPDATED_SOCIAL;
        updatedConsultancy.econ = UPDATED_ECON;
        updatedConsultancy.hop = UPDATED_HOP;
        updatedConsultancy.job = UPDATED_JOB;
        updatedConsultancy.country = UPDATED_COUNTRY;
        updatedConsultancy.origin = UPDATED_ORIGIN;
        updatedConsultancy.email = UPDATED_EMAIL;
        updatedConsultancy.title = UPDATED_TITLE;
        updatedConsultancy.question = UPDATED_QUESTION;
        updatedConsultancy.answer = UPDATED_ANSWER;
        updatedConsultancy.doctor = UPDATED_DOCTOR;
        updatedConsultancy.consultantId = UPDATED_CONSULTANT_ID;
        updatedConsultancy.show = UPDATED_SHOW;
        updatedConsultancy.paid = UPDATED_PAID;
        updatedConsultancy.impressions = UPDATED_IMPRESSIONS;

        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(updatedConsultancy)
            .when()
            .put("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode());

        // Validate the Consultancy in the database
        var consultancyList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(consultancyList).hasSize(databaseSizeBeforeUpdate);
        var testConsultancy = consultancyList.stream().filter(it -> updatedConsultancy.id.equals(it.id)).findFirst().get();
        assertThat(testConsultancy.userId).isEqualTo(UPDATED_USER_ID);
        assertThat(testConsultancy.name).isEqualTo(UPDATED_NAME);
        assertThat(testConsultancy.date).isEqualTo(UPDATED_DATE);
        assertThat(testConsultancy.age).isEqualTo(UPDATED_AGE);
        assertThat(testConsultancy.gender).isEqualTo(UPDATED_GENDER);
        assertThat(testConsultancy.religion).isEqualTo(UPDATED_RELIGION);
        assertThat(testConsultancy.rel2).isEqualTo(UPDATED_REL_2);
        assertThat(testConsultancy.edu).isEqualTo(UPDATED_EDU);
        assertThat(testConsultancy.social).isEqualTo(UPDATED_SOCIAL);
        assertThat(testConsultancy.econ).isEqualTo(UPDATED_ECON);
        assertThat(testConsultancy.hop).isEqualTo(UPDATED_HOP);
        assertThat(testConsultancy.job).isEqualTo(UPDATED_JOB);
        assertThat(testConsultancy.country).isEqualTo(UPDATED_COUNTRY);
        assertThat(testConsultancy.origin).isEqualTo(UPDATED_ORIGIN);
        assertThat(testConsultancy.email).isEqualTo(UPDATED_EMAIL);
        assertThat(testConsultancy.title).isEqualTo(UPDATED_TITLE);
        assertThat(testConsultancy.question).isEqualTo(UPDATED_QUESTION);
        assertThat(testConsultancy.answer).isEqualTo(UPDATED_ANSWER);
        assertThat(testConsultancy.doctor).isEqualTo(UPDATED_DOCTOR);
        assertThat(testConsultancy.consultantId).isEqualTo(UPDATED_CONSULTANT_ID);
        assertThat(testConsultancy.show).isEqualTo(UPDATED_SHOW);
        assertThat(testConsultancy.paid).isEqualTo(UPDATED_PAID);
        assertThat(testConsultancy.impressions).isEqualTo(UPDATED_IMPRESSIONS);
    }

    @Test
    public void updateNonExistingConsultancy() {
        var databaseSizeBeforeUpdate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(consultancy)
            .when()
            .put("/api/consultancies")
            .then()
            .statusCode(BAD_REQUEST.getStatusCode());

        // Validate the Consultancy in the database
        var consultancyList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(consultancyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteConsultancy() {
        // Initialize the database
        consultancy = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(consultancy)
            .when()
            .post("/api/consultancies")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var databaseSizeBeforeDelete = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Delete the consultancy
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .delete("/api/consultancies/{id}", consultancy.id)
            .then()
            .statusCode(NO_CONTENT.getStatusCode());

        // Validate the database contains one less item
        var consultancyList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(consultancyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void getAllConsultancies() {
        // Initialize the database
        consultancy = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(consultancy)
            .when()
            .post("/api/consultancies")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        // Get all the consultancyList
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies?sort=id,desc")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .body("id", hasItem(consultancy.id.intValue()))
            .body("userId", hasItem(DEFAULT_USER_ID.intValue()))            .body("name", hasItem(DEFAULT_NAME))            .body("date", hasItem(TestUtil.formatDateTime(DEFAULT_DATE)))            .body("age", hasItem(DEFAULT_AGE))            .body("gender", hasItem(DEFAULT_GENDER))            .body("religion", hasItem(DEFAULT_RELIGION))            .body("rel2", hasItem(DEFAULT_REL_2))            .body("edu", hasItem(DEFAULT_EDU))            .body("social", hasItem(DEFAULT_SOCIAL))            .body("econ", hasItem(DEFAULT_ECON))            .body("hop", hasItem(DEFAULT_HOP))            .body("job", hasItem(DEFAULT_JOB))            .body("country", hasItem(DEFAULT_COUNTRY))            .body("origin", hasItem(DEFAULT_ORIGIN))            .body("email", hasItem(DEFAULT_EMAIL))            .body("title", hasItem(DEFAULT_TITLE))            .body("question", hasItem(DEFAULT_QUESTION))            .body("answer", hasItem(DEFAULT_ANSWER))            .body("doctor", hasItem(DEFAULT_DOCTOR))            .body("consultantId", hasItem(DEFAULT_CONSULTANT_ID.intValue()))            .body("show", hasItem(DEFAULT_SHOW.booleanValue()))            .body("paid", hasItem(DEFAULT_PAID.booleanValue()))            .body("impressions", hasItem(DEFAULT_IMPRESSIONS.intValue()));
    }

    @Test
    public void getConsultancy() {
        // Initialize the database
        consultancy = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(consultancy)
            .when()
            .post("/api/consultancies")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var response = // Get the consultancy
            given()
                .auth()
                .preemptive()
                .oauth2(adminToken)
                .accept(APPLICATION_JSON)
                .when()
                .get("/api/consultancies/{id}", consultancy.id)
                .then()
                .statusCode(OK.getStatusCode())
                .contentType(APPLICATION_JSON)
                .extract().as(ENTITY_TYPE);

        // Get the consultancy
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies/{id}", consultancy.id)
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .body("id", is(consultancy.id.intValue()))
            
                .body("userId", is(DEFAULT_USER_ID.intValue()))
                .body("name", is(DEFAULT_NAME))
                .body("date", is(TestUtil.formatDateTime(DEFAULT_DATE)))
                .body("age", is(DEFAULT_AGE))
                .body("gender", is(DEFAULT_GENDER))
                .body("religion", is(DEFAULT_RELIGION))
                .body("rel2", is(DEFAULT_REL_2))
                .body("edu", is(DEFAULT_EDU))
                .body("social", is(DEFAULT_SOCIAL))
                .body("econ", is(DEFAULT_ECON))
                .body("hop", is(DEFAULT_HOP))
                .body("job", is(DEFAULT_JOB))
                .body("country", is(DEFAULT_COUNTRY))
                .body("origin", is(DEFAULT_ORIGIN))
                .body("email", is(DEFAULT_EMAIL))
                .body("title", is(DEFAULT_TITLE))
                .body("question", is(DEFAULT_QUESTION))
                .body("answer", is(DEFAULT_ANSWER))
                .body("doctor", is(DEFAULT_DOCTOR))
                .body("consultantId", is(DEFAULT_CONSULTANT_ID.intValue()))
                .body("show", is(DEFAULT_SHOW.booleanValue()))
                .body("paid", is(DEFAULT_PAID.booleanValue()))
                .body("impressions", is(DEFAULT_IMPRESSIONS.intValue()));
    }

    @Test
    public void getNonExistingConsultancy() {
        // Get the consultancy
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/consultancies/{id}", Long.MAX_VALUE)
            .then()
            .statusCode(NOT_FOUND.getStatusCode());
    }
}
