/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.Status;
import DAO.UpdateUsers;
import java.sql.Date;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Safaa
 */
@Path("/update")
public class UpdateUserWS {
    /**
     * this method update firstName in table users by calling the
      updateFirstName from UpdateUsers
     *
     * @param userEmail
     * @param firstName
     * @return Status
     * @author Safaa
     */
    @POST
    @Path("/first")
    @Produces(MediaType.APPLICATION_JSON)
    
public Status updateFirstName(@HeaderParam("userEmail") String userEmail, @HeaderParam("userfirstName") String firstName) {
  
     
        UpdateUsers updateUsers = new UpdateUsers();

        boolean firstFlag = updateUsers.updateFirstName(userEmail, firstName);

       Status status = new Status();
        
        if (firstFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully firstName updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage(" firstName failed");
            
        }
    
      return status ;
    }//end of method FirstName

/////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * this method update lastName in table users by calling the updateLastName
      from UpdateUsers
     *
     * @param userEmail
     * @param lastName
     * @return Status
     * @author Safaa
     */
    @POST
    @Path("/last")
    @Produces(MediaType.APPLICATION_JSON)

    public Status updateLastName(@HeaderParam("userEmail") String userEmail, @HeaderParam("userlastName") String lastName) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean lastFlag = updateUsers.updateLastName(userEmail, lastName);

       Status status = new Status();
        
        if (lastFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully lastName updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage(" lastName failed");
            
        }
       
       return status;

    }//end of method
    
    
     /**
     * this method update lastName in table users by calling the updateLastName
      from UpdateUsers
     *
     * @param userEmail
     * @param lastName
     * @param firstName
     * @return Status
     * @author Safaa
     */
    @POST
    @Path("/full")
    @Produces(MediaType.APPLICATION_JSON)

    public Status updateFullName(@HeaderParam("userEmail") String userEmail, @HeaderParam("userfirstName") String firstName,@HeaderParam("userlastName") String lastName ) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean fullFlag = updateUsers.updateFullName(userEmail, firstName, lastName);

       Status status = new Status();
        
        if (fullFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully lastName updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage(" fullName failed");
            
        }
       
       return status;

    }//end of method


