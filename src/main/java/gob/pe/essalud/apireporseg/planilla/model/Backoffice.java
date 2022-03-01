package gob.pe.essalud.apireporseg.planilla.model;
import lombok.Data;

@Data
public class Backoffice {
    private String   nombre_regla;
    private String   cod_resultado;
    private String flag_resultado;
    private String descripcion;
    private String num_docu_hijo;
}
