package gob.pe.essalud.apireporseg.planilla.repository;

import gob.pe.essalud.apireporseg.planilla.model.Persona;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface ReportesMapper
{
    public abstract void ejecutarCalificacion(Map paramMap);

    public abstract void envioBackOffice(Map paramMap);

    public abstract void getPersona(Map paramMap);

    public abstract void getHijo(Map paramMap);

    public abstract void getTitulares(Map paramMap);

    public abstract Persona buscarPersonaXide(Map paramMap);

    public abstract void ejecutarCalificaSepelio(Map paramMap);

    public abstract void insertarComprobanteSepelio(Map paramMap);

    public abstract void ejecutarOrdenPagoSepelio(Map paramMap);

    public abstract void listarGrilla(Map paramMap);

    public abstract void listarGrillaDETAL_01(Map paramMap);

    public abstract void listarGrillaAltasCNV_02(Map paramMap);

    public abstract void listarGrillaReporteBajas_03(Map paramMap);

    public abstract void listarGrillaAcreComple_04(Map paramMap);

    public abstract void listarGrillaEcoSeguros_05(Map paramMap);

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