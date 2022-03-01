package gob.pe.essalud.apireporseg.planilla.model;

public class Archivo {
    private String idArchivo;
    private String idAcreditacion;
    private String numSerie;
    private String numDocumento;
    private String codPeriodo;
    private String fecEmisionDoc;
    private String tpDocRuc;
    private String nrDocRUc;
    private String fecIniVincLab;
    private String tpArchivo;
    private String urlDirectorio;
    private String base64;
    private String fecRegistro;
    private String fecActualizacion;

    @Override
    public String toString() {
        return "Archivo{" +
                "idArchivo='" + idArchivo + '\'' +
                ", idAcreditacion='" + idAcreditacion + '\'' +
                ", numSerie='" + numSerie + '\'' +
                ", numDocumento='" + numDocumento + '\'' +
                ", codPeriodo='" + codPeriodo + '\'' +
                ", fecEmisionDoc='" + fecEmisionDoc + '\'' +
                ", tpDocRuc='" + tpDocRuc + '\'' +
                ", nrDocRUc='" + nrDocRUc + '\'' +
                ", fecIniVincLab='" + fecIniVincLab + '\'' +
                ", tpArchivo='" + tpArchivo + '\'' +
                ", urlDirectorio='" + urlDirectorio + '\'' +
                ", base64='" + base64 + '\'' +
                ", fecRegistro='" + fecRegistro + '\'' +
                ", fecActualizacion='" + fecActualizacion + '\'' +
                ", usuarioCrea='" + usuarioCrea + '\'' +
                ", usuarioMod='" + usuarioMod + '\'' +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                ", nombreArchivoNew='" + nombreArchivoNew + '\'' +
                '}';
    }

    private String usuarioCrea;
    private String usuarioMod;
    private String nombreArchivo;
    private String nombreArchivoNew;

    public String getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(String idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getIdAcreditacion() {
        return idAcreditacion;
    }

    public void setIdAcreditacion(String idAcreditacion) {
        this.idAcreditacion = idAcreditacion;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getCodPeriodo() {
        return codPeriodo;
    }

    public void setCodPeriodo(String codPeriodo) {
        this.codPeriodo = codPeriodo;
    }

    public String getFecEmisionDoc() {
        return fecEmisionDoc;
    }

    public void setFecEmisionDoc(String fecEmisionDoc) {
        this.fecEmisionDoc = fecEmisionDoc;
    }

    public String getTpDocRuc() {
        return tpDocRuc;
    }

    public void setTpDocRuc(String tpDocRuc) {
        this.tpDocRuc = tpDocRuc;
    }

    public String getNrDocRUc() {
        return nrDocRUc;
    }

    public void setNrDocRUc(String nrDocRUc) {
        this.nrDocRUc = nrDocRUc;
    }

    public String getFecIniVincLab() {
        return fecIniVincLab;
    }

    public void setFecIniVincLab(String fecIniVincLab) {
        this.fecIniVincLab = fecIniVincLab;
    }

    public String getTpArchivo() {
        return tpArchivo;
    }

    public void setTpArchivo(String tpArchivo) {
        this.tpArchivo = tpArchivo;
    }

    public String getUrlDirectorio() {
        return urlDirectorio;
    }

    public void setUrlDirectorio(String urlDirectorio) {
        this.urlDirectorio = urlDirectorio;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(String fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public String getFecActualizacion() {
        return fecActualizacion;
    }

    public void setFecActualizacion(String fecActualizacion) {
        this.fecActualizacion = fecActualizacion;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(String usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivoNew() {
        return nombreArchivoNew;
    }

    public void setNombreArchivoNew(String nombreArchivoNew) {
        this.nombreArchivoNew = nombreArchivoNew;
    }
}
