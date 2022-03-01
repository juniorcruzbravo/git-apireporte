package gob.pe.essalud.apireporseg.planilla.model;


import lombok.Data;

import java.util.List;

@Data
public class Calificacion {
    private List<ConsultaVirtualLactancia> lactanciasResultados;
    private List<Backoffice> backoffice ;

}
