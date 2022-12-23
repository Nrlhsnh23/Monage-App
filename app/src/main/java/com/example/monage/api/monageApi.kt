package com.example.monage.api

import retrofit2.Response
import retrofit2.http.*

public interface monageApi {
        @GET("/rest/v1/monage?select=*&order=id.asc")
        suspend fun get(
            @Header("Authorization") token: String,
            @Header("apikey") apiKey: String
        ) : Response<List<monageItem>>

        @POST("/rest/v1/monage")
        suspend fun create(
            @Header("Authorization") token: String,
            @Header("apikey") apiKey: String,
            @Body monageData: monageData
        )

        @PATCH("/rest/v1/monage")
        suspend fun update(
            @Header("Authorization") token: String,
            @Header("apikey") apiKey: String,
            @Query("id") idQuery : String,
            @Body monageData: monageData
        ) : Response<Unit>

        @DELETE("/rest/v1/monage")
        suspend fun delete(
            @Header("Authorization") token: String,
            @Header("apikey") apiKey: String,
            @Query("id") idQuery : String
        ) : Response<Unit>
    }