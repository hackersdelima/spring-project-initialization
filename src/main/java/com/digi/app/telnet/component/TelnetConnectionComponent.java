package com.digi.app.telnet.component;

import com.digi.app.telnet.config.TelnetConfig;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TelnetConnectionComponent {

    public List<List<String>> telnetConnectionAndResponseList(String command) {
        try {
            List<List<String>> serverResponses=new ArrayList<>();
            Socket client = new Socket(TelnetConfig.ip, TelnetConfig.port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF(command);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            String serverResponse = in.readUTF();
            serverResponses=conversion(serverResponse);
            client.close();
            return serverResponses;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
    public List<List<String>> conversion(String response) {

        //deleting unused part
        StringBuilder stringBuilder = new StringBuilder(response);
        int firstQuotationIndex = stringBuilder.indexOf("\"", 1);
        stringBuilder.delete(0, firstQuotationIndex);

        //splitting main data part into list
        String dataAfterRemovingFirstPart = stringBuilder.toString(); //main data working fine
        String replacingCommaaByColons = dataAfterRemovingFirstPart.replace("\",\"", "\"::\"");
        String[] splittedmaindatas = replacingCommaaByColons.split("::");

        List<List<String>> responseList = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        //for first list value of splittedmaindatas

        for (String data : splittedmaindatas) {
            String pre = "\"";
            String post = "\"";
            strings = Arrays.asList(data.replaceAll("^.*?" + pre, "")
                    .split(post + ".*?(\"|$)"));
            responseList.add(strings);
        }
        return responseList;
    }

   /* public String telnetConnectionAndResponseString(String command) {
        boolean connectionStatus = startConnection(TelnetConfig.ip, TelnetConfig.port);
        if (connectionStatus) {
            String response = sendMessage(command);
            stopConnection();
            return response;
        }
        return null;

    }
*/


    /*public boolean startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public String sendMessage(String msg) {
        String resp = "";
        try {
            out.println(msg);
            resp = in.readLine();
            return resp;
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/
}
