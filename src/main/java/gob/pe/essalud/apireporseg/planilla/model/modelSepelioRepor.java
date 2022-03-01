package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class modelSepelioRepor {

    private String fuente;
    private String id_tramite_emergencia;
    private String fecha_creacion;
    private String oficinacevitaseg;
    private String formulario_detalle;
    private String tipo_documento_aseg;
    private String numero_documento_aseg;
    private String apellidos_nombres_aseg;
    private String celular_aseg;
    private String correo_aseg;
    private String tipo_doc_titular;
    private String numero_doc_titular;
    private String rucultimo_empleador;
    private String certificado_defuncion;
    private String fecha_fallecimiento;
    private String monto_subsidio;
    private String tipo_doc_beneficiario;
    private String nro_doc_beneficiario;
    private String parentesco_beneficiario;
    private String fecha_comprobante;
    private String ruccomprobante;
    private String tipo_comprobante;
    private String serie_comprobante;
    private String numero_comprobante;
    private String importe_comprobante;
    private String nombre_archivo_adjunto;
    private String ubicacion_archivo;
}
