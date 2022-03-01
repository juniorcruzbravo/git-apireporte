package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class ResultadoAsegurado {
    private String  tpdoc;
    private String  nrdoc;
    private String  apepaterno;
    private String  apematerno;
    private String  nombres;
    private String  estcivil;
    private String  sexo;
    private String  fecnacimiento;
    private String  fefallecid;
    private String  codverf;
    private String  fecincripcion;
    private String  fecexpedicion;
    private String  tpdocpadre;
    private String  nrdocpadre;
    private String  nompadre;
    private String  docmadre;
    private String  nrdocmadre;
    private String  nommadre;
    private String  nomApCompleto;
}
