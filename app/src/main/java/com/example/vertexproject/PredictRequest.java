package com.example.vertexproject;

import java.util.List;
import java.util.Map;

public class PredictRequest {
    private List<Map<String, Object>> instances;

    public PredictRequest(List<Map<String, Object>> instances) {
        this.instances = instances;
    }

    public List<Map<String, Object>> getInstances() {
        return instances;
    }

    public void setInstances(List<Map<String, Object>> instances) {
        this.instances = instances;
    }
}

