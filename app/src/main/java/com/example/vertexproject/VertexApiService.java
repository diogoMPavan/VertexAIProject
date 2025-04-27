package com.example.vertexproject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VertexApiService {
    @POST("v1/projects/{project}/locations/{location}/publishers/google/models/{model}:predict")
    Call<PredictResponse> predictText(
            @Path("project") String projectId,
            @Path("location") String location,
            @Path("model") String modelId,
            @Body PredictRequest body,
            @Header("Authorization") String authHeader
    );
}

