package com.example.httptest.Service;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;

@Service
public class UploadService {

    public void doRead(String filename, String fullName){
        try(BufferedReader br = new BufferedReader(new FileReader(fullName + filename))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            System.out.println(line);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


}
