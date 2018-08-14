package com.example.httptest;

import org.springframework.stereotype.Component;

@Component
public class AppResponse {


    String Status;

    @Override
    public String toString() {
        return "AppResponse{" +
                "Status='" + Status + '\'' +
                '}';
    }

    public AppResponse() {
        Status = "status";
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
