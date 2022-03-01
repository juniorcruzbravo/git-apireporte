package gob.pe.essalud.apireporseg.util;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class FechaSolicitudUtil {

    private final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
    public Date StringaDate(String fecha) throws ParseException {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date= simpleDateFormat.parse(fecha);
        Date sqlStartDate = new Date(date.getTime());
        return sqlStartDate;
    }
    public String DateString(Date fecha) {
        String date="";
        try {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            date = simpleDateFormat.format(fecha);
        }catch (NullPointerException e){
            date="";
        }
        return date;
    }
    public Long DiasEntreFechas(Date FechaInicio,Date fechaFin){
        Long diasCalculados=(fechaFin.getTime() - FechaInicio.getTime() )/MILLSECS_PER_DAY;
        return diasCalculados;
    }
    public boolean seCruzanFechas(Date FechaInicio,Date FechaFin){
        Long cantidadDias=DiasEntreFechas(FechaInicio,FechaFin);
        if(cantidadDias>0){
            return false;
        }else{
            return true;
        }
    }
    public String FechaInicioMes(){
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        return dia+ "/" + (mes+1) + "/" + anio;
    }

    public String FechaFinMes(){
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int ultimoDiaMes = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
        return ultimoDiaMes+ "/" + (mes+1) + "/" + anio;
    }

    public String FechayHoraActual(){
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        String FechayHora  = dia + "/" + (mes+1) + "/" + año + " a las "+ String.format("%02d:%02d:%02d %n",hora, minuto, segundo);

        return FechayHora;
    }

    public String FechaActual(){
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        String FechayHora  = dia + "/" + (mes+1) + "/" + año;

        return FechayHora;
    }

}
