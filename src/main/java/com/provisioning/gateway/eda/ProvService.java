package com.provisioning.gateway.eda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;


@Service
public class ProvService {

	private static final Logger logger = LoggerFactory.getLogger(ProvService.class);
    @Value("${eda.deprov.command}")
    private String deprov;

    @Value("${eda.prov.command}")
    private String prov1;

    @Value("${eda.prov.command1}")
    private String prov2;

    @Value("${eda.prov.IP}")
    private String IP;

    @Value("${eda.prov.port}")
    private Integer port;

    @Value("${eda.prov.timeoutconnectms}")
    private Integer timeoutConnectMs;

    @Value("${eda.userName}")
    private String edaUserName;

    @Value("${eda.password}")
    private String edaPassword;

    public Boolean sendRequest(String msisdn, Boolean isProv) {
        DataInputStream incomingStream = null;
        DataOutputStream outgoingStream = null;
        String replyMsg = "";
        boolean isLogin = false;
        boolean isLogout = false;

        int tokencount = 0;
        String input = "";
        Socket clientsock = null;
        long startTime = new Date().getTime();

        try {
            SocketAddress sockaddr = new InetSocketAddress(IP, port);
            clientsock = new Socket();
            //clientsock.setSoTimeout(timeoutSoMs);
            clientsock.connect(sockaddr, timeoutConnectMs);
            //Setting up objects to read and write data
            incomingStream = new DataInputStream(clientsock.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(incomingStream));
            outgoingStream = new DataOutputStream(clientsock.getOutputStream());
            StringBuilder incomingResponse = new StringBuilder();
            String buffer = "";

            int token;
            while ((token = incomingStream.read()) != -1) {
                incomingResponse.append((char) token);
                tokencount++;
                if (incomingResponse.toString().endsWith("Enter command:")) {
                    break;
                }
            }
            logger.info("[" + msisdn + "]<-" + incomingResponse.toString());
            incomingResponse.setLength(0);
            logger.info("[" + msisdn + "]Terminal is ready to accept commands");

            //Logging in
            String loginCommand = "LOGIN:"+edaUserName+":"+edaPassword+";" + "\r\n";
            outgoingStream.writeBytes(loginCommand);
            outgoingStream.flush();
            logger.info("[" + msisdn + " Login Command ]->" + loginCommand);
            //Reading Loggin result
            while ((token = incomingStream.read()) != -1) {
                incomingResponse.append((char) token);
                tokencount++;
                if (incomingResponse.toString().endsWith("RESP:0;")) {
                    isLogin = true;
                } else {
                }
                if (incomingResponse.toString().endsWith("Enter command:")) {
                    break;//Consuming Stream till we get Enter Command
                }
            }
            logger.info("[" + msisdn + "]<-" + incomingResponse.toString());
            incomingResponse.setLength(0);
            if(!isProv){
                //De Provision Command
                String deProvCommand = deprov.replace("%msisdn%", msisdn) + "\r\n";
                outgoingStream.writeBytes(deProvCommand);
                outgoingStream.flush();
                logger.info("[" + msisdn + " DeProvisioning Command ]->" + deProvCommand + "\r\n");
                //Reading Request result
                while ((token = incomingStream.read()) != -1) {
                    incomingResponse.append((char) token);
                    tokencount++;
                    if (incomingResponse.toString().endsWith("Enter command:")) {
                        break;//Consuming Stream till we get Enter Command
                    }
                }
                logger.info("<-" + incomingResponse.toString());
                incomingResponse.setLength(0);
            }else{
                //Provision Command 1
                String provisiongCommand1 = deprov.replace("%msisdn%", msisdn) + "\r\n";
                outgoingStream.writeBytes(provisiongCommand1);
                outgoingStream.flush();
                logger.info("[" + msisdn + " Provisioning Command 1 ]->" + provisiongCommand1 + "\r\n");
                //Reading Request result
                while ((token = incomingStream.read()) != -1) {
                    incomingResponse.append((char) token);
                    tokencount++;
                    if (incomingResponse.toString().endsWith("Enter command:")) {
                        break;//Consuming Stream till we get Enter Command
                    }
                }
                logger.info("<-" + incomingResponse.toString());
                incomingResponse.setLength(0);
                String provisiongCommand2 = prov1.replace("%msisdn%", msisdn) + "\r\n";
                outgoingStream.writeBytes(provisiongCommand2);
                outgoingStream.flush();
                logger.info("[" + msisdn + " Provisioning Command 2 ]->" + provisiongCommand2 + "\r\n");
                //Reading Request result
                while ((token = incomingStream.read()) != -1) {
                    incomingResponse.append((char) token);
                    tokencount++;
                /*if (incomingResponse.toString().endsWith(";")) {
                    break;
                }*/
                    if (incomingResponse.toString().endsWith("Enter command:")) {
                        break;//Consuming Stream till we get Enter Command
                    }
                }
                logger.info("<-" + incomingResponse.toString());
                incomingResponse.setLength(0);
                String provisiongCommand3 = prov2.replace("%msisdn%", msisdn) + "\r\n";
                outgoingStream.writeBytes(provisiongCommand3);
                outgoingStream.flush();
                logger.info("[" + msisdn + " Provisioning Command 3 ]->" + provisiongCommand3 + "\r\n");
                //Reading Request result
                while ((token = incomingStream.read()) != -1) {
                    incomingResponse.append((char) token);
                    tokencount++;
                /*if (incomingResponse.toString().endsWith(";")) {
                    break;
                }*/
                    if (incomingResponse.toString().endsWith("Enter command:")) {
                        break;//Consuming Stream till we get Enter Command
                    }
                }
                logger.info("<-" + incomingResponse.toString());
                incomingResponse.setLength(0);
            }

            //logger.debug("Received After Login:" + output.toString());
            //Logging OUT
            loginCommand = "LOGOUT;" + "\r\n";
            outgoingStream.writeBytes(loginCommand);
            outgoingStream.flush();
            logger.info("[" + msisdn + "]->" + loginCommand);

            //Reading Logout result
            while ((token = incomingStream.read()) != -1) {
                incomingResponse.append((char) token);
                tokencount++;
                if (incomingResponse.toString().endsWith("RESP:0;")) {
                    isLogout = true;
                    break;
                }
            }

            logger.info("[" + msisdn + "]<-" + incomingResponse.toString());
            logger.info("[" + msisdn + "]Response:" + replyMsg);

            incomingStream.close();
            outgoingStream.close();
            clientsock.close();
            logger.info("[" + msisdn + "][Connection closed]");

        } catch (Exception ex) {
            logger.error("[" + msisdn + "]" + ex.getMessage());
            return false;
        } finally {
            closeSilently(clientsock);
        }
        return true;
    }
    public void closeSilently(Socket s) {
        if (s != null) {
            try {
                s.close();
            } catch (IOException e2) {
                logger.error("Exception while closing socket:" + e2.toString());
            }
        }
    }
    public Boolean edaProv(String msisdn){
        return true;
    }
}
