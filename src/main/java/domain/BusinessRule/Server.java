package domain.BusinessRule;


import domain.businessRuleGenerator.AttributeRule.AttributeCompareRule;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        new Server().startServer();
    }

    public void startServer() {
        final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

        Runnable serverTask = () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(5000);
                System.out.println("Waiting for client to send request...");
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    clientProcessingPool.submit(new ClientTask(clientSocket));
                }
            } catch (IOException e) {
                System.err.println("Unable to generate rule");
                e.printStackTrace();
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    private class ClientTask implements Runnable {
        private final Socket clientSocket;
        private ClientTask(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
        @Override
        public void run() {
            System.out.println("Connected");
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                String line = "";
                while(in.available() > 0) {
                    try {
                        line = in.readUTF();
                        System.out.println(line);


                        System.out.println(line);
                        out.println(handleInput(line));



                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("Closing connection");
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String handleInput(String data) {
        String returnValue = null;

        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(data);

        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        int id = Integer.parseInt(json.get("ruleId").toString());
        String method = json.get("method").toString();

        BusinessRuleFacadeInterface businessRuleFacade;
        switch (method) {
            case "generate":
                businessRuleFacade = new BusinessRuleFacade(id);
                returnValue = businessRuleFacade.createNewBusinessRule();
                break;
            case "set":
                businessRuleFacade = new BusinessRuleFacade(id);
                businessRuleFacade.setBusinessRule();
                break;
            case "update":
                businessRuleFacade = new BusinessRuleFacade(id);
                businessRuleFacade.updateBusinessRule();
                break;
            case "remove":
                businessRuleFacade = new BusinessRuleFacade(id);
                returnValue =  businessRuleFacade.removeBusinessRule();

                break;
        }
        return returnValue;
    }
}
