package com.example.httptest.Service;

import com.example.httptest.AppResponse;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;

@Service
public class UploadService {

    public boolean doRead(String filename, String fullName, AppResponse appResponse) throws  Exception{
        appResponse.setFilesUploadSux(false);
        try{
            BufferedReader br = new BufferedReader(new FileReader(fullName + filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            System.out.println(line);
//            appResponse.setFilesUploadSux(true);
        }
        catch(Exception e){
         return false;
        }
        return true;
    }


}
