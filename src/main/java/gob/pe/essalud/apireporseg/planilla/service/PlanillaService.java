package gob.pe.essalud.apireporseg.planilla.service;

import gob.pe.essalud.apireporseg.planilla.model.Constancia;
import gob.pe.essalud.apireporseg.planilla.model.DataSepelio;
import gob.pe.essalud.apireporseg.planilla.model.Persona;
import gob.pe.essalud.apireporseg.planilla.model.SendCanjeCitt;
import java.util.List;
import java.util.Map;

public abstract interface PlanillaService
{
    public abstract void ejecutarCalificacion(Map paramMap);

    public abstract void envioBackOffice(Map paramMap);

    public abstract String esNull(String paramString);

    public abstract void getPersona(Map paramMap);

    public abstract void getHijo(Map paramMap);

    public abstract List<Constancia> getListConstanciaAcreCmpl(String paramString1, String paramString2, String paramString3);

//    public abstract DataSepelio mapearDataSepelio(SendCanjeCitt paramSendCanjeCitt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

    public abstract String equivalenciaDocumentoSASSIA(String paramString);

    public abstract String equivalenciaDocumentoSASNETI(String paramString);

    public abstract void getTitulares(Map paramMap);

    public abstract Persona buscarPersonaXide(Map paramMap);

    public abstract void ejecutarCalificaSepelio(Map paramMap);

    public abstract void insertarComprobanteSepelio(Map paramMap);

    public abstract void ejecutarOrdenPagoSepelio(Map paramMap);

    public abstract void listarGrilla(Map paramMap);

    public abstract void listarGrillaEcoSeguros_05(Map paramMap);

    public abstract void listarGrillaReporteBajas_03(Map paramMap);

    public abstract void listarGrillaAltasCNV_02(Map paramMap);

    public abstract void listarGrillaAcreComple_04(Map paramMap);

    public abstract void listarGrillaDETAL_01(Map paramMap);

    public abstract void listarGrillaCANJE_06(Map paramMap);

    public abstract void listarGrillaMensualAsegurado_07(Map paramMap);

    public abstract void listarGrillaMensualEMPRESA_08(Map paramMap);

    public abstract void listarGrillaEXPE_SAS_09(Map paramMap);

    public abstract void listarGrillaEXPE_VIVA_10(Map paramMap);

    public abstract void listarGrilla20_PRIMEROS_DIAS_11(Map paramMap);

    public abstract void listarGrilla_Sepelio_12(Map paramMap);

    public abstract void listarGrilla_Direccion_13(Map paramMap);

    public abstract void listarGrilla_Bajas_14(Map paramMap);

    public abstract void listarGrilla_Latencia_15(Map paramMap);
}