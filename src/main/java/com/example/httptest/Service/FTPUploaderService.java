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

import static org.apache.commons.net.ftp.FTPReply.COMMAND_OK;

@Service
public class FTPUploaderService {


@Value("${application.host}")
String host;


String hostDir = "/";

@Value("${application.user}")
String user;

@Value("${application.pwd}")
String pwd;
    FTPClient ftp = null;

    public boolean setupConnection() throws Exception {
        boolean hostcon = false;
        boolean con = false;
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host);
        reply = ftp.getReplyCode();
        System.out.println("the reply -------= " + reply);
//        if (reply == 220 || reply == 331 || reply == 530 ) System.out.println("wwwWWWWWWWWWWWWWWWWWWWWWWWWW");
        if (!FTPReply.isPositiveCompletion(reply)) {

            ftp.disconnect();
            System.out.println("no connection");
            throw new Exception("Exception in connecting to FTP Server");
        }
        else {
            ftp.enterLocalPassiveMode();
            ftp.login(user, pwd);
            reply = ftp.getReplyCode();
            System.out.println("reply2 -------=" + reply);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            con = true;
        }
        if (!(reply == 230)) con = false;
        System.out.println("connection -----" + con);
        hostcon = true;
        return con;
    }

    public void uploadFile(String fileName, String fullPath)
            throws Exception {
        System.out.println("-----------------------------");


            String localFileFullName = fullPath + fileName;
            try (InputStream input = new FileInputStream(new File(localFileFullName))) {
                this.ftp.storeFile(hostDir + fileName, input);
                System.out.println("try sending");
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