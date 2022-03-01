package gob.pe.essalud.apireporseg.util;

import com.jcraft.jsch.*;

import java.io.InputStream;

public class SftpClient {

    private String server;
    private int port;
    private String username;
    private String password;

    public void upload(InputStream uploadData, String Path, String FileName) {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(getUsername(),
                    getServer(), getPort());
            session.setPassword(getPassword());
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            config.put("PreferredAuthentications",
                    "publickey,keyboard-interactive,password");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp channelSftp = (ChannelSftp) channel;
            try {
                channelSftp.cd(Path);
                channelSftp.put(uploadData,  FileName);
            } catch (SftpException e) {
                System.out.println("Creating Directory...");
                String[] complPath = Path.split("\\\\");
                for (String dir : complPath) {
                    if (dir.length() > 0) {
                        try {
                            System.out.println("Current Dir : " + channelSftp.pwd());
                            channelSftp.cd(dir);
                        } catch (SftpException e2) {
                            channelSftp.mkdir(dir);
                            channelSftp.cd(dir);
                        }
                    }
                }
                System.out.println("Current Dir : " + channelSftp.pwd());
                channelSftp.put(uploadData,  FileName);
            }
            channelSftp.exit();
            session.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
