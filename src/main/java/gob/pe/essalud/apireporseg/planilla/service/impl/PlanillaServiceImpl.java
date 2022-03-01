package gob.pe.essalud.apireporseg.planilla.service.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import gob.pe.essalud.apireporseg.planilla.model.Constancia;
import gob.pe.essalud.apireporseg.planilla.model.DataSepelio;
import gob.pe.essalud.apireporseg.planilla.model.Persona;
import gob.pe.essalud.apireporseg.planilla.model.SendCanjeCitt;
import gob.pe.essalud.apireporseg.planilla.repository.ReportesMapper;
import gob.pe.essalud.apireporseg.planilla.service.PlanillaService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.UriBuilder;

import gob.pe.essalud.apireporseg.util.UsuarioOspeProperties;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanillaServiceImpl implements PlanillaService
{
    String usuario;
    String ip;
    private String URLWS;
    private static final String CAMPO_ERROR = "error";
    private static final String CAMPO_MENSAJE = "message";
    private static final String REST_OVACREDCMPLT = "wsacredcmplov";
    private String USERWSDL = "appacreditacioncmpl";
    private String PASSWORDWSDL = "D9JKH9F7DSN7V5FaHuzGVIJGDuXKVoXGD9BwZSBiHIvsD5FGDErYVkBsHEFYDoX7HkBiH9F7DSBY";
    WebResource servicePort;
    JSONObject jsonresult;
    JSONArray jsonArrayresult;

    @Autowired
    private ReportesMapper reportesMapper;

    @Autowired
    public PlanillaServiceImpl(UsuarioOspeProperties USproperties)
    {
        this.usuario=USproperties.getUsuario();
        this.ip=USproperties.getIp();
        this.URLWS=USproperties.getUrlws();

        URI location = UriBuilder.fromUri(URLWS)
                .build();
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        servicePort = client.resource(location);
    }

    public void ejecutarCalificacion(Map map)
    {
        try
        {
            map.put("codUsuario", this.usuario);
            map.put("ip", this.ip);

            this.reportesMapper.ejecutarCalificacion(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public String esNull(String texto)
    {
        String resultado = "";
        if ((texto == null) || (texto == ""))
            resultado = "";
        else {
            resultado = texto;
        }
        return resultado;
    }

    public void envioBackOffice(Map map)
    {
        try {
            map.put("codUsuario", this.usuario);
            map.put("ip", this.ip);

            this.reportesMapper.envioBackOffice(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void getPersona(Map params)
    {
        try
        {
            this.reportesMapper.getPersona(params);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void getHijo(Map params) {
        try {
            this.reportesMapper.getHijo(params);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void getTitulares(Map params) {
        try {
            this.reportesMapper.getTitulares(params);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public List<Constancia> getListConstanciaAcreCmpl(String tpDoc, String nrDoc, String idUsuario) {
        List listDet = new ArrayList();

        String jsonResult = (String)this.servicePort.path("wsacredcmplov").path("consultAccreCmpl")
                .queryParam("tpDoc", tpDoc)
                .queryParam("nrDoc", nrDoc)
                .queryParam("login", idUsuario)
                .queryParam("user", this.USERWSDL)
                .queryParam("pass", this.PASSWORDWSDL)
                .get(String.class);

        listDet = leerJsonResultList(jsonResult);
        return listDet;
    }

    private List<Constancia> leerJsonResultList(String jsonResult) {
        List listDet = new ArrayList();

        this.jsonresult = new JSONObject(jsonResult);
        System.out.println("Datos " + jsonResult);
        if (this.jsonresult.getInt("error") == 0) {
            this.jsonArrayresult = ((JSONArray)this.jsonresult.get("detail"));
            if (this.jsonArrayresult.length() > 0) {
                for (int m = 0; m < this.jsonArrayresult.length(); ++m) {
                    JSONObject object = (JSONObject)this.jsonArrayresult.get(m);
                    Constancia constancia = new Constancia();
                    constancia.setTpDoc(object.getString("TpDoc"));
                    constancia.setDesDoc(object.getString("dsTpDoc"));
                    constancia.setNrDoc(object.getString("nrDoc"));
                    constancia.setApPaterno(object.getString("apPaterno"));
                    constancia.setApMaterno(object.getString("apMaterno"));
                    constancia.setNombres(object.getString("nombres"));
                    constancia.setVinculo(object.getString("vinculo"));
                    constancia.setFecIniAcred(object.getString("fecIniSol"));
                    constancia.setFecFinAcred(object.getString("fecFinSol"));
                    constancia.setAutFamiliar(object.getString("autFamiliar"));
                    constancia.setDgansasFamiliar(object.getString("dgansasFamiliar"));
                    constancia.setAutTitular(object.getString("autTitular"));
                    constancia.setEstado(object.getString("estado"));
                    constancia.setFecProceso(object.getString("fecProceso"));
                    listDet.add(constancia);
                }
            }
        }
        return listDet;
    }

//    public DataSepelio mapearDataSepelio(SendCanjeCitt solicitud, String tipo_doc_usuario, String numdoc_usuario, String codOspe, String tipo_pago, String idUsuario)
//    {
//        DataSepelio bean = new DataSepelio();
//        bean.setV_cod_tdoc_beneficiario("101");
//        bean.setV_cod_edoc_beneficiario(solicitud.getTipo_doc_Be());
//        bean.setV_num_ndoc_beneficiario(solicitud.getNum_doc_Be());
//        bean.setV_txt_nombre_beneficiario(solicitud.getNombresApellBe());
//        bean.setV_cod_tdoc_titular("101");
//        bean.setV_cod_edoc_titular(solicitud.getTipo_doc_titular());
//        bean.setV_txt_nombre_titular(solicitud.getNombresyApellTitular());
//        bean.setV_num_ndoc_titular(solicitud.getNum_doc_titular());
//        bean.setV_num_monto_subsidiado("0");
//        bean.setV_monto_solicitado(solicitud.getMontoSolicitado());
//        bean.setV_fec_fallecimiento_titular("");
//        bean.setV_cod_tparentesco("326");
//        bean.setV_cod_eparentesco("0199");
//        bean.setV_flag_resultado("0");
//        bean.setV_numero_expediente("");
//        bean.setV_fecha_pago("");
//        bean.setV_fecha_cese("");
//        bean.setV_fecha_creacion("");
//        bean.setV_cod_usuario_crea("SEPELIOOV");
//        bean.setV_ip_usuario_crea("10.0.0.0");
//        bean.setV_cod_usuario_viva(idUsuario);
//        bean.setV_flag_activo_inactivo("1");
//        bean.setV_cod_edoc_usuario(tipo_doc_usuario);
//        bean.setV_num_doc_usuario(numdoc_usuario);
//        bean.setV_cod_ospe(codOspe);
//        bean.setV_tip_pago(tipo_pago);
//        return bean;
//    }

    public String equivalenciaDocumentoSASSIA(String tipo)
    {
        String valor = "";
        switch (tipo)
        {
            case "01":
                valor = "1";
                break;
            case "04":
                valor = "2";
                break;
            case "23":
                valor = "P";
                break;
            default:
                valor = "2";
        }

        return valor;
    }

    public String equivalenciaDocumentoSASNETI(String tipo) {
        String valor = "";
        switch (tipo)
        {
            case "01":
                valor = "01";
                break;
            case "04":
                valor = "04";
                break;
            case "23":
                valor = "22";
                break;
            default:
                valor = "04";
        }

        return valor;
    }

    public Persona buscarPersonaXide(Map map)
    {
        return this.reportesMapper.buscarPersonaXide(map);
    }

    public void ejecutarCalificaSepelio(Map map)
    {
        try
        {
            this.reportesMapper.ejecutarCalificaSepelio(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void insertarComprobanteSepelio(Map map)
    {
        try {
            this.reportesMapper.insertarComprobanteSepelio(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void ejecutarOrdenPagoSepelio(Map map)
    {
        try
        {
            this.reportesMapper.ejecutarOrdenPagoSepelio(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrilla(Map map)
    {
        try
        {
            this.reportesMapper.listarGrilla(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrillaEcoSeguros_05(Map map)
    {
        try
        {
            this.reportesMapper.listarGrillaEcoSeguros_05(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrillaReporteBajas_03(Map map)
    {
        try
        {
            this.reportesMapper.listarGrillaReporteBajas_03(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrillaAltasCNV_02(Map map)
    {
        try
        {
            this.reportesMapper.listarGrillaAltasCNV_02(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrillaAcreComple_04(Map map)
    {
        try {
            this.reportesMapper.listarGrillaAcreComple_04(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrillaDETAL_01(Map map)
    {
        try
        {
            this.reportesMapper.listarGrillaDETAL_01(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrillaCANJE_06(Map map)
    {
        try
        {
            this.reportesMapper.listarGrillaCANJE_06(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrillaMensualAsegurado_07(Map map)
    {
        try
        {
            this.reportesMapper.listarGrillaMensualAsegurado_07(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrillaMensualEMPRESA_08(Map map)
    {
        try
        {
            this.reportesMapper.listarGrillaMensualEMPRESA_08(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrillaEXPE_SAS_09(Map map)
    {
        try {
            this.reportesMapper.listarGrillaEXPE_SAS_09(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrillaEXPE_VIVA_10(Map map)
    {
        try
        {
            this.reportesMapper.listarGrillaEXPE_VIVA_10(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrilla20_PRIMEROS_DIAS_11(Map map)
    {
        try
        {
            this.reportesMapper.listarGrilla20_PRIMEROS_DIAS_11(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrilla_Sepelio_12(Map map)
    {
        try
        {
            this.reportesMapper.listarGrilla_Sepelio_12(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrilla_Direccion_13(Map map)
    {
        try
        {
            this.reportesMapper.listarGrilla_Direccion_13(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrilla_Bajas_14(Map map)
    {
        try
        {
            this.reportesMapper.listarGrilla_Bajas_14(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void listarGrilla_Latencia_15(Map map)
    {
        try
        {
            this.reportesMapper.listarGrilla_Latencia_15(map);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}