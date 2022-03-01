package gob.pe.essalud.apireporseg.util;

public class Variables {
    private Variables() {
        throw new IllegalStateException("Variables class");
    }

    //JSON Node names Insured
    public static final String TAG_DATA = "data";
    public static final String TAG_ARCHIVE = "archive";
    public static final String TAG_ERROR = "error";
    public static final String TAG_MESSAGE = "message";
    public static final String TAG_20DAYS = "ind20days";
    public static final String TAG_QT_DAYS_FAL20 = "diasfalt20";
    public static final String TAG_20DIASVALIDO = "diasval20";
    public static final String TAG_ANIO_EVALUACION = "anio20dias";

    //JSON Node names getInsuredResult

    public static final String TAG_NR_DOC = "nrdoc";
    public static final String TAG_APE_PATERNO = "appaterno";
    public static final String TAG_APE_MATERNO = "apmaterno";
    public static final String TAG_NOMBRES = "nombres";
    public static final String TAG_ESTCIVIL = "estcivil";
    public static final String TAG_SEXO = "sexo";
    public static final String TAG_FEC_NACIMIENTO = "fecnac";
    public static final String TAG_FEC_FALLECIMIENTO = "fecfall";

    //JSON Node names getTaxpayer
    public static final String TAG_NR_RUC = "nrruc";
    public static final String TAG_RAZON_SOCIAL = "razsocial";
    public static final String TAG_ESTADO = "estado";
    public static final String TAG_CD_CNV = "cdCnv";
    public static final String TAG_TP_DOC = "tpdoc";
    public static final String TAG_USRCR = "usrCr";
    public static final String TAG_CE_ADS = "ceads";
    public static final String TAG_IN_VIG = "invig";
    public static final String TAG_FI_VIG = "fivig";
    public static final String TAG_MESSAGE_ERROR = "Se genero un error en la consulta";
    public static final String TAG_HEAD = "head";
    public static final String TAG_DETAIL = "detail";

    public static final String TAG_SOLICITUD = "codSolicitud";
    public static final String TAG_IDECOMPROBANTEOV = "idecomprobanteov";
    public static final String TAG_COD_ARCHIVE = "codArchivo";

    public static final String TAG_TABLE_TP_DOCUMENTO = "101";
    public static final String TAG_TABLE_TP_SUBSIDIO = "1101";
    public static final String TAG_TPDOC_RUC_SAS = "06";

    public static final String TAG_MSG_ERROR_JSON_INI = "{\"error\":[\"";
    public static final String TAG_MSG_ERROR_JSON_FIN = "\"]}";
    public static final String TAG_VAL_TP_DOC = TAG_MSG_ERROR_JSON_INI + "El parametro Tipo Doc. max 2 caracteres" + TAG_MSG_ERROR_JSON_FIN;
    public static final String TAG_VAL_NR_DOC = TAG_MSG_ERROR_JSON_INI + "El parametro NrDoc. max 15 caracteres" + TAG_MSG_ERROR_JSON_FIN;
    public static final String TAG_VAL_USUARIO = TAG_MSG_ERROR_JSON_INI + "El parametro Usuario. max 30 caracteres" + TAG_MSG_ERROR_JSON_FIN;
    public static final String TAG_VAL_PASSW = TAG_MSG_ERROR_JSON_INI + "El parametro Password. max 100 caracteres" + TAG_MSG_ERROR_JSON_FIN;
    public static final String TAG_VAL_NRRUC = TAG_MSG_ERROR_JSON_INI + "El parametro Ruc. max 11 caracteres" + TAG_MSG_ERROR_JSON_FIN;
    public static final String TAG_MSG_ERROR_JSON = TAG_MSG_ERROR_JSON_INI + "Los parametros recibidos son incorrectos" + TAG_MSG_ERROR_JSON_FIN;

    public static final String TAG_CONSULT = "Consulta";
    public static final String TAG_REMOVE = "Eliminar";
    public static final String TAG_REGISTER = "Registrar";
    public static final String TAG_UPDATE = "Actualizar";

    public static final String TAG_NRRUC = "nrruc";
    public static final String TAG_USER = "user";

    public static final String TAG_SELECCIONADO = "seleccionado";
    public static final String TAG_NRCITT = "nrcitt";
    public static final String TAG_FECINI_CITT = "fecini_citt";
    public static final String TAG_FECFIN_CITT = "fecfin_citt";

    public static final String TAG_SQLEXCEPTION = "SQLExceptionFinally: ";
    public static final String TAG_SQLMESSAGE = "\nMensaje:";
    public static final String TAG_MESSAGE_CITT1 = "CITT No Encontrados";
    public static final String TAG_MESSAGE_SIN_DATA = "No se encuentran registros";

    public static final String TAG_MESSAGE_ASEG_NO_REG = "Asegurado No Registrado";


    public static final String TAG_BEGIN = "BEGIN ";

    /**
     * Campos de BD
     */
    public static final String TAG_TP_PAGO = "tpPago";
    public static final String TAG_NR_DOC2 = "nrDoc";
    public static final String TAG_TP_DOC_RUC = "tpDocRuc";

    public static final String TAG_TPDOC = "TPDOC";
    public static final String TAG_NRDOC = "NRDOC";
    public static final String TAG_APEPATERNO = "APEPATERNO";
    public static final String TAG_APEMATERNO = "APEMATERNO";
    public static final String TAG_NOMBRESBD = "NOMBRES";
    public static final String TAG_ESTCIVILBD = "ESTCIVIL";
    public static final String TAG_FECNACIMIENTOBD = "FECNACIMIENTO";
    public static final String TAG_TPDOCPADREBD = "TPDOCPADRE";
    public static final String TAG_NRDOCPADREBD = "NRDOCPADRE";
    public static final String TAG_NOMPADREBD = "NOMPADRE";
    public static final String TAG_DOCMADREBD = "DOCMADRE";
    public static final String TAG_NRDOCMADREBD = "NRDOCMADRE";
    public static final String TAG_NOMMADREBD = "NOMMADRE";
    public static final String TAG_SEXOBD = "SEXO";
    public static final String TAG_CODVERFBD = "CODVERF";
    public static final String TAG_FEFALLECIDBD = "FEFALLECID";
    public static final String TAG_FECINCRIPCIONBD = "FECINCRIPCION";
    public static final String TAG_FECEXPEDICIONBD = "FECEXPEDICION";

    public static final String TAG_NOMBRE_ARCHIVE = "NOMBREARCHIVO";
    public static final String TAG_URL_ARCHIVE = "URLARCHIVO";
    public static final String TAG_FEC_REGISTRO = "FECREGISTRO";
}
