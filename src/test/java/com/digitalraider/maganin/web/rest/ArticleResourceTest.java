package com.digitalraider.maganin.web.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import com.digitalraider.maganin.TestUtil;
import com.digitalraider.maganin.domain.Article;
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
public class ArticleResourceTest {

    private static final TypeRef<Article> ENTITY_TYPE = new TypeRef<>() {
    };

    private static final TypeRef<List<Article>> LIST_OF_ENTITY_TYPE = new TypeRef<>() {
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

    private static final Integer DEFAULT_IMPRESSIONS = 1;
    private static final Integer UPDATED_IMPRESSIONS = 2;

    private static final Integer DEFAULT_AVG_RATING = 1;
    private static final Integer UPDATED_AVG_RATING = 2;

    private static final String DEFAULT_THUMBNAIL = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL = "BBBBBBBBBB";

    private static final String DEFAULT_KEYWORDS = "AAAAAAAAAA";
    private static final String UPDATED_KEYWORDS = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_LAST_MOD = Instant.ofEpochSecond(0L).truncatedTo(ChronoUnit.SECONDS);
    private static final Instant UPDATED_DATE_LAST_MOD = Instant.now().truncatedTo(ChronoUnit.SECONDS);



    String adminToken;

    Article article;

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
    public static Article createEntity() {
        var article = new Article();
        article.title = DEFAULT_TITLE;
        article.author = DEFAULT_AUTHOR;
        article.relatedURL = DEFAULT_RELATED_URL;
        article.dateAdded = DEFAULT_DATE_ADDED;
        article.shortDesc = DEFAULT_SHORT_DESC;
        article.longDesc = DEFAULT_LONG_DESC;
        article.display = DEFAULT_DISPLAY;
        article.accessLevel = DEFAULT_ACCESS_LEVEL;
        article.impressions = DEFAULT_IMPRESSIONS;
        article.avgRating = DEFAULT_AVG_RATING;
        article.thumbnail = DEFAULT_THUMBNAIL;
        article.keywords = DEFAULT_KEYWORDS;
        article.dateLastMod = DEFAULT_DATE_LAST_MOD;
        return article;
    }

    @BeforeEach
    public void initTest() {
        article = createEntity();
    }

    @Test
    public void createArticle() {
        var databaseSizeBeforeCreate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Create the Article
        article = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(article)
            .when()
            .post("/api/articles")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        // Validate the Article in the database
        var articleList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(articleList).hasSize(databaseSizeBeforeCreate + 1);
        var testArticle = articleList.stream().filter(it -> article.id.equals(it.id)).findFirst().get();
        assertThat(testArticle.title).isEqualTo(DEFAULT_TITLE);
        assertThat(testArticle.author).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testArticle.relatedURL).isEqualTo(DEFAULT_RELATED_URL);
        assertThat(testArticle.dateAdded).isEqualTo(DEFAULT_DATE_ADDED);
        assertThat(testArticle.shortDesc).isEqualTo(DEFAULT_SHORT_DESC);
        assertThat(testArticle.longDesc).isEqualTo(DEFAULT_LONG_DESC);
        assertThat(testArticle.display).isEqualTo(DEFAULT_DISPLAY);
        assertThat(testArticle.accessLevel).isEqualTo(DEFAULT_ACCESS_LEVEL);
        assertThat(testArticle.impressions).isEqualTo(DEFAULT_IMPRESSIONS);
        assertThat(testArticle.avgRating).isEqualTo(DEFAULT_AVG_RATING);
        assertThat(testArticle.thumbnail).isEqualTo(DEFAULT_THUMBNAIL);
        assertThat(testArticle.keywords).isEqualTo(DEFAULT_KEYWORDS);
        assertThat(testArticle.dateLastMod).isEqualTo(DEFAULT_DATE_LAST_MOD);
    }

