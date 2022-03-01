package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class modelMensualAsegurado {

    private String id;
    private String id_tipo_documento;
    private String numero_documento;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private String correo;
    private String celular;
    private String dependencia;
    private String estado;
}
