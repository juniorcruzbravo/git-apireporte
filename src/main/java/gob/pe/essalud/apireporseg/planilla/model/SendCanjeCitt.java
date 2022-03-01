package gob.pe.essalud.apireporseg.planilla.model;

import java.util.List;

public class SendCanjeCitt {
    private String tpDocAseg;
    private String nrDocAseg;
    private String tpDocRuc;
    private String nrDocRuc;

    private List<Archivo> archivos;

    private String serieBoleta;
    private String numeroBoleta;
    private String periodoBoleta;
    private String fecEmisionBoleta;
    private String nrDocRucBoleta;
    private String fecVinLaboralBoleta;
    private String nombreArchivoBoleta;
    private String urlArchivoBoleta;
    private String base64Boleta;

    private String fecEmisionResol;
    private String nrDocRucResol;
    private String fecVinLaboralResol;
    private String nombreArchivoResol;
    private String urlArchivoResol;
    private String base64Resol;

    private String periodoForm;
    private String fecEmisionForm;
    private String nrDocRucForm;
    private String fecVinLaboralForm;
    private String nombreArchivoForm;
    private String urlArchivoForm;
    private String base64Form;
    private String ide_usuario;
    private String cod_ospe;

    public String getIde_usuario() {
        return ide_usuario;
    }

    public void setIde_usuario(String ide_usuario) {
        this.ide_usuario = ide_usuario;
    }

    public String getCod_ospe() {
        return cod_ospe;
    }

    public void setCod_ospe(String cod_ospe) {
        this.cod_ospe = cod_ospe;
    }

    private String tpTramite;

    public String getNum_ndoc_beneficiario() {
        return num_ndoc_beneficiario;
    }

    public void setNum_ndoc_beneficiario(String num_ndoc_beneficiario) {
        this.num_ndoc_beneficiario = num_ndoc_beneficiario;
    }

    public String getTxt_nombre_completo() {
        return txt_nombre_completo;
    }

    public void setTxt_nombre_completo(String txt_nombre_completo) {
        this.txt_nombre_completo = txt_nombre_completo;
    }

    public String getAutogenerado() {
        return autogenerado;
    }

    public void setAutogenerado(String autogenerado) {
        this.autogenerado = autogenerado;
    }

    public String getRed_asistencial() {
        return red_asistencial;
    }

    public void setRed_asistencial(String red_asistencial) {
        this.red_asistencial = red_asistencial;
    }

    public String getCentro_asistencial() {
        return centro_asistencial;
    }

    public void setCentro_asistencial(String centro_asistencial) {
        this.centro_asistencial = centro_asistencial;
    }

    public String getTipo_asegurado() {
        return tipo_asegurado;
    }

    public void setTipo_asegurado(String tipo_asegurado) {
        this.tipo_asegurado = tipo_asegurado;
    }

    public String getNum_ruc_empleador() {
        return num_ruc_empleador;
    }

    public void setNum_ruc_empleador(String num_ruc_empleador) {
        this.num_ruc_empleador = num_ruc_empleador;
    }

    public String getTxt_razon_social() {
        return txt_razon_social;
    }

