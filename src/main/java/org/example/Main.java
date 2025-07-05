package org.example;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;
import java.util.List;

// 主类，程序入口所在类
public class Main {
    /**
     * 程序主入口方法，启动 FTP 服务器
     * @param args 命令行参数
     * @throws FtpException 启动 FTP 服务器过程中可能抛出的异常
     */
    public static void main(String[] args) throws FtpException {
        System.out.printf("Hello and welcome!\n");
        // 创建 FTP 服务器工厂实例，用于配置和创建 FTP 服务器
        FtpServerFactory serverFactory = new FtpServerFactory();
        // 创建监听器工厂实例，用于配置服务器监听端口和模式
        ListenerFactory listenerFactory = new ListenerFactory();
        // 设置 FTP 服务器监听端口为 2121
        listenerFactory.setPort(2121);

        // 如果服务器有公网 IP，需要设置公网 IP，用于被动模式下客户端连接
        // listenerFactory.setPassiveExternalAddress("your.public.ip.address"); 

        // 将配置好的监听器添加到服务器工厂
        serverFactory.addListener("default", listenerFactory.createListener());

        // 创建一个新的 FTP 用户
        BaseUser user = new BaseUser();
        // 设置用户名
        user.setName("admin");
        user.setPassword("1");
        
        // 根据操作系统类型设置不同的主目录路径
        String os = System.getProperty("os.name").toLowerCase();
        String homeDir;
        if (os.contains("win")) {
            homeDir = "E:\\log";
        } else {
            homeDir = System.getProperty("user.home") + "/log";
        }
        user.setHomeDirectory(homeDir);

        // 设置权限
        // 创建权限列表，用于管理用户权限
        List<Authority> authorities = new ArrayList<>();
        // 添加写入权限到权限列表
        authorities.add(new WritePermission());
        // 将权限列表设置给用户
        user.setAuthorities(authorities);

        // 获取服务器的用户管理器实例
        UserManager userManager = serverFactory.getUserManager();
        // 将新创建的用户保存到用户管理器中
        userManager.save(user);

        // 使用服务器工厂创建 FTP 服务器实例
        FtpServer server = serverFactory.createServer();
        // 启动 FTP 服务器
        server.start();
        System.out.println("FTP 服务器已启动");
    }
}