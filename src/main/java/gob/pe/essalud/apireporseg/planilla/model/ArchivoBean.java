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
public class ArchivoBean {
    private Integer idArchivo;
    private Integer idSolicitud;
    private String nombre;
    private String urlAcceso;

    /**
     * @return the idArchivo
     */
    public Integer getIdArchivo() {
        return idArchivo;
    }

    /**
     * @param idArchivo the idArchivo to set
     */
    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    /**
     * @return the idSolicitud
     */
    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * @param idSolicitud the idSolicitud to set
     */
    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
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
     * @return the urlAcceso
     */
    public String getUrlAcceso() {
        return urlAcceso;
    }

    /**
     * @param urlAcceso the urlAcceso to set
     */
    public void setUrlAcceso(String urlAcceso) {
        this.urlAcceso = urlAcceso;
    }
    
}
