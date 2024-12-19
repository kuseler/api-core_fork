package com.educoach;

import com.educoach.teachers.Teacher;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.ws.rs.core.Response;

@QuarkusTest
public class TeacherResourceV1Test {

    @Test
    void getAll_should_return_204_when_no_teacher_exists() {
        given()
                .when()
                .get("/api/v1/teachers")
                .then()
                .statusCode(204);
    }

    @Test
    void getAll_should_return_list_of_teachers() {
        final var teacher = new Teacher(null, "John", "Doe", null);
        final var created = given()
                .contentType(ContentType.JSON)
                .body(teacher)
                .post("/api/v1/teachers")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract()
                .as(Teacher.class);
        final var got = given()
                .when()
                .get("/api/v1/teachers")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(Teacher[].class);

        assertThat(got).hasSize(1);
        assertThat(got[0]).isEqualTo(created);
    }
}
