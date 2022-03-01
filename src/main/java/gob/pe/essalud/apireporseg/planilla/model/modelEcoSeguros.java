package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class modelEcoSeguros {

    private String 	solicitante;
    private String 	celular;
    private String 	correo;
    private String 	ruc;
    private String 	razon_social;
    private String 	ospe;
    private String 	cod_benef;
    private String 	nro_benef;
    private String 	beneficiario;
    private String 	cod_etipo_subsidio;
    private String 	citt;
    private String 	monto;
    private String 	fec_ini_solicitud;
    private String 	fec_fin_solicitud;
    private String 	fecha_solicitud;
    private String 	fecha_proceso;
    private String 	nit;
    private String  cit_20_primeros;
    private String  motivo_observacion;
    private String  fecha_cese;
    private String  tipo_pago;
    private String  direccion;
}
