package gob.pe.essalud.apireporseg.planilla.service.impl;

import gob.pe.essalud.apireporseg.planilla.model.ArchivoAccredCmplBean;
import gob.pe.essalud.apireporseg.planilla.model.ArchivoBean;
import gob.pe.essalud.apireporseg.planilla.model.Comprobantes;
import gob.pe.essalud.apireporseg.planilla.model.ConexionDataSource;
import gob.pe.essalud.apireporseg.planilla.model.ConsultAccreCmplBean;
import gob.pe.essalud.apireporseg.planilla.model.DataSepelio;
import gob.pe.essalud.apireporseg.planilla.model.InabilityBean;
import gob.pe.essalud.apireporseg.planilla.model.InhabilitadosAsegbean;
import gob.pe.essalud.apireporseg.planilla.model.LactanciaSmartBean;
import gob.pe.essalud.apireporseg.planilla.model.ListArchivo;
import gob.pe.essalud.apireporseg.planilla.model.MaternityBean;
import gob.pe.essalud.apireporseg.planilla.model.PeriodosLaboradosBean;
import gob.pe.essalud.apireporseg.planilla.model.PersonBean;
import gob.pe.essalud.apireporseg.planilla.model.ReactivationBean;
import gob.pe.essalud.apireporseg.planilla.model.ReportAccreditation;
import gob.pe.essalud.apireporseg.planilla.model.ReportArchiveBean;
import gob.pe.essalud.apireporseg.planilla.model.ReportInabilityBean;
import gob.pe.essalud.apireporseg.planilla.model.ReportInhabilityDetBean;
import gob.pe.essalud.apireporseg.planilla.model.ReportLactanciaBean;
import gob.pe.essalud.apireporseg.planilla.model.ReportLactanciaDetBean;
import gob.pe.essalud.apireporseg.planilla.model.ReportLactanciaSmartBean;
import gob.pe.essalud.apireporseg.planilla.model.ReportMaternityBean;
import gob.pe.essalud.apireporseg.planilla.model.ReportMaternityDetBean;
import gob.pe.essalud.apireporseg.planilla.model.ReportRequestBean;
import gob.pe.essalud.apireporseg.planilla.model.RightHabienteBean;
import gob.pe.essalud.apireporseg.planilla.model.SubLactanciaBean;
import gob.pe.essalud.apireporseg.planilla.model.TaxpayerBean;
import gob.pe.essalud.apireporseg.planilla.model.TrabajoHabitual;
import gob.pe.essalud.apireporseg.planilla.model.WebSerLogBean;
import gob.pe.essalud.apireporseg.planilla.model.cittBean;
import gob.pe.essalud.apireporseg.planilla.model.modelAcredComple;
import gob.pe.essalud.apireporseg.planilla.service.WSOspeVirtualDAO;
import gob.pe.essalud.apireporseg.util.UsuarioOspeProperties;
import gob.pe.essalud.apireporseg.util.Various;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class WSOspeVirtualDAOImpl
        implements WSOspeVirtualDAO
{
    private static final Logger LOGGER = LogManager.getLogger(WSOspeVirtualDAOImpl.class);
    private String PACKAGE;
    private String OWNER;
    private String DATASOURCE;

    public WSOspeVirtualDAOImpl(UsuarioOspeProperties properties)
    {
        this.PACKAGE = properties.getPackageBD();
        this.OWNER = properties.getOwnerBD();
        this.DATASOURCE = properties.getDatasourceBD();
    }

    public Map validarAccesoX(String login, String pass)
    {
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String clave = pass;
        CallableStatement cs = null;

        String mensaje = "";
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_VALIDE_USER(?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : validarAccesoX");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);
            cs.setString(1, login);
            cs.setString(2, clave);
            cs.registerOutParameter(3, 12);
            cs.executeUpdate();
            mensaje = cs.getString(3);
            cs.close();
        } catch (SQLException e) {
            LOGGER.error("WSOspeVirtualDAOImpl ERROR : validarAccesoX");
            LOGGER.error("e.getErrorCode() " + e.getErrorCode());
            LOGGER.error("e.getMessage() " + e.getMessage());
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        if (mensaje.equals("OK")) {
            mapa.put("data", mensaje);
            mapa.put("error", "0");
        } else {
            mapa.put("data", mensaje);
            mapa.put("error", "1");
        }
        return mapa;
    }

    public Map getInsuredData(String tpdoc, String nrdoc)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        PersonBean bean = null;
        CallableStatement cs = null;

        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_DATA_INSURED(?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getInsuredData");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpdoc);
            cs.setString(2, nrdoc);
            cs.registerOutParameter(3, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(3);

            if (rs != null) {
                while (rs.next()) {
                    bean = new PersonBean();
                    bean.setTpDoc(Various.stringNull(rs.getString("TPDOC")));
                    bean.setNrDoc(Various.stringNull(rs.getString("NRDOC")));
                    bean.setApPaterno(Various.stringNull(rs.getString("APEPATERNO")));
                    bean.setApMaterno(Various.stringNull(rs.getString("APEMATERNO")));
                    bean.setNombres(Various.stringNull(rs.getString("NOMBRES")));
                    bean.setEstCivil(Various.stringNull(rs.getString("ESTCIVIL")));
                    bean.setSexo(Various.stringNull(rs.getString("SEXO")));
                    bean.setFecNacimiento(Various.stringNull(rs.getString("FECNACIMIENTO")));
                    bean.setFecFallecimiento(Various.stringNull(rs.getString("FEFALLECID")));
                    bean.setCodVerif(Various.stringNull(rs.getString("CODVERF")));
                    bean.setFecInscripcion(Various.stringNull(rs.getString("FECINCRIPCION")));
                    bean.setFecEmision(Various.stringNull(rs.getString("FECEXPEDICION")));
                    bean.setTpdocPadre(Various.stringNull(rs.getString("TPDOCPADRE")));
                    bean.setNroDocPadre(Various.stringNull(rs.getString("NRDOCPADRE")));
                    bean.setNomPadre(Various.stringNull(rs.getString("NOMPADRE")));
                    bean.setTpDocMadre(Various.stringNull(rs.getString("DOCMADRE")));
                    bean.setNroDocMadre(Various.stringNull(rs.getString("NRDOCMADRE")));
                    bean.setNomMadre(Various.stringNull(rs.getString("NOMMADRE")));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("data", bean);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", "Asegurado No Registrado");
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", "Asegurado No Registrado");
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public String registrarLog(WebSerLogBean bn)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        CallableStatement cs = null;

        String mensaje = "";

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".SP_REGISTER_LOG(?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : registrarLog");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, bn.getLogin());
            cs.setString(2, bn.getAction());
            cs.setString(3, bn.getService());
            cs.setString(4, bn.getTxtPlotRequest());
            cs.setString(5, bn.getTxtPlotResponse());
            cs.setString(6, bn.getIdUsuario());

            cs.registerOutParameter(7, 12);
            cs.executeUpdate();

            mensaje = cs.getString(7);

            cs.close();
        } catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mensaje;
    }

    public Map getTaxpayer(String nrruc) {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;

        CallableStatement cs = null;

        TaxpayerBean bean = null;
        List resultado = new ArrayList();
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_DATA_TAXPAYER(?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getTaxpayer");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, nrruc);
            cs.registerOutParameter(2, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(2);

            if (rs != null) {
                while (rs.next())
                {
                    bean = new TaxpayerBean();
                    bean.setNrRuc(Various.stringNull(rs.getString(1)));
                    bean.setRazSocial(Various.stringNull(rs.getString(2)));
                    bean.setEstado(Various.stringNull(rs.getString(3)));
                    bean.setTpDocRuc(Various.stringNull(rs.getString(4)));
                    bean.setDesDocRuc(Various.stringNull(rs.getString(5)));
                    resultado.add(bean);
                }
                if (!resultado.isEmpty()) {
                    mapa.put("data", bean);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", "RUC No Registrado");
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", "RUC No Registrado");
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map getCITT(String tpdoc, String nrdoc, String nrRuc, String tpsub)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;

        CallableStatement cs = null;

        cittBean bean = null;
        List resultado = new ArrayList();
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_DATA_CITT(?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getCITT");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpdoc);
            cs.setString(2, nrdoc);
            cs.setString(3, nrRuc);
            cs.setString(4, tpsub);
            cs.registerOutParameter(5, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(5);

            if (rs != null) {
                while (rs.next())
                {
                    bean = new cittBean();
                    bean.setCitt(Various.stringNull(rs.getString(1)));
                    bean.setAutogenerado(Various.stringNull(rs.getString(2)));
                    bean.setTpSubsidio(Various.stringNull(rs.getString(3)));
                    bean.setFecInicio(Various.stringNull(rs.getString(4)));
                    bean.setFecFin(Various.stringNull(rs.getString(5)));
                    resultado.add(bean);
                }
                if (!resultado.isEmpty()) {
                    mapa.put("data", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", "CITT No Encontrados");
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", "CITT No Encontrados");
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map getFamilyBond(String tpdoc, String nrdoc, String fecIni, String fecFin)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        CallableStatement cs = null;

        RightHabienteBean bean = null;
        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_DATA_FAMILYBOND(?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getFamilyBond");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpdoc);
            cs.setString(2, nrdoc);
            cs.setString(3, fecIni);
            cs.setString(4, fecFin);
            cs.registerOutParameter(5, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(5);

            if (rs != null) {
                while (rs.next()) {
                    bean = new RightHabienteBean();
                    bean.setTpDoc(Various.stringNull(rs.getString(1)));
                    bean.setNrDoc(Various.stringNull(rs.getString(2)));
                    bean.setApPaterno(Various.stringNull(rs.getString(3)));
                    bean.setApMaterno(Various.stringNull(rs.getString(4)));
                    bean.setNombres(Various.stringNull(rs.getString(5)));
                    bean.setTpVinculo(Various.stringNull(rs.getString(6)));
                    resultado.add(bean);
                }
                if (!resultado.isEmpty()) {
                    mapa.put("data", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", "DerechoHabientes No Encontrados");
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", "DerechoHabientes No Encontrados");
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map insertarLactancia(SubLactanciaBean bean)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();

        CallableStatement cs = null;

        String vOUTIDENUMSOLICITUD_OV = "";
        String vOUTMENSSAGE = "";
        String vOUTIND = "";
        LinkedHashMap mapa = new LinkedHashMap();
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REGISTER_LACTANCIA(?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : insertarLactancia");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setInt(1, bean.getCodTDocHijo().intValue());
            cs.setString(2, bean.getCodEDocHijo());
            cs.setString(3, bean.getNumDocHijo());
            cs.setInt(4, bean.getCodTDocBeneficiario().intValue());
            cs.setString(5, bean.getCodEDocBeneficiario());
            cs.setString(6, bean.getNumDocBeneficiario());
            cs.setString(7, bean.getCodETipoParto());
            cs.setString(8, bean.getCodCUI());
            cs.setString(9, bean.getCodEDocUsuario());
            cs.setString(10, bean.getNumDocUsuario());
            cs.setString(11, bean.getCodOspe());
            cs.registerOutParameter(12, 12);
            cs.registerOutParameter(13, 12);
            cs.registerOutParameter(14, 12);
            cs.executeUpdate();
            vOUTIDENUMSOLICITUD_OV = cs.getString(12);
            vOUTMENSSAGE = cs.getString(13);
            vOUTIND = cs.getString(14);

            mapa.put("codSolicitud", vOUTIDENUMSOLICITUD_OV);
            mapa.put("message", vOUTMENSSAGE);

            if (vOUTIND.equals("N"))
                mapa.put("error", "0");
            else {
                mapa.put("error", "1");
            }

            cs.close();
        } catch (SQLException e) {
            LOGGER.error("insertarLactancia\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            mapa.put("codSolicitud", vOUTIDENUMSOLICITUD_OV);
            mapa.put("message", Integer.valueOf(e.getErrorCode()));
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map get20Days(String tpdoc, String nrdoc, String nrRuc, String tpsub, String fecIni, String fecFin)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        CallableStatement cs = null;
        Integer PONCANTIDADFALTANTE = null;
        Integer PONTIENE20DIAS = null;
        String PONANIOEVALUACION = null;
        Integer PON20DIASVALIDO = null;
        cittBean bean = null;
        List resultado = new ArrayList();

        System.err.println("fecIni _>" + fecIni);
        System.err.println("fecFin _>" + fecFin);

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_DATA_20DAYS(?,?,?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : get20Days");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);
            cs.setString(1, tpdoc);
            cs.setString(2, nrdoc);
            cs.setString(3, nrRuc);
            cs.setString(4, tpsub);
            cs.setString(5, fecIni);
            cs.setString(6, fecFin);
            cs.registerOutParameter(7, 4);
            cs.registerOutParameter(8, 4);
            cs.registerOutParameter(9, 12);
            cs.registerOutParameter(10, 4);
            cs.registerOutParameter(11, -10);
            cs.executeUpdate();

            PONTIENE20DIAS = Integer.valueOf(cs.getInt(7));
            PONCANTIDADFALTANTE = Integer.valueOf(cs.getInt(8));
            PONANIOEVALUACION = cs.getString(9);
            PON20DIASVALIDO = Integer.valueOf(cs.getInt(10));
            rs = (ResultSet)cs.getObject(11);

            if (PONTIENE20DIAS.intValue() == 1) {
                mapa.put("data", resultado);
                mapa.put("ind20days", PONTIENE20DIAS);
                mapa.put("diasfalt20", PONCANTIDADFALTANTE);
                mapa.put("anio20dias", PONANIOEVALUACION);
                mapa.put("diasval20", PON20DIASVALIDO);
                mapa.put("error", "0");
            }
            else if (rs != null) {
                while (rs.next()) {
                    bean = new cittBean();
                    bean.setCitt(Various.stringNull(rs.getString(1)));
                    bean.setAutogenerado(Various.stringNull(rs.getString(2)));
                    bean.setTpSubsidio(Various.stringNull(rs.getString(3)));
                    bean.setFecInicio(Various.stringNull(rs.getString(4)));
                    bean.setFecFin(Various.stringNull(rs.getString(5)));
                    resultado.add(bean);
                }
                if (!resultado.isEmpty()) {
                    mapa.put("data", resultado);
                    mapa.put("ind20days", PONTIENE20DIAS);
                    mapa.put("diasfalt20", PONCANTIDADFALTANTE);
                    mapa.put("anio20dias", PONANIOEVALUACION);
                    mapa.put("diasval20", PON20DIASVALIDO);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", "CITT No Encontrados");
                    mapa.put("ind20days", PONTIENE20DIAS);
                    mapa.put("diasfalt20", PONCANTIDADFALTANTE);
                    mapa.put("anio20dias", PONANIOEVALUACION);
                    mapa.put("diasval20", PON20DIASVALIDO);
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", "CITT No Encontrados");
                mapa.put("ind20days", PONTIENE20DIAS);
                mapa.put("diasfalt20", PONCANTIDADFALTANTE);
                mapa.put("anio20dias", PONANIOEVALUACION);
                mapa.put("diasval20", PON20DIASVALIDO);
                mapa.put("error", "0");
            }
        }
        catch (SQLException e)
        {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map getReactivationEmployer(String nrruc, String state, String codOspe)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        CallableStatement cs = null;

        ReactivationBean bean = null;
        List resultado = new ArrayList();
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_GETREACTIVATION_EMPLOYER(?,?,?,?); END;";
        try {
            LOGGER.info("WSOspeVirtualDAOImpl : getReactivationEmployer");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, nrruc);
            cs.setString(2, state);
            cs.setString(3, codOspe);
            cs.registerOutParameter(4, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(4);

            if (rs != null) {
                while (rs.next())
                {
                    bean = new ReactivationBean();
                    bean.setNumExpediente(Various.stringNull(rs.getString(1)));
                    bean.setCodTipoAnulacion(Various.stringNull(rs.getString(2)));
                    bean.setNumEmision(Various.stringNull(rs.getString(3)));
                    bean.setEstado(Various.stringNull(rs.getString(4)));
                    bean.setIdeDoc(Various.stringNull(rs.getString(5)));
                    bean.setTipoSubsidio(Various.stringNull(rs.getString(6)));
                    bean.setFechaPago(Various.stringNull(rs.getString(7)));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("data", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", resultado);
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map getReactivationInsured(String tpdoc, String nrdoc, String state)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        CallableStatement cs = null;

        ReactivationBean bean = null;
        List resultado = new ArrayList();
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_GETREACTIVATION_INSURED(?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getReactivationInsured");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpdoc);
            cs.setString(2, nrdoc);
            cs.setString(3, state);
            cs.registerOutParameter(4, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(4);

            if (rs != null) {
                while (rs.next())
                {
                    bean = new ReactivationBean();
                    bean.setIdeNumAnu(Various.stringNull(rs.getString(1)));
                    bean.setNumSolici(Various.stringNull(rs.getString(2)));
                    bean.setNumExpediente(Various.stringNull(rs.getString(3)));
                    bean.setCodTipoAnulacion(Various.stringNull(rs.getString(4)));
                    bean.setNumSecuencia(Various.stringNull(rs.getString(5)));
                    bean.setNumEmision(Various.stringNull(rs.getString(6)));
                    bean.setEstado(Various.stringNull(rs.getString(7)));
                    bean.setIdeDoc(Various.stringNull(rs.getString(8)));
                    bean.setTipoSubsidio(Various.stringNull(rs.getString(9)));
                    bean.setFechaPago(Various.stringNull(rs.getString(10)));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("data", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", resultado);
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map sendReactivation(String pivExpedienteNumero, String pivUsuarioCodigo, String pivUsuarioTerminal, String pivUsuarioIp)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        LinkedHashMap madet = new LinkedHashMap();
        CallableStatement cs = null;

        String povexpedientefechapago = null;
        Integer poverrorflag = null;
        String poverrormensaje = null;
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_SEND_REACTIVATION(?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : sendReactivation");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, pivExpedienteNumero);
            cs.setString(2, pivUsuarioCodigo);
            cs.setString(3, pivUsuarioTerminal);
            cs.setString(4, pivUsuarioIp);
            cs.registerOutParameter(5, 12);
            cs.registerOutParameter(6, 4);
            cs.registerOutParameter(7, 12);
            cs.executeUpdate();
            povexpedientefechapago = cs.getString(5);
            poverrorflag = Integer.valueOf(cs.getInt(6));
            poverrormensaje = cs.getString(7);

            madet.put("pov_expediente_fecha_pago", Various.stringNull(povexpedientefechapago));
            madet.put("pov_error_flag", poverrorflag);
            madet.put("pov_error_mensaje", Various.stringNull(poverrormensaje));

            mapa.put("data", madet);
            mapa.put("error", "0");

            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("sendReactivation\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map getEmploymentRelationship(String tpdoc, String nrdoc)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        CallableStatement cs = null;

        TaxpayerBean bean = null;
        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_GETEMPLOYMENTRELATIONSHIP(?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getEmploymentRelationship");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpdoc);
            cs.setString(2, nrdoc);
            cs.registerOutParameter(3, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(3);

            if (rs != null) {
                while (rs.next()) {
                    bean = new TaxpayerBean();
                    bean.setNrRuc(Various.stringNull(rs.getString(1)));
                    bean.setRazSocial(Various.stringNull(rs.getString(2)));
                    bean.setEstado(Various.stringNull(rs.getString(3)));
                    bean.setIdnumerico(Various.stringNull(rs.getString(4)));
                    bean.setTpDocRuc(Various.stringNull(rs.getString(5)));
                    bean.setDesDocRuc(Various.stringNull(rs.getString(6)));
                    bean.setTpEmp(Various.stringNull(rs.getString(7)));
                    resultado.add(bean);
                }
                if (!resultado.isEmpty()) {
                    mapa.put("data", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", resultado);
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map sendReimbursementPayment(String pivTipoDocTitular, String pivNumDocTitular, String pivRucEmpleador, String pidFechaInicioSubs, String pidFechaFinSubs, String pivTipoPago)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        LinkedHashMap madet = new LinkedHashMap();
        CallableStatement cs = null;

        String povesctr = null;
        String povespagoreembolso = null;
        String povmensajeevaluar = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REIMBURSEMENT_PAYMENT(?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : sendReimbursementPayment");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, pivTipoDocTitular);
            cs.setString(2, pivNumDocTitular);
            cs.setString(3, pivRucEmpleador);
            cs.setString(4, pidFechaInicioSubs);
            cs.setString(5, pidFechaFinSubs);
            cs.setString(6, pivTipoPago);
            cs.registerOutParameter(7, 12);
            cs.registerOutParameter(8, 12);
            cs.registerOutParameter(9, 12);
            cs.executeUpdate();

            povesctr = cs.getString(7);
            povespagoreembolso = cs.getString(8);
            povmensajeevaluar = cs.getString(9);

            madet.put("pov_es_ctr", Various.stringNull(povesctr));
            madet.put("pov_es_pago_reembolso", Various.stringNull(povespagoreembolso));
            madet.put("pov_mensaje_evaluar", Various.stringNull(povmensajeevaluar));

            mapa.put("data", madet);
            mapa.put("error", "0");

            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("SendReimbursementPayment\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map reportLactancia(String codSolicitud, String tpDoc, String nrDoc)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();

        CallableStatement cs = null;
        ResultSet rs = null;
        ReportLactanciaBean detail = null;
        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_CONSULT_LACTANCIA(?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : reportLactancia");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setString(1, codSolicitud);
            cs.setString(2, tpDoc);
            cs.setString(3, nrDoc);
            cs.registerOutParameter(4, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(4);

            if (rs != null) {
                while (rs.next()) {
                    detail = new ReportLactanciaBean();
                    detail.setCodSolicitud(Various.stringNull(rs.getString("CODSOLICITUD")));
                    detail.setTpDocUsuario(Various.stringNull(rs.getString("TPDOCUSUARIO")));
                    detail.setNrDocUsuario(Various.stringNull(rs.getString("NRDOCUSUARIO")));
                    detail.setNombreUsuario(Various.stringNull(rs.getString("NOMBREUSUARIO")));
                    detail.setTpSubsidio(Various.stringNull(rs.getString("TPSUBSIDIO")));
                    detail.setEstSolicitud(Various.stringNull(rs.getString("ESTSOLICITUD")));
                    detail.setFecCreacion(Various.stringNull(rs.getString("FECCREACION")));
                    detail.setFecProceso(Various.stringNull(rs.getString("FECPROCESO")));
                    detail.setCodLactancia(Various.stringNull(rs.getString("CODLACTANCIA")));
                    detail.setTpDocHijo(Various.stringNull(rs.getString("TPDOCHIJO")));
                    detail.setNrDocHijo(Various.stringNull(rs.getString("NRDOCHIJO")));
                    detail.setNombreHijo(Various.stringNull(rs.getString("NOMBREHIJO")));
                    detail.setTpDocBenef(Various.stringNull(rs.getString("TPDOCBENEF")));
                    detail.setNrDocBenef(Various.stringNull(rs.getString("NRDOCBENEF")));
                    detail.setNombreBenef(Various.stringNull(rs.getString("NOMBREBENEFICIARIO")));
                    detail.setCodCui(Various.stringNull(rs.getString("CODCUI")));
                    detail.setTpParto(Various.stringNull(rs.getString("TPPARTO")));

                    resultado.add(detail);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("detail", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("detail", resultado);
                    mapa.put("error", "1");
                }
            }
            else {
                mapa.put("detail", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public int createRequest(String tpdoc, String nrdoc, String tpsub, String url, String codOspe, String tipoPago)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        CallableStatement cs = null;
        int codSolicitud = 0;
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REGISTER_SOLICITUD(?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : createRequest");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setString(1, "101");
            cs.setString(2, tpdoc);
            cs.setString(3, nrdoc);
            cs.setString(4, "1101");
            cs.setString(5, tpsub);
            cs.setString(6, url);
            cs.setString(7, codOspe);
            cs.setString(8, tipoPago);
            cs.registerOutParameter(9, 4);
            cs.executeUpdate();

            codSolicitud = cs.getInt(9);

            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("createRequest\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return codSolicitud;
    }

    public Map registerInability(InabilityBean bean)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;
        String vOUTMENSSAGE = null;
        String vOUTIND = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REGISTER_INABILITY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : registerInability");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);
            cs.setInt(1, bean.getIdSolicitud());
            cs.setString(2, "101");
            cs.setString(3, bean.getTpdocAseg());
            cs.setString(4, bean.getNrdocAseg());
            cs.setString(5, bean.getNombres());
            cs.setString(6, bean.getNrruc());
            cs.setString(7, bean.getFeciniSol());
            cs.setString(8, bean.getFecfinSol());
            cs.setString(9, bean.getFeciniVac());
            cs.setString(10, bean.getFeciniVac());
            cs.setString(11, bean.getFecCese());
            cs.setDouble(12, bean.getMontoSol());
            cs.setString(13, bean.getNrcitt());
            cs.setString(14, bean.getFeciniCitt());
            cs.setString(15, bean.getFecfinCitt());
            cs.setString(16, bean.getTpcitt());
            cs.setString(17, bean.getTieneSctr());
            cs.setString(18, bean.getEstaTrabajando());
            cs.setString(19, bean.getEsSectorPrivado());
            cs.setString(20, bean.getTieneDeclaracionJurada());
            cs.registerOutParameter(21, 12);
            cs.registerOutParameter(22, 12);
            cs.executeUpdate();

            vOUTMENSSAGE = cs.getString(21);
            vOUTIND = cs.getString(22);

            mapa.put("message", vOUTMENSSAGE);

            if (vOUTIND.equals("N"))
                mapa.put("error", "0");
            else {
                mapa.put("error", "1");
            }

            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("registerInability\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map registerArchive(ArchivoBean bean)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;
        Integer vIDEARVICHEOV = null;
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REGISTER_ARCHIVE(?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : registerArchive");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);
            cs.setInt(1, bean.getIdSolicitud().intValue());
            cs.setString(2, bean.getNombre());
            cs.setString(3, bean.getUrlAcceso());
            cs.registerOutParameter(4, 4);
            cs.executeUpdate();

            vIDEARVICHEOV = Integer.valueOf(cs.getInt(4));

            mapa.put("codArchivo", vIDEARVICHEOV);

            if (vIDEARVICHEOV.intValue() == 0)
                mapa.put("error", "1");
            else {
                mapa.put("error", "0");
            }

            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("registerArchive\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map reportInability(String tpDoc, String nrDoc, String tpCitt, String state)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;
        ResultSet rs = null;
        ReportInabilityBean detail = null;
        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_CONSULT_INABILITY(?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : reportInability");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpCitt);
            cs.setString(2, tpDoc);
            cs.setString(3, nrDoc);
            cs.setString(4, state);
            cs.registerOutParameter(5, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(5);

            if (rs != null)
            {
                while (rs.next()) {
                    detail = new ReportInabilityBean();

                    detail.setCodSolicitud(Integer.valueOf(rs.getInt("COD_SOLICITUD")));
                    detail.setTipDoc(Various.stringNull(rs.getString("TIP_DOC")));
                    detail.setNroDoc(Various.stringNull(rs.getString("NR_DOC")));
                    detail.setNombre(Various.stringNull(rs.getString("NOMBRE")));
                    detail.setRuc(Various.stringNull(rs.getString("RUC")));
                    detail.setRzSocial(Various.stringNull(rs.getString("RZ_SOCIAL")));
                    detail.setFeciniVac(Various.stringNull(rs.getString("FECINI_VAC")));
                    detail.setFecfinVac(Various.stringNull(rs.getString("FECFIN_VAC")));
                    detail.setFecCese(Various.stringNull(rs.getString("FEC_CESE")));
                    detail.setNumMontoSol(Various.stringNull(rs.getString("NUM_MONTO_SOLICITADO")));
                    detail.setFeciniSol(Various.stringNull(rs.getString("FECINI_SOL")));
                    detail.setFecfinSol(Various.stringNull(rs.getString("FECFIN_SOL")));
                    detail.setCitt(Various.stringNull(rs.getString("LISTA_CITT")));
                    resultado.add(detail);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("detail", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("detail", resultado);
                    mapa.put("error", "1");
                }
            }
            else {
                mapa.put("detail", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map updateStatusInability(String codSolicitud, String opcion, String tpdoc, String nrdoc, String tpPago)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;
        String vMESSAGE = null;
        String VOUTIND = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_UPDATE_STATE_INABILITY(?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : updateStatusInability");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);
            cs.setString(1, codSolicitud);
            cs.setString(2, opcion);
            cs.setString(3, tpdoc);
            cs.setString(4, nrdoc);
            cs.setString(5, tpPago);
            cs.registerOutParameter(6, 12);
            cs.registerOutParameter(7, 12);
            cs.executeUpdate();

            vMESSAGE = cs.getString(6);
            VOUTIND = cs.getString(7);

            mapa.put("data", vMESSAGE);
            mapa.put("error", VOUTIND);

            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("updateStatusInability\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map valRegisterInability(InabilityBean bean)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;
        Integer vOUTIDENUMSOLICITU = null;
        String vOUTSTATE = null;
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_VAL_INABILITY(?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : valRegisterInability");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);
            cs.setString(1, bean.getNrruc());
            cs.setString(2, bean.getNrcitt());
            cs.setString(3, "101");
            cs.setString(4, bean.getTpdocAseg());
            cs.setString(5, bean.getNrdocAseg());
            cs.setString(6, bean.getTpcitt());
            cs.registerOutParameter(7, 4);
            cs.registerOutParameter(8, 12);
            cs.executeUpdate();

            vOUTIDENUMSOLICITU = Integer.valueOf(cs.getInt(7));
            vOUTSTATE = cs.getString(8);
            LOGGER.debug(vOUTSTATE);

            mapa.put("codSolicitud", vOUTIDENUMSOLICITU);
            if (vOUTIDENUMSOLICITU.intValue() == 0)
                mapa.put("error", "0");
            else {
                mapa.put("error", "1");
            }
            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("valRegisterInability\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map sendBackOfficeInability(Integer pinIdeSolicitud, String picCodTdocTitularSia, String pivNumDocBeneficiario, String pivCodigoUsuario)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;
        String povNombreCompleto = null;
        String povNit = null;
        String povMotivoRechazo = null;
        String povInd = null;
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_SEND_BACK_OFFICE_INABILITY(?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : sendBackOfficeInability");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);
            cs.setInt(1, pinIdeSolicitud.intValue());
            cs.setString(2, picCodTdocTitularSia);
            cs.setString(3, pivNumDocBeneficiario);
            cs.setString(4, pivCodigoUsuario);
            cs.registerOutParameter(5, 12);
            cs.registerOutParameter(6, 12);
            cs.registerOutParameter(7, 12);
            cs.registerOutParameter(8, 12);
            cs.executeUpdate();

            povNombreCompleto = cs.getString(5);
            povNit = cs.getString(6);
            povMotivoRechazo = cs.getString(7);
            povInd = cs.getString(8);

            Map mrs = new HashMap();
            mrs.put("pov_nombre_completo", povNombreCompleto);
            mrs.put("pov_nit", povNit);
            mrs.put("pov_motivo_rechazo", povMotivoRechazo);

            mapa.put("data", mrs);
            if (povInd.equals("N"))
                mapa.put("error", "0");
            else {
                mapa.put("error", "1");
            }
            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("valRegisterInability\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map verifyPaymentMaternity(String citt)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;
        Integer flgArm1 = null;
        Integer flgArm2 = null;
        Integer flgCittCompl = null;
        Integer povInd = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_VERIF_PAY_MATERNITY(?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : verifyPaymentMaternity");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);
            cs.setString(1, citt);
            cs.registerOutParameter(2, 4);
            cs.registerOutParameter(3, 4);
            cs.registerOutParameter(4, 4);
            cs.registerOutParameter(5, 4);
            cs.executeUpdate();

            flgArm1 = Integer.valueOf(cs.getInt(2));
            flgArm2 = Integer.valueOf(cs.getInt(3));
            flgCittCompl = Integer.valueOf(cs.getInt(4));
            povInd = Integer.valueOf(cs.getInt(5));

            Map mrs = new HashMap();
            mrs.put("flg_arm_1", flgArm1);
            mrs.put("flg_arm_2", flgArm2);
            mrs.put("flg_citt_compl", flgCittCompl);
            mapa.put("data", mrs);
            mapa.put("error", povInd);
            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("verifyPaymentMaternity\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map insertMatSolicitud(MaternityBean bean)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;
        Integer vFlgRs = null;
        Integer vNumSolRs = null;
        String vError = null;
        Integer vIND = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REGISTER_MATERNITY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : insertMatSolicitud");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);
            cs.setInt(1, bean.getCodTTipoSub());
            cs.setString(2, bean.getCodETipoSub());
            cs.setInt(3, bean.getCodTDocBenef());
            cs.setString(4, bean.getCodEDocBenef());
            cs.setString(5, bean.getNumDocBenef());
            cs.setString(6, bean.getNumRUCEmple());
            cs.setString(7, bean.getNumCitt());
            cs.setString(8, bean.getFecIniCitt());
            cs.setString(9, bean.getFecFinCitt());
            cs.setInt(10, bean.getNumDiasCitt());
            cs.setDouble(11, bean.getNumMontoSolic());
            cs.setDouble(12, bean.getNumMontoPagad());
            cs.setString(13, bean.getFecIniSubSolic());
            cs.setString(14, bean.getFecFinSubSolic());
            cs.setInt(15, bean.getNumDiasSub());
            cs.setInt(16, bean.getNumArmada());
            cs.setString(17, bean.getFlgActivoInactivo());
            cs.setString(18, bean.getFecUsuarioCrea());
            cs.setString(19, bean.getCodUsuarioCrea());
            cs.setInt(20, bean.getCodTdocUsuario());
            cs.setString(21, bean.getCodEdocUsuario());
            cs.setString(22, bean.getNumDocUsuario());
            cs.setString(23, bean.getIpUsuarioCrea());
            cs.setString(24, bean.getNombreCompleto());
            cs.setString(25, bean.getRazonSocial());
            cs.setString(26, bean.getTipoEmpleado());
            cs.setString(27, bean.getTipoSeguro());
            cs.setString(28, bean.getZonaUpe());
            cs.setString(29, bean.getFlgSectorEducacion());
            cs.setInt(30, bean.getNumDeclJurada());
            cs.setString(31, bean.getFecCese());
            cs.setString(32, bean.getCodOspe());
            cs.setString(33, bean.getTipPago());
            cs.setString(34, bean.getNombreArchivo());
            cs.registerOutParameter(35, 4);
            cs.registerOutParameter(36, 4);
            cs.registerOutParameter(37, 12);
            cs.registerOutParameter(38, 4);
            cs.executeUpdate();

            vFlgRs = Integer.valueOf(cs.getInt(35));
            vNumSolRs = Integer.valueOf(cs.getInt(36));
            vError = cs.getString(37);
            vIND = Integer.valueOf(cs.getInt(38));

            Map mrs = new HashMap();
            mrs.put("flg_resultado", vFlgRs);
            mrs.put("num_solicitud_resultado", vNumSolRs);
            mrs.put("txt_error", vError);
            mapa.put("data", mrs);
            mapa.put("error", vIND);

            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("insertMatSolicitud\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map getInsuredReniec(String tpdoc, String nrdoc)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        PersonBean bean = null;
        CallableStatement cs = null;

        List resultado = new ArrayList();
        LOGGER.info("el owner" + this.OWNER);
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_GET_INSURED_RENIEC(?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getInsuredReniec");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpdoc);
            cs.setString(2, nrdoc);
            cs.registerOutParameter(3, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(3);

            if (rs != null) {
                while (rs.next()) {
                    bean = new PersonBean();
                    bean.setTpDoc(Various.stringNull(rs.getString("TPDOC")));
                    bean.setNrDoc(Various.stringNull(rs.getString("NRDOC")));
                    bean.setApPaterno(Various.stringNull(rs.getString("APEPATERNO")));
                    bean.setApMaterno(Various.stringNull(rs.getString("APEMATERNO")));
                    bean.setNombres(Various.stringNull(rs.getString("NOMBRES")));
                    bean.setEstCivil(Various.stringNull(rs.getString("ESTCIVIL")));
                    bean.setSexo(Various.stringNull(rs.getString("SEXO")));
                    bean.setFecNacimiento(Various.stringNull(rs.getString("FECNACIMIENTO")));
                    bean.setFecFallecimiento(Various.stringNull(rs.getString("FEFALLECID")));
                    bean.setCodVerif(Various.stringNull(rs.getString("CODVERF")));
                    bean.setFecInscripcion(Various.stringNull(rs.getString("FECINCRIPCION")));
                    bean.setFecEmision(Various.stringNull(rs.getString("FECEXPEDICION")));
                    bean.setTpdocPadre(Various.stringNull(rs.getString("TPDOCPADRE")));
                    bean.setNroDocPadre(Various.stringNull(rs.getString("NRDOCPADRE")));
                    bean.setNomPadre(Various.stringNull(rs.getString("NOMPADRE")));
                    bean.setTpDocMadre(Various.stringNull(rs.getString("DOCMADRE")));
                    bean.setNroDocMadre(Various.stringNull(rs.getString("NRDOCMADRE")));
                    bean.setNomMadre(Various.stringNull(rs.getString("NOMMADRE")));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("data", bean);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", "Asegurado No Registrado");
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", "Asegurado No Registrado");
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map consultRequest(String tpdoc, String nrdoc, String codOspe, String tpPago, String idGroup, String fecIni, String fecFin)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();

        CallableStatement cs = null;
        ResultSet rs = null;
        ReportRequestBean bean = null;
        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_CONSUL_SOLICITUD(?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : consultRequest");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setString(1, "101");
            cs.setString(2, tpdoc);
            cs.setString(3, nrdoc);
            cs.setString(4, codOspe);
            cs.setString(5, tpPago);
            cs.setString(6, idGroup);
            cs.setString(7, fecIni);
            cs.setString(8, fecFin);
            cs.registerOutParameter(9, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(9);

            if (rs != null) {
                while (rs.next()) {
                    bean = new ReportRequestBean();
                    bean.setSolicitudOV(Various.stringNull(rs.getString("SOLICITUDOV")));
                    bean.setCodDoc(Various.stringNull(rs.getString("CODDOC")));
                    bean.setDesDoc(Various.stringNull(rs.getString("DESCODDOC")));
                    bean.setNrDoc(Various.stringNull(rs.getString("DOCUMENTO")));
                    bean.setUsuario(Various.stringNull(rs.getString("USUARIO")));
                    bean.setBeneficiario(Various.stringNull(rs.getString("BENEFICIARIO")));
                    bean.setCodSub(Various.stringNull(rs.getString("CODSUB")));
                    bean.setDesSub(Various.stringNull(rs.getString("DESCSUB")));
                    bean.setFecCreac(Various.stringNull(rs.getString("FECCREACION")));
                    bean.setFecModif(Various.stringNull(rs.getString("FECMODIF")));
                    bean.setEstado(Various.stringNull(rs.getString("ESTADO")));
                    bean.setFecproc(Various.stringNull(rs.getString("FECPROC")));
                    bean.setSolicitud(Various.stringNull(rs.getString("SOLICITUD")));
                    bean.setCodOspe(Various.stringNull(rs.getString("CODOSPE")));
                    bean.setTpPago(Various.stringNull(rs.getString("TPPAGO")));
                    bean.setSolicitudGroup(Various.stringNull(rs.getString("IDGROUP")));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("detail", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("detail", resultado);
                    mapa.put("error", "1");
                }
            }
            else {
                mapa.put("detail", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map consultRequestGroup(String tpdoc, String nrdoc, String codOspe, String tpPago, String idGroup, String fecIni, String fecFin)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();

        CallableStatement cs = null;
        ResultSet rs = null;
        ReportRequestBean bean = null;
        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_CONSUL_GROUP_SOL(?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : consultRequestGroup");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setString(1, "101");
            cs.setString(2, tpdoc);
            cs.setString(3, nrdoc);
            cs.setString(4, codOspe);
            cs.setString(5, tpPago);
            cs.setString(6, idGroup);
            cs.setString(7, fecIni);
            cs.setString(8, fecFin);
            cs.registerOutParameter(9, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(9);

            if (rs != null) {
                while (rs.next()) {
                    bean = new ReportRequestBean();
                    bean.setSolicitudGroup(Various.stringNull(rs.getString("CODPROC")));
                    bean.setCodDoc(Various.stringNull(rs.getString("CODDOC")));
                    bean.setDesDoc(Various.stringNull(rs.getString("DESCODDOC")));
                    bean.setNrDoc(Various.stringNull(rs.getString("DOCUMENTO")));
                    bean.setNombre(Various.stringNull(rs.getString("NOMBRE")));
                    bean.setCodSub(Various.stringNull(rs.getString("CODSUB")));
                    bean.setDesSub(Various.stringNull(rs.getString("DESCSUB")));
                    bean.setFecCreac(Various.stringNull(rs.getString("FECCREACION")));
                    bean.setFecModif(Various.stringNull(rs.getString("FECMODIF")));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("detail", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("detail", resultado);
                    mapa.put("error", "1");
                }
            }
            else {
                mapa.put("detail", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map consultFamilyHolder(String tpDoc, String nrDoc)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();

        LinkedHashMap mapa = new LinkedHashMap();

        CallableStatement cs = null;
        ResultSet rs = null;
        ConsultAccreCmplBean bean = null;
        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_FAMILY_BOND(?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : consultFamilyHolder");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpDoc);
            cs.setString(2, nrDoc);
            cs.registerOutParameter(3, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(3);

            if (rs != null) {
                while (rs.next()) {
                    bean = new ConsultAccreCmplBean();
                    bean.setAutFamiliar(Various.stringNull(rs.getString("AUTOGFAMIL")));
                    bean.setDgansasFamiliar(Various.stringNull(rs.getString("DGANSAS")));
                    bean.setTpDoc(Various.stringNull(rs.getString("DGANSAS")));
                    bean.setTpDoc(Various.stringNull(rs.getString("TP_DOC")));
                    bean.setDsTpDoc(Various.stringNull(rs.getString("DS_DOC")));
                    bean.setNrDoc(Various.stringNull(rs.getString("NR_DOC")));
                    bean.setApPaterno(Various.stringNull(rs.getString("AP_PATERNO")));
                    bean.setApMaterno(Various.stringNull(rs.getString("AP_MATERNO")));
                    bean.setNombres(Various.stringNull(rs.getString("NOMBRES")));
                    bean.setVinculo(Various.stringNull(rs.getString("VINCULO")));
                    bean.setFecIniSol(Various.stringNull(rs.getString("FECINI")));
                    bean.setFecFinSol(Various.stringNull(rs.getString("FECFIN")));
                    bean.setEstado(Various.stringNull(rs.getString("ESTADO")));
                    bean.setAutTitular(Various.stringNull(rs.getString("AUTOGTITULAR")));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("detail", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("detail", resultado);
                    mapa.put("error", "1");
                }
            }
            else {
                mapa.put("detail", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            String strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map consultAccreCmpl(String tpDoc, String nrDoc)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();

        LinkedHashMap mapa = new LinkedHashMap();

        CallableStatement cs = null;
        ResultSet rs = null;
        ConsultAccreCmplBean bean = null;
        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REPORT_ACREDCMPL(?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : consultAccreCmpl");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpDoc);
            cs.setString(2, nrDoc);
            cs.registerOutParameter(3, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(3);

            if (rs != null) {
                while (rs.next()) {
                    bean = new ConsultAccreCmplBean();
                    bean.setAutFamiliar(Various.stringNull(rs.getString("AUTOGFAMIL")));
                    bean.setDgansasFamiliar(Various.stringNull(rs.getString("DGANSAS")));
                    bean.setTpDoc(Various.stringNull(rs.getString("DGANSAS")));
                    bean.setTpDoc(Various.stringNull(rs.getString("TP_DOC")));
                    bean.setDsTpDoc(Various.stringNull(rs.getString("DS_DOC")));
                    bean.setNrDoc(Various.stringNull(rs.getString("NR_DOC")));
                    bean.setApPaterno(Various.stringNull(rs.getString("AP_PATERNO")));
                    bean.setApMaterno(Various.stringNull(rs.getString("AP_MATERNO")));
                    bean.setNombres(Various.stringNull(rs.getString("NOMBRES")));
                    bean.setVinculo(Various.stringNull(rs.getString("VINCULO")));
                    bean.setFecIniSol(Various.stringNull(rs.getString("FECINI")));
                    bean.setFecFinSol(Various.stringNull(rs.getString("FECFIN")));
                    bean.setEstado(Various.stringNull(rs.getString("ESTADO")));
                    bean.setAutTitular(Various.stringNull(rs.getString("AUTOGTITULAR")));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("detail", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("detail", resultado);
                    mapa.put("error", "1");
                }
            }
            else {
                mapa.put("detail", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            String strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map sendAccredCmpl(String tpDocBenf, String nrDocBenf, String tpDocRuc, String nrDocRuc, String user)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;

        String povMssg = null;
        String povInd = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_ACCRE_CMPL_HEADLINE(?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : sendAccredCmpl");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpDocBenf);
            cs.setString(2, nrDocBenf);
            cs.setString(3, tpDocRuc);
            cs.setString(4, nrDocRuc);
            cs.setString(5, user);
            cs.registerOutParameter(6, 12);
            cs.registerOutParameter(7, 12);
            cs.executeUpdate();

            povMssg = cs.getString(6);
            povInd = cs.getString(7);

            mapa.put("message", Various.stringNull(povMssg));
            if (povInd.equals("E"))
                mapa.put("error", "1");
            else {
                mapa.put("error", "0");
            }
            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("sendAccredCmpl\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            mapa.put("message", Integer.valueOf(e.getErrorCode()));
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
                mapa.put("message", Integer.valueOf(e.getErrorCode()));
                mapa.put("error", "1");
            }
        }

        return mapa;
    }

    public Map sendArchiveAcredCmpl(ArchivoAccredCmplBean bn)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;

        String povMssg = null;
        String povInd = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_ARCHIVE_SUSTENTION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : sendArchiveAcredCmpl");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);
            cs.setString(1, bn.getIdAcreditacion());
            cs.setString(2, bn.getNumSerie());
            cs.setString(3, bn.getNumDocumento());
            cs.setString(4, bn.getCodPeriodo());
            cs.setString(5, bn.getFecEmisionDoc());
            cs.setString(6, bn.getTpDocRuc());
            cs.setString(7, bn.getNrDocRUc());
            cs.setString(8, bn.getFecIniVincLab());
            cs.setString(9, bn.getTpArchivo());
            cs.setString(10, bn.getNombreArchivoNew());
            cs.setString(11, bn.getUrlDirectorio());
            cs.setString(12, bn.getBase64());
            cs.setString(13, bn.getUsuarioCrea());
            cs.registerOutParameter(14, 12);
            cs.registerOutParameter(15, 12);
            cs.executeUpdate();

            povMssg = cs.getString(14);
            povInd = cs.getString(15);

            mapa.put("message", Various.stringNull(povMssg));
            if (povInd.equals("E"))
                mapa.put("error", "1");
            else {
                mapa.put("error", "0");
            }
            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("sendArchiveAcredCmpl\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            mapa.put("message", Integer.valueOf(e.getErrorCode()));
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
                mapa.put("message", Integer.valueOf(e.getErrorCode()));
                mapa.put("error", "1");
            }
        }

        return mapa;
    }

    public Map consultAccreditation(String tpDoc, String nrDoc)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();

        CallableStatement cs = null;
        ResultSet rs = null;
        ReportAccreditation bean = null;
        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_DATA_ACCREDITATION(?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : consultAccreditation");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpDoc);
            cs.setString(2, nrDoc);
            cs.registerOutParameter(3, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(3);

            if (rs != null) {
                while (rs.next()) {
                    bean = new ReportAccreditation();
                    bean.setTpDoc(Various.stringNull(rs.getString(1)));
                    bean.setNrDoc(Various.stringNull(rs.getString(2)));
                    bean.setCdCentro(Various.stringNull(rs.getString(3)));
                    bean.setFecIniAcred(Various.stringNull(rs.getString(4)));
                    bean.setFecFinAcred(Various.stringNull(rs.getString(5)));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("data", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", resultado);
                    mapa.put("error", "1");
                }
            }
            else {
                mapa.put("data", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map getBorradoresReembolso(String nrRuc, String usrCr)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();

        CallableStatement cs = null;
        ResultSet rs = null;
        ReportMaternityBean bean = null;
        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_GET_BORRADOR_REEMBOLSO(?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getBorradoresReembolso");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setString(1, nrRuc);
            cs.setString(2, usrCr);
            cs.registerOutParameter(3, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(3);

            if (rs != null) {
                while (rs.next()) {
                    bean = new ReportMaternityBean();
                    bean.setIdSolicitud(Various.stringNull(rs.getString(1)));
                    bean.setNrDoc(Various.stringNull(rs.getString(2)));
                    bean.setNombre(Various.stringNull(rs.getString(3)));
                    bean.setNrCitt(Various.stringNull(rs.getString(4)));
                    bean.setFecIniPer(Various.stringNull(rs.getString(5)));
                    bean.setFecFinPer(Various.stringNull(rs.getString(6)));
                    bean.setCantDias(Various.stringNull(rs.getString(7)));
                    bean.setFec_cese(Various.stringNull(rs.getString(8)));
                    bean.setMonto(Various.stringNull(rs.getString(9)));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("data", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", resultado);
                    mapa.put("error", "1");
                }
            }
            else {
                mapa.put("data", resultado);
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map deleteBorradorReembolso(String codSolicitud)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;

        String txtError = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_DEL_BORRADOR_REEMBOLSO(?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : deleteBorradorReembolso");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, codSolicitud);
            cs.registerOutParameter(2, 4);
            cs.registerOutParameter(3, 12);
            cs.executeUpdate();

            int flgResult = cs.getInt(2);
            txtError = cs.getString(3);
            mapa.put("flgResult", Integer.valueOf(flgResult));
            mapa.put("txtError", Various.stringNull(txtError));
            mapa.put("error", "0");
            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("deleteBorradorReembolso\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            mapa.put("message", Integer.valueOf(e.getErrorCode()));
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
                mapa.put("message", Integer.valueOf(e.getErrorCode()));
                mapa.put("error", "1");
            }
        }

        return mapa;
    }

    public Map registrarPagoReembolso(String idArray, String codUser)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;

        String txtError = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REG_PAGO_REEMBOLSO(?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : registrarPagoReembolso");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, idArray);
            cs.setString(2, codUser);
            cs.registerOutParameter(3, 4);
            cs.registerOutParameter(4, 12);
            cs.executeUpdate();

            int flgResult = cs.getInt(3);
            txtError = cs.getString(4);
            mapa.put("flgResult", Integer.valueOf(flgResult));
            mapa.put("txtError", Various.stringNull(txtError));
            mapa.put("error", "0");
            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("registrarPagoReembolso.\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            mapa.put("message", Integer.valueOf(e.getErrorCode()));
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
                mapa.put("message", Integer.valueOf(e.getErrorCode()));
                mapa.put("error", "1");
            }
        }

        return mapa;
    }

    public Map getDetalleSolicitud(String codSol, String tpConsul)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();

        CallableStatement cs = null;
        ResultSet rs = null;
        ReportMaternityDetBean maBean = null;
        ReportInhabilityDetBean inBean = null;
        ReportLactanciaDetBean labean = null;
        ReportLactanciaSmartBean smartBean = null;

        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REPORT_SUBSIDIES(?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getDetalleSolicitud");
            LOGGER.info(sqlStr);
            cs = connection.prepareCall(sqlStr);

            cs.setString(1, codSol);
            cs.setString(2, tpConsul);
            cs.registerOutParameter(3, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(3);

            if (tpConsul.equals("LT")) {
                if (rs != null) {
                    while (rs.next()) {
                        labean = new ReportLactanciaDetBean();
                        labean.setIdSolicitud(Various.stringNull(rs.getString(1)));
                        labean.setOrigen(Various.stringNull(rs.getString(2)));
                        labean.setCodTipDocBenef(Various.stringNull(rs.getString(3)));
                        labean.setNumDocBenef(Various.stringNull(rs.getString(4)));
                        labean.setCodTipHijo(Various.stringNull(rs.getString(5)));
                        labean.setNumDocHijo(Various.stringNull(rs.getString(6)));
                        labean.setFecNacHijo(Various.stringNull(rs.getString(7)));
                        labean.setCodTipTitular(Various.stringNull(rs.getString(8)));
                        labean.setNumDocTitular(Various.stringNull(rs.getString(9)));
                        labean.setCodEmpleador(Various.stringNull(rs.getString(10)));
                        labean.setEstadoSolicitud(Various.stringNull(rs.getString(11)));
                        labean.setResultCalific(Various.stringNull(rs.getString(12)));
                        labean.setDetalleError(Various.stringNull(rs.getString(13)));
                        labean.setExpedienteNit(Various.stringNull(rs.getString(14)));
                        labean.setFecCreacion(Various.stringNull(rs.getString(15)));
                        labean.setCodUsuario(Various.stringNull(rs.getString(16)));
                        labean.setIpUsuarioCrea(Various.stringNull(rs.getString(17)));
                        labean.setMontoSubsidio(Various.stringNull(rs.getString(18)));
                        labean.setFecPago(Various.stringNull(rs.getString(19)));
                        resultado.add(labean);
                    }

                    if (!resultado.isEmpty()) {
                        mapa.put("data", labean);
                        mapa.put("error", "0");
                    } else {
                        mapa.put("data", labean);
                        mapa.put("error", "1");
                    }
                }
                else {
                    mapa.put("data", labean);
                    mapa.put("error", "1");
                }
            }
            if (tpConsul.equals("LS")) {
                if (rs != null) {
                    while (rs.next())
                    {
                        smartBean = new ReportLactanciaSmartBean();
                        smartBean.setIdeSolicitud(Various.stringNull(rs.getString(1)));
                        smartBean.setTpoDocTitular(Various.stringNull(rs.getString(2)));
                        smartBean.setNroDocTitular(Various.stringNull(rs.getString(3)));
                        smartBean.setTpoDocMadre(Various.stringNull(rs.getString(4)));
                        smartBean.setNroDocMadre(Various.stringNull(rs.getString(5)));
                        smartBean.setCodCNV(Various.stringNull(rs.getString(6)));
                        smartBean.setFecNacHijo(Various.stringNull(rs.getString(7)));
                        smartBean.setRucEmpleador(Various.stringNull(rs.getString(8)));
                        smartBean.setMontoSubsidio(Various.stringNull(rs.getString(9)));
                        smartBean.setResultCalificacion(Various.stringNull(rs.getString(10)));
                        smartBean.setCodMotivoObserv(Various.stringNull(rs.getString(11)));
                        smartBean.setEstadoExpediente(Various.stringNull(rs.getString(12)));
                        smartBean.setNroExpediente(Various.stringNull(rs.getString(13)));
                        smartBean.setFecEvalExpediente(Various.stringNull(rs.getString(14)));
                        smartBean.setTpoLactancia(Various.stringNull(rs.getString(15)));
                        smartBean.setCodRenaes(Various.stringNull(rs.getString(16)));
                        smartBean.setEstablecimientoSalud(Various.stringNull(rs.getString(17)));
                        smartBean.setFecPago(Various.stringNull(rs.getString(18)));
                        smartBean.setCodAgenciaSeguros(Various.stringNull(rs.getString(19)));
                        smartBean.setOficinaSeguros(Various.stringNull(rs.getString(20)));
                        smartBean.setCodOspe(Various.stringNull(rs.getString(21)));
                        resultado.add(labean);
                    }

                    if (!resultado.isEmpty()) {
                        mapa.put("data", smartBean);
                        mapa.put("error", "0");
                    } else {
                        mapa.put("data", smartBean);
                        mapa.put("error", "1");
                    }
                }
                else {
                    mapa.put("data", smartBean);
                    mapa.put("error", "1");
                }
            }
            if (tpConsul.equals("IN")) {
                if (rs != null) {
                    while (rs.next()) {
                        inBean = new ReportInhabilityDetBean();
                        inBean.setIdSolicitud(Various.stringNull(rs.getString(1)));
                        inBean.setFecRegistro(Various.stringNull(rs.getString(2)));
                        inBean.setExpedienteNit(Various.stringNull(rs.getString(3)));
                        inBean.setCodTipDocBenef(Various.stringNull(rs.getString(4)));
                        inBean.setNumDocBenef(Various.stringNull(rs.getString(5)));
                        inBean.setNombres(Various.stringNull(rs.getString(6)));
                        inBean.setTipEmpleado(Various.stringNull(rs.getString(7)));
                        inBean.setRuc(Various.stringNull(rs.getString(8)));
                        inBean.setRazonSocial(Various.stringNull(rs.getString(9)));
                        inBean.setMontoSolicitado(Various.stringNull(rs.getString(10)));
                        inBean.setMontoPagado(Various.stringNull(rs.getString(11)));
                        inBean.setCitt(Various.stringNull(rs.getString(12)));
                        inBean.setFecIniSubs(Various.stringNull(rs.getString(13)));
                        inBean.setFecFinSubs(Various.stringNull(rs.getString(14)));
                        inBean.setDiasSubs(Various.stringNull(rs.getString(15)));
                        inBean.setDiasRecortados(Various.stringNull(rs.getString(16)));
                        inBean.setFecIniPagado(Various.stringNull(rs.getString(17)));
                        inBean.setFecFinPagado(Various.stringNull(rs.getString(18)));
                        inBean.setFecPago(Various.stringNull(rs.getString(19)));
                        inBean.setFecCese(Various.stringNull(rs.getString(20)));
                        inBean.setCodUsuario(Various.stringNull(rs.getString(21)));
                        inBean.setIpUsuarioCrea(Various.stringNull(rs.getString(22)));
                        inBean.setFlagResultado(Various.stringNull(rs.getString(23)));
                        inBean.setEstadoSolicitud(Various.stringNull(rs.getString(24)));
                        inBean.setMotivoRechazo(Various.stringNull(rs.getString(25)));
                        inBean.setAgSeguros(Various.stringNull(rs.getString(26)));
                        inBean.setCodAgSeguros(Various.stringNull(rs.getString(27)));
                        inBean.setDesSeguros(Various.stringNull(rs.getString(28)));
                        inBean.setUpe(Various.stringNull(rs.getString(29)));
                        resultado.add(inBean);
                    }

                    if (!resultado.isEmpty()) {
                        mapa.put("data", inBean);
                        mapa.put("error", "0");
                    } else {
                        mapa.put("data", inBean);
                        mapa.put("error", "1");
                    }
                }
                else {
                    mapa.put("data", inBean);
                    mapa.put("error", "1");
                }
            }
            if (tpConsul.equals("MA"))
                if (rs != null) {
                    while (rs.next()) {
                        maBean = new ReportMaternityDetBean();
                        maBean.setIdSolicitud(Various.stringNull(rs.getString(1)));
                        maBean.setCodTipDocTitular(Various.stringNull(rs.getString(2)));
                        maBean.setNumDocTitular(Various.stringNull(rs.getString(3)));
                        maBean.setNombres(Various.stringNull(rs.getString(4)));
                        maBean.setRuc(Various.stringNull(rs.getString(5)));
                        maBean.setRazonSocial(Various.stringNull(rs.getString(6)));
                        maBean.setCitt(Various.stringNull(rs.getString(7)));
                        maBean.setFecIniCitt(Various.stringNull(rs.getString(8)));
                        maBean.setFecFinCitt(Various.stringNull(rs.getString(9)));
                        maBean.setDiasCitt(Various.stringNull(rs.getString(10)));
                        maBean.setArmada(Various.stringNull(rs.getString(11)));
                        maBean.setFecIniSubs(Various.stringNull(rs.getString(12)));
                        maBean.setFecFinSubs(Various.stringNull(rs.getString(13)));
                        maBean.setDiasSubs(Various.stringNull(rs.getString(14)));
                        maBean.setTipAsegurado(Various.stringNull(rs.getString(15)));
                        maBean.setTipEmpleado(Various.stringNull(rs.getString(16)));
                        maBean.setMontoSolic(Various.stringNull(rs.getString(17)));
                        maBean.setMontoPagado(Various.stringNull(rs.getString(18)));
                        maBean.setResultCalificado(Various.stringNull(rs.getString(19)));
                        maBean.setResultError(Various.stringNull(rs.getString(20)));
                        maBean.setTipProceso(Various.stringNull(rs.getString(21)));
                        maBean.setEstadoSolic(Various.stringNull(rs.getString(22)));
                        maBean.setExpedNit(Various.stringNull(rs.getString(23)));
                        maBean.setCodUsuario(Various.stringNull(rs.getString(24)));
                        maBean.setFecRegistro(Various.stringNull(rs.getString(25)));
                        maBean.setIpUsuarioCrea(Various.stringNull(rs.getString(26)));
                        maBean.setAgSeguros(Various.stringNull(rs.getString(27)));
                        maBean.setDesSeguros(Various.stringNull(rs.getString(28)));
                        maBean.setFecPago(Various.stringNull(rs.getString(29)));
                        maBean.setUpe(Various.stringNull(rs.getString(30)));
                        resultado.add(maBean);
                    }

                    if (!resultado.isEmpty()) {
                        mapa.put("data", maBean);
                        mapa.put("error", "0");
                    } else {
                        mapa.put("data", maBean);
                        mapa.put("error", "1");
                    }
                }
                else {
                    mapa.put("data", maBean);
                    mapa.put("error", "1");
                }
        }
        catch (SQLException e)
        {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }
        return mapa;
    }

    public Map sendDirectPayment(String tpDocTi, String nrDocTi, String ruc, String fecIniSubs, String fecFinSubs, String tpPago)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;

        String pov_trab_publico = null;
        String pov_es_ctr = null;
        String pov_es_pago_directo = null;
        String pov_mensaje_evaluar = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_ES_DIRECTPAYMENT(?,?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : sendDirectPayment");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpDocTi);
            cs.setString(2, nrDocTi);
            cs.setString(3, ruc);
            cs.setString(4, fecIniSubs);
            cs.setString(5, fecFinSubs);
            cs.setString(6, tpPago);
            cs.registerOutParameter(7, 12);
            cs.registerOutParameter(8, 12);
            cs.registerOutParameter(9, 12);
            cs.registerOutParameter(10, 12);
            cs.executeUpdate();

            pov_trab_publico = cs.getString(7);
            pov_es_ctr = cs.getString(8);
            pov_es_pago_directo = cs.getString(9);
            pov_mensaje_evaluar = cs.getString(10);

            mapa.put("pov_trab_publico", pov_trab_publico);
            mapa.put("pov_es_ctr", pov_es_ctr);
            mapa.put("pov_es_pago_directo", pov_es_pago_directo);
            mapa.put("pov_mensaje_evaluar", pov_mensaje_evaluar);
            mapa.put("error", "0");
            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("sendDirectPayment.\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            mapa.put("message", Integer.valueOf(e.getErrorCode()));
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
                mapa.put("message", Integer.valueOf(e.getErrorCode()));
                mapa.put("error", "1");
            }
        }

        return mapa;
    }

    public Map sendAccredCmplRigthHolder(String autFam, String tpDocTi, String nrDocTi, String nrDocRuc, String user)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;

        String povMssg = null;
        String povInd = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_ACCRE_CMPL_RIGHTHOLDER(?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : sendAccredCmpl");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, autFam);
            cs.setString(2, tpDocTi);
            cs.setString(3, nrDocTi);
            cs.setString(4, nrDocRuc);
            cs.setString(5, user);
            cs.registerOutParameter(6, 12);
            cs.registerOutParameter(7, 12);
            cs.executeUpdate();

            povMssg = cs.getString(6);
            povInd = cs.getString(7);

            mapa.put("message", Various.stringNull(povMssg));
            if (povInd.equals("E"))
                mapa.put("error", "1");
            else {
                mapa.put("error", "0");
            }
            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("sendAccredCmplRigthHolder\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            mapa.put("message", Integer.valueOf(e.getErrorCode()));
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
                mapa.put("message", Integer.valueOf(e.getErrorCode()));
                mapa.put("error", "1");
            }
        }

        return mapa;
    }

    public Map valAccredCmplHeadLine(String tpDoc, String nrDoc, String tpDocRuc, String nrDocRuc)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;

        String povMssg = null;
        String povInd = null;

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_VAL_ACCRE_CMPL_HEADLINE(?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : valAccredCmplHeadLine");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpDoc);
            cs.setString(2, nrDoc);
            cs.setString(3, tpDocRuc);
            cs.setString(4, nrDocRuc);
            cs.registerOutParameter(5, 12);
            cs.registerOutParameter(6, 12);
            cs.executeUpdate();

            povMssg = cs.getString(5);
            povInd = cs.getString(6);

            mapa.put("message", Various.stringNull(povMssg));
            if (povInd.equals("E"))
                mapa.put("error", "1");
            else {
                mapa.put("error", "0");
            }
            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("valAccredCmplHeadLine\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            mapa.put("message", Integer.valueOf(e.getErrorCode()));
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
                mapa.put("message", Integer.valueOf(e.getErrorCode()));
                mapa.put("error", "1");
            }
        }

        return mapa;
    }

    public Map getParentsReniec(String nrDoc)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        PersonBean bean = null;
        CallableStatement cs = null;

        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_GET_PARENTS_RENIEC(?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getParents");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, nrDoc);
            cs.registerOutParameter(2, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(2);

            if (rs != null) {
                while (rs.next()) {
                    bean = new PersonBean();
                    bean.setTpDoc(Various.stringNull(rs.getString("TPDOC")));
                    bean.setNrDoc(Various.stringNull(rs.getString("NRDOC")));
                    bean.setApPaterno(Various.stringNull(rs.getString("APEPATERNO")));
                    bean.setApMaterno(Various.stringNull(rs.getString("APEMATERNO")));
                    bean.setNombres(Various.stringNull(rs.getString("NOMBRES")));
                    bean.setEstCivil(Various.stringNull(rs.getString("ESTCIVIL")));
                    bean.setSexo(Various.stringNull(rs.getString("SEXO")));
                    bean.setFecNacimiento(Various.stringNull(rs.getString("FECNACIMIENTO")));
                    bean.setTpdocPadre(Various.stringNull(rs.getString("TPDOCPADRE")));
                    bean.setNroDocPadre(Various.stringNull(rs.getString("NRDOCPADRE")));
                    bean.setNomPadre(Various.stringNull(rs.getString("NOMPADRE")));
                    bean.setTpDocMadre(Various.stringNull(rs.getString("DOCMADRE")));
                    bean.setNroDocMadre(Various.stringNull(rs.getString("NRDOCMADRE")));
                    bean.setNomMadre(Various.stringNull(rs.getString("NOMMADRE")));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("data", bean);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", "Asegurado No Registrado");
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", "Asegurado No Registrado");
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
            mapa.put("data", strErrores);
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map getListLactSmart(String tpDoc, String nrDoc, String cdCnv)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        LactanciaSmartBean bean = null;
        CallableStatement cs = null;

        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_CONSULT_LACT_SMART(?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getListLactSmart");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpDoc);
            cs.setString(2, nrDoc);
            cs.setString(3, cdCnv);
            cs.registerOutParameter(4, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(4);

            if (rs != null) {
                while (rs.next()) {
                    bean = new LactanciaSmartBean();

                    bean.setIdeDetalleLactanciaSmart(Various.stringNull(rs.getString(1)));
                    bean.setIdeRegevalLactanciaSmart(Various.stringNull(rs.getString(2)));
                    bean.setNumIdeSolicitud(Various.stringNull(rs.getString(3)));
                    bean.setIdeNumRepolact(Various.stringNull(rs.getString(4)));
                    bean.setCodTdocTitular(Various.stringNull(rs.getString(5)));
                    bean.setCodEdocTitular(Various.stringNull(rs.getString(6)));
                    bean.setNumNdocTitular(Various.stringNull(rs.getString(7)));
                    bean.setTxtNombreCompletoTitular(Various.stringNull(rs.getString(8)));
                    bean.setCodTdocMadre(Various.stringNull(rs.getString(9)));
                    bean.setCodEdocMadre(Various.stringNull(rs.getString(10)));
                    bean.setNumNdocMadre(Various.stringNull(rs.getString(11)));
                    bean.setTxtNombreCompletoMadre(Various.stringNull(rs.getString(12)));
                    bean.setCodTdocHijo(Various.stringNull(rs.getString(13)));
                    bean.setCodEdocHijo(Various.stringNull(rs.getString(14)));
                    bean.setNumNdocHijo(Various.stringNull(rs.getString(15)));
                    bean.setTxtNombreCompletoHijo(Various.stringNull(rs.getString(16)));
                    bean.setTxtVinculoFamiliar(Various.stringNull(rs.getString(17)));
                    bean.setFecPartoMadre(Various.stringNull(rs.getString(18)));
                    bean.setTxtCnvReniec(Various.stringNull(rs.getString(19)));
                    bean.setCodCasRenaes(Various.stringNull(rs.getString(20)));
                    bean.setTxtNombreEstablecimiento(Various.stringNull(rs.getString(21)));
                    bean.setCodOspe(Various.stringNull(rs.getString(22)));
                    bean.setNumRucEmpleador(Various.stringNull(rs.getString(23)));
                    bean.setTxtRazonSocial(Various.stringNull(rs.getString(24)));
                    bean.setCodTipoEmpleado(Various.stringNull(rs.getString(25)));
                    bean.setTxtTipoEmpleado(Various.stringNull(rs.getString(26)));
                    bean.setCodTipoSeguro(Various.stringNull(rs.getString(27)));
                    bean.setTxtTipoSeguro(Various.stringNull(rs.getString(28)));
                    bean.setNumMontoPagado(Various.stringNull(rs.getString(29)));
                    bean.setNumExpediente(Various.stringNull(rs.getString(30)));
                    bean.setFecPago(Various.stringNull(rs.getString(31)));
                    bean.setTxtCodigoBanco(Various.stringNull(rs.getString(32)));
                    bean.setTxtNombreBanco(Various.stringNull(rs.getString(33)));
                    bean.setFlgActivoInactivo(Various.stringNull(rs.getString(34)));
                    bean.setFlgResultado(Various.stringNull(rs.getString(35)));
                    bean.setTxtMotivoRechazo(Various.stringNull(rs.getString(36)));
                    bean.setFlgMarca(Various.stringNull(rs.getString(37)));
                    bean.setFecUsuarioCrea(Various.stringNull(rs.getString(38)));
                    bean.setCodUsuarioCrea(Various.stringNull(rs.getString(39)));
                    bean.setTxtIpusuarioCrea(Various.stringNull(rs.getString(40)));
                    bean.setFecUsuarioAct(Various.stringNull(rs.getString(41)));
                    bean.setCodUsuarioAct(Various.stringNull(rs.getString(42)));
                    bean.setTxtIpusuarioAct(Various.stringNull(rs.getString(43)));

                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("data", bean);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", "Asegurado No Registrado");
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", "Asegurado No Registrado");
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
            mapa.put("data", strErrores);
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map getDisabledPerson(String nrDoc)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        InhabilitadosAsegbean bean = null;
        CallableStatement cs = null;

        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_VAL_DISABLED_PERSON(?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getDisabledPerson");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, nrDoc);
            cs.registerOutParameter(2, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(2);

            if (rs != null) {
                while (rs.next()) {
                    bean = new InhabilitadosAsegbean();
                    bean.setIndInhabilitado(Various.stringNull(rs.getString(1)));
                    bean.setDescInhabilitado(Various.stringNull(rs.getString(2)));
                    resultado.add(bean);
                }
                if (!resultado.isEmpty()) {
                    mapa.put("data", bean);
                    mapa.put("error", "0");
                }
            } else {
                mapa.put("data", "Se genero un error en la consulta");
                mapa.put("error", "1");
            }
        } catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
            mapa.put("data", strErrores);
            mapa.put("error", "1");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public Map getArchiveVUA(String tpdoc, String nrdoc, String tpproc)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();

        ResultSet rs = null;
        ReportArchiveBean bean = null;
        CallableStatement cs = null;

        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_GET_ARCHIVE_OV(?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : getArchiveVUA");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, tpdoc);
            cs.setString(2, nrdoc);
            cs.setString(3, tpproc.toUpperCase());
            cs.registerOutParameter(4, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(4);

            if (rs != null) {
                while (rs.next()) {
                    bean = new ReportArchiveBean();
                    bean.setDsDoc(Various.stringNull(rs.getString("TPDOC")));
                    bean.setNrDoc(Various.stringNull(rs.getString("NRDOC")));
                    bean.setApPaterno(Various.stringNull(rs.getString("APEPATERNO")));
                    bean.setApMaterno(Various.stringNull(rs.getString("APEMATERNO")));
                    bean.setNombres(Various.stringNull(rs.getString("NOMBRES")));
                    bean.setNomArchivo(Various.stringNull(rs.getString("NOMBREARCHIVO")));
                    bean.setUrlArchivo(Various.stringNull(rs.getString("URLARCHIVO")));
                    bean.setFecRegistro(Various.stringNull(rs.getString("FECREGISTRO")));
                    resultado.add(bean);
                }

                if (!resultado.isEmpty()) {
                    mapa.put("data", resultado);
                    mapa.put("error", "0");
                } else {
                    mapa.put("data", "No se encuentran registros");
                    mapa.put("error", "1");
                }
            } else {
                mapa.put("data", "No se encuentran registros");
                mapa.put("error", "1");
            }
        }
        catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        }
        finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public String get_contribuyentes_direcion(String dato)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        CallableStatement cs = null;
        String respuesta = "";
        String sqlStr = "{ ? = call " + this.OWNER + "." + "PKG_PE_GLOBAL" + ".fn_get_contribuyentes_direcion(?)}";
        try
        {
            cs = connection.prepareCall(sqlStr);
            cs.registerOutParameter(1, 12);
            cs.setString(2, dato);
            cs.executeUpdate();
            respuesta = cs.getString(1);
            cs.close();
        } catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }

        }

        return respuesta;
    }

    public String get_reniec_direccion(String dato)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        CallableStatement cs = null;
        String respuesta = "";
        String sqlStr = "{ ? = call " + this.OWNER + "." + "PKG_PE_GLOBAL" + ".fn_get_reniec_direccion(?)}";
        try
        {
            cs = connection.prepareCall(sqlStr);
            cs.registerOutParameter(1, 12);
            cs.setString(2, dato);
            cs.executeUpdate();
            respuesta = cs.getString(1);
            cs.close();
        } catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return respuesta;
    }

    public String get_reniec_ubigeo(String dato)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        CallableStatement cs = null;
        String respuesta = "";
        String sqlStr = "{ ? = call " + this.OWNER + "." + "PKG_PE_GLOBAL" + ".fn_get_reniec_ubigeo(?)}";
        try
        {
            cs = connection.prepareCall(sqlStr);
            cs.registerOutParameter(1, 12);
            cs.setString(2, dato);
            cs.executeUpdate();
            respuesta = cs.getString(1);
            cs.close();
        } catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return respuesta;
    }

    public String get_contribuyentes_ubigeo(String dato)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        CallableStatement cs = null;
        String respuesta = "";
        String sqlStr = "{ ? = call " + this.OWNER + "." + "PKG_PE_GLOBAL" + ".fn_get_contribuyentes_ubigeo(?)}";
        try
        {
            cs = connection.prepareCall(sqlStr);
            cs.registerOutParameter(1, 12);
            cs.setString(2, dato);
            cs.executeUpdate();
            respuesta = cs.getString(1);
            cs.close();
        } catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return respuesta;
    }

    public List<PeriodosLaboradosBean> get_subsidio_periodo(String ruc, String fecha_ini, String fecha_fin, String tipo_doc, String numDoc)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        List lista = new ArrayList();
        InhabilitadosAsegbean bean = null;
        CallableStatement cs = null;

        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + "PKG_PE_GLOBAL" + ".SP_SUBSIDIO_PERIODO(?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("SP_SUBSIDIO_PERIODO : SP_SUBSIDIO_PERIODO");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, ruc);
            cs.setString(2, fecha_ini);
            cs.setString(3, fecha_fin);
            cs.setString(4, tipo_doc);
            cs.setString(5, numDoc);
            cs.registerOutParameter(6, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(6);

            if (rs != null) {
                LOGGER.info("entre a perido subsidio");

                while (rs.next()) {
                    LOGGER.info("entre a perido subsidio");
                    LOGGER.info("data" + rs.getString(1));
                    LOGGER.info("data2" + rs.getString(2));
                    PeriodosLaboradosBean perido = new PeriodosLaboradosBean();

                    perido.setPer_aporta(Various.stringNull(rs.getString(1)));
                    perido.setDias_labor(Various.stringNull(rs.getString(2)));
                    lista.add(perido);
                    LOGGER.info("La clase" + perido.toString());
                }

            }

        }
        catch (SQLException e)
        {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
            lista = null;
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return lista;
    }

    public String get_obtener_nombre_anio(String dato)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        CallableStatement cs = null;
        String respuesta = "";
        String sqlStr = "{ ? = call " + this.OWNER + "." + "PKG_PE_GLOBAL" + ".fn_obtener_nombre_anio(?)}";
        try
        {
            cs = connection.prepareCall(sqlStr);
            cs.registerOutParameter(1, 12);
            cs.setString(2, dato);
            cs.executeUpdate();
            respuesta = cs.getString(1);
            cs.close();
        } catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return respuesta;
    }

    public String get_obtener_nombre_decenio(String dato)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        CallableStatement cs = null;
        String respuesta = "";
        String sqlStr = "{ ? = call " + this.OWNER + "." + "PKG_PE_GLOBAL" + ".fn_obtener_nombre_decenio(?)}";
        try
        {
            cs = connection.prepareCall(sqlStr);
            cs.registerOutParameter(1, 12);
            cs.setString(2, dato);
            cs.executeUpdate();
            respuesta = cs.getString(1);
            cs.close();
        } catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return respuesta;
    }

    public String get_obtener_nitt_monto_exceso(String ide_codigo)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        String strErrores = null;
        CallableStatement cs = null;
        String respuesta = "";
        String sqlStr = "{ ? = call " + this.OWNER + "." + this.PACKAGE + ".fn_nitt(?)}";
        try
        {
            cs = connection.prepareCall(sqlStr);
            cs.registerOutParameter(1, 12);
            cs.setString(2, ide_codigo);
            cs.executeUpdate();
            respuesta = cs.getString(1);
            cs.close();
        } catch (SQLException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return respuesta;
    }
//
//    public Map getListaTrabajoHabitual()
//    {
//        String strErrores = null;
//        LinkedHashMap mapa = new LinkedHashMap();
//        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
//        Connection connection = conn.getConnection();
//        ResultSet rs = null;
//        TrabajoHabitual bean = null;
//        CallableStatement cs = null;
//        List resultado = new ArrayList();
//        LOGGER.info("el owner" + this.OWNER);
//        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_LISTA_TRABAJO_HABITAL(?); END;";
//        try
//        {
//            LOGGER.info("WSOspeVirtualDAOImpl : getInsuredReniec");
//            LOGGER.info(sqlStr);
//            cs = connection.prepareCall(sqlStr);
//            cs.registerOutParameter(1, -10);
//            cs.executeUpdate();
//            rs = (ResultSet)cs.getObject(1);
//
//            if (rs != null) {
//                while (rs.next()) {
//                    bean = new TrabajoHabitual();
//                    bean.setCodigo(Various.stringNull(rs.getString("CODIGO")));
//                    bean.setDescripcion(Various.stringNull(rs.getString("DESCRIPCION")));
//                    resultado.add(bean);
//                }
//
//                if (!resultado.isEmpty()) {
//                    mapa.put("data", resultado);
//                    mapa.put("error", "0");
//                } else {
//                    mapa.put("data", "Asegurado No Registrado");
//                    mapa.put("error", "1");
//                }
//            } else {
//                mapa.put("data", "Asegurado No Registrado");
//                mapa.put("error", "1");
//            }
//        }
//        catch (SQLException e) {
//            StringWriter errors = new StringWriter();
//            e.printStackTrace(new PrintWriter(errors));
//            strErrores = e.toString();
//            LOGGER.error(strErrores);
//        }
//        finally {
//            try {
//                if (cs != null) {
//                    cs.close();
//                }
//                if (rs != null) {
//                    rs.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                    conn.close();
//                }
//            }
//            catch (SQLException e) {
//                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
//            }
//        }
//        return mapa;
//    }

//    public Map insertarsepelio(DataSepelio bean)
//    {
//        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
//        Connection connection = conn.getConnection();
//
//        CallableStatement cs = null;
//
//        String vOUTIDENUMSOLICITUD_OV = "";
//        String vOUTMENSSAGE = "";
//        String vOUTIND = "";
//        LinkedHashMap mapa = new LinkedHashMap();
//        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REGISTER_SEPELIO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
//        try
//        {
//            LOGGER.info("WSOspeVirtualDAOImpl : insertarSepelio");
//            LOGGER.info(sqlStr);
//            cs = connection.prepareCall(sqlStr);
//
//            cs.setString(1, bean.getV_cod_tdoc_beneficiario());
//            cs.setString(2, bean.getV_cod_edoc_beneficiario());
//            cs.setString(3, bean.getV_num_ndoc_beneficiario());
//            cs.setString(4, bean.getV_txt_nombre_beneficiario());
//            cs.setString(5, bean.getV_cod_tdoc_titular());
//            cs.setString(6, bean.getV_cod_edoc_titular());
//            cs.setString(7, bean.getV_txt_nombre_titular());
//            cs.setString(8, bean.getV_num_ndoc_titular());
//            cs.setString(9, bean.getV_num_monto_subsidiado());
//            cs.setString(10, bean.getV_monto_solicitado());
//            cs.setString(11, bean.getV_fec_fallecimiento_titular());
//            cs.setString(12, bean.getV_cod_tparentesco());
//            cs.setString(13, bean.getV_cod_eparentesco());
//            cs.setString(14, bean.getV_flag_resultado());
//            cs.setString(15, bean.getV_numero_expediente());
//            cs.setString(16, bean.getV_fecha_pago());
//            cs.setString(17, bean.getV_fecha_cese());
//            cs.setString(18, bean.getV_fecha_creacion());
//            cs.setString(19, bean.getV_cod_usuario_crea());
//            cs.setString(20, bean.getV_ip_usuario_crea());
//            cs.setString(21, bean.getV_cod_usuario_viva());
//            cs.setString(22, bean.getV_flag_activo_inactivo());
//            cs.setString(23, bean.getV_cod_edoc_usuario());
//            cs.setString(24, bean.getV_num_doc_usuario());
//            cs.setString(25, bean.getV_cod_ospe());
//            cs.setString(26, bean.getV_tip_pago());
//
//            cs.registerOutParameter(27, 12);
//            cs.registerOutParameter(28, 12);
//            cs.registerOutParameter(29, 12);
//            cs.executeUpdate();
//            vOUTIDENUMSOLICITUD_OV = cs.getString(27);
//            vOUTMENSSAGE = cs.getString(28);
//            vOUTIND = cs.getString(29);
//
//            mapa.put("codSolicitud", vOUTIDENUMSOLICITUD_OV);
//            mapa.put("message", vOUTMENSSAGE);
//
//            if (vOUTIND.equals("N"))
//                mapa.put("error", "0");
//            else {
//                mapa.put("error", "1");
//            }
//
//            cs.close();
//        } catch (SQLException e) {
//            LOGGER.error("insertarsepelio\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
//            mapa.put("codSolicitud", vOUTIDENUMSOLICITUD_OV);
//            mapa.put("message", Integer.valueOf(e.getErrorCode()));
//            mapa.put("error", "1");
//        } finally {
//            try {
//                if (cs != null) {
//                    cs.close();
//                    connection.close();
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
//            }
//        }
//
//        return mapa;
//    }

    public Map registerComproanteTotal(List<Comprobantes> bean, Integer idSoli)
    {
        Iterator it = bean.iterator();
        Map map = new HashMap();
        do { if (!it.hasNext()) break;
            map = registerComproantes((Comprobantes)it.next(), idSoli); }
        while (!map.get("error").toString().equals("1"));

        return map;
    }

//    public Map registerRutas(ListArchivo bean)
//    {
//        Map map = new HashMap();
//
//        Iterator it = bean.getArchivos().iterator();
//        do {
//            if (!it.hasNext()) {
//                break;
//            }
//            map = registerArchive((ArchivoBean)it.next());
//        }
//
//        while (!map.get("error").toString().equals("1"));
//
//        return map;
//    }

    public Map registerComproantes(Comprobantes bean, Integer idSoli)
    {
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        LinkedHashMap mapa = new LinkedHashMap();
        CallableStatement cs = null;
        Integer vIDECOMPROBANTEOV = null;
        String sqlStr = "BEGIN " + this.OWNER + "." + this.PACKAGE + ".PRC_REGISTER_COMPROBANTES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
        try
        {
            LOGGER.info("WSOspeVirtualDAOImpl : PRC_REGISTER_COMPROBANTES");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);
            cs.setInt(1, idSoli.intValue());
            cs.setString(2, bean.getNumero_ruc());
            cs.setString(3, bean.getRazon_social());
            cs.setString(4, bean.getFecha_comprobante());
            cs.setString(5, bean.getNumero_serie());
            cs.setString(6, bean.getNumero_comprobante());
            cs.setFloat(7, Float.parseFloat(bean.getImporte()));
            cs.setString(8, "1114");
            cs.setString(9, bean.getTipo_comprobante());
            cs.setString(10, "0");
            cs.setString(11, "");
            cs.setString(12, "");
            cs.setString(13, bean.getTipo_concepto());
            cs.setString(14, bean.getDes_comprobante());
            cs.registerOutParameter(15, 4);
            cs.executeUpdate();

            vIDECOMPROBANTEOV = Integer.valueOf(cs.getInt(15));

            mapa.put("idecomprobanteov", vIDECOMPROBANTEOV);

            if (vIDECOMPROBANTEOV.intValue() == 0)
                mapa.put("error", "1");
            else {
                mapa.put("error", "0");
            }

            cs.close();
        }
        catch (SQLException e) {
            LOGGER.error("registerComprobantes\nError Nro.: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    connection.close();
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return mapa;
    }

    public List<modelAcredComple> get_listarGrillaAcreComple(String fecha_ini, String fecha_fin)
    {
        String strErrores = null;
        LinkedHashMap mapa = new LinkedHashMap();
        ConexionDataSource conn = new ConexionDataSource(this.DATASOURCE);
        Connection connection = conn.getConnection();
        ResultSet rs = null;
        List lista = new ArrayList();
        InhabilitadosAsegbean bean = null;
        CallableStatement cs = null;

        List resultado = new ArrayList();

        String sqlStr = "BEGIN " + this.OWNER + "." + "PKG_CONSULTA_REPOR_SEGUROS" + ".SP_VC_GRILLA_ACRE_COMPLE(?,?,?); END;";
        try
        {
            LOGGER.info("SP_SUBSIDIO_PERIODO : SP_SUBSIDIO_PERIODO");
            LOGGER.info(sqlStr);

            cs = connection.prepareCall(sqlStr);

            cs.setString(1, fecha_ini);
            cs.setString(2, fecha_fin);

            cs.registerOutParameter(3, -10);
            cs.executeUpdate();
            rs = (ResultSet)cs.getObject(3);

            if (rs != null) {
                LOGGER.info("entre a get_listarGrillaAcreComple");

                while (rs.next()) {
                    LOGGER.info("entre a get_listarGrillaAcreComple");
                    LOGGER.info("data" + rs.getString(1));
                    LOGGER.info("data2" + rs.getString(2));
                    modelAcredComple perido = new modelAcredComple();

                    perido.setNum_acred(Various.stringNull(rs.getString(1)));
                    perido.setCod_tipacred(Various.stringNull(rs.getString(2)));
                    lista.add(perido);
                    LOGGER.info("La clase" + perido.toString());
                }

            }

        }
        catch (SQLException e)
        {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            strErrores = e.toString();
            LOGGER.error(strErrores);
            lista = null;
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                    conn.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("SQLExceptionFinally: " + e.getErrorCode() + "\nMensaje:" + e.getMessage() + "\n");
            }
        }

        return lista;
    }
}