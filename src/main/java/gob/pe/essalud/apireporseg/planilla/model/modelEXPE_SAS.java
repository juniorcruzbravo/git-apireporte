package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class modelEXPE_SAS {

    private String ide_num_solicitu;
    private String cod_tdoc_titular;
    private String cod_edoc_titular;
    private String num_doc_titular;
    private String cod_ttipo_subsidio;
    private String cod_etipo_subsidio;
    private String cod_testado_subsidio;
    private String cod_eestado_subsidio;
    private String fec_fecha_presentacion;
    private String fec_fecha_proceso;
    private String cod_expediente_nit;
    private String flg_activo_inactivo;
    private String fec_usuario_crea;
    private String cod_usuario_crea;
    private String txt_ipusuario_crea;
    private String fec_usuario_act;
    private String cod_usuario_act;
    private String txt_ipusuario_act;
    private String cod_empleador;
    private String cod_ide_repolact;
    private String num_monto_subsidiado;
    private String numero_nitt_monto_recortado;


}
