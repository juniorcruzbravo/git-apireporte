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
public class InabilityBean {
   
    private int idSolicitud;                 
    private String tpdocAseg;
    private String nrdocAseg;
    private String nombres;
    private String nrruc;
    private String rzsocial;
    private String feciniSol;
    private String fecfinSol;
    private String feciniVac;
    private String fecfinVac;
    private String fecCese;
    private double montoSol;
    private String nrcitt;
    private String feciniCitt;
    private String fecfinCitt;
    private String tpcitt;
    private String esSectorPrivado;
    private String estaTrabajando;
    private String tieneSctr;
    private String tieneDeclaracionJurada;

    /**
     * @return the idSolicitud
     */
    public int getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * @param idSolicitud the idSolicitud to set
     */
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    /**
     * @return the tpdocAseg
     */
    public String getTpdocAseg() {
        return tpdocAseg;
    }

    /**
     * @param tpdocAseg the tpdocAseg to set
     */
    public void setTpdocAseg(String tpdocAseg) {
        this.tpdocAseg = tpdocAseg;
    }

    /**
     * @return the nrdocAseg
     */
    public String getNrdocAseg() {
        return nrdocAseg;
    }

    /**
     * @param nrdocAseg the nrdocAseg to set
     */
    public void setNrdocAseg(String nrdocAseg) {
        this.nrdocAseg = nrdocAseg;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the nrruc
     */
    public String getNrruc() {
        return nrruc;
    }

    /**
     * @param nrruc the nrruc to set
     */
    public void setNrruc(String nrruc) {
        this.nrruc = nrruc;
    }

    /**
     * @return the rzsocial
     */
    public String getRzsocial() {
        return rzsocial;
    }

    /**
     * @param rzsocial the rzsocial to set
     */
    public void setRzsocial(String rzsocial) {
        this.rzsocial = rzsocial;
    }

    /**
     * @return the feciniSol
     */
    public String getFeciniSol() {
        return feciniSol;
    }

    /**
     * @param feciniSol the feciniSol to set
     */
    public void setFeciniSol(String feciniSol) {
        this.feciniSol = feciniSol;
    }

    /**
     * @return the fecfinSol
     */
    public String getFecfinSol() {
        return fecfinSol;
    }

    /**
     * @param fecfinSol the fecfinSol to set
     */
    public void setFecfinSol(String fecfinSol) {
        this.fecfinSol = fecfinSol;
    }

    /**
     * @return the feciniVac
     */
    public String getFeciniVac() {
        return feciniVac;
    }

    /**
     * @param feciniVac the feciniVac to set
     */
    public void setFeciniVac(String feciniVac) {
        this.feciniVac = feciniVac;
    }

    /**
     * @return the fecfinVac
     */
    public String getFecfinVac() {
        return fecfinVac;
    }

    /**
     * @param fecfinVac the fecfinVac to set
     */
    public void setFecfinVac(String fecfinVac) {
        this.fecfinVac = fecfinVac;
    }

    /**
     * @return the fecCese
     */
    public String getFecCese() {
        return fecCese;
    }

    /**
     * @param fecCese the fecCese to set
     */
    public void setFecCese(String fecCese) {
        this.fecCese = fecCese;
    }

    /**
     * @return the montoSol
     */
    public double getMontoSol() {
        return montoSol;
    }

    /**
     * @param montoSol the montoSol to set
     */
    public void setMontoSol(double montoSol) {
        this.montoSol = montoSol;
    }

    /**
     * @return the nrcitt
     */
    public String getNrcitt() {
        return nrcitt;
    }

    /**
     * @param nrcitt the nrcitt to set
     */
    public void setNrcitt(String nrcitt) {
        this.nrcitt = nrcitt;
    }

    /**
     * @return the feciniCitt
     */
    public String getFeciniCitt() {
        return feciniCitt;
    }

    /**
     * @param feciniCitt the feciniCitt to set
     */
    public void setFeciniCitt(String feciniCitt) {
        this.feciniCitt = feciniCitt;
    }

    /**
     * @return the fecfinCitt
     */
    public String getFecfinCitt() {
        return fecfinCitt;
    }

    /**
     * @param fecfinCitt the fecfinCitt to set
     */
    public void setFecfinCitt(String fecfinCitt) {
        this.fecfinCitt = fecfinCitt;
    }

    /**
     * @return the tpcitt
     */
    public String getTpcitt() {
        return tpcitt;
    }

    /**
     * @param tpcitt the tpcitt to set
     */
    public void setTpcitt(String tpcitt) {
        this.tpcitt = tpcitt;
    }

    /**
     * @return the esSectorPrivado
     */
    public String getEsSectorPrivado() {
        return esSectorPrivado;
    }

    /**
     * @param esSectorPrivado the esSectorPrivado to set
     */
    public void setEsSectorPrivado(String esSectorPrivado) {
        this.esSectorPrivado = esSectorPrivado;
    }

    /**
     * @return the estaTrabajando
     */
    public String getEstaTrabajando() {
        return estaTrabajando;
    }

    /**
     * @param estaTrabajando the estaTrabajando to set
     */
    public void setEstaTrabajando(String estaTrabajando) {
        this.estaTrabajando = estaTrabajando;
    }

    /**
     * @return the tieneSctr
     */
    public String getTieneSctr() {
        return tieneSctr;
    }

    /**
     * @param tieneSctr the tieneSctr to set
     */
    public void setTieneSctr(String tieneSctr) {
        this.tieneSctr = tieneSctr;
    }

    /**
     * @return the tieneDeclaracionJurada
     */
    public String getTieneDeclaracionJurada() {
        return tieneDeclaracionJurada;
    }

    /**
     * @param tieneDeclaracionJurada the tieneDeclaracionJurada to set
     */
    public void setTieneDeclaracionJurada(String tieneDeclaracionJurada) {
        this.tieneDeclaracionJurada = tieneDeclaracionJurada;
    }

    
}
