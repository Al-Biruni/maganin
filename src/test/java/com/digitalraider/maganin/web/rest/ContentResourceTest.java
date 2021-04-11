package com.digitalraider.maganin.web.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import com.digitalraider.maganin.TestUtil;
import com.digitalraider.maganin.domain.Content;
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
public class ContentResourceTest {

    private static final TypeRef<Content> ENTITY_TYPE = new TypeRef<>() {
    };

    private static final TypeRef<List<Content>> LIST_OF_ENTITY_TYPE = new TypeRef<>() {
    };

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final String DEFAULT_RELATED_URL = "AAAAAAAAAA";
    private static final String UPDATED_RELATED_URL = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_ADDED = Instant.ofEpochSecond(0L).truncatedTo(ChronoUnit.SECONDS);
    private static final Instant UPDATED_DATE_ADDED = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    private static final String DEFAULT_SHORT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_LONG_DESC = "AAAAAAAAAA";
    private static final String UPDATED_LONG_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY = "BBBBBBBBBB";

    private static final Integer DEFAULT_ACCESS_LEVEL = 1;
    private static final Integer UPDATED_ACCESS_LEVEL = 2;

    private static final Instant DEFAULT_EXPIRE = Instant.ofEpochSecond(0L).truncatedTo(ChronoUnit.SECONDS);
    private static final Instant UPDATED_EXPIRE = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    private static final Integer DEFAULT_IMPRESSIONS = 1;
    private static final Integer UPDATED_IMPRESSIONS = 2;

    private static final Integer DEFAULT_CLICK_THRUS = 1;
    private static final Integer UPDATED_CLICK_THRUS = 2;

    private static final Integer DEFAULT_AVG_RATING = 1;
    private static final Integer UPDATED_AVG_RATING = 2;

    private static final Integer DEFAULT_RATINGS = 1;
    private static final Integer UPDATED_RATINGS = 2;

    private static final String DEFAULT_THUMBNAIL = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_1 = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_1 = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_END = Instant.ofEpochSecond(0L).truncatedTo(ChronoUnit.SECONDS);
    private static final Instant UPDATED_DATE_END = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    private static final String DEFAULT_KEYWORDS = "AAAAAAAAAA";
    private static final String UPDATED_KEYWORDS = "BBBBBBBBBB";

    private static final String DEFAULT_CREATIONS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CREATIONS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_WAZN = "AAAAAAAAAA";
    private static final String UPDATED_WAZN = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_LAST_MOD = Instant.ofEpochSecond(0L).truncatedTo(ChronoUnit.SECONDS);
    private static final Instant UPDATED_DATE_LAST_MOD = Instant.now().truncatedTo(ChronoUnit.SECONDS);



    String adminToken;

    Content content;

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
    public static Content createEntity() {
        var content = new Content();
        content.userId = DEFAULT_USER_ID;
        content.title = DEFAULT_TITLE;
        content.author = DEFAULT_AUTHOR;
        content.relatedURL = DEFAULT_RELATED_URL;
        content.dateAdded = DEFAULT_DATE_ADDED;
        content.shortDesc = DEFAULT_SHORT_DESC;
        content.longDesc = DEFAULT_LONG_DESC;
        content.display = DEFAULT_DISPLAY;
        content.accessLevel = DEFAULT_ACCESS_LEVEL;
        content.expire = DEFAULT_EXPIRE;
        content.priority = DEFAULT_PRIORITY;
        content.impressions = DEFAULT_IMPRESSIONS;
        content.clickThrus = DEFAULT_CLICK_THRUS;
        content.avgRating = DEFAULT_AVG_RATING;
        content.ratings = DEFAULT_RATINGS;
        content.thumbnail = DEFAULT_THUMBNAIL;
        content.dateEnd = DEFAULT_DATE_END;
        content.keywords = DEFAULT_KEYWORDS;
        content.creationsType = DEFAULT_CREATIONS_TYPE;
        content.wazn = DEFAULT_WAZN;
        content.country = DEFAULT_COUNTRY;
        content.dateLastMod = DEFAULT_DATE_LAST_MOD;
        return content;
    }

