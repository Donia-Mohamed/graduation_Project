/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import model.AlzheimerDB;

/**
 *
 * @author Safaa 
 */

@Path("/rest")
public class AlzheimerWS {
  
    /**
   * This method is example  for using web service.
   * @return 
   */
    
   @GET
  @Produces("text/html")
    public String getName(){
        AlzheimerDB db=new AlzheimerDB();
        
        return "<h1>Hello </h1>";
       // return "<h1> Value is"+db.selectMemory()+" </h1>";
    }
    
    
    
}
