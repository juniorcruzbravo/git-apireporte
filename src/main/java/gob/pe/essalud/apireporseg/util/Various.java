package gob.pe.essalud.apireporseg.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;

public class Various {

    private String message = "";

    public static String stringNull(String s) {
        return s == null ? "" : s;
    }

    public String getMessage() {
        return message;
    }

    public boolean checkDateAndTime(String fecha, String hora) {

        boolean d = true;
        boolean t = true;
        message = "";

        if (fecha.length() == 0 && hora.length() == 0) {
            message = "Debe ingresar fecha y/o hora";
            return false;
        }

        if (hora.length() != 0) {
            t = isValidTime(hora);
            if (!t) {
                message = message + "Hora " + hora + " no valida<br/>";
            }
        }
        if (fecha.length() != 0) {
            d = isValidDate(fecha);
            if (!d) {
                message = message + "Fecha " + fecha + " no valida";
            }
        }

        return d && t;

    }

    public boolean isValidTime(String hora) {
        boolean bleReturn = true;
        try {
            int intHour = Integer.parseInt(hora.substring(0, 2));
            int intMin = Integer.parseInt(hora.substring(3));
            if (!(intHour >= 0 && intHour <= 23 && intMin >= 0 && intMin <= 59)) {
                bleReturn = false;
            }
        } catch (NumberFormatException e) {
            bleReturn = false;
        }
        return bleReturn;
    }

    public static boolean isValidDate(String fecha) {

        boolean bleReturn = false;
        try {
            int intDay = Integer.parseInt(fecha.substring(0, 2));
            int intMonth = Integer.parseInt(fecha.substring(3, 5));
            int intYear = Integer.parseInt(fecha.substring(6));

            if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7
                    || intMonth == 8 || intMonth == 10 || intMonth == 12) { // Meses con 31 dias
                bleReturn = (intDay >= 1 && intDay <= 31);
            } else {
                if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) { // Meses con 30 dias
                    bleReturn = (intDay >= 1 && intDay <= 30);
                } else {
                    if (intMonth == 2) {
                        if (isLeapYear(intYear)) {
                            bleReturn = (intDay >= 1 && intDay <= 29);
                        } else {
                            bleReturn = (intDay >= 1 && intDay <= 28);
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            bleReturn = false;
        }

        return bleReturn;
    }

    public static boolean isValidDate2(String fecha) {

        boolean bleReturn = false;
        try {
            int intDay = Integer.parseInt(fecha.substring(8));
            int intMonth = Integer.parseInt(fecha.substring(5, 7));
            int intYear = Integer.parseInt(fecha.substring(0, 4));

            if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7
                    || intMonth == 8 || intMonth == 10 || intMonth == 12) { // Meses con 31 dias
                bleReturn = (intDay >= 1 && intDay <= 31);
            } else {
                if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) { // Meses con 30 dias
                    bleReturn = (intDay >= 1 && intDay <= 30);
                } else {
                    if (intMonth == 2) {
                        if (isLeapYear(intYear)) {
                            bleReturn = (intDay >= 1 && intDay <= 29);
                        } else {
                            bleReturn = (intDay >= 1 && intDay <= 28);
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            bleReturn = false;
        }

        return bleReturn;
    }

    private static boolean isLeapYear(int intYear) {
        boolean bleReturn = true;
        if (((intYear % 4) == 0) && !((intYear % 100) == 0)) {
            bleReturn = true;
        } else {
            bleReturn = ((intYear % 400) == 0);
        }
        return bleReturn;
    }

    public boolean isValidEmail(String email) {
        int n = email.length();
        int at = email.indexOf('@');
        int dot = email.lastIndexOf('.');
        if (at == -1) {
            return false;
        }
        if (dot == -1) {
            return false;
        }
        if (email.indexOf(' ') != -1) {
            return false;
        }
        if (email.indexOf(',') != -1) {
            return false;
        }
        if (email.lastIndexOf('@') != at) {
            return false;
        }
        if (email.charAt(at - 1) == ('.') || email.charAt(at + 1) == ('.')) {
            return false;
        }

        if (email.charAt(n - 1) == '.' || email.charAt(0) == '.'
                || email.charAt(n - 1) == '@' || email.charAt(0) == '@') {
            return false;
        }
        if (dot < at) {
            return false;
        }
        return true;
    }

    public boolean isValidDocument(String DocType, String DocNumber) {
        try {
            HashSet hshType = new HashSet(); // Contiene los distintos tipos de documentos
            hshType.add("DNI");
            hshType.add("CE");
            hshType.add("TIP");
            hshType.add("");
            if (!hshType.contains(DocType)) {
                return false; // Si Tipo Doc. no est� entre los permitidos
            }
            if (DocType.equals("") && !DocNumber.equals("")) {
                return false;
            } else {
                if (DocType.equals("DNI") && DocNumber.length() != 8 && Long.parseLong(DocNumber) > 0) {
                    return false;
                }
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public boolean isDayBetweenNowAndOneMonthLater(String DayMonth) {

        boolean bleReturn;

        Calendar rightNow = Calendar.getInstance();
        int nowYear = rightNow.get(Calendar.YEAR);

        bleReturn = isValidDate(DayMonth + "/" + nowYear);

        if (bleReturn) {
            int nowMonth = rightNow.get(Calendar.MONTH) + 1;
            int nowDay = rightNow.get(Calendar.DAY_OF_MONTH);

            int dia_x = Integer.parseInt(DayMonth.substring(0, 2));
            int mes_x = Integer.parseInt(DayMonth.substring(3, 5));

            rightNow.add(Calendar.MONTH, 1);

            int after30dayMonth = rightNow.get(Calendar.MONTH) + 1;
            int after30dayDay = rightNow.get(Calendar.DAY_OF_MONTH);

            boolean isBetween30 = false;
            if (mes_x == nowMonth || mes_x == after30dayMonth) {
                if (mes_x == nowMonth) {
                    isBetween30 = dia_x >= nowDay;
                } else {
                    isBetween30 = dia_x <= after30dayDay;
                }
            }
            bleReturn = bleReturn && isBetween30;

        }
        return bleReturn;
    }


    /* Obtiene fecha como cadena dado un formato y el numero de dias respecto al dia actual */
    /* --- 1� param. - Formato de Fecha, 2� param. Nro. dias respecto al dia actual ---- */
    public String getDateAsString(String formatStr, int shift) {

        String dateStr;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, shift);
            java.util.Date fecha = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            dateStr = formatter.format(fecha);
        } catch (Exception e) {
            dateStr = "error";
        }
        return dateStr;
    }

}