    @BeforeEach
    public void initTest() {
        content = createEntity();
    }

    @Test
    public void createContent() {
        var databaseSizeBeforeCreate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Create the Content
        content = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(content)
            .when()
            .post("/api/contents")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        // Validate the Content in the database
        var contentList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(contentList).hasSize(databaseSizeBeforeCreate + 1);
        var testContent = contentList.stream().filter(it -> content.id.equals(it.id)).findFirst().get();
        assertThat(testContent.userId).isEqualTo(DEFAULT_USER_ID);
        assertThat(testContent.title).isEqualTo(DEFAULT_TITLE);
        assertThat(testContent.author).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testContent.relatedURL).isEqualTo(DEFAULT_RELATED_URL);
        assertThat(testContent.dateAdded).isEqualTo(DEFAULT_DATE_ADDED);
        assertThat(testContent.shortDesc).isEqualTo(DEFAULT_SHORT_DESC);
        assertThat(testContent.longDesc).isEqualTo(DEFAULT_LONG_DESC);
        assertThat(testContent.display).isEqualTo(DEFAULT_DISPLAY);
        assertThat(testContent.accessLevel).isEqualTo(DEFAULT_ACCESS_LEVEL);
        assertThat(testContent.expire).isEqualTo(DEFAULT_EXPIRE);
        assertThat(testContent.priority).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testContent.impressions).isEqualTo(DEFAULT_IMPRESSIONS);
        assertThat(testContent.clickThrus).isEqualTo(DEFAULT_CLICK_THRUS);
        assertThat(testContent.avgRating).isEqualTo(DEFAULT_AVG_RATING);
        assertThat(testContent.ratings).isEqualTo(DEFAULT_RATINGS);
        assertThat(testContent.thumbnail).isEqualTo(DEFAULT_THUMBNAIL);
        assertThat(testContent.dateEnd).isEqualTo(DEFAULT_DATE_END);
        assertThat(testContent.keywords).isEqualTo(DEFAULT_KEYWORDS);
        assertThat(testContent.creationsType).isEqualTo(DEFAULT_CREATIONS_TYPE);
        assertThat(testContent.wazn).isEqualTo(DEFAULT_WAZN);
        assertThat(testContent.country).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testContent.dateLastMod).isEqualTo(DEFAULT_DATE_LAST_MOD);
    }

    @Test
    public void createContentWithExistingId() {
        var databaseSizeBeforeCreate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Create the Content with an existing ID
        content.id = 1L;

        // An entity with an existing ID cannot be created, so this API call must fail
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(content)
            .when()
            .post("/api/contents")
            .then()
            .statusCode(BAD_REQUEST.getStatusCode());

        // Validate the Content in the database
        var contentList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(contentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void updateContent() {
        // Initialize the database
        content = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(content)
            .when()
            .post("/api/contents")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var databaseSizeBeforeUpdate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Get the content
        var updatedContent = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents/{id}", content.id)
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().body().as(ENTITY_TYPE);

        // Update the content
        updatedContent.userId = UPDATED_USER_ID;
        updatedContent.title = UPDATED_TITLE;
        updatedContent.author = UPDATED_AUTHOR;
        updatedContent.relatedURL = UPDATED_RELATED_URL;
        updatedContent.dateAdded = UPDATED_DATE_ADDED;
        updatedContent.shortDesc = UPDATED_SHORT_DESC;
        updatedContent.longDesc = UPDATED_LONG_DESC;
        updatedContent.display = UPDATED_DISPLAY;
        updatedContent.accessLevel = UPDATED_ACCESS_LEVEL;
        updatedContent.expire = UPDATED_EXPIRE;
        updatedContent.priority = UPDATED_PRIORITY;
        updatedContent.impressions = UPDATED_IMPRESSIONS;
        updatedContent.clickThrus = UPDATED_CLICK_THRUS;
        updatedContent.avgRating = UPDATED_AVG_RATING;
        updatedContent.ratings = UPDATED_RATINGS;
        updatedContent.thumbnail = UPDATED_THUMBNAIL;
        updatedContent.dateEnd = UPDATED_DATE_END;
        updatedContent.keywords = UPDATED_KEYWORDS;
        updatedContent.creationsType = UPDATED_CREATIONS_TYPE;
        updatedContent.wazn = UPDATED_WAZN;
        updatedContent.country = UPDATED_COUNTRY;
        updatedContent.dateLastMod = UPDATED_DATE_LAST_MOD;

        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(updatedContent)
            .when()
            .put("/api/contents")
            .then()
            .statusCode(OK.getStatusCode());

        // Validate the Content in the database
        var contentList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
        var testContent = contentList.stream().filter(it -> updatedContent.id.equals(it.id)).findFirst().get();
        assertThat(testContent.userId).isEqualTo(UPDATED_USER_ID);
        assertThat(testContent.title).isEqualTo(UPDATED_TITLE);
        assertThat(testContent.author).isEqualTo(UPDATED_AUTHOR);
        assertThat(testContent.relatedURL).isEqualTo(UPDATED_RELATED_URL);
        assertThat(testContent.dateAdded).isEqualTo(UPDATED_DATE_ADDED);
        assertThat(testContent.shortDesc).isEqualTo(UPDATED_SHORT_DESC);
        assertThat(testContent.longDesc).isEqualTo(UPDATED_LONG_DESC);
        assertThat(testContent.display).isEqualTo(UPDATED_DISPLAY);
        assertThat(testContent.accessLevel).isEqualTo(UPDATED_ACCESS_LEVEL);
        assertThat(testContent.expire).isEqualTo(UPDATED_EXPIRE);
        assertThat(testContent.priority).isEqualTo(UPDATED_PRIORITY);
        assertThat(testContent.impressions).isEqualTo(UPDATED_IMPRESSIONS);
        assertThat(testContent.clickThrus).isEqualTo(UPDATED_CLICK_THRUS);
        assertThat(testContent.avgRating).isEqualTo(UPDATED_AVG_RATING);
        assertThat(testContent.ratings).isEqualTo(UPDATED_RATINGS);
        assertThat(testContent.thumbnail).isEqualTo(UPDATED_THUMBNAIL);
        assertThat(testContent.dateEnd).isEqualTo(UPDATED_DATE_END);
        assertThat(testContent.keywords).isEqualTo(UPDATED_KEYWORDS);
        assertThat(testContent.creationsType).isEqualTo(UPDATED_CREATIONS_TYPE);
        assertThat(testContent.wazn).isEqualTo(UPDATED_WAZN);
        assertThat(testContent.country).isEqualTo(UPDATED_COUNTRY);
        assertThat(testContent.dateLastMod).isEqualTo(UPDATED_DATE_LAST_MOD);
    }

    @Test
    public void updateNonExistingContent() {
        var databaseSizeBeforeUpdate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents")
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
            .body(content)
            .when()
            .put("/api/contents")
            .then()
            .statusCode(BAD_REQUEST.getStatusCode());

        // Validate the Content in the database
        var contentList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteContent() {
        // Initialize the database
        content = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(content)
            .when()
            .post("/api/contents")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var databaseSizeBeforeDelete = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Delete the content
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .delete("/api/contents/{id}", content.id)
            .then()
            .statusCode(NO_CONTENT.getStatusCode());

        // Validate the database contains one less item
        var contentList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(contentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void getAllContents() {
        // Initialize the database
        content = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(content)
            .when()
            .post("/api/contents")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        // Get all the contentList
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents?sort=id,desc")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .body("id", hasItem(content.id.intValue()))
            .body("userId", hasItem(DEFAULT_USER_ID.intValue()))            .body("title", hasItem(DEFAULT_TITLE))            .body("author", hasItem(DEFAULT_AUTHOR))            .body("relatedURL", hasItem(DEFAULT_RELATED_URL))            .body("dateAdded", hasItem(TestUtil.formatDateTime(DEFAULT_DATE_ADDED)))            .body("shortDesc", hasItem(DEFAULT_SHORT_DESC))            .body("longDesc", hasItem(DEFAULT_LONG_DESC))            .body("display", hasItem(DEFAULT_DISPLAY))            .body("accessLevel", hasItem(DEFAULT_ACCESS_LEVEL.intValue()))            .body("expire", hasItem(TestUtil.formatDateTime(DEFAULT_EXPIRE)))            .body("priority", hasItem(DEFAULT_PRIORITY.intValue()))            .body("impressions", hasItem(DEFAULT_IMPRESSIONS.intValue()))            .body("clickThrus", hasItem(DEFAULT_CLICK_THRUS.intValue()))            .body("avgRating", hasItem(DEFAULT_AVG_RATING.intValue()))            .body("ratings", hasItem(DEFAULT_RATINGS.intValue()))            .body("thumbnail", hasItem(DEFAULT_THUMBNAIL))            .body("image1", hasItem(DEFAULT_IMAGE_1))            .body("dateEnd", hasItem(TestUtil.formatDateTime(DEFAULT_DATE_END)))            .body("keywords", hasItem(DEFAULT_KEYWORDS))            .body("creationsType", hasItem(DEFAULT_CREATIONS_TYPE))            .body("wazn", hasItem(DEFAULT_WAZN))            .body("country", hasItem(DEFAULT_COUNTRY))            .body("dateLastMod", hasItem(TestUtil.formatDateTime(DEFAULT_DATE_LAST_MOD)));
    }

    @Test
    public void getContent() {
        // Initialize the database
        content = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(content)
            .when()
            .post("/api/contents")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var response = // Get the content
            given()
                .auth()
                .preemptive()
                .oauth2(adminToken)
                .accept(APPLICATION_JSON)
                .when()
                .get("/api/contents/{id}", content.id)
                .then()
                .statusCode(OK.getStatusCode())
                .contentType(APPLICATION_JSON)
                .extract().as(ENTITY_TYPE);

        // Get the content
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents/{id}", content.id)
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .body("id", is(content.id.intValue()))

                .body("userId", is(DEFAULT_USER_ID.intValue()))
                .body("title", is(DEFAULT_TITLE))
                .body("author", is(DEFAULT_AUTHOR))
                .body("relatedURL", is(DEFAULT_RELATED_URL))
                .body("dateAdded", is(TestUtil.formatDateTime(DEFAULT_DATE_ADDED)))
                .body("shortDesc", is(DEFAULT_SHORT_DESC))
                .body("longDesc", is(DEFAULT_LONG_DESC))
                .body("display", is(DEFAULT_DISPLAY))
                .body("accessLevel", is(DEFAULT_ACCESS_LEVEL.intValue()))
                .body("expire", is(TestUtil.formatDateTime(DEFAULT_EXPIRE)))
                .body("priority", is(DEFAULT_PRIORITY.intValue()))
                .body("impressions", is(DEFAULT_IMPRESSIONS.intValue()))
                .body("clickThrus", is(DEFAULT_CLICK_THRUS.intValue()))
                .body("avgRating", is(DEFAULT_AVG_RATING.intValue()))
                .body("ratings", is(DEFAULT_RATINGS.intValue()))
                .body("thumbnail", is(DEFAULT_THUMBNAIL))
                .body("image1", is(DEFAULT_IMAGE_1))
                .body("dateEnd", is(TestUtil.formatDateTime(DEFAULT_DATE_END)))
                .body("keywords", is(DEFAULT_KEYWORDS))
                .body("creationsType", is(DEFAULT_CREATIONS_TYPE))
                .body("wazn", is(DEFAULT_WAZN))
                .body("country", is(DEFAULT_COUNTRY))
                .body("dateLastMod", is(TestUtil.formatDateTime(DEFAULT_DATE_LAST_MOD)));
    }

    @Test
    public void getNonExistingContent() {
        // Get the content
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/contents/{id}", Long.MAX_VALUE)
            .then()
            .statusCode(NOT_FOUND.getStatusCode());
    }
}
