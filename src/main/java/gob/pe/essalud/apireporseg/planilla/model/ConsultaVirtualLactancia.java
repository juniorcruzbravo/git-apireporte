package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class ConsultaVirtualLactancia {
    private String nombreHijo;
    private String dniAsegurado;
    private String nombreAsegurado;
    private String dniMadre;
    private String nombreMadre;
    private String fecNacRn;
    private String correlativo;
    private String resultado;
    private String detalle;
    private String resultadoDesc;
    private String exp_nit;
    private String comentario;
    private String fechaPago;
    private String num_doc_hijo;
}
