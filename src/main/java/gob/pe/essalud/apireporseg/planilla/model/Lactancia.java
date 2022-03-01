package gob.pe.essalud.apireporseg.planilla.model;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Lactancia {
    private String tipoDocumento;
    @NotNull
    @Size(min = 8, max = 18)
    private String numeroDocumento;
    private String tipoDocumentoHijo;
    @NotNull
    @Size(min = 8, max = 18)
    private String numeroDocumentoHijo;
    private String cui;
    private char partoMultiple;
    private String reject;
    @NotNull
    private String nombreNiño;
    @NotNull
    private String nombreMadre;
}
