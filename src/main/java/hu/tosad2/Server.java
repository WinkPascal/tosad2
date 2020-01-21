package hu.tosad2;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
// A Java program for a Server
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import domain.BusinessRule.BusinessRuleFacade;
import domain.BusinessRule.BusinessRuleFacadeInterface;

public class Server
{
	
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;

    public static void main(String args[]) {
        Server server = new Server(5000);
    }
    
    // constructor with port
    public Server(int port){
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            socket = server.accept();
            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            
            String line = "";

            // reads message from client until "Over" is sent
            while (!line.equals("*STOP*")) {
                try {
                    line = in.readUTF();
                    handleInput(line);
                }
                catch(IOException i) {
                    System.out.println(i);
                }
            }
            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }
    
    private void handleInput(String data) {
    	JSONParser parser = new JSONParser();
    	JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int id =Integer.parseInt(json.get("id").toString());
		String method = json.get("method").toString();
		
    	BusinessRuleFacadeInterface businessRuleFacade;
    	switch(method) {
    	case "generate":
    		businessRuleFacade = new BusinessRuleFacade(id);
    		businessRuleFacade.createNewBusinessRule();
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
    		businessRuleFacade.removeBusinessRule();
    		break;
    	}
    }
}
