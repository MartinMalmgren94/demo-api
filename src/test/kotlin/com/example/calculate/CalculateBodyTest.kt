package com.example.calculate


import com.example.data.classes.CalculateResponse
import com.google.gson.Gson
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class CalculateBodyTest {

    @Test
    fun testCalculateSubtractionBodyEndpoint() {
        val expectedData = Gson().toJson(CalculateResponse(0, false))
        val bodyData = Gson().toJson(CalculateBody("-", 1, 1))
        given()
            .body(bodyData)
            .contentType(ContentType.JSON)
        .`when`()
            .post("/calculate")
        .then()
            .statusCode(200)
            .body(`is`(expectedData))
    }

    @Test
    fun testCalculateBodyEndpointWithBadData() {
        val bodyData = Gson().toJson(CalculateBody("a", 1, 2))
        given()
            .body(bodyData)
            .contentType(ContentType.JSON)
        .`when`()
            .post("/calculate")
        .then()
            .statusCode(400)

    }

    @Test
    fun testCalculateBodyEndpointWithBadDataType() {
        val bodyData = Gson().toJson(CalculateBody("+", 1, 2))
        given()
            .body(bodyData)
            .contentType(ContentType.TEXT)
            .`when`()
            .post("/calculate")
            .then()
            .statusCode(415)

    }
}