    @Test
    public void createArticleWithExistingId() {
        var databaseSizeBeforeCreate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Create the Article with an existing ID
        article.id = 1L;

        // An entity with an existing ID cannot be created, so this API call must fail
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(article)
            .when()
            .post("/api/articles")
            .then()
            .statusCode(BAD_REQUEST.getStatusCode());

        // Validate the Article in the database
        var articleList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(articleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void updateArticle() {
        // Initialize the database
        article = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(article)
            .when()
            .post("/api/articles")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var databaseSizeBeforeUpdate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Get the article
        var updatedArticle = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles/{id}", article.id)
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().body().as(ENTITY_TYPE);

        // Update the article
        updatedArticle.title = UPDATED_TITLE;
        updatedArticle.author = UPDATED_AUTHOR;
        updatedArticle.relatedURL = UPDATED_RELATED_URL;
        updatedArticle.dateAdded = UPDATED_DATE_ADDED;
        updatedArticle.shortDesc = UPDATED_SHORT_DESC;
        updatedArticle.longDesc = UPDATED_LONG_DESC;
        updatedArticle.display = UPDATED_DISPLAY;
        updatedArticle.accessLevel = UPDATED_ACCESS_LEVEL;
        updatedArticle.impressions = UPDATED_IMPRESSIONS;
        updatedArticle.avgRating = UPDATED_AVG_RATING;
        updatedArticle.thumbnail = UPDATED_THUMBNAIL;
        updatedArticle.keywords = UPDATED_KEYWORDS;
        updatedArticle.dateLastMod = UPDATED_DATE_LAST_MOD;

        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(updatedArticle)
            .when()
            .put("/api/articles")
            .then()
            .statusCode(OK.getStatusCode());

        // Validate the Article in the database
        var articleList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(articleList).hasSize(databaseSizeBeforeUpdate);
        var testArticle = articleList.stream().filter(it -> updatedArticle.id.equals(it.id)).findFirst().get();
        assertThat(testArticle.title).isEqualTo(UPDATED_TITLE);
        assertThat(testArticle.author).isEqualTo(UPDATED_AUTHOR);
        assertThat(testArticle.relatedURL).isEqualTo(UPDATED_RELATED_URL);
        assertThat(testArticle.dateAdded).isEqualTo(UPDATED_DATE_ADDED);
        assertThat(testArticle.shortDesc).isEqualTo(UPDATED_SHORT_DESC);
        assertThat(testArticle.longDesc).isEqualTo(UPDATED_LONG_DESC);
        assertThat(testArticle.display).isEqualTo(UPDATED_DISPLAY);
        assertThat(testArticle.accessLevel).isEqualTo(UPDATED_ACCESS_LEVEL);
        assertThat(testArticle.impressions).isEqualTo(UPDATED_IMPRESSIONS);
        assertThat(testArticle.avgRating).isEqualTo(UPDATED_AVG_RATING);
        assertThat(testArticle.thumbnail).isEqualTo(UPDATED_THUMBNAIL);
        assertThat(testArticle.keywords).isEqualTo(UPDATED_KEYWORDS);
        assertThat(testArticle.dateLastMod).isEqualTo(UPDATED_DATE_LAST_MOD);
    }

    @Test
    public void updateNonExistingArticle() {
        var databaseSizeBeforeUpdate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles")
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
            .body(article)
            .when()
            .put("/api/articles")
            .then()
            .statusCode(BAD_REQUEST.getStatusCode());

        // Validate the Article in the database
        var articleList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(articleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteArticle() {
        // Initialize the database
        article = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(article)
            .when()
            .post("/api/articles")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var databaseSizeBeforeDelete = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Delete the article
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .delete("/api/articles/{id}", article.id)
            .then()
            .statusCode(NO_CONTENT.getStatusCode());

        // Validate the database contains one less item
        var articleList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(articleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void getAllArticles() {
        // Initialize the database
        article = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(article)
            .when()
            .post("/api/articles")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        // Get all the articleList
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles?sort=id,desc")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .body("id", hasItem(article.id.intValue()))
            .body("userId", hasItem(DEFAULT_USER_ID.intValue()))            .body("title", hasItem(DEFAULT_TITLE))            .body("author", hasItem(DEFAULT_AUTHOR))            .body("relatedURL", hasItem(DEFAULT_RELATED_URL))            .body("dateAdded", hasItem(TestUtil.formatDateTime(DEFAULT_DATE_ADDED)))            .body("shortDesc", hasItem(DEFAULT_SHORT_DESC))            .body("longDesc", hasItem(DEFAULT_LONG_DESC))            .body("display", hasItem(DEFAULT_DISPLAY))            .body("accessLevel", hasItem(DEFAULT_ACCESS_LEVEL.intValue()))            .body("impressions", hasItem(DEFAULT_IMPRESSIONS.intValue()))            .body("avgRating", hasItem(DEFAULT_AVG_RATING.intValue()))            .body("thumbnail", hasItem(DEFAULT_THUMBNAIL))            .body("keywords", hasItem(DEFAULT_KEYWORDS))            .body("country", hasItem(DEFAULT_COUNTRY))            .body("dateLastMod", hasItem(TestUtil.formatDateTime(DEFAULT_DATE_LAST_MOD)));
    }

    @Test
    public void getArticle() {
        // Initialize the database
        article = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(article)
            .when()
            .post("/api/articles")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var response = // Get the article
            given()
                .auth()
                .preemptive()
                .oauth2(adminToken)
                .accept(APPLICATION_JSON)
                .when()
                .get("/api/articles/{id}", article.id)
                .then()
                .statusCode(OK.getStatusCode())
                .contentType(APPLICATION_JSON)
                .extract().as(ENTITY_TYPE);

        // Get the article
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles/{id}", article.id)
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .body("id", is(article.id.intValue()))

                .body("userId", is(DEFAULT_USER_ID.intValue()))
                .body("title", is(DEFAULT_TITLE))
                .body("author", is(DEFAULT_AUTHOR))
                .body("relatedURL", is(DEFAULT_RELATED_URL))
                .body("dateAdded", is(TestUtil.formatDateTime(DEFAULT_DATE_ADDED)))
                .body("shortDesc", is(DEFAULT_SHORT_DESC))
                .body("longDesc", is(DEFAULT_LONG_DESC))
                .body("display", is(DEFAULT_DISPLAY))
                .body("accessLevel", is(DEFAULT_ACCESS_LEVEL.intValue()))
                .body("impressions", is(DEFAULT_IMPRESSIONS.intValue()))
                .body("avgRating", is(DEFAULT_AVG_RATING.intValue()))
                .body("thumbnail", is(DEFAULT_THUMBNAIL))
                .body("keywords", is(DEFAULT_KEYWORDS))
                .body("country", is(DEFAULT_COUNTRY))
                .body("dateLastMod", is(TestUtil.formatDateTime(DEFAULT_DATE_LAST_MOD)));
    }

    @Test
    public void getNonExistingArticle() {
        // Get the article
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/articles/{id}", Long.MAX_VALUE)
            .then()
            .statusCode(NOT_FOUND.getStatusCode());
    }
}
