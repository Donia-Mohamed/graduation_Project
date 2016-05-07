/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import dto.Relationship;
import dto.Relative;
import dto.User;
import java.util.ArrayList;

/**
 *
 * @author atef
 */
public interface RequestDaoInterface {
    
    /**
     * this method used to get list of user requests 
     * @param patientEmail
     * @return array of users that represents relatives
     */
    public ArrayList<Relative>  getRequests(String patientEmail);
    
    
    /**
     * this method used to respond to request with true for accept case and false for refuse.
     * @param patientEmail
     * @param relativeEmail
     * @param respond
     * @return boolean that represents if it was successful operation or not.
     */
   public boolean respondToRequest(String patientEmail,String relativeEmail,boolean respond);
   
   /**
    * this method used to add a patient using its email by Relative
    * @param relativeEmail 
    * @param patientEmail
    * @return boolean that represent state
    */
   public boolean addRequest(String relativeEmail,String patientEmail,int familyPosition);
   
   
  
}



