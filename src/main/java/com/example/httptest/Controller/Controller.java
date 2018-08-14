package com.example.httptest.Controller;

//import com.github.kuljaninemir.springbootftpclient.FTPFileWriter;
import com.example.httptest.AppResponse;
import com.example.httptest.Service.FTPUploaderService;
import com.example.httptest.Service.UploadService;
//import org.slf4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
//import java.util.logging.Logger;

@RestController
public class Controller {



    @Autowired
    UploadService uploadService;

    @Autowired
    FTPUploaderService ftpUploaderService;

    @Autowired
    AppResponse appResponse;


    @GetMapping("/")
    public String hello(){return "go to the localhost:8080/ftpsend";}

    @GetMapping("/ftpsend")
    public AppResponse hi(@RequestParam("filestring") String[] stringarray){


        boolean flag = true;
       appResponse.setStatus("sux");
        Logger log = LogManager.getLogger(Controller.class.getName());


        log.debug("---------START-----------");
        for (int  i = 0; i < stringarray.length; i++){
            log.info(" " + stringarray.length + "files detected in request");



        }
//        System.out.println(stringarray.length + "files detected");
        File file = new File("src/main/resources/");
        String absolutePath = file.getAbsolutePath() + "\\";
//        System.out.println(absolutePath);

        log.info("download from resource:");
        try {
            for (int i = 0; i < stringarray.length; i++) {
                flag = uploadService.doRead(stringarray[i], absolutePath);
            }
        }catch (Exception e){
            flag = false;
            log.debug("not sux donload");
        }
        if (flag == true)
//            log.info("---connection---");
            try {
                flag = ftpUploaderService.setupConnection();
            } catch(Exception e){
                flag = false;
            }

//            System.out.println("--------------sending-----------");

//            System.out.println("--------------test-----------");
           if (flag == true) try
            {
                for (int i = 0; i < stringarray.length; i++) {
                    System.out.println("--------------test-----------");
                    log.debug("" + i + "file is sending");
                    ftpUploaderService.uploadFile(stringarray[i], absolutePath);
                }

            } catch (Exception e) {
                log.info(" exception while file sending", e);
                flag = false;

              log.debug("Non correct reuest..please try again");
            } finally {
                log.debug("disconnected");
                ftpUploaderService.disconnect();
            }

        else appResponse.setStatus("not sux");
        if (!flag) log.info("not sux");
        System.out.println("flag = " + flag);
        return appResponse;}


}


