/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Memory;
import java.util.ArrayList;

/**
 *
 * @author Doaa
 */
public interface MemoryDaoInterface {
    
     public int saveMemoryText(String patientEmail, String relativeEmail, String text, String date);
     public ArrayList<Memory> getMemories(String patientEmail);
     public int deleteMemory(String patientEmail,int memoryId);
     public int updateTextMemory(int memoryId, String date, String text);
     public int getRelativeId(String relativeEmail);
}
