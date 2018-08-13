package com.example.httptest.Service;
import java.io.*;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

@Service
public class FTPUploaderService {

//    String host ="ftp.javabebop-com-ua.1gb.ua";
//    String host1 = "test.rebex.net";
//    String user1 = "demo";
//    String pwd1 = "password";
////    String localFileFullName = "D:\\Pankaj\\images\\MyImage.png";
////    String fileName = "image.png";
//    String hostDir = "/";
//    String user = "w_javabebop-com-ua_1a9aeef1";
//    String pwd = "b7286fa58wr";
////    @Autowired
//    private Environment env;
//    String keyValue = env.getProperty(key);
//    @Value("${application.fullPath}")
//    String fullPath;
@Value("${application.host}")
String host;

@Value("${application.hostDir}")
String hostDir;

@Value("${application.user}")
String user;

@Value("${application.pwd}")
String pwd;
    FTPClient ftp = null;

    public void setupConnection() throws Exception {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        ftp.enterLocalPassiveMode();
        ftp.login(user, pwd);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);

    }

    public void uploadFile(String fileName, String fullPath)
            throws Exception {
        System.out.println("-----------------------------");


            String localFileFullName = fullPath + fileName;
            try (InputStream input = new FileInputStream(new File(localFileFullName))) {
                this.ftp.storeFile(hostDir + fileName, input);
                System.out.println("try ftp");
            }

    }

    public void disconnect() {
        System.out.println("-----------------------------");
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                // do nothing as file is already saved to server
            }
        }
    }

}