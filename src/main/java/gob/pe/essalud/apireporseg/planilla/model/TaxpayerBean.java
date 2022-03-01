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
public class TaxpayerBean {
    
    private String nrRuc;
    private String razSocial;
    private String estado;
    private String idnumerico;
    private String tpDocRuc;
    private String desDocRuc;
    private String tpEmp;


    public void setTpEmp(String tpEmp) {
        this.tpEmp = tpEmp;
    }

    public String getTpEmp() {
        return tpEmp;
    }



    /**
     * @return the nrRuc
     */
    public String getNrRuc() {
        return nrRuc;
    }

    /**
     * @param nrRuc the nrRuc to set
     */
    public void setNrRuc(String nrRuc) {
        this.nrRuc = nrRuc;
    }

    /**
     * @return the razSocial
     */
    public String getRazSocial() {
        return razSocial;
    }

    /**
     * @param razSocial the razSocial to set
     */
    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdnumerico() {
        return idnumerico;
    }

    public void setIdnumerico(String idnumerico) {
        this.idnumerico = idnumerico;
    }

    public String getTpDocRuc() {
        return tpDocRuc;
    }

    public void setTpDocRuc(String tpDocRuc) {
        this.tpDocRuc = tpDocRuc;
    }

    public String getDesDocRuc() {
        return desDocRuc;
    }

    public void setDesDocRuc(String desDocRuc) {
        this.desDocRuc = desDocRuc;
    }
}
