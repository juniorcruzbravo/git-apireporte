/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.pe.essalud.apireporseg.planilla.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author cristhian.estrada
 */
public class UacUserBean implements Serializable{

    private static long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    private String idUser;
    private String login;
    private String pass;
    private String active;
    private String name;
    private Date dtInsert;
    private Date dtUpdate;
    private Integer exist;
    private Integer type;
    private Integer passWarning;
    private Integer passExpire;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }


    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the active
     */
    public String getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dtInsert
     */
    public Date getDtInsert() {
        return dtInsert;
    }

    /**
     * @param dtInsert the dtInsert to set
     */
    public void setDtInsert(Date dtInsert) {
        this.dtInsert = dtInsert;
    }

    /**
     * @return the dtUpdate
     */
    public Date getDtUpdate() {
        return dtUpdate;
    }

    /**
     * @param dtUpdate the dtUpdate to set
     */
    public void setDtUpdate(Date dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    /**
     * @return the exist
     */
    public Integer getExist() {
        return exist;
    }

    /**
     * @param exist the exist to set
     */
    public void setExist(Integer exist) {
        this.exist = exist;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the passWarning
     */
    public Integer getPassWarning() {
        return passWarning;
    }

    /**
     * @param passWarning the passWarning to set
     */
    public void setPassWarning(Integer passWarning) {
        this.passWarning = passWarning;
    }

    /**
     * @return the passExpire
     */
    public Integer getPassExpire() {
        return passExpire;
    }

    /**
     * @param passExpire the passExpire to set
     */
    public void setPassExpire(Integer passExpire) {
        this.passExpire = passExpire;
    }

    
    
}
