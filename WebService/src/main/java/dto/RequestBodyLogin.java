/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dono
 */
@XmlRootElement
public class RequestBodyLogin {@XmlElement String hello;
    @XmlElement public String email;
    @XmlElement public String password;
    
}
