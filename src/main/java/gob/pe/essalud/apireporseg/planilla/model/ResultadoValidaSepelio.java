package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class ResultadoValidaSepelio {
    private String   ide;
    private String   tipodoctitular;
    private String   numdoctitular;
    private String   nombretitular;
    private String   fecnacimiento;
    private String   tiposegurotitular;
    private String   detalle_error;
    private String   codresult;
}
