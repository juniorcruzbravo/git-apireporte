package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

public @Data
class DataSepelio {
    private String v_cod_tdoc_beneficiario;
    private String v_cod_edoc_beneficiario;
    private String v_num_ndoc_beneficiario;
    private String v_txt_nombre_beneficiario;
    private String v_cod_tdoc_titular;
    private String v_cod_edoc_titular;
    private String v_txt_nombre_titular;
    private String v_num_ndoc_titular;
    private String v_num_monto_subsidiado;
    private String v_monto_solicitado;
    private String v_fec_fallecimiento_titular;
    private String v_cod_tparentesco;
    private String v_cod_eparentesco;
    private String v_flag_resultado;
    private String v_numero_expediente;
    private String v_fecha_pago;
    private String v_fecha_cese;
    private String v_fecha_creacion;
    private String v_cod_usuario_crea;
    private String v_ip_usuario_crea;
    private String v_cod_usuario_viva;
    private String v_flag_activo_inactivo;
    private String v_cod_edoc_usuario;
    private String v_num_doc_usuario;
    private String v_cod_ospe;
    private String v_tip_pago;

}
