package com.example.calculate

import com.example.data.classes.CalculateResponse
import com.example.data.classes.InfoResponse
import com.google.gson.Gson
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class CalculateTest {

    @Test
    fun testCalculateEndpoint() {
        val expectedData = Gson().toJson(InfoResponse("With this endpoint we can do awesome calculations."))
        given()
          .`when`()
             .get("/calculate")
          .then()
             .statusCode(200)
             .body(`is`(expectedData))
    }

    @Test
    fun testCalculateAdvancedEndpoint() {
        val expectedData = Gson().toJson(CalculateResponse(20, true))
        given()
        .`when`()
            .post("/calculate/2/*/10")
        .then()
            .statusCode(200)
            .body(`is`(expectedData))
    }

    @Test
    fun testCalculateAdditionEndpoint() {
        val expectedData = Gson().toJson(CalculateResponse(2, false))
        given()
        .`when`()
            .post("/calculate/1/+/1")
        .then()
            .statusCode(200)
            .body(`is`(expectedData))
    }

    @Test
    fun testCalculateSubtractionEndpoint() {
        val expectedData = Gson().toJson(CalculateResponse(0, false))
        given()
        .`when`()
            .post("/calculate/1/-/1")
        .then()
            .statusCode(200)
            .body(`is`(expectedData))
    }

    @Test
    fun testCalculateAdvancedEndpointWithBadData() {
        given()
        .`when`()
            .post("/calculate/1/a/1")
        .then()
            .statusCode(400)

    }
}