    public void setTxt_razon_social(String txt_razon_social) {
        this.txt_razon_social = txt_razon_social;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRuc_ipress() {
        return ruc_ipress;
    }

    public void setRuc_ipress(String ruc_ipress) {
        this.ruc_ipress = ruc_ipress;
    }

    public String getPersonal_tratante() {
        return personal_tratante;
    }

    public void setPersonal_tratante(String personal_tratante) {
        this.personal_tratante = personal_tratante;
    }

    public String getColegio_medico() {
        return colegio_medico;
    }

    public void setColegio_medico(String colegio_medico) {
        this.colegio_medico = colegio_medico;
    }

    public String getTipo_atencion() {
        return tipo_atencion;
    }

    public void setTipo_atencion(String tipo_atencion) {
        this.tipo_atencion = tipo_atencion;
    }

    public String getContingencia() {
        return contingencia;
    }

    public void setContingencia(String contingencia) {
        this.contingencia = contingencia;
    }

    public String getFecha_pro_par() {
        return fecha_pro_par;
    }

    public void setFecha_pro_par(String fecha_pro_par) {
        this.fecha_pro_par = fecha_pro_par;
    }

    public String getCie1O_1_cod() {
        return cie1O_1_cod;
    }

    public void setCie1O_1_cod(String cie1O_1_cod) {
        this.cie1O_1_cod = cie1O_1_cod;
    }

    public String getCie1O_1_des() {
        return cie1O_1_des;
    }

    public void setCie1O_1_des(String cie1O_1_des) {
        this.cie1O_1_des = cie1O_1_des;
    }

    public String getCie1O_2_cod() {
        return cie1O_2_cod;
    }

    public void setCie1O_2_cod(String cie1O_2_cod) {
        this.cie1O_2_cod = cie1O_2_cod;
    }

    public String getCie1O_2_des() {
        return cie1O_2_des;
    }

    public void setCie1O_2_des(String cie1O_2_des) {
        this.cie1O_2_des = cie1O_2_des;
    }

    public String getFecha_tramite() {
        return fecha_tramite;
    }

    public void setFecha_tramite(String fecha_tramite) {
        this.fecha_tramite = fecha_tramite;
    }

    public String getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(String fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getNum_dias_citt() {
        return num_dias_citt;
    }

    public void setNum_dias_citt(String num_dias_citt) {
        this.num_dias_citt = num_dias_citt;
    }

    public String getNum_folios() {
        return num_folios;
    }

    public void setNum_folios(String num_folios) {
        this.num_folios = num_folios;
    }

    public String getTrabajo_habitual() {
        return trabajo_habitual;
    }

    public void setTrabajo_habitual(String trabajo_habitual) {
        this.trabajo_habitual = trabajo_habitual;
    }
    private String  tip_ndoc_beneficiario;
    private String  num_ndoc_beneficiario;
    private String  txt_nombre_completo;



    private String autogenerado;
    private String red_asistencial;
    private String  centro_asistencial;
    private String  tipo_asegurado;
    private String  num_ruc_empleador;
    private String  txt_razon_social;
    private String  correo_electronico;
    private String  telefono;
    private String  ruc_ipress;
    private String  personal_tratante;
    private String  colegio_medico;
    private String  tipo_atencion;
    private String  contingencia;
    private String  fecha_pro_par;
    private String  cie1O_1_cod;
    private String  cie1O_1_des;
    private String  cie1O_2_cod;
    private String  cie1O_2_des;
    private String  fecha_tramite;
    private String  fecha_ini;

    private String  cod_tipo_atencion;



    public String getFecha_otorgamiento() {
        return fecha_otorgamiento;
    }

    public void setFecha_otorgamiento(String fecha_otorgamiento) {
        this.fecha_otorgamiento = fecha_otorgamiento;
    }

    private String  cod_personal_tratante;
    private String  cod_contingencia;
    private  String fecha_otorgamiento;


    public String getCod_trabajo() {
        return cod_trabajo;
    }

    public void setCod_trabajo(String cod_trabajo) {
        this.cod_trabajo = cod_trabajo;
    }

    private String  cod_trabajo;


    public String getCod_tipo_atencion() {
        return cod_tipo_atencion;
    }

    public void setCod_tipo_atencion(String cod_tipo_atencion) {
        this.cod_tipo_atencion = cod_tipo_atencion;
    }

    public String getCod_personal_tratante() {
        return cod_personal_tratante;
    }

    public void setCod_personal_tratante(String cod_personal_tratante) {
        this.cod_personal_tratante = cod_personal_tratante;
    }

    public String getCod_contingencia() {
        return cod_contingencia;
    }

    public void setCod_contingencia(String cod_contingencia) {
        this.cod_contingencia = cod_contingencia;
    }




    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    private String  fecha_fin;
    private String  num_dias_citt;
    private String  num_folios;
    private String  trabajo_habitual;
    private String  tipo_pago;


    public String getTpTramite() {
        return tpTramite;
    }

    public void setTpTramite(String tpTramite) {
        this.tpTramite = tpTramite;
    }

    public String getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(String detallePedido) {
        this.detallePedido = detallePedido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTpOSPE() {
        return tpOSPE;
    }

    public void setTpOSPE(String tpOSPE) {
        this.tpOSPE = tpOSPE;
    }

    private String detallePedido;
    private String celular;
    private String correo;
    private String tpOSPE;


    public String getTpDocAseg() {
        return tpDocAseg;
    }

    public void setTpDocAseg(String tpDocAseg) {
        this.tpDocAseg = tpDocAseg;
    }

    public String getNrDocAseg() {
        return nrDocAseg;
    }

    public void setNrDocAseg(String nrDocAseg) {
        this.nrDocAseg = nrDocAseg;
    }

    public String getTpDocRuc() {
        return tpDocRuc;
    }

    public void setTpDocRuc(String tpDocRuc) {
        this.tpDocRuc = tpDocRuc;
    }

    public String getNrDocRuc() {
        return nrDocRuc;
    }

    public void setNrDocRuc(String nrDocRuc) {
        this.nrDocRuc = nrDocRuc;
    }

    /**Boleta**/
    public String getSerieBoleta() {
        return serieBoleta;
    }

    public void setSerieBoleta(String serieBoleta) {
        this.serieBoleta = serieBoleta;
    }

    public String getNumeroBoleta() {
        return numeroBoleta;
    }

    public void setNumeroBoleta(String numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }

    public String getPeriodoBoleta() {
        return periodoBoleta;
    }

    public void setPeriodoBoleta(String periodoBoleta) {
        this.periodoBoleta = periodoBoleta;
    }

    public String getFecEmisionBoleta() {
        return fecEmisionBoleta;
    }

    public void setFecEmisionBoleta(String fecEmisionBoleta) {
        this.fecEmisionBoleta = fecEmisionBoleta;
    }

    public String getNrDocRucBoleta() {
        return nrDocRucBoleta;
    }

    public void setNrDocRucBoleta(String nrDocRucBoleta) {
        this.nrDocRucBoleta = nrDocRucBoleta;
    }

    public String getFecVinLaboralBoleta() {
        return fecVinLaboralBoleta;
    }

    public void setFecVinLaboralBoleta(String fecVinLaboralBoleta) {
        this.fecVinLaboralBoleta = fecVinLaboralBoleta;
    }

    public String getNombreArchivoBoleta() {
        return nombreArchivoBoleta;
    }

    public void setNombreArchivoBoleta(String nombreArchivoBoleta) {
        this.nombreArchivoBoleta = nombreArchivoBoleta;
    }

    public String getUrlArchivoBoleta() {
        return urlArchivoBoleta;
    }

    public void setUrlArchivoBoleta(String urlArchivoBoleta) {
        this.urlArchivoBoleta = urlArchivoBoleta;
    }

    public String getBase64Boleta() {
        return base64Boleta;
    }

    public void setBase64Boleta(String base64Boleta) {
        this.base64Boleta = base64Boleta;
    }

    /**Resolucion**/
    public String getFecEmisionResol() {
        return fecEmisionResol;
    }

    public void setFecEmisionResol(String fecEmisionResol) {
        this.fecEmisionResol = fecEmisionResol;
    }

    public String getNrDocRucResol() {
        return nrDocRucResol;
    }

    public void setNrDocRucResol(String nrDocRucResol) {
        this.nrDocRucResol = nrDocRucResol;
    }

    public String getFecVinLaboralResol() {
        return fecVinLaboralResol;
    }

    public void setFecVinLaboralResol(String fecVinLaboralResol) {
        this.fecVinLaboralResol = fecVinLaboralResol;
    }

    public String getNombreArchivoResol() {
        return nombreArchivoResol;
    }

    public void setNombreArchivoResol(String nombreArchivoResol) {
        this.nombreArchivoResol = nombreArchivoResol;
    }

    public String getUrlArchivoResol() {
        return urlArchivoResol;
    }

    public void setUrlArchivoResol(String urlArchivoResol) {
        this.urlArchivoResol = urlArchivoResol;
    }

    public String getBase64Resol() {
        return base64Resol;
    }

    public void setBase64Resol(String base64Resol) {
        this.base64Resol = base64Resol;
    }

    /**Formulario**/
    public String getPeriodoForm() {
        return periodoForm;
    }

    public void setPeriodoForm(String periodoForm) {
        this.periodoForm = periodoForm;
    }

    public String getFecEmisionForm() {
        return fecEmisionForm;
    }

    public void setFecEmisionForm(String fecEmisionForm) {
        this.fecEmisionForm = fecEmisionForm;
    }

    public String getNrDocRucForm() {
        return nrDocRucForm;
    }

    public void setNrDocRucForm(String nrDocRucForm) {
        this.nrDocRucForm = nrDocRucForm;
    }

    public String getFecVinLaboralForm() {
        return fecVinLaboralForm;
    }

    public void setFecVinLaboralForm(String fecVinLaboralForm) {
        this.fecVinLaboralForm = fecVinLaboralForm;
    }

    public String getNombreArchivoForm() {
        return nombreArchivoForm;
    }

    public void setNombreArchivoForm(String nombreArchivoForm) {
        this.nombreArchivoForm = nombreArchivoForm;
    }

    public String getUrlArchivoForm() {
        return urlArchivoForm;
    }

    public void setUrlArchivoForm(String urlArchivoForm) {
        this.urlArchivoForm = urlArchivoForm;
    }

    public String getBase64Form() {
        return base64Form;
    }

    public void setBase64Form(String base64Form) {
        this.base64Form = base64Form;
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    public String getTip_ndoc_beneficiario() {
        return tip_ndoc_beneficiario;
    }

    public void setTip_ndoc_beneficiario(String tip_ndoc_beneficiario) {
        this.tip_ndoc_beneficiario = tip_ndoc_beneficiario;
    }

    private List<Comprobantes> comprobantes;

    public List<Comprobantes> getComprobantes() {
        return comprobantes;
    }

    public void setComprobantes(List<Comprobantes> comprobantes) {
        this.comprobantes = comprobantes;
    }

    private String tipo_doc_titular;
    private String num_doc_titular;
    private  String nombresyApellTitular;
    private String num_doc_Be;
    private  String tipo_doc_Be;
    private String  nombresApellBe;
    private String  codigoParentesco;
    private String  desParentesco;
    private String  montoSolicitado;


    public String getTipo_doc_titular() {
        return tipo_doc_titular;
    }

    public void setTipo_doc_titular(String tipo_doc_titular) {
        this.tipo_doc_titular = tipo_doc_titular;
    }

    public String getNum_doc_titular() {
        return num_doc_titular;
    }

    public void setNum_doc_titular(String num_doc_titular) {
        this.num_doc_titular = num_doc_titular;
    }

    public String getNombresyApellTitular() {
        return nombresyApellTitular;
    }

    public void setNombresyApellTitular(String nombresyApellTitular) {
        this.nombresyApellTitular = nombresyApellTitular;
    }

    public String getNum_doc_Be() {
        return num_doc_Be;
    }

    public void setNum_doc_Be(String num_doc_Be) {
        this.num_doc_Be = num_doc_Be;
    }

    public String getTipo_doc_Be() {
        return tipo_doc_Be;
    }

    public void setTipo_doc_Be(String tipo_doc_Be) {
        this.tipo_doc_Be = tipo_doc_Be;
    }

    public String getNombresApellBe() {
        return nombresApellBe;
    }

    public void setNombresApellBe(String nombresApellBe) {
        this.nombresApellBe = nombresApellBe;
    }

    public String getCodigoParentesco() {
        return codigoParentesco;
    }

    public void setCodigoParentesco(String codigoParentesco) {
        this.codigoParentesco = codigoParentesco;
    }

    public String getDesParentesco() {
        return desParentesco;
    }

    public void setDesParentesco(String desParentesco) {
        this.desParentesco = desParentesco;
    }

    public String getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(String montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }



}
