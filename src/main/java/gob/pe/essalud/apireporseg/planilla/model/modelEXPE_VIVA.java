package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class modelEXPE_VIVA {

    private String ide_num_solicitud_ov;
    private String cod_tdoc_usuario;
    private String cod_edoc_usuario;
    private String num_doc_usuario;
    private String cod_ttipo_subsidio;
    private String cod_etipo_subsidio;
    private String fec_usuario_crea;
    private String fec_usuario_act;
    private String flg_estado_pro;
    private String fec_proceso;
    private String url_documento;
    private String ide_num_solicitud;
    private String cod_ospe;
    private String tip_pago;
    private String ide_group_solicitud_ov;

}
