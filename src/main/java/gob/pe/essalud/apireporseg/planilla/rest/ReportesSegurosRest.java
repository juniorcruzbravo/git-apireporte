package gob.pe.essalud.apireporseg.planilla.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gob.pe.essalud.apireporseg.planilla.model.Backoffice;
import gob.pe.essalud.apireporseg.planilla.model.Calificacion;
import gob.pe.essalud.apireporseg.planilla.model.Comprobantes;
import gob.pe.essalud.apireporseg.planilla.model.Constancia;
import gob.pe.essalud.apireporseg.planilla.model.ConsultaVirtualLactancia;
import gob.pe.essalud.apireporseg.planilla.model.DataSepelio;
import gob.pe.essalud.apireporseg.planilla.model.LactanciaNA;
import gob.pe.essalud.apireporseg.planilla.model.ListArchivo;
import gob.pe.essalud.apireporseg.planilla.model.PagosCsampago;
import gob.pe.essalud.apireporseg.planilla.model.Resultado;
import gob.pe.essalud.apireporseg.planilla.model.ResultadoAsegurado;
import gob.pe.essalud.apireporseg.planilla.model.ResultadoGenerarOrdenPago;
import gob.pe.essalud.apireporseg.planilla.model.ResultadoInsercion;
import gob.pe.essalud.apireporseg.planilla.model.ResultadoInsercionRutas;
import gob.pe.essalud.apireporseg.planilla.model.ResultadoValidaSepelio;
import gob.pe.essalud.apireporseg.planilla.model.SendCanjeCitt;
import gob.pe.essalud.apireporseg.planilla.model.TitularesSepelio;
import gob.pe.essalud.apireporseg.planilla.model.model20PrimerosDias;
import gob.pe.essalud.apireporseg.planilla.model.modelAcredComple;
import gob.pe.essalud.apireporseg.planilla.model.modelAltaCNV;
import gob.pe.essalud.apireporseg.planilla.model.modelBajas;
import gob.pe.essalud.apireporseg.planilla.model.modelCanjeCitt;
import gob.pe.essalud.apireporseg.planilla.model.modelDetail127;
import gob.pe.essalud.apireporseg.planilla.model.modelDireccion;
import gob.pe.essalud.apireporseg.planilla.model.modelEXPE_SAS;
import gob.pe.essalud.apireporseg.planilla.model.modelEXPE_VIVA;
import gob.pe.essalud.apireporseg.planilla.model.modelEcoSeguros;
import gob.pe.essalud.apireporseg.planilla.model.modelLatencia;
import gob.pe.essalud.apireporseg.planilla.model.modelMensualAsegurado;
import gob.pe.essalud.apireporseg.planilla.model.modelMensualEmpresa;
import gob.pe.essalud.apireporseg.planilla.model.modelReporteBajas;
import gob.pe.essalud.apireporseg.planilla.model.modelSepelioRepor;
import gob.pe.essalud.apireporseg.planilla.service.PlanillaService;
import gob.pe.essalud.apireporseg.planilla.service.WSOspeVirtualDAO;
import gob.pe.essalud.apireporseg.util.FechaSolicitudUtil;
import gob.pe.essalud.apireporseg.util.FileSystemStorageService;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins={"*"})
@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ReportesSegurosRest
{
    private static final Logger logger = LogManager.getLogger(ReportesSegurosRest.class);

    @Autowired
    private PlanillaService planillaService;

    @Autowired
    private WSOspeVirtualDAO OspeVirtualDAO;

    @Autowired
    private FileSystemStorageService storageService;
    List<ResultadoAsegurado> listPersona;
    List<TitularesSepelio> listTitulares;
    List<ConsultaVirtualLactancia> list;
    List<ResultadoValidaSepelio> listSepelioValida;
    List<ResultadoGenerarOrdenPago> listSepelioOrden;
    List<Backoffice> back;

//    @GetMapping({"/buscarRest/{docu}/{tipo}/{dependencia}"})
//    public ResponseEntity<ResultadoAsegurado> showBuscar(@PathVariable("docu") String docu, @PathVariable("tipo") String tipo)
//    {
//        ResultadoAsegurado persona = new ResultadoAsegurado();
//        logger.info("Entre a la funcion ");
//        Map map = new HashMap();
//        map.put("TP_DOC", tipo);
//        map.put("NR_DOC", docu);
//
//        this.planillaService.getPersona(map);
//        this.listPersona = ((List)map.get("CUR_RECORDSET"));
//
//        if (this.listPersona.size() > 0) {
//            persona = (ResultadoAsegurado)this.listPersona.get(0);
//            persona.setNomApCompleto(persona.getNombres() + " " + persona.getApepaterno() + " " + persona.getApematerno());
//        }
//        else {
//            persona.setNombres("");
//            persona.setApepaterno("");
//            persona.setApematerno("");
//            persona.setSexo("");
//            persona.setNomApCompleto("");
//        }
//
//        logger.info("El resultado es el siguiente " + persona.toString());
//
//        return new ResponseEntity(persona, HttpStatus.OK);
//    }

//    @GetMapping({"/buscarRestHijo/{docu}/{tipo}"})
//    public ResponseEntity<ResultadoAsegurado> showBuscarHijo(@PathVariable("docu") String docu, @PathVariable("tipo") String tipo)
//    {
//        ResultadoAsegurado persona = new ResultadoAsegurado();
//        logger.info("Entre a la funcion hijo");
//
//        Map map = new HashMap();
//        map.put("NR_DOC", docu);
//
//        this.planillaService.getHijo(map);
//        this.listPersona = ((List)map.get("CUR_RECORDSET"));
//
//        if (this.listPersona.size() > 0) {
//            persona = (ResultadoAsegurado)this.listPersona.get(0);
//            persona.setNomApCompleto(persona.getNombres() + " " + persona.getApepaterno() + " " + persona.getApematerno());
//        }
//        else {
//            persona.setNombres("");
//            persona.setApepaterno("");
//            persona.setApematerno("");
//            persona.setSexo("");
//            persona.setNomApCompleto("");
//        }
//
//        logger.info("El resultado es el siguiente " + persona.toString());
//
//        return new ResponseEntity(persona, HttpStatus.OK);
//    }

//    @PostMapping({"/lactanciaInsert"})
//    public ResponseEntity<Calificacion> ejecutarAutomatizado(@RequestBody String jsonLactancia)
//    {
//        logger.info("El resultado es el siguiente " + jsonLactancia);
//
//        Calificacion calificacion = new Calificacion();
//
//        Gson gson = new Gson();
//
//        List lactanciasResultados = new ArrayList();
//        List backoffice = new ArrayList();
//        Type tipoLista = new TypeToken() {  }
//                .getType();
//        List lactancia = (List)gson.fromJson(jsonLactancia, tipoLista);
//
//        Iterator it = lactancia.iterator();
//
//        while (it.hasNext())
//        {
//            LactanciaNA item = (LactanciaNA)it.next();
//
//            Map map = new HashMap();
//
//            map.put("tipoDocumentoMadre", item.getCodEDocBeneficiario());
//            map.put("dniMadre", item.getNumDocBeneficiario());
//            map.put("tipoDocumentoHijo", item.getCodEDocHijo());
//            map.put("numeroDocumentoHijo", item.getNumDocHijo());
//            map.put("cui", item.getNumDocHijo());
//
//            map.put("partoMultiple", item.getCodETipoParto());
//            map.put("parametro1", Character.valueOf('0'));
//
//            this.planillaService.ejecutarCalificacion(map);
//
//            this.list = ((List)map.get("VS_CURSOR"));
//
//            this.back = ((List)map.get("poc_backoffice"));
//
//            String vs_resultado = this.planillaService.esNull((String)map.get("VS_RESULT")).trim();
//            String vs_exp_nit = this.planillaService.esNull((String)map.get("VS_EXP_O_NIT")).trim();
//            String vs_detalle = this.planillaService.esNull((String)map.get("VS_DETALLE")).trim();
//
//            ((ConsultaVirtualLactancia)this.list.get(0)).setExp_nit(vs_exp_nit);
//            ((ConsultaVirtualLactancia)this.list.get(0)).setNombreHijo(item.getNombreHijo());
//            ((ConsultaVirtualLactancia)this.list.get(0)).setNombreMadre(item.getNombreMadre());
//            ((ConsultaVirtualLactancia)this.list.get(0)).setNum_doc_hijo(item.getNumDocHijo());
//            ((ConsultaVirtualLactancia)this.list.get(0)).setResultado(vs_resultado);
//            if (vs_resultado.equals("1")) {
//                ((ConsultaVirtualLactancia)this.list.get(0)).setFechaPago(vs_detalle);
//            }
//
//            if ((vs_resultado.equals("2")) &&
//                    (((ConsultaVirtualLactancia)this.list.get(0)).getCorrelativo() != null) && (!((ConsultaVirtualLactancia)this.list.get(0)).getCorrelativo().isEmpty())) {
//                Map map2 = new HashMap();
//                map2.put("correlativo", ((ConsultaVirtualLactancia)this.list.get(0)).getCorrelativo());
//                logger.info("EL correlativo es " + ((ConsultaVirtualLactancia)this.list.get(0)).getCorrelativo());
//                this.planillaService.envioBackOffice(map2);
//                String vs_nit = this.planillaService.esNull((String)map2.get("VS_EXP_O_NIT")).trim();
//                String vs_detalle1 = this.planillaService.esNull((String)map2.get("VS_DETALLE")).trim();
//                ((ConsultaVirtualLactancia)this.list.get(0)).setExp_nit(vs_nit);
//                ((ConsultaVirtualLactancia)this.list.get(0)).setComentario(vs_detalle1);
//            }
//
//            lactanciasResultados.addAll(this.list);
//
//            if (this.back != null)
//                backoffice.addAll(this.back);
//            else {
//                logger.info("El Array No tiene elementos");
//            }
//
//        }
//
//        calificacion.setBackoffice(backoffice);
//        calificacion.setLactanciasResultados(lactanciasResultados);
//
//        logger.info("El resultado final de todo es" + calificacion.toString());
//
//        return new ResponseEntity(calificacion, HttpStatus.OK);
//    }

    @GetMapping({"/derechohabientes/{docu}/{tipo}"})
    public ResponseEntity<List<Constancia>> showBuscarderechohabientes(@PathVariable("docu") String docu, @PathVariable("tipo") String tipo)
    {
        List derechohabientes = this.planillaService.getListConstanciaAcreCmpl(tipo, docu, "rdh");
        logger.info("derechohabientes fueron " + derechohabientes);

        return new ResponseEntity(derechohabientes, HttpStatus.OK);
    }

//    @PostMapping({"/enviarSolicitud"})
//    public ResponseEntity<Resultado> grabarSolicitud(@RequestPart("solicitud") SendCanjeCitt solicitud, @RequestPart("files") MultipartFile[] files)
//    {
//        FechaSolicitudUtil util = new FechaSolicitudUtil();
//        String fechayHora = util.FechaActual();
//
//        System.out.println("DATOS TOTALES FORMULARIO ");
//
//        System.out.println(solicitud.getTipo_doc_titular());
//        System.out.println(solicitud.getNum_doc_titular());
//        System.out.println(solicitud.getNombresyApellTitular());
//        System.out.println(solicitud.getTipo_doc_Be());
//        System.out.println(solicitud.getNum_doc_Be());
//        System.out.println(solicitud.getNombresApellBe());
//        System.out.println(solicitud.getCodigoParentesco());
//        System.out.println(solicitud.getDesParentesco());
//        System.out.println(solicitud.getMontoSolicitado());
//        System.out.println(solicitud.getCod_ospe());
//        System.out.println(solicitud.getIde_usuario());
//
//        System.out.println(solicitud.getComprobantes().toString());
//        System.out.println(files.toString());
//
//        ArrayList documentos = new ArrayList();
//        ArrayList archivoBeans = new ArrayList();
//        ListArchivo arhivos = new ListArchivo();
//        Resultado resultado = new Resultado();
//        resultado.setFlag(Integer.valueOf(0));
//        try
//        {
//            if (resultado.getFlag().intValue() == 0)
//            {
//                System.out.println("listado de Archivos " + files.length);
//                System.out.println("FILES" + files.length);
//                System.out.println("Correo" + solicitud.getCorreo());
//                System.out.println("tipopago" + solicitud.getTipo_pago());
//
//                DataSepelio bean = new DataSepelio();
//                bean = this.planillaService.mapearDataSepelio(solicitud, solicitud.getTipo_doc_Be(), solicitud.getNum_doc_Be(), solicitud.getCod_ospe(), "S", solicitud.getIde_usuario());
//                ResultadoInsercion resultadoInser = new ResultadoInsercion();
//                ResultadoInsercionRutas resultadoInserRutas = new ResultadoInsercionRutas();
//                ArrayList archivoEnvio = new ArrayList();
//
//                System.out.println("Los dato enviados son " + bean.toString());
//
//                Map map = new HashMap();
//
//                map.put("p_tipo_doc_titular", bean.getV_cod_edoc_titular());
//                map.put("p_num_doc_titular", bean.getV_num_ndoc_titular());
//                map.put("p_num_dependencia", bean.getV_cod_ospe());
//                map.put("p_ip_usuario_crea", bean.getV_ip_usuario_crea());
//
//                this.planillaService.ejecutarCalificaSepelio(map);
//
//                this.listSepelioValida = ((List)map.get("p_grilla_valida"));
//
//                System.out.println("RESULTADO valida " + this.listSepelioValida.toString());
//
//                System.out.println("RESULTADO MAPS " + map.toString());
//
//                if (((String)map.get("p_cod_resultado")).equals("1"))
//                {
//                    int idx = 0;
//                    for (Comprobantes o : solicitud.getComprobantes()) {
//                        System.out.println("ENTREFFFFFFFFFFFFFFFF");
//                        Map mapCompro = new HashMap();
//
//                        mapCompro.put("p_detalle_sepelio", map.get("v_ide_solicitud"));
//                        mapCompro.put("p_ruc_empresa", o.getNumero_ruc());
//                        mapCompro.put("p_fec_comprobante", o.getFecha_comprobante());
//                        mapCompro.put("p_numero_serie", o.getNumero_serie());
//                        mapCompro.put("p_num_comprobante", o.getNumero_comprobante());
//                        mapCompro.put("p_imp_comprobante", Float.valueOf(Float.parseFloat(o.getImporte())));
//                        mapCompro.put("p_tipo_comprobante", o.getTipo_comprobante());
//
//                        System.out.println("DATOS MANDADOS MAPS " + mapCompro.toString());
//
//                        this.planillaService.insertarComprobanteSepelio(mapCompro);
//                        ++idx;
//                    }
//
//                    Object mapOrdenPago = new HashMap();
//
//                    ((Map)mapOrdenPago).put("p_ide_solicitud", map.get("v_ide_solicitud"));
//                    ((Map)mapOrdenPago).put("p_tipo_doc_beneficiario", bean.getV_cod_edoc_beneficiario());
//                    ((Map)mapOrdenPago).put("p_num_doc_beneficiario", bean.getV_num_ndoc_beneficiario());
//                    ((Map)mapOrdenPago).put("v_nombre_completo", bean.getV_txt_nombre_beneficiario());
//                    ((Map)mapOrdenPago).put("v_monto_subsidio", bean.getV_monto_solicitado());
//                    ((Map)mapOrdenPago).put("p_imp_decl_jurada", "0");
//                    ((Map)mapOrdenPago).put("p_num_dependencia", bean.getV_cod_ospe());
//                    ((Map)mapOrdenPago).put("p_ip_origen", bean.getV_ip_usuario_crea());
//
//                    System.out.println("RESULTADO MAPS  orden antes de enviar los datos  " + mapOrdenPago.toString());
//
//                    this.planillaService.ejecutarOrdenPagoSepelio((Map)mapOrdenPago);
//
//                    this.listSepelioOrden = ((List)((Map)mapOrdenPago).get("p_grilla_resultado"));
//
//                    System.out.println("RESULTADO valida Orden " + this.listSepelioOrden.toString());
//
//                    System.out.println("RESULTADO MAPS  orden " + mapOrdenPago.toString());
//
//                    System.out.println("entre inserte bien ->" + ((Map)mapOrdenPago).get("p_cod_resultado"));
//                    if (((String)((Map)mapOrdenPago).get("p_cod_resultado")).equals("1")) {
//                        resultado.setFlag(Integer.valueOf(0));
//                        resultado.setDescripcion("su fecha de pago es " + ((Map)mapOrdenPago).get("p_texto_1") + " y su numero de expediente es " + ((Map)mapOrdenPago).get("p_texto_2"));
//                    }
//                    else if (((String)((Map)mapOrdenPago).get("p_cod_resultado")).equals("2")) {
//                        resultado.setFlag(Integer.valueOf(1));
//                        resultado.setDescripcion("Su solicitud se califico con exito sin embargo hubieron observaciones en las fechas de los comprobantes, se le genero el siguiente nit de seguimiento " + ((Map)mapOrdenPago).get("p_texto_2"));
//                    }
//                    else
//                    {
//                        resultado.setFlag(Integer.valueOf(1));
//                        resultado.setDescripcion("Se ha producido un error al momento de generar su orden de pago <br> Por favor volver a intentar, si el error persiste puede llamar a nuestras centrales telefonicas <br> Disculpa las molestias.");
//                    }
//
//                }
//                else
//                {
//                    System.out.println("no entre entre-->" + map.get("error"));
//                    resultado.setFlag(Integer.valueOf(1));
//
//                    if (this.listSepelioValida != null) {
//                        resultado.setDescripcion("Se ha producido el siguiente error al momento de validar solicitud <br>" + ((ResultadoValidaSepelio)this.listSepelioValida.get(0)).getDetalle_error());
//                    }
//                    else
//                    {
//                        resultado.setDescripcion("Se ha producido un error al momento de validar solicitud <br> Por favor volver a intentar, si el error persiste puede llamar a nuestras centrales telefonicas <br> Disculpa las molestias");
//                    }
//
//                }
//
//            }
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            resultado.setFlag(Integer.valueOf(1));
//            resultado.setDescripcion("Se ha producido un error inesperado<br> Por favor volver a intentar, si el error persiste puede llamar a nuestras centrales telefonicas <br> Disculpa las molestias");
//        }
//
//        return (ResponseEntity<Resultado>)new ResponseEntity(resultado, HttpStatus.OK);
//    }

    @GetMapping({"/buscarTitulares/{docu}"})
    public ResponseEntity<List<TitularesSepelio>> showBuscarTitulares(@PathVariable("docu") Integer docu)
    {
        System.out.println("entre a busar buscarTitulares");
        ResultadoAsegurado persona = new ResultadoAsegurado();
        logger.info("Entre a la funcion hijo");

        Map map = new HashMap();
        map.put("ID_NUMERICO", docu);

        this.planillaService.getTitulares(map);
        this.listTitulares = ((List)map.get("CUR_RECORDSET"));

        System.out.println("los titutalares " + this.listTitulares.toString());

        return new ResponseEntity(this.listTitulares, HttpStatus.OK);
    }

//    @GetMapping({"/buscarBeneficiario/{ide}"})
//    public ResponseEntity<InputStreamResource> Beneficiario(@PathVariable("ide") String ide)
//    {
//        System.out.println("entre a busar buscarBeneficiario");
//        try {
//            System.out.println("entre a busar buscarBeneficiario 2");
//            List listUsers = new ArrayList();
//            Comprobantes compro1 = new Comprobantes();
//            compro1.setDes_comprobante("Compro 1");
//            compro1.setDes_concepto("Pago 1");
//            compro1.setId("1");
//            compro1.setFecha_comprobante("01/08/2021");
//
//            Comprobantes compro2 = new Comprobantes();
//            compro2.setDes_comprobante("Compro 1");
//            compro2.setDes_concepto("Pago 1");
//            compro2.setId("1");
//            compro2.setFecha_comprobante("01/08/2021");
//
//            Comprobantes compro3 = new Comprobantes();
//            compro3.setDes_comprobante("Compro 1");
//            compro3.setDes_concepto("Pago 1");
//            compro3.setId("1");
//            compro3.setFecha_comprobante("01/08/2021");
//            Comprobantes compro4 = new Comprobantes();
//            compro4.setDes_comprobante("Compro 1");
//            compro4.setDes_concepto("Pago 1");
//            compro4.setId("1");
//            compro4.setFecha_comprobante("01/08/2021");
//            listUsers.add(compro1);
//            listUsers.add(compro2);
//            listUsers.add(compro3);
//            listUsers.add(compro4);
//            System.out.println("la lista enviads" + listUsers.size());
//            UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "attachment;filename=hero.docx");
//
//            System.out.println("LLEGUE HASTA EL ENVIAR");
//
//            return ((ResponseEntity.BodyBuilder)ResponseEntity.ok()
//                    .headers(headers))
//                    .body(new InputStreamResource(excelExporter
//                            .export()));
//        }
//        catch (Exception e)
//        {
//            throw new NullPointerException(e.getMessage());
//        }
//    }

//    @GetMapping({"/buscarExcelReporte/{ruc}/{fechaIni}/{fechaFin}"})
//    public ResponseEntity<InputStreamResource> buscarExcelReporte(@PathVariable("ruc") String ruc, @PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
//    {
//        List listUsers = null;
//        try
//        {
//            if (ruc.equals("0")) {
//                ruc = "";
//            }
//
//            if (fechaIni.equals("0")) {
//                fechaIni = "";
//            }
//
//            if (fechaFin.equals("0")) {
//                fechaFin = "";
//            }
//
//            Map map = new HashMap();
//            map.put("PIV_NUMERO_RUC", ruc);
//            map.put("PIV_FECHA_INICIO", fechaIni);
//            map.put("PIV_FECHA_FECHA", fechaFin);
//            this.planillaService.listarGrilla(map);
//            listUsers = (List)map.get("VS_CURSOR");
//
//            UserExcelExporterReporte excelExporter = new UserExcelExporterReporte(listUsers);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "attachment;filename=hero.docx");
//
//            return ((ResponseEntity.BodyBuilder)ResponseEntity.ok()
//                    .headers(headers))
//                    .body(new InputStreamResource(excelExporter
//                            .export()));
//        }
//        catch (Exception e)
//        {
//            throw new NullPointerException(e.getMessage());
//        }
//    }

//    @GetMapping({"/excel/{ide}"})
//    public void exportToExcel(@PathVariable("ide") String ide, HttpServletResponse response)
//            throws IOException
//    {
//        System.out.println("el ide fue excel -->" + ide);
//
//        List listUsers = new ArrayList();
//        Comprobantes compro1 = new Comprobantes();
//        compro1.setDes_comprobante("Compro 1");
//        compro1.setDes_concepto("Pago 1");
//        compro1.setId("1");
//        compro1.setFecha_comprobante("01/08/2021");
//
//        Comprobantes compro2 = new Comprobantes();
//        compro2.setDes_comprobante("Compro 1");
//        compro2.setDes_concepto("Pago 1");
//        compro2.setId("1");
//        compro2.setFecha_comprobante("01/08/2021");
//
//        Comprobantes compro3 = new Comprobantes();
//        compro3.setDes_comprobante("Compro 1");
//        compro3.setDes_concepto("Pago 1");
//        compro3.setId("1");
//        compro3.setFecha_comprobante("01/08/2021");
//        Comprobantes compro4 = new Comprobantes();
//        compro4.setDes_comprobante("Compro 1");
//        compro4.setDes_concepto("Pago 1");
//        compro4.setId("1");
//        compro4.setFecha_comprobante("01/08/2021");
//        listUsers.add(compro1);
//        listUsers.add(compro2);
//        listUsers.add(compro3);
//        listUsers.add(compro4);
//        System.out.println("la lista enviads" + listUsers.size());
//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//
//        UserExcelExporter3 excelExporter1 = new UserExcelExporter3(listUsers);
//        excelExporter1.export(response);
//    }

//    @GetMapping({"/exportToExcelResponde/{ruc}/{fechaIni}/{fechaFin}"})
//    public void exportToExcelResponde(@PathVariable("ruc") String ruc, @PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin, HttpServletResponse response)
//            throws IOException
//    {
//        List listUsers = new ArrayList();
//
//        if (ruc.equals("0")) {
//            ruc = "";
//        }
//
//        if (fechaIni.equals("0")) {
//            fechaIni = "";
//        }
//
//        if (fechaFin.equals("0")) {
//            fechaFin = "";
//        }
//
//        Map map = new HashMap();
//        map.put("PIV_NUMERO_RUC", ruc);
//        map.put("PIV_FECHA_INICIO", fechaIni);
//        map.put("PIV_FECHA_FECHA", fechaFin);
//        this.planillaService.listarGrilla(map);
//        listUsers = (List)map.get("VS_CURSOR");
//
//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//
//        UserExcelExporterReporte3 excelExporter1 = new UserExcelExporterReporte3(listUsers);
//        excelExporter1.export(response);
//    }

    @GetMapping({"/listarPagos/{ruc}/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<PagosCsampago>> listarPagos(@PathVariable("ruc") String ruc, @PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (ruc.equals("0")) {
                ruc = "";
            }

            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();
            map.put("PIV_NUMERO_RUC", ruc);
            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrilla(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarDataEcoSeguros_05/{fechaIni}/{fechaFin}/{dependencia}"})
    public ResponseEntity<List<modelEcoSeguros>> listarDataEcoSeguros(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin,@PathVariable("dependencia") String dependencia)
    {
        List list = null;
        try {
            logger.info("Entre listar listarDataEcoSeguros_05");

            logger.info("Entre fechaIni" + fechaIni);
            logger.info("Entre fechaFin" + fechaFin);

            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            map.put("PIV_DEPENDENCIA", dependencia);


            this.planillaService.listarGrillaEcoSeguros_05(map);
            list = (List)map.get("VS_CURSOR");
            //System.out.println("la lista fue" + list.toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarDataReporteBajas_03/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelReporteBajas>> listarDataReporteBajas(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrillaReporteBajas_03(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarDataAltasCNV_02/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelAltaCNV>> listarDataAltasCNV(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin) {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrillaAltasCNV_02(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarDataAcreComple_04/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelAcredComple>> listarDataAcreComple(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrillaAcreComple_04(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrillaDETAL_01/{fechaIni}/{fechaFin}/{dependencia}"})
    public ResponseEntity<List<modelDetail127>> listarGrillaDETAL_01(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin, @PathVariable("dependencia") String dependencia)
    {
        List list = null;
        try {
            logger.info("Entre listarGrillaDETAL_01");
            logger.info("Entre fechaIni" + fechaIni);
            logger.info("Entre fechaFin" + fechaFin);

            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            map.put("PIV_DEPENDENCIA", dependencia);
            this.planillaService.listarGrillaDETAL_01(map);
            list = (List)map.get("VS_CURSOR");
            System.out.println("ENTRE AL METRO LISTA ACRE LOS:");
            System.out.println("LOS CAMPOS FUERON " + fechaIni + fechaFin);
            System.out.println("la lista fue" + list.toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrillaCANJE_06/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelCanjeCitt>> listarGrillaCANJE_06(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrillaCANJE_06(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrillaMensualAsegurado_07/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelMensualAsegurado>> listarGrillaMensualAsegurado_07(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrillaMensualAsegurado_07(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrillaMensualEMPRESA_08/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelMensualEmpresa>> listarGrillaMensualEMPRESA_08(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrillaMensualEMPRESA_08(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrillaEXPE_SAS_09/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelEXPE_SAS>> listarGrillaEXPE_SAS_09(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrillaEXPE_SAS_09(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrillaEXPE_VIVA_10/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelEXPE_VIVA>> listarGrillaEXPE_VIVA_10(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrillaEXPE_VIVA_10(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrilla20_PRIMEROS_DIAS_11/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<model20PrimerosDias>> listarGrilla20_PRIMEROS_DIAS_11(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrilla20_PRIMEROS_DIAS_11(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrillaSepelio_12/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelSepelioRepor>> listarGrillaSepelio_12(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrilla_Sepelio_12(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrillaDireccion_13/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelDireccion>> listarGrillaDireccion_13(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrilla_Direccion_13(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrillaBajas_14/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelBajas>> listarGrillaBajas_14(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrilla_Bajas_14(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping({"/listarGrillaLatencia_15/{fechaIni}/{fechaFin}"})
    public ResponseEntity<List<modelLatencia>> listarGrillaLatencia_15(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin)
    {
        List list = null;
        try
        {
            if (fechaIni.equals("0")) {
                fechaIni = "";
            }

            if (fechaFin.equals("0")) {
                fechaFin = "";
            }

            Map map = new HashMap();

            map.put("PIV_FECHA_INICIO", fechaIni);
            map.put("PIV_FECHA_FECHA", fechaFin);
            this.planillaService.listarGrilla_Latencia_15(map);
            list = (List)map.get("VS_CURSOR");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity(list, HttpStatus.OK);
    }
}