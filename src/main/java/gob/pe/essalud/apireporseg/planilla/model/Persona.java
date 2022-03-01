package gob.pe.essalud.apireporseg.planilla.model;

import lombok.Data;

@Data
public class Persona {
    private Long ideNumericoPersona;
    private Integer codTdocumentPersona;
    private String codEdocumentPersona;
    private Integer codTpaisemiDocument;
    private String codEpaisemiDocument;
    private String numDocumentPersona;
    private String codAutogenePersona;
    private String txtApepaterPersona;
    private String txtApematerPersona;
    private String txtApecasadPersona;
    private String txtPrinombrPersona;
    private String txtSegnombrPersona;
    private String txtNombresPersona;
    private Integer codTestciviPersona;
    private String codEestciviPersona;
    private Integer codTubigeoNacimien;
    private String codEubigeoNacimien;
    private Integer codTsexoPersona;
    private String codEsexoPersona;
    private String flgEstadoInhabili;
    private Integer codTmotivoInhabili;
    private String codEmotivoInhabili;
    private String numCieRuc;
    private String fecInicvigePersona;
    private String fecTermvigePersona;
    private String fecNacimiPersona;
    private String fecFallecPersona;
    private String fecInvaliPerparci;
    private String fecInvaliPertotal;
    private Integer codToperacioSunat;
    private String codEoperacioSunat;
    private String numPaqueteSunat;
    private String numDocPadre;
    private String numDocMadre;
    private String fecRegistroSistema;
    private String horRegistroSistema;
    private String codUsuarioSistema;
    private String codTerminalSistema;
    private String nombreCompleto;
}
