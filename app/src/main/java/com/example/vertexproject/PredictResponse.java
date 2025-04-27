package com.example.vertexproject;

import java.util.List;
import java.util.Map;

public class PredictResponse {
    private List<Map<String, Object>> predictions;

    public List<Map<String, Object>> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Map<String, Object>> predictions) {
        this.predictions = predictions;
    }
}

