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
public class WebSerLogBean implements Serializable{
    
    private static long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    
    private String login;
    private int idLog;
    private int idUserPk;
    private String action;
    private String service;
    private String codIp;
    private String codMac;
    private Date dtConsulta;
    private String txtPlotRequest;
    private String txtPlotResponse;
    private String idUsuario;

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
     * @return the idLog
     */
    public int getIdLog() {
        return idLog;
    }

    /**
     * @param idLog the idLog to set
     */
    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    /**
     * @return the idUserPk
     */
    public int getIdUserPk() {
        return idUserPk;
    }

    /**
     * @param idUserPk the idUserPk to set
     */
    public void setIdUserPk(int idUserPk) {
        this.idUserPk = idUserPk;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the codIp
     */
    public String getCodIp() {
        return codIp;
    }

    /**
     * @param codIp the codIp to set
     */
    public void setCodIp(String codIp) {
        this.codIp = codIp;
    }

    /**
     * @return the codMac
     */
    public String getCodMac() {
        return codMac;
    }

    /**
     * @param codMac the codMac to set
     */
    public void setCodMac(String codMac) {
        this.codMac = codMac;
    }

    /**
     * @return the dtConsulta
     */
    public Date getDtConsulta() {
        return dtConsulta;
    }

    /**
     * @param dtConsulta the dtConsulta to set
     */
    public void setDtConsulta(Date dtConsulta) {
        this.dtConsulta = dtConsulta;
    }

    /**
     * @return the txtPlotRequest
     */
    public String getTxtPlotRequest() {
        return txtPlotRequest;
    }

    /**
     * @param txtPlotRequest the txtPlotRequest to set
     */
    public void setTxtPlotRequest(String txtPlotRequest) {
        this.txtPlotRequest = txtPlotRequest;
    }

    /**
     * @return the txtPlotResponse
     */
    public String getTxtPlotResponse() {
        return txtPlotResponse;
    }

    /**
     * @param txtPlotResponse the txtPlotResponse to set
     */
    public void setTxtPlotResponse(String txtPlotResponse) {
        this.txtPlotResponse = txtPlotResponse;
    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
