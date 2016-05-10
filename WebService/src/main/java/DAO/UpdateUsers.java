/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.AlzheimerDB;

/**
 *
 * @author Safaa
 */
public class UpdateUsers {

    /**
     * this method used to update fistName in table user
     *
     * @param email
     * @param fName
     * @return boolean (true when Success to update first_name in table users
     * and return false when failure)
     * @author Safaa
     */
    public boolean updateFirstName(String email, String fName) {
        boolean flag = false;
        String updateFirstName = "update users SET first_name = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updateFirstName);

            ps.setString(1, fName);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            System.out.println(rs + " records updated");

            ps.close();

            connection.close();

        }//end of try
        catch (SQLException ex) {

            ex.printStackTrace();

            flag = false;

        }//enf of catch

        return flag;

    }//end of method updateFirstName

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * this method used to update lastName in table user
     *
     * @param emai
     * @param lName
     * @return boolean (true when Success to update last_name in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateLastName(String emai, String lName) {

        boolean flag = false;

        String updateLastNamesql = "update users SET last_name = ? where email = ?";

        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updateLastNamesql);

            ps.setString(1, lName);

            ps.setString(2, emai);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        }//end of try
        catch (SQLException ex) {

            ex.printStackTrace();

            flag = false;
        }//end of catch
        return flag;
    }//end of method updateLastName

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param email
     * @param date
     * @return boolean (true when Success to update birthday in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateBirthDate(String email, Date date) {

        boolean flag = false;

        String updatedatesql = "update users SET birthday = ? where email = ?";

        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updatedatesql);

            ps.setDate(1, date);

            ps.setString(2, email);

            ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        } //end of try
        catch (SQLException ex) {

            ex.printStackTrace();

            flag = false;

        }//end of catch

        return flag;
    }//end of method updateBirthDate
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param email
     * @param phonNum
     * @return boolean (true when Success to update phone_num in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updatePhoneNum(String email, String phonNum) {
        boolean flag = false;
        String updatePhoneNumsql = "update users SET phone_num = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updatePhoneNumsql);

            ps.setString(1, phonNum);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        }//end of try
        catch (SQLException ex) {
            ex.printStackTrace();
        }//end of catch
        return flag;
    }//end of method

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param email
     * @param gender
     * @return boolean (true when Success to update gender in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateGender(String email, int gender) {

        boolean flag = false;
        System.out.println("gender :" + gender);
        String updatGendersql = "update users SET gender = ? where email = ?";

        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updatGendersql);

            ps.setInt(1, gender);

            ps.setString(2, email);

            ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        } //end of try
        catch (SQLException ex) {

            ex.printStackTrace();

            flag = false;

        }//end of catch

        return flag;
    }//end of method
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param email
     * @param phonNum
     * @return boolean (true when Success to update phone_num in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updatephonNum(String email, String phone) {
        boolean flag = false;
        String updatePhoneNumsql = "update users SET phone_num = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updatePhoneNumsql);

            ps.setString(1, phone);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        }//end of try
        catch (SQLException ex) {
            ex.printStackTrace();
        }//end of catch
        return flag;
    }//end of method
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param email
     * @param homeNum
     * @return boolean (true when Success to update home_num in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateHomeNum(String email, String homeNum) {

        boolean flag = false;

        String updateHomeNumsql = "update users SET home_num = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updateHomeNumsql);

            ps.setString(1, homeNum);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        }//end of try
        catch (SQLException ex) {

            ex.printStackTrace();
            flag = false;
        }//end of catch
        return flag;
    }//end of method
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param email
     * @param country
     * @return boolean (true when Success to update country in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateCountry(String email, String country) {

        boolean flag = false;

        String updateCountrysql = "update users SET country = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updateCountrysql);

            ps.setString(1, country);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        }//end of try
        catch (SQLException ex) {

            ex.printStackTrace();
            flag = false;
        }//end of catch
        return flag;
    }//end of method

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param email
     * @param city
     * @return boolean (true when Success to update city in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateCity(String email, String city) {

        boolean flag = false;

        String updateCitysql = "update users SET city = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updateCitysql);

            ps.setString(1, city);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        }//end of try
        catch (SQLException ex) {

            ex.printStackTrace();
            flag = false;
        }//end of catch
        return flag;
    }//end of method

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param email
     * @param address
     * @return boolean (true when Success to update address in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateAddress(String email, String address) {

        boolean flag = false;

        String updateAddresssql = "update users SET address = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updateAddresssql);

            ps.setString(1, address);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        }//end of try 
        catch (SQLException ex) {

            ex.printStackTrace();
            flag = false;
        }//end of catch
        return flag;
    }//end of method

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param email
     * @param type
     * @return boolean (true when Success to update type in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateType(String email, int type) {

        boolean flag = false;

        String updateTypesql = "update users SET type = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updateTypesql);

            ps.setInt(1, type);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        }//end of try 
        catch (SQLException ex) {

            ex.printStackTrace();
            flag = false;
        }//end of catch
        return flag;
    }//end of method

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param email
     * @param pass
     * @return boolean (true when Success to update password in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updatePassword(String email, String pass) {

        boolean flag = false;

        String updatePasswordsql = "update users SET password = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updatePasswordsql);

            ps.setString(1, pass);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        } //end of try
        catch (SQLException ex) {

            ex.printStackTrace();
            flag = false;
        }//end of catch
        return flag;
    }//end of method
    //////// /////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param email
     * @param longitude
     * @return boolean (true when Success to update longitude in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateLongitude(String email, String longitude) {

        boolean flag = false;

        String updateLongisql = "update users SET longitude = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updateLongisql);

            ps.setString(1, longitude);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        }//end of try
        catch (SQLException ex) {

            ex.printStackTrace();
            flag = false;
        }//end of catch
        return flag;
    }//end of method

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param email
     * @param latitude
     * @return boolean (true when Success to update latitude in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateLatitude(String email, String latitude) {

        boolean flag = false;

        String updateLatitsql = "update users SET latitude = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updateLatitsql);

            ps.setString(1, latitude);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        }//end of try 
        catch (SQLException ex) {

            ex.printStackTrace();
            flag = false;
        }//end of catch
        return flag;
    }//end of method

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param email
     * @param img
     * @return boolean (true when Success to update image_url in table users and
     * return false when failure)
     * @author Safaa
     */
    public boolean updateImg(String email, String img) {

        boolean flag = false;

        String updateLatitsql = "update users SET image_url = ? where email = ?";
        try {

            AlzheimerDB alzheimerDB = new AlzheimerDB();

            Connection connection = alzheimerDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(updateLatitsql);

            ps.setString(1, img);

            ps.setString(2, email);

            int rs = ps.executeUpdate();

            flag = true;

            ps.close();

            connection.close();

        } //end of try
        catch (SQLException ex) {

            ex.printStackTrace();
            flag = false;
        }//end of catch
        return flag;
    }//end of method updateImg

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////           
}//end of class
