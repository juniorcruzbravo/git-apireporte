package gob.pe.essalud.apireporseg.planilla.model;

public class Comprobantes {

    private String id;
    private String numero_ruc;
    private String razon_social;
    private String tipo_comprobante;
    private String des_comprobante;
    private String numero_serie;
    private String numero_comprobante;
    private String fecha_comprobante;
    private String tipo_concepto;
    private String des_concepto;
    private String importe;

    @Override
    public String toString() {
        return "Comprobantes{" +
                "id='" + id + '\'' +
                ", numero_ruc='" + numero_ruc + '\'' +
                ", razon_social='" + razon_social + '\'' +
                ", tipo_comprobante='" + tipo_comprobante + '\'' +
                ", des_comprobante='" + des_comprobante + '\'' +
                ", numero_serie='" + numero_serie + '\'' +
                ", numero_comprobante='" + numero_comprobante + '\'' +
                ", fecha_comprobante='" + fecha_comprobante + '\'' +
                ", tipo_concepto='" + tipo_concepto + '\'' +
                ", des_concepto='" + des_concepto + '\'' +
                ", importe='" + importe + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero_ruc() {
        return numero_ruc;
    }

    public void setNumero_ruc(String numero_ruc) {
        this.numero_ruc = numero_ruc;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(String tipo_comprobante) {
        this.tipo_comprobante = tipo_comprobante;
    }

    public String getDes_comprobante() {
        return des_comprobante;
    }

    public void setDes_comprobante(String des_comprobante) {
        this.des_comprobante = des_comprobante;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
    }

    public String getFecha_comprobante() {
        return fecha_comprobante;
    }

    public void setFecha_comprobante(String fecha_comprobante) {
        this.fecha_comprobante = fecha_comprobante;
    }

    public String getTipo_concepto() {
        return tipo_concepto;
    }

    public void setTipo_concepto(String tipo_concepto) {
        this.tipo_concepto = tipo_concepto;
    }

    public String getDes_concepto() {
        return des_concepto;
    }

    public void setDes_concepto(String des_concepto) {
        this.des_concepto = des_concepto;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }
}
