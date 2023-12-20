package com.demo03.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;

public class SFTPClientUtil {


    //private transient Logger log = LoggerFactory.getLogger(this.getClass());

    private ChannelSftp sftp;

    private Session session;
    /** SFTP 登录用户名*/
    private String username;
    /** SFTP 登录密码*/
    private String password;
    /** 私钥 */
    private String privateKey;
    /** SFTP 服务器地址IP地址*/
    private String host;
    /** SFTP 端口*/
    private int port;


    /**
     * 构造基于密码认证的sftp对象
     */
    public SFTPClientUtil(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     */
    public SFTPClientUtil(String username, String host, int port, String privateKey) {
        this.username = username;
        this.host = host;
        this.port = port;
        this.privateKey = privateKey;
    }

    public SFTPClientUtil(){}


    /**
     * 连接sftp服务器
     */
    public void login(){
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                jsch.addIdentity(privateKey);// 设置私钥
            }

            session = jsch.getSession(username, host, port);

            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout(){
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }


    /**
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     * @param basePath  服务器的基础路径
     * @param directory  上传到该目录
     * @param sftpFileName  sftp端文件名
     * @param input   输入流
     */
    public void upload(String basePath,String directory, String sftpFileName, InputStream input) throws SftpException{
        try {
            sftp.cd(basePath);
            sftp.cd(directory);
        } catch (SftpException e) {
            //目录不存在，则创建文件夹
            String [] dirs=directory.split("/");
            String tempPath=basePath;
            for(String dir:dirs){
                if(null== dir || "".equals(dir)) continue;
                tempPath+="/"+dir;
                try{
                    sftp.cd(tempPath);
                }catch(SftpException ex){
                    sftp.mkdir(tempPath);
                    sftp.cd(tempPath);
                }
            }
        }
        sftp.put(input, sftpFileName);  //上传文件
    }


    /**
     * 下载文件。
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     */
    public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        File file = new File(saveFile);
        sftp.get(downloadFile, new FileOutputStream(file));
    }

    /**
     * 下载文件
     * @param directory 下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     */
    public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        InputStream is = sftp.get(downloadFile);

        byte[] fileData = IOUtils.toByteArray(is);

        return fileData;
    }


    /**
     * 删除文件
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public void delete(String directory, String deleteFile) throws SftpException{
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }

    /**
     * 删除文件夹
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件夹名
     */
    public void deleteDir(String directory, String deleteFile) throws SftpException{
        sftp.cd(directory);
        sftp.rmdir(deleteFile);
    }

    /**
     * 列出目录下的文件
     * @param directory 要列出的目录
     */
    public Vector<ChannelSftp.LsEntry> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 创建文件夹
     * @param filepath
     * @throws SftpException
     * @throws IOException
     */
    public void createfilelist(String basepath,String filepath) throws SftpException{
        //目录不存在，则创建文件夹
        String [] dirs=filepath.split("/");
        String tempPath=basepath;
        for(String dir:dirs){
            if(null== dir || "".equals(dir)) continue;
            tempPath+="/"+dir;
            try{
                sftp.cd(tempPath);
            }catch(SftpException ex){
                sftp.mkdir(tempPath);
                sftp.cd(tempPath);
            }
        }
    }

    /**
     * 读取文件
     * @param sPath
     * @throws SftpException
     * @throws IOException
     */
    public Map<String, InputStream> readFile(String sPath) throws SftpException {

        Map<String, InputStream> stringHashMap  =new LinkedHashMap<String, InputStream>();
        try {
            sftp.cd(sPath);

        } catch (SftpException e) {

            sftp.mkdir(sPath);
            sftp.cd(sPath);

        }
        Vector<ChannelSftp.LsEntry>  listFiles = sftp.ls(sftp.pwd());
        for (ChannelSftp.LsEntry file : listFiles)
        {
            String fileName = file.getFilename();
            try
            {
                InputStream    inputStream = sftp.get(sftp.pwd() + "/" + fileName);

                stringHashMap.put(fileName,inputStream);
            }
            catch (SftpException e)
            {
                e.printStackTrace();
            }

        }
        return stringHashMap;
    }

    /**
     * 按序读取文件
     * @param sPath
     * @throws SftpException
     * @throws IOException
     */
    public Map<String, InputStream> readFileBySort(String sPath) throws SftpException {

        Map<String, InputStream> stringHashMap = new LinkedHashMap<String, InputStream>();
        try {
            sftp.cd(sPath);

        } catch (SftpException e) {

            sftp.mkdir(sPath);
            sftp.cd(sPath);

        }
        try {
            Vector<ChannelSftp.LsEntry> listFiles = sftp.ls(sftp.pwd());
            HashMap<Long, String> hashMap = new HashMap<Long, String>();
            for (ChannelSftp.LsEntry file : listFiles) {
                // 防止hashmap的key重复导致的覆盖，加上一个时间戳
                // 如果key值仍旧重复，采用开放地址法放入hashmap
                Calendar calendar = Calendar.getInstance();
                String time = calendar.getTimeInMillis() + "";
                String timestamp = time.substring(time.length() - 3, time.length());
                String fileName = file.getFilename();
                System.out.println(file.getAttrs().getMTime());
                Long i = (Long.parseLong((file.getAttrs().getMTime())+"")) * 1000 + Long.parseLong(timestamp);
                System.out.println(fileName + "--" + timestamp + "--" + i);
                if ("".equals(hashMap.get(i)) || null == hashMap.get(i)) {
                    hashMap.put(i, fileName);
                } else {
                    Long tEndWith = i + 1;
                    System.out.println("获取" + tEndWith + "的值：" + hashMap.get(tEndWith));
                    while (null != (hashMap.get(tEndWith))) {
                        System.out.println("获取" + tEndWith + "的值：" + hashMap.get(tEndWith));
                        tEndWith = tEndWith + 1;
                    }
                    System.out.println("将key" + tEndWith + "，value值：" + fileName + "放入map");
                    hashMap.put(tEndWith, fileName);
                }
            }

            Set set = hashMap.keySet();
            Object[] arr = set.toArray();
            Arrays.sort(arr);
            System.out.println("外包案件放入Map顺序:");
            for (Object key : arr) {
                System.out.println(key + ": " + hashMap.get(key));
                InputStream inputStream = sftp.get(sftp.pwd() + "/" + hashMap.get(key));
                stringHashMap.put(hashMap.get(key), inputStream);
            }
        } catch (SftpException e) {
            e.printStackTrace();
        }

        return stringHashMap;
    }

    //上传文件测试
    /**
     * @param args
     * @throws SftpException
     * @throws IOException
     */
    public static void main(String[] args) throws SftpException, IOException {
        SFTPClientUtil tSFTPClientUtil = new SFTPClientUtil("root","@abr201412271314","124.223.38.120",22);
        tSFTPClientUtil.login();
        tSFTPClientUtil.download("/home/temp", "temp.sql", "D:\\L"+"/"+"temp.sql");
        File file1 = new File("D:\\L\\t1.sql");
        InputStream is = new FileInputStream(file1);
        //tSFTPClientUtil.upload("/home/temp", "", "t1.sql", is);
        tSFTPClientUtil.logout();
        is.close();
        //tSFTPClientUtil.logout();
    }

}
