package gob.pe.essalud.apireporseg.planilla.service;

import gob.pe.essalud.apireporseg.planilla.model.ArchivoAccredCmplBean;
import gob.pe.essalud.apireporseg.planilla.model.ArchivoBean;
import gob.pe.essalud.apireporseg.planilla.model.Comprobantes;
import gob.pe.essalud.apireporseg.planilla.model.DataSepelio;
import gob.pe.essalud.apireporseg.planilla.model.InabilityBean;
import gob.pe.essalud.apireporseg.planilla.model.ListArchivo;
import gob.pe.essalud.apireporseg.planilla.model.MaternityBean;
import gob.pe.essalud.apireporseg.planilla.model.PeriodosLaboradosBean;
import gob.pe.essalud.apireporseg.planilla.model.SubLactanciaBean;
import gob.pe.essalud.apireporseg.planilla.model.WebSerLogBean;
import gob.pe.essalud.apireporseg.planilla.model.modelAcredComple;
import java.util.List;
import java.util.Map;

public abstract interface WSOspeVirtualDAO
{
    public abstract Map validarAccesoX(String paramString1, String paramString2);

    public abstract String registrarLog(WebSerLogBean paramWebSerLogBean);

    public abstract Map getInsuredData(String paramString1, String paramString2);

    public abstract Map getInsuredReniec(String paramString1, String paramString2);

    public abstract Map getTaxpayer(String paramString);

    public abstract Map getCITT(String paramString1, String paramString2, String paramString3, String paramString4);

    public abstract Map getFamilyBond(String paramString1, String paramString2, String paramString3, String paramString4);

    public abstract Map insertarLactancia(SubLactanciaBean paramSubLactanciaBean);

    public abstract Map get20Days(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

    public abstract Map getReactivationEmployer(String paramString1, String paramString2, String paramString3);

    public abstract Map getReactivationInsured(String paramString1, String paramString2, String paramString3);

    public abstract Map sendReactivation(String paramString1, String paramString2, String paramString3, String paramString4);

    public abstract Map getEmploymentRelationship(String paramString1, String paramString2);

    public abstract Map sendReimbursementPayment(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

    public abstract Map reportLactancia(String paramString1, String paramString2, String paramString3);

    public abstract int createRequest(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

    public abstract Map registerInability(InabilityBean paramInabilityBean);

    public abstract Map registerArchive(ArchivoBean paramArchivoBean);

    public abstract Map reportInability(String paramString1, String paramString2, String paramString3, String paramString4);

    public abstract Map updateStatusInability(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

    public abstract Map valRegisterInability(InabilityBean paramInabilityBean);

    public abstract Map sendBackOfficeInability(Integer paramInteger, String paramString1, String paramString2, String paramString3);

    public abstract Map verifyPaymentMaternity(String paramString);

    public abstract Map insertMatSolicitud(MaternityBean paramMaternityBean);

    public abstract Map consultRequest(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7);

    public abstract Map consultRequestGroup(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7);

    public abstract Map consultFamilyHolder(String paramString1, String paramString2);

    public abstract Map consultAccreCmpl(String paramString1, String paramString2);

    public abstract Map sendAccredCmpl(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

    public abstract Map sendArchiveAcredCmpl(ArchivoAccredCmplBean paramArchivoAccredCmplBean);

    public abstract Map sendAccredCmplRigthHolder(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

    public abstract Map valAccredCmplHeadLine(String paramString1, String paramString2, String paramString3, String paramString4);

    public abstract Map consultAccreditation(String paramString1, String paramString2);

    public abstract Map getBorradoresReembolso(String paramString1, String paramString2);

    public abstract Map deleteBorradorReembolso(String paramString);

    public abstract Map registrarPagoReembolso(String paramString1, String paramString2);

    public abstract Map getDetalleSolicitud(String paramString1, String paramString2);

    public abstract Map sendDirectPayment(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

    public abstract Map getParentsReniec(String paramString);

    public abstract Map getListLactSmart(String paramString1, String paramString2, String paramString3);

    public abstract Map getDisabledPerson(String paramString);

    public abstract Map getArchiveVUA(String paramString1, String paramString2, String paramString3);

    public abstract String get_contribuyentes_direcion(String paramString);

    public abstract String get_reniec_ubigeo(String paramString);

    public abstract String get_reniec_direccion(String paramString);

    public abstract String get_contribuyentes_ubigeo(String paramString);

    public abstract List<PeriodosLaboradosBean> get_subsidio_periodo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

    public abstract String get_obtener_nombre_anio(String paramString);

    public abstract String get_obtener_nombre_decenio(String paramString);

    public abstract String get_obtener_nitt_monto_exceso(String paramString);

    //public abstract Map getListaTrabajoHabitual();

   // public abstract Map insertarsepelio(DataSepelio paramDataSepelio);

    public abstract Map registerComproantes(Comprobantes paramComprobantes, Integer paramInteger);

    public abstract Map registerComproanteTotal(List<Comprobantes> paramList, Integer paramInteger);

   // public abstract Map registerRutas(ListArchivo paramListArchivo);

    public abstract List<modelAcredComple> get_listarGrillaAcreComple(String paramString1, String paramString2);
}