package com;

import javax.websocket.Session;

/**
 * User
 */
public class User {

    private Session session;
    private String username;
    private int userId;

    User(Session session,String username){
        this.session=session;
        this.username=username;
        

    }
    public Session getSession() {
        return session;
    }
    public int getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    

}