package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class modelDireccion {

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
    private String direccion_act;
    private String departamento_act;
    private String provincia_act;
    private String distrito_act;
    private String nombre_archivo_adjunto;
    private String ubicacion_archivo;



}
