/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.pe.essalud.apireporseg.planilla.model;

/**
 *
 * @author externo.cestrada
 */
public class ReportMaternityBean {

    private String idSolicitud;
    private String nrDoc;
    private String nombre;
    private String nrCitt;
    private String fecIniPer;
    private String fecFinPer;
    private String cantDias;
    private String fec_cese;
    private String monto;

    /**
     * @return the idSolicitud
     */
    public String getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * @param idSolicitud the idSolicitud to set
     */
    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    /**
     * @return the nrDoc
     */
    public String getNrDoc() {
        return nrDoc;
    }

    /**
     * @param nrDoc the nrDoc to set
     */
    public void setNrDoc(String nrDoc) {
        this.nrDoc = nrDoc;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nrCitt
     */
    public String getNrCitt() {
        return nrCitt;
    }

    /**
     * @param nrCitt the nrCitt to set
     */
    public void setNrCitt(String nrCitt) {
        this.nrCitt = nrCitt;
    }

    /**
     * @return the fecIniPer
     */
    public String getFecIniPer() {
        return fecIniPer;
    }

    /**
     * @param fecIniPer the fecIniPer to set
     */
    public void setFecIniPer(String fecIniPer) {
        this.fecIniPer = fecIniPer;
    }

    /**
     * @return the fecFinPer
     */
    public String getFecFinPer() {
        return fecFinPer;
    }

    /**
     * @param fecFinPer the fecFinPer to set
     */
    public void setFecFinPer(String fecFinPer) {
        this.fecFinPer = fecFinPer;
    }

    /**
     * @return the cantDias
     */
    public String getCantDias() {
        return cantDias;
    }

    /**
     * @param cantDias the cantDias to set
     */
    public void setCantDias(String cantDias) {
        this.cantDias = cantDias;
    }

    /**
     * @return the fec_cese
     */
    public String getFec_cese() {
        return fec_cese;
    }

    /**
     * @param fec_cese the fec_cese to set
     */
    public void setFec_cese(String fec_cese) {
        this.fec_cese = fec_cese;
    }

    /**
     * @return the monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(String monto) {
        this.monto = monto;
    }

}
