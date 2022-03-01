
package gob.pe.essalud.apireporseg.planilla.model;

public class ReporteCorreo {

    private String ospe_nombre;
    private String notificacion_fecha;
    private String administrado_nombre;
    private String administrado_direccion;
    private String asegurado_nombre;
    private String asegurado_dni;
    private String empleador_nombre;
    private String empleador_ruc;
    private String expediente_numero;
    private String expediente_subsidio;
    private String expediente_pago;
    private String expediente_monto;
    private String expediente_nitt;
    private String expediente_periodo;
    private String año;
    private String titulo_año;
    private String empleador_direccion;
    private String cenio;
    private String ubigeo;
    private String ubigeo_empresa;
    private String fecha;
    private String ide_solicitud;
    private String tipo_doc;
    private String num_doc;
    private String nombre_completo;
    private String expe_o_nit;
    private String fechaIni;
    private String fechaFin;

    @Override
    public String toString() {
        return "ReporteCorreo{" +
                "ospe_nombre='" + ospe_nombre + '\'' +
                ", notificacion_fecha='" + notificacion_fecha + '\'' +
                ", administrado_nombre='" + administrado_nombre + '\'' +
                ", administrado_direccion='" + administrado_direccion + '\'' +
                ", asegurado_nombre='" + asegurado_nombre + '\'' +
                ", asegurado_dni='" + asegurado_dni + '\'' +
                ", empleador_nombre='" + empleador_nombre + '\'' +
                ", empleador_ruc='" + empleador_ruc + '\'' +
                ", expediente_numero='" + expediente_numero + '\'' +
                ", expediente_subsidio='" + expediente_subsidio + '\'' +
                ", expediente_pago='" + expediente_pago + '\'' +
                ", expediente_monto='" + expediente_monto + '\'' +
                ", expediente_nitt='" + expediente_nitt + '\'' +
                ", expediente_periodo='" + expediente_periodo + '\'' +
                ", año='" + año + '\'' +
                ", titulo_año='" + titulo_año + '\'' +
                ", empleador_direccion='" + empleador_direccion + '\'' +
                ", cenio='" + cenio + '\'' +
                ", ubigeo='" + ubigeo + '\'' +
                ", ubigeo_empresa='" + ubigeo_empresa + '\'' +
                ", fecha='" + fecha + '\'' +
                ", ide_solicitud='" + ide_solicitud + '\'' +
                ", tipo_doc='" + tipo_doc + '\'' +
                ", num_doc='" + num_doc + '\'' +
                ", nombre_completo='" + nombre_completo + '\'' +
                ", expe_o_nit='" + expe_o_nit + '\'' +
                ", fechaIni='" + fechaIni + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                '}';
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getIde_solicitud() {
        return ide_solicitud;
    }

    public void setIde_solicitud(String ide_solicitud) {
        this.ide_solicitud = ide_solicitud;
    }
    public String getExpe_o_nit() {
        return expe_o_nit;
    }

    public void setExpe_o_nit(String expe_o_nit) {
        this.expe_o_nit = expe_o_nit;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }
    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(String num_doc) {
        this.num_doc = num_doc;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getOspe_nombre() {
        return ospe_nombre;
    }

    public void setOspe_nombre(String ospe_nombre) {
        this.ospe_nombre = ospe_nombre;
    }

    public String getNotificacion_fecha() {
        return notificacion_fecha;
    }

    public void setNotificacion_fecha(String notificacion_fecha) {
        this.notificacion_fecha = notificacion_fecha;
    }

    public String getAdministrado_nombre() {
        return administrado_nombre;
    }

    public void setAdministrado_nombre(String administrado_nombre) {
        this.administrado_nombre = administrado_nombre;
    }

    public String getAdministrado_direccion() {
        return administrado_direccion;
    }

    public void setAdministrado_direccion(String administrado_direccion) {
        this.administrado_direccion = administrado_direccion;
    }

    public String getAsegurado_nombre() {
        return asegurado_nombre;
    }

    public void setAsegurado_nombre(String asegurado_nombre) {
        this.asegurado_nombre = asegurado_nombre;
    }

    public String getAsegurado_dni() {
        return asegurado_dni;
    }

    public void setAsegurado_dni(String asegurado_dni) {
        this.asegurado_dni = asegurado_dni;
    }

    public String getEmpleador_nombre() {
        return empleador_nombre;
    }

    public void setEmpleador_nombre(String empleador_nombre) {
        this.empleador_nombre = empleador_nombre;
    }

    public String getEmpleador_ruc() {
        return empleador_ruc;
    }

    public void setEmpleador_ruc(String empleador_ruc) {
        this.empleador_ruc = empleador_ruc;
    }

    public String getExpediente_numero() {
        return expediente_numero;
    }

    public void setExpediente_numero(String expediente_numero) {
        this.expediente_numero = expediente_numero;
    }

    public String getExpediente_subsidio() {
        return expediente_subsidio;
    }

    public void setExpediente_subsidio(String expediente_subsidio) {
        this.expediente_subsidio = expediente_subsidio;
    }

    public String getExpediente_pago() {
        return expediente_pago;
    }

    public void setExpediente_pago(String expediente_pago) {
        this.expediente_pago = expediente_pago;
    }

    public String getExpediente_monto() {
        return expediente_monto;
    }

    public void setExpediente_monto(String expediente_monto) {
        this.expediente_monto = expediente_monto;
    }

    public String getExpediente_nitt() {
        return expediente_nitt;
    }

    public void setExpediente_nitt(String expediente_nitt) {
        this.expediente_nitt = expediente_nitt;
    }

    public String getExpediente_periodo() {
        return expediente_periodo;
    }

    public void setExpediente_periodo(String expediente_periodo) {
        this.expediente_periodo = expediente_periodo;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getTitulo_año() {
        return titulo_año;
    }

    public void setTitulo_año(String titulo_año) {
        this.titulo_año = titulo_año;
    }

    public String getEmpleador_direccion() {
        return empleador_direccion;
    }

    public void setEmpleador_direccion(String empleador_direccion) {
        this.empleador_direccion = empleador_direccion;
    }

    public String getCenio() {
        return cenio;
    }

    public void setCenio(String cenio) {
        this.cenio = cenio;
    }

    public String getUbigeo_empresa() {
        return ubigeo_empresa;
    }

    public void setUbigeo_empresa(String ubigeo_empresa) {
        this.ubigeo_empresa = ubigeo_empresa;
    }


}
