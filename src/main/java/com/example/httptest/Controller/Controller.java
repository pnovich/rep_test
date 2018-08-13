package com.example.httptest.Controller;

//import com.github.kuljaninemir.springbootftpclient.FTPFileWriter;
import com.example.httptest.Service.FTPUploaderService;
import com.example.httptest.Service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
public class Controller {
    @Value("${application.fullPath}")
    String fullPath;

    @Autowired
    UploadService uploadService;

    @Autowired
    FTPUploaderService ftpUploaderService;


    @GetMapping("/hello")
    public String hello(){return "hello";}

    @GetMapping("/hi")
    public String hi(@RequestParam("filestring") String[] stringarray){



        for (int  i = 0; i < stringarray.length; i++){
            System.out.println(stringarray[i]);

        }
        System.out.println(stringarray.length + "files detected");
        File file = new File("src/main/resources/");
        String absolutePath = file.getAbsolutePath() + "\\";
        System.out.println(absolutePath);
        uploadService.doRead(stringarray[0],absolutePath);

//        for (int  i = 0; i < stringarray.length; i++){
////            System.out.println(stringarray[i]);
//            fileSendService.sendFilesByFtp(stringarray[i]);
//
//        }
        try {
            ftpUploaderService.setupConnection();
            System.out.println("--------------sending-----------");

//            System.out.println("--------------test-----------");
            for (int i = 0; i < stringarray.length; i ++){
                System.out.println("--------------test-----------");
                ftpUploaderService.uploadFile(stringarray[i],absolutePath);
            }
            System.out.println("--------------disconnect-----------");
            ftpUploaderService.disconnect();
        }catch(Exception e){
            System.out.println("exception in ftp");
            e.printStackTrace();
            System.out.println("waiting..");
        }

        return "hi";}


}


