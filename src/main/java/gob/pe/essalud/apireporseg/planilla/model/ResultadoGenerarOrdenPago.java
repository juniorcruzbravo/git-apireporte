package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class ResultadoGenerarOrdenPago {

    private String  tipodocbeneficiario;
    private String numdocbeneficiario;
    private String nombrecompletobene;
    private String monto_subsidiado;
    private String continuar;

}
