package gob.pe.essalud.apireporseg.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Util {

    public String cambioNombrePDF(String docTit,String nrRuc,Integer ind){
        String newNombre ="";
        String TipoProc = "AC";
        String ext = ".pdf";

        Calendar fecha = new GregorianCalendar();
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        String horaFormt = "_"+String.format("%02d%02d%02d",
                hora, minuto, segundo);

        newNombre = docTit + TipoProc +ind.toString()+ nrRuc + horaFormt+ext;

        return newNombre;
    }

}
