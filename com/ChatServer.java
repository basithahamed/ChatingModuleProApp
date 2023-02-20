package com;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * ChatServer
 */
@ServerEndpoint("/chat")
public class ChatServer {
    static Set<User> arr=new HashSet<>();
   
    // private int userId;

    @OnOpen
    public void connect(Session s) {
        Map<String,List<String>> hashMap=s.getRequestParameterMap();
        System.out.println("Connected Successfully");        
        if(!alreadyExist(hashMap.get("name").get(0)))
        {
            // arr.add(this);
            arr.add(new User(s, hashMap.get("name").get(0)));
        }

    }
    @OnMessage
    public void retriveMessage(Session session,String message) throws ParseException {
        System.out.println("From front end:"+message);
        JSONObject js=(JSONObject)new JSONParser().parse(message);
        // System.out.println("name:"+js.get("userName"));
        for (User user : arr) {
            System.out.println("username:"+user.getUsername());
            
            if(user.getUsername().equals(js.get("Bharath")))
            {
                user.getSession().getAsyncRemote().sendText("addedSucessfully");
   
            }
        }
    }
    @OnError
    public void error(Throwable cause) {
        System.out.println("oops:"+cause);
        // e.printStackTrace();
    }
    public boolean alreadyExist(String name) {
        boolean result = false;
        try 
        {
            for (User chatServer : arr) {
                if (chatServer.getUsername().equals(name)) {
                    result=true;
                }
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}