package com.demo03.modules.sftp;


import com.demo03.utils.SFTPClientUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

import java.io.*;
import java.util.Vector;

public class UpDownSftp {

    private static String username = "root";
    private static String password = "@abr20141227131";
    private static String hostname = "124.223.38.120";
    private static int port = 22;

    public static void downloadPicture() throws SftpException, FileNotFoundException {
        String username = "root";
        String password = "@abr20141227131";
        String hostname = "124.223.38.120";
        int port = 22;
        SFTPClientUtil tSFTPClientUtil = new SFTPClientUtil("root","@abr201412271314","124.223.38.120",22);

        //SFTPClientUtil tSFTPClientUtil = new SFTPClientUtil(username,password,hostname,port);
        tSFTPClientUtil.login();
        tSFTPClientUtil.download("/home/temp", "temp.sql", "D:\\L"+"/"+"temp.sql");
        tSFTPClientUtil.logout();
    }


    /**
     * 上传文件
     */
    public static void upload() throws IOException, SftpException {
        String username = "root";
        String password = "@abr20141227131";
        String hostname = "124.223.38.120";
        int port = 22;
        SFTPClientUtil tSFTPClientUtil = new SFTPClientUtil("root","@abr201412271314","124.223.38.120",22);
        //SFTPClientUtil tSFTPClientUtil = new SFTPClientUtil(username, password, hostname, port);
        tSFTPClientUtil.login();
        File file1 = new File("D:\\L\\t1.sql");
        InputStream is = new FileInputStream(file1);
        tSFTPClientUtil.upload("/home/temp", "", "t1.sql", is);
        tSFTPClientUtil.logout();
        is.close();
    }

    /**
     *
     * @param args
     * @throws SftpException
     * @throws IOException
     */


    public static void main(String[] args) throws SftpException, IOException {
        //UpDownSftp.upload();
        //UpDownSftp.downloadPicture();
        SFTPClientUtil tSFTPClientUtil = new SFTPClientUtil("root","@abr201412271314","124.223.38.120",22);
        tSFTPClientUtil.login();

        Vector<ChannelSftp.LsEntry> listFiles = tSFTPClientUtil.listFiles("/home/temp");
        for (ChannelSftp.LsEntry file : listFiles) {
            String fileName = file.getFilename();
            System.out.println("fileName:"+fileName);
        }
    }

}
