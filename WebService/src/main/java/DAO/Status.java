/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Relative;
import dto.User;

/**
 *
 * @author Safaa Forget
 */
public class Status {
   
    int status;

   
    String message;
    
    User user;
    Relative relative;

    public Relative getRelative() {
        return relative;
    }

    public void setRelative(Relative relative) {
        this.relative = relative;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
