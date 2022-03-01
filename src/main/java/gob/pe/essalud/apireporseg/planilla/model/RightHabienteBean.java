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
public class RightHabienteBean {
    private String tpDoc;
    private String nrDoc;
    private String apPaterno;
    private String apMaterno;
    private String nombres;
    private String tpVinculo;

    /**
     * @return the tpDoc
     */
    public String getTpDoc() {
        return tpDoc;
    }

    /**
     * @param tpDoc the tpDoc to set
     */
    public void setTpDoc(String tpDoc) {
        this.tpDoc = tpDoc;
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
     * @return the apPaterno
     */
    public String getApPaterno() {
        return apPaterno;
    }

    /**
     * @param apPaterno the apPaterno to set
     */
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    /**
     * @return the apMaterno
     */
    public String getApMaterno() {
        return apMaterno;
    }

    /**
     * @param apMaterno the apMaterno to set
     */
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
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
     * @return the tpVinculo
     */
    public String getTpVinculo() {
        return tpVinculo;
    }

    /**
     * @param tpVinculo the tpVinculo to set
     */
    public void setTpVinculo(String tpVinculo) {
        this.tpVinculo = tpVinculo;
    }
}
