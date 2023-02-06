package com.example.controller.calculate

import com.example.controller.calculate.validation.ValidateCalculate
import com.example.data.classes.*
import com.example.service.CalculateService
import com.google.gson.Gson
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/calculate")
class CalculateController {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun calculateInfo(): String = Gson().toJson(InfoResponse("With this endpoint we can do awesome calculations."))


    @POST
    @Path("/{numb1}/{operator}/{numb2}")
    @Produces(MediaType.APPLICATION_JSON)
    fun result(@PathParam("numb1") numb1 : Int, @PathParam("operator") operator : String, @PathParam("numb2") numb2 : Int) : Response  {
        // validating the incoming data.
        try {
            ValidateCalculate(numb1, numb2, operator)
        }
        catch (e: Exception) {
            val errorResponse = Gson().toJson(e.message)
            return Response.status(400).entity(errorResponse).build()
        }
        val response : String? = Gson().toJson(CalculateService().calculate(numb1, numb2, operator))
        return Response.status(200).entity(response).build()

    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun resultBody(@Valid body: CalculateBody) : Response {
        val response : String? = Gson().toJson(CalculateService().calculate(body.numb1, body.numb2, body.operator))
        return Response.status(200).entity(response).build()

    }
}