//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *  this method update date in table users by calling the update updateBirthDate
      from UpdateUsers
     * @param userEmail
     * @param date
     * @return Status
     * @author Safaa
     * 
     */
    @POST
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)

    public Status updateDate(@HeaderParam("userEmail") String userEmail, @HeaderParam("myDate") Date date) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean dateFlag = updateUsers.updateBirthDate(userEmail, date);
          Status status = new Status();
        
        if (dateFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully date updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage(" date update failed");
            
        }
     
       return status;


       

    }//end of method 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * this method update gender in table users by calling the updateGender
      from UpdateUsers
     * @param userEmail
     * @param gender
     * @return Status
     * @author Safaa
     */
    @POST
    @Path("/gender")
    @Produces(MediaType.APPLICATION_JSON)

    public Status updateGender(@HeaderParam("userEmail") String userEmail, @HeaderParam("gender") int gender) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean genderFlag = updateUsers.updateGender(userEmail, gender);

       Status status = new Status();
        
        if (genderFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully gender updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage(" gender failed");
            
        }
       // return "{status :" + statusResult + "}";
       return status;


    }//end of method  getGender

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *this method update phoneNum in table users by calling the updatePhoneNum
      from UpdateUsers
     * @param userEmail
     * @param phone
     * @return Status
     *  @author Safaa
     */
    @POST
    @Path("/phone")
    @Produces(MediaType.APPLICATION_JSON)

    public Status updatephonNum(@HeaderParam("userEmail") String userEmail, @HeaderParam("phone") String phone) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean phoneFlag = updateUsers.updatePhoneNum(userEmail, phone);

       Status status = new Status();
        
        if (phoneFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully phone updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage(" phone update failed");
            
        }
       
       return status;

    }//end of method getphonNum

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *this method update homeNum in table users by calling the updateHomeNum(
      from UpdateUsers
     * @param userEmail
     * @param home
     * @return Status
     * @author Safaa
     */
    @POST
    @Path("/home")
    @Produces(MediaType.APPLICATION_JSON)

    public Status updateHomeNum(@HeaderParam("userEmail") String userEmail, @HeaderParam("home") String home) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean homeFlag = updateUsers.updateHomeNum(userEmail, home);
        
         Status status = new Status();
        
        if (homeFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully home number updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage(" home number update failed");
            
        }
       
       return status;


    }//end of method 

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * *this method update country in table users by calling the updateCountry((
      from UpdateUsers
    
     * @author Safaa
     * @param userEmail
     * @param country
    * @return Status
     */
    @POST
    @Path("/country")
    @Produces(MediaType.APPLICATION_JSON)

    public Status updateCountry(@HeaderParam("userEmail") String userEmail, @HeaderParam("country") String country) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean countryFlag = updateUsers.updateCountry(userEmail, country);

         Status status = new Status();
        
        if (countryFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully country updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage(" country update failed");
            
        }
       
       return status;

    }//end of method 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *this method update city in table users by calling the updateCity(
      from UpdateUsers
    
     * @author Safaa
     * @param userEmail
     * @param city
     * @return Status
     */
    @POST
    @Path("/city")
    @Produces(MediaType.APPLICATION_JSON)

    public Status updateCity(@HeaderParam("userEmail") String userEmail, @HeaderParam("city") String city) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean cityFlag = updateUsers.updateCity(userEmail, city);

        Status status = new Status();
        
        if (cityFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully city updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage(" city update failed");
            
        }
       // return "{status :" + statusResult + "}";
       return status;

    }//end of method get
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *this method update address in table users by calling the updateAddress(
      from UpdateUsers
     * @return Status
     * @author Safaa
     * @param userEmail
     * @param address
 
     */
    @POST
    @Path("/address")
   @Produces(MediaType.APPLICATION_JSON)

    public Status updateAddress(@HeaderParam("userEmail") String userEmail, @HeaderParam("address") String address) {
        
        
        UpdateUsers updateUsers = new UpdateUsers();
        
         boolean addressFlag=updateUsers.updateAddress(userEmail, address);
         
        Status status = new Status();
       
        
        if (addressFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully address updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage(" address update failed");
            
        }
     
       return status;
    }//end of method 
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *this method update type in table users by calling the updateType (
      from UpdateUsers
    
     * @author Safaa
     * @param userEmail
     * @param type
      * @return Status
     */
    @POST
    @Path("/type")
    @Produces(MediaType.APPLICATION_JSON)

    public Status updateType(@HeaderParam("userEmail") String userEmail, @HeaderParam("type") int type) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean typeFlag = updateUsers.updateType(userEmail, type);
        Status status = new Status();
        
        if (typeFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully type updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage("type update failed");
            
        }
    
       return status;
        
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    /**
    *this method update password in table users by calling the updatePassword (
      from UpdateUsers
   
     * @author Safaa
     * @param userEmail
     * @param pass
      * @return Status
     */
    @POST
    @Path("/pass")
    @Produces(MediaType.APPLICATION_JSON)

    public Status updatePassword(@HeaderParam("userEmail") String userEmail, @HeaderParam("pass") String pass) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean passwordFlag = updateUsers.updatePassword(userEmail, pass);

         Status status = new Status();
        
        if (passwordFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully password updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage("password update failed");
            
        }
       // return "{status :" + statusResult + "}";
       return status;
        

    }//end of method 
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *this method update longitude in table users by calling the updateLongitude(
      from UpdateUsers
  
     * @author Safaa
     * @param userEmail
     * @param longit
     * @return Status
     */

    @POST
    @Path("/long")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Status updateLongitude(@HeaderParam("userEmail") String userEmail, @HeaderParam("lon") String longit) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean longitudeFlag = updateUsers.updateLongitude(userEmail, longit);

          Status status = new Status();
        
        if (longitudeFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully Longitude updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage("Longitude update failed");
            
        }
       // return "{status :" + statusResult + "}";
       return status;
        
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *this method update latitude in table users by calling the updateLatitude (
      from UpdateUsers
     * @author Safaa
     * @param userEmail
     * @param latitude
     * @return Status
     */
    @POST
    @Path("/lat")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Status updateLatitude(@HeaderParam("userEmail") String userEmail, @HeaderParam("lat") String latitude) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean latitudeFlag = updateUsers.updateLatitude(userEmail, latitude);
        Status status = new Status();
        
        if (latitudeFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully Latitude updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage("Latitude update failed");
            
        }
       // return "{status :" + statusResult + "}";
       return status;

       
    }//end of method Latitude

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *this method update latitude in table users by calling the updateLatitude (
      from UpdateUsers
     * @author Safaa
     * @param userEmail
     * @param img
     * @return Status
     */
    @POST
    @Path("/img")
    @Produces(MediaType.APPLICATION_JSON)
    public Status updateImg(@HeaderParam("userEmail") String userEmail, @HeaderParam("img") String img) {

        UpdateUsers updateUsers = new UpdateUsers();

        boolean imgFlag = updateUsers.updateImg(userEmail, img);
  Status status = new Status();
        
        if (imgFlag) {
        
            status.setStatus(1);
           status.setMessage("Successfully image updated");
            
        } else {
            
            status.setStatus(0);
           status.setMessage("image update failed");
            
        }
      
       return status;
       
    }//end of method getImg

//    /**
//     * this method update firstName in table users by calling the
//      updateFirstName from UpdateUsers
//     *
//     * @param userEmail
//     * @param firstName
//     * @return Status
//     * @author Safaa
//     */
//    @GET
//    @Path("/first/{userEmail}/{userfirstName}")
//    @Produces(MediaType.APPLICATION_JSON)
//    
//public Status updateFirstName(@PathParam("userEmail") String userEmail, @PathParam("userfirstName") String firstName) {
//  
//     
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean firstFlag = updateUsers.updateFirstName(userEmail, firstName);
//
//       Status status = new Status();
//        
//        if (firstFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully firstName updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage(" firstName failed");
//            
//        }
//    
//      return status ;
//    }//end of method FirstName
//
///////////////////////////////////////////////////////////////////////////////////////////////////////
//    /**
//     * this method update lastName in table users by calling the updateLastName
//      from UpdateUsers
//     *
//     * @param userEmail
//     * @param lastName
//     * @return Status
//     * @author Safaa
//     */
//    @GET
//    @Path("/last/{userEmail}/{userlastName}")
//    @Produces(MediaType.APPLICATION_JSON)
//
//    public Status updateLastName(@PathParam("userEmail") String userEmail, @PathParam("userlastName") String lastName) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean lastFlag = updateUsers.updateLastName(userEmail, lastName);
//
//       Status status = new Status();
//        
//        if (lastFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully lastName updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage(" lastName failed");
//            
//        }
//       
//       return status;
//
//    }//end of method
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /**
//     *  this method update date in table users by calling the update updateBirthDate
//      from UpdateUsers
//     * @param userEmail
//     * @param date
//     * @return Status
//     * @author Safaa
//     * 
//     */
//    @GET
//    @Path("/date/{userEmail}/{myDate}")
//    @Produces(MediaType.APPLICATION_JSON)
//
//    public Status updateDate(@PathParam("userEmail") String userEmail, @PathParam("myDate") Date date) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean dateFlag = updateUsers.updateBirthDate(userEmail, date);
//          Status status = new Status();
//        
//        if (dateFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully date updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage(" date update failed");
//            
//        }
//     
//       return status;
//
//
//       
//
//    }//end of method 
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /**
//     * this method update gender in table users by calling the updateGender
//      from UpdateUsers
//     * @param userEmail
//     * @param gender
//     * @return Status
//     * @author Safaa
//     */
//    @GET
//    @Path("/gender/{userEmail}/{gender}")
//    @Produces(MediaType.APPLICATION_JSON)
//
//    public Status updateGender(@PathParam("userEmail") String userEmail, @PathParam("gender") int gender) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean genderFlag = updateUsers.updateGender(userEmail, gender);
//
//       Status status = new Status();
//        
//        if (genderFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully gender updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage(" gender failed");
//            
//        }
//       // return "{status :" + statusResult + "}";
//       return status;
//
//
//    }//end of method  getGender
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /**
//     *this method update phoneNum in table users by calling the updatePhoneNum
//      from UpdateUsers
//     * @param userEmail
//     * @param phone
//     * @return Status
//     *  @author Safaa
//     */
//    @GET
//    @Path("/phone/{userEmail}/{phone}")
//    @Produces(MediaType.APPLICATION_JSON)
//
//    public Status updatephonNum(@PathParam("userEmail") String userEmail, @PathParam("phone") String phone) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean phoneFlag = updateUsers.updatePhoneNum(userEmail, phone);
//
//       Status status = new Status();
//        
//        if (phoneFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully phone updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage(" phone update failed");
//            
//        }
//       
//       return status;
//
//    }//end of method getphonNum
//
//    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /**
//     *this method update homeNum in table users by calling the updateHomeNum(
//      from UpdateUsers
//     * @param userEmail
//     * @param home
//     * @return Status
//     * @author Safaa
//     */
//    @GET
//    @Path("/home/{userEmail}/{home}")
//    @Produces(MediaType.APPLICATION_JSON)
//
//    public Status updateHomeNum(@PathParam("userEmail") String userEmail, @PathParam("home") String home) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean homeFlag = updateUsers.updateHomeNum(userEmail, home);
//        
//         Status status = new Status();
//        
//        if (homeFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully home number updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage(" home number update failed");
//            
//        }
//       
//       return status;
//
//
//    }//end of method 
//
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /**
//     * *this method update country in table users by calling the updateCountry((
//      from UpdateUsers
//    
//     * @author Safaa
//     * @param userEmail
//     * @param country
//    * @return Status
//     */
//    @GET
//    @Path("/country/{userEmail}/{country}")
//    @Produces(MediaType.APPLICATION_JSON)
//
//    public Status updateCountry(@PathParam("userEmail") String userEmail, @PathParam("country") String country) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean countryFlag = updateUsers.updateCountry(userEmail, country);
//
//         Status status = new Status();
//        
//        if (countryFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully country updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage(" country update failed");
//            
//        }
//       
//       return status;
//
//    }//end of method 
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    /**
//     *this method update city in table users by calling the updateCity(
//      from UpdateUsers
//    
//     * @author Safaa
//     * @param userEmail
//     * @param city
//     * @return Status
//     */
//    @GET
//    @Path("/city/{userEmail}/{city}")
//    @Produces(MediaType.APPLICATION_JSON)
//
//    public Status updateCity(@PathParam("userEmail") String userEmail, @PathParam("city") String city) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean cityFlag = updateUsers.updateCity(userEmail, city);
//
//        Status status = new Status();
//        
//        if (cityFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully city updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage(" city update failed");
//            
//        }
//       // return "{status :" + statusResult + "}";
//       return status;
//
//    }//end of method get
//    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    /**
//     *this method update address in table users by calling the updateAddress(
//      from UpdateUsers
//     * @return Status
//     * @author Safaa
//     * @param userEmail
//     * @param address
// 
//     */
//    @GET
//    @Path("/address/{userEmail}/{address}")
//   @Produces(MediaType.APPLICATION_JSON)
//
//    public Status updateAddress(@PathParam("userEmail") String userEmail, @PathParam("address") String address) {
//        
//        
//        UpdateUsers updateUsers = new UpdateUsers();
//        
//         boolean addressFlag=updateUsers.updateAddress(userEmail, address);
//         
//        Status status = new Status();
//       
//        
//        if (addressFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully address updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage(" address update failed");
//            
//        }
//     
//       return status;
//    }//end of method 
//    ///////////////////////////////////////////////////////////////////////////////////////////////////
//
//    /**
//     *this method update type in table users by calling the updateType (
//      from UpdateUsers
//    
//     * @author Safaa
//     * @param userEmail
//     * @param type
//      * @return Status
//     */
//    @GET
//    @Path("/type/{userEmail}/{type}")
//    @Produces(MediaType.APPLICATION_JSON)
//
//    public Status updateType(@PathParam("userEmail") String userEmail, @PathParam("type") int type) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean typeFlag = updateUsers.updateType(userEmail, type);
//        Status status = new Status();
//        
//        if (typeFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully type updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage("type update failed");
//            
//        }
//    
//       return status;
//        
//    }
//    ///////////////////////////////////////////////////////////////////////////////////////////////////
//
//    /**
//    *this method update password in table users by calling the updatePassword (
//      from UpdateUsers
//   
//     * @author Safaa
//     * @param userEmail
//     * @param pass
//      * @return Status
//     */
//    @GET
//    @Path("/pass/{userEmail}/{pass}")
//    @Produces(MediaType.APPLICATION_JSON)
//
//    public Status updatePassword(@PathParam("userEmail") String userEmail, @PathParam("pass") String pass) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean passwordFlag = updateUsers.updatePassword(userEmail, pass);
//
//         Status status = new Status();
//        
//        if (passwordFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully password updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage("password update failed");
//            
//        }
//       // return "{status :" + statusResult + "}";
//       return status;
//        
//
//    }//end of method 
//    //////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    /**
//     *this method update longitude in table users by calling the updateLongitude(
//      from UpdateUsers
//  
//     * @author Safaa
//     * @param userEmail
//     * @param longit
//     * @return Status
//     */
//
//    @GET
//    @Path("/lon/{userEmail}/{lon}")
//    @Produces(MediaType.APPLICATION_JSON)
//    
//    public Status updateLongitude(@PathParam("userEmail") String userEmail, @PathParam("lon") String longit) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean longitudeFlag = updateUsers.updateLongitude(userEmail, longit);
//
//          Status status = new Status();
//        
//        if (longitudeFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully Longitude updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage("Longitude update failed");
//            
//        }
//       // return "{status :" + statusResult + "}";
//       return status;
//        
//    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    /**
//     *this method update latitude in table users by calling the updateLatitude (
//      from UpdateUsers
//     * @author Safaa
//     * @param userEmail
//     * @param latitude
//     * @return Status
//     */
//    @GET
//    @Path("/lat/{userEmail}/{lat}")
//    @Produces(MediaType.APPLICATION_JSON)
//    
//    public Status updateLatitude(@PathParam("userEmail") String userEmail, @PathParam("lat") String latitude) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean latitudeFlag = updateUsers.updateLatitude(userEmail, latitude);
//        Status status = new Status();
//        
//        if (latitudeFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully Latitude updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage("Latitude update failed");
//            
//        }
//       // return "{status :" + statusResult + "}";
//       return status;
//
//       
//    }//end of method Latitude
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /**
//     *this method update latitude in table users by calling the updateLatitude (
//      from UpdateUsers
//     * @author Safaa
//     * @param userEmail
//     * @param img
//     * @return Status
//     */
//    @GET
//    @Path("/img/{userEmail}/{img}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Status updateImg(@PathParam("userEmail") String userEmail, @PathParam("img") String img) {
//
//        UpdateUsers updateUsers = new UpdateUsers();
//
//        boolean imgFlag = updateUsers.updateImg(userEmail, img);
//  Status status = new Status();
//        
//        if (imgFlag) {
//        
//            status.setStatus(1);
//           status.setMessage("Successfully image updated");
//            
//        } else {
//            
//            status.setStatus(0);
//           status.setMessage("image update failed");
//            
//        }
//      
//       return status;
//       
//    }//end of method getImg

}//end of class
