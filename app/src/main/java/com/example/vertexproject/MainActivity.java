package com.example.vertexproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button buttonCall;
    private TextView textViewResult;
    private ProgressBar progressBar;
    private VertexApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCall = findViewById(R.id.buttonCall);
        textViewResult = findViewById(R.id.textViewResult);
        progressBar = findViewById(R.id.progressBar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-aiplatform.googleapis.com/") //Retorna 404 por padrão
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(VertexApiService.class);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamarVertexAI();
            }
        });
    }

    private void chamarVertexAI() {
        progressBar.setVisibility(View.VISIBLE);
        textViewResult.setText("");

        Map<String, Object> input = new HashMap<>();
        input.put("content", "Olá, Vertex AI! Me responda.");

        PredictRequest request = new PredictRequest(Collections.singletonList(input));

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(request);
        Log.d("Predict request: ", jsonRequest);

        apiService.predictText(
                "SeuIdDeProjeto",
                "us-central1",
                "text-bison@001",
                request,
                "Bearer SeuTokenComInicio 'ya29'"
        ).enqueue(new Callback<PredictResponse>() {
            @Override
            public void onResponse(Call<PredictResponse> call, Response<PredictResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    PredictResponse predictResponse = response.body();
                    Map<String, Object> firstPrediction = predictResponse.getPredictions().get(0);

                    if (firstPrediction != null) {
                        String content = (String) firstPrediction.get("content");
                        textViewResult.setText(content != null ? content : "Resposta vazia.");
                    } else {
                        textViewResult.setText("Nenhuma previsão retornada.");
                    }
                } else {
                    textViewResult.setText("Erro na resposta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PredictResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                textViewResult.setText("Erro: " + t.getMessage());
            }
        });
    }
}
