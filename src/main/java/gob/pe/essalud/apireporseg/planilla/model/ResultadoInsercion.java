package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class ResultadoInsercion {
    String codSolicitud;
    String message;
    String error;
    String codigoSolicitudes;
}
