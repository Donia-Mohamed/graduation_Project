/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.fcm;

/**
 *
 * @author atef
 */
public class RequestLocationData extends Data{

    private final static int REQUEST_ID = 9393;
    
    String firstname;
    String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    String email;
    String imageUrl;

    public RequestLocationData(String firstname, String lastname, String email, String imageUrl) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.imageUrl = imageUrl;
    }

   
    
    public RequestLocationData() {
        this.requestId = REQUEST_ID;
    }

    
}
