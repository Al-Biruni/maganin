package com.digitalraider.maganin.web.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import com.digitalraider.maganin.TestUtil;
import com.digitalraider.maganin.domain.Category;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import liquibase.Liquibase;
import io.quarkus.liquibase.LiquibaseFactory;
import org.junit.jupiter.api.*;

import javax.inject.Inject;

import java.util.List;
    
@QuarkusTest
public class CategoryResourceTest {

    private static final TypeRef<Category> ENTITY_TYPE = new TypeRef<>() {
    };

    private static final TypeRef<List<Category>> LIST_OF_ENTITY_TYPE = new TypeRef<>() {
    };

    private static final String DEFAULT_CATEGORY_TYPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_TYPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PARENT_ID = 1L;
    private static final Long UPDATED_PARENT_ID = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HIDE_SEARCH = false;
    private static final Boolean UPDATED_HIDE_SEARCH = true;



    String adminToken;

    Category category;

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
    public static Category createEntity() {
        var category = new Category();
        category.categoryTypeId = DEFAULT_CATEGORY_TYPE_ID;
        category.categoryName = DEFAULT_CATEGORY_NAME;
        category.parentId = DEFAULT_PARENT_ID;
        category.description = DEFAULT_DESCRIPTION;
        category.imageURL = DEFAULT_IMAGE_URL;
        category.hideSearch = DEFAULT_HIDE_SEARCH;
        return category;
    }

    @BeforeEach
    public void initTest() {
        category = createEntity();
    }

    @Test
    public void createCategory() {
        var databaseSizeBeforeCreate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Create the Category
        category = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(category)
            .when()
            .post("/api/categories")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        // Validate the Category in the database
        var categoryList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(categoryList).hasSize(databaseSizeBeforeCreate + 1);
        var testCategory = categoryList.stream().filter(it -> category.id.equals(it.id)).findFirst().get();
        assertThat(testCategory.categoryTypeId).isEqualTo(DEFAULT_CATEGORY_TYPE_ID);
        assertThat(testCategory.categoryName).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testCategory.parentId).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testCategory.description).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCategory.imageURL).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testCategory.hideSearch).isEqualTo(DEFAULT_HIDE_SEARCH);
    }

    @Test
    public void createCategoryWithExistingId() {
        var databaseSizeBeforeCreate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Create the Category with an existing ID
        category.id = 1L;

        // An entity with an existing ID cannot be created, so this API call must fail
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(category)
            .when()
            .post("/api/categories")
            .then()
            .statusCode(BAD_REQUEST.getStatusCode());

        // Validate the Category in the database
        var categoryList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(categoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void updateCategory() {
        // Initialize the database
        category = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(category)
            .when()
            .post("/api/categories")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var databaseSizeBeforeUpdate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Get the category
        var updatedCategory = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories/{id}", category.id)
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().body().as(ENTITY_TYPE);

        // Update the category
        updatedCategory.categoryTypeId = UPDATED_CATEGORY_TYPE_ID;
        updatedCategory.categoryName = UPDATED_CATEGORY_NAME;
        updatedCategory.parentId = UPDATED_PARENT_ID;
        updatedCategory.description = UPDATED_DESCRIPTION;
        updatedCategory.imageURL = UPDATED_IMAGE_URL;
        updatedCategory.hideSearch = UPDATED_HIDE_SEARCH;

        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(updatedCategory)
            .when()
            .put("/api/categories")
            .then()
            .statusCode(OK.getStatusCode());

        // Validate the Category in the database
        var categoryList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(categoryList).hasSize(databaseSizeBeforeUpdate);
        var testCategory = categoryList.stream().filter(it -> updatedCategory.id.equals(it.id)).findFirst().get();
        assertThat(testCategory.categoryTypeId).isEqualTo(UPDATED_CATEGORY_TYPE_ID);
        assertThat(testCategory.categoryName).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testCategory.parentId).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testCategory.description).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCategory.imageURL).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testCategory.hideSearch).isEqualTo(UPDATED_HIDE_SEARCH);
    }

    @Test
    public void updateNonExistingCategory() {
        var databaseSizeBeforeUpdate = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories")
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
            .body(category)
            .when()
            .put("/api/categories")
            .then()
            .statusCode(BAD_REQUEST.getStatusCode());

        // Validate the Category in the database
        var categoryList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(categoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCategory() {
        // Initialize the database
        category = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(category)
            .when()
            .post("/api/categories")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var databaseSizeBeforeDelete = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE)
            .size();

        // Delete the category
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .delete("/api/categories/{id}", category.id)
            .then()
            .statusCode(NO_CONTENT.getStatusCode());

        // Validate the database contains one less item
        var categoryList = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .extract().as(LIST_OF_ENTITY_TYPE);

        assertThat(categoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void getAllCategories() {
        // Initialize the database
        category = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(category)
            .when()
            .post("/api/categories")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        // Get all the categoryList
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories?sort=id,desc")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .body("id", hasItem(category.id.intValue()))
            .body("categoryTypeId", hasItem(DEFAULT_CATEGORY_TYPE_ID))            .body("categoryName", hasItem(DEFAULT_CATEGORY_NAME))            .body("parentId", hasItem(DEFAULT_PARENT_ID.intValue()))            .body("description", hasItem(DEFAULT_DESCRIPTION))            .body("imageURL", hasItem(DEFAULT_IMAGE_URL))            .body("hideSearch", hasItem(DEFAULT_HIDE_SEARCH.booleanValue()));
    }

    @Test
    public void getCategory() {
        // Initialize the database
        category = given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(category)
            .when()
            .post("/api/categories")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().as(ENTITY_TYPE);

        var response = // Get the category
            given()
                .auth()
                .preemptive()
                .oauth2(adminToken)
                .accept(APPLICATION_JSON)
                .when()
                .get("/api/categories/{id}", category.id)
                .then()
                .statusCode(OK.getStatusCode())
                .contentType(APPLICATION_JSON)
                .extract().as(ENTITY_TYPE);

        // Get the category
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories/{id}", category.id)
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON)
            .body("id", is(category.id.intValue()))
            
                .body("categoryTypeId", is(DEFAULT_CATEGORY_TYPE_ID))
                .body("categoryName", is(DEFAULT_CATEGORY_NAME))
                .body("parentId", is(DEFAULT_PARENT_ID.intValue()))
                .body("description", is(DEFAULT_DESCRIPTION))
                .body("imageURL", is(DEFAULT_IMAGE_URL))
                .body("hideSearch", is(DEFAULT_HIDE_SEARCH.booleanValue()));
    }

    @Test
    public void getNonExistingCategory() {
        // Get the category
        given()
            .auth()
            .preemptive()
            .oauth2(adminToken)
            .accept(APPLICATION_JSON)
            .when()
            .get("/api/categories/{id}", Long.MAX_VALUE)
            .then()
            .statusCode(NOT_FOUND.getStatusCode());
    }
}
