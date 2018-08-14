package com.example.httptest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component

public class AppResponse {

    public AppResponse() {
        Status = "not sux";
        filesUploadSux = false;
        ftpHostConnection = false;
        ftpLogging = false;


    }

    public String Status;
    public boolean filesUploadSux;
    public boolean ftpHostConnection;
    public  boolean ftpLogging;

    public AppResponse(String status, Boolean filesUploadSux,boolean ftpHostConnection,boolean ftpLogging) {
        Status = status;
        filesUploadSux= filesUploadSux;
        ftpHostConnection =ftpHostConnection;
        ftpLogging =ftpLogging;
    }


    public boolean isFtpLogging() {
        return ftpLogging;
    }

    public void setFtpLogging(boolean ftpLogging) {
        this.ftpLogging = ftpLogging;
    }

    public boolean isFilesUploadSux() {
        return filesUploadSux;
    }

    public void setFilesUploadSux(boolean filesUploadSux) {
        this.filesUploadSux = filesUploadSux;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }



    public boolean isFtpHostConnection() {
        return ftpHostConnection;
    }

    public void setFtpHostConnection(boolean ftpHostConnection) {
        this.ftpHostConnection = ftpHostConnection;
    }



    @Override
    public String toString() {
        return "AppResponse{" +
                "Status='" + Status + '\'' +
//                ", filesUploadSux=" + filesUploadSux +
                ", ftpHostConnection=" + ftpHostConnection +
                ", ftpLogging=" + ftpLogging +
                '}';
    }
}
