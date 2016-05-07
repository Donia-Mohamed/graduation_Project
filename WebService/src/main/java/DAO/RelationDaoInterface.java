/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Relative;
import dto.User;
import java.util.ArrayList;

/**
 *
 * @author atef
 */
public interface RelationDaoInterface {
    
    /**
     * this method used to get List of relatives of this patients
     * @param patientEmail
     * @return ArrayList
     */
    public ArrayList<Relative> getRelatives(String patientEmail);
    
    /**
     * this method used to get list of patients to this relative.
     * @param relativeEmail
     * @return  ArrayList
     */
    public ArrayList<User> getPatients(String relativeEmail);
    
    
    
    
    /**
     * this method is used to remove patient from patients list in relative list. 
     * @param patientEmail
     * @param relativeEmail
     * @return 
     */
    public boolean removePatient(String patientEmail,String relativeEmail);
    
}
