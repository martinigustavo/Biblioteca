/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gustavo
 */
public class Data {

    private static final Locale myLocale = new Locale("pt", "BR");
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy", myLocale);

    public static Date dataAtual() {
        Date novaData = null;

        try {
            novaData = SDF.parse(dateToString(new Date()));
        } catch (ParseException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }

        return novaData;
    }

    public static Date stringToDate(String data) {
        Date novaData = null;

        try {
            novaData = SDF.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }

        return novaData;
    }

    public static String dateToString(Date data) {
        String novaData = SDF.format(data);

        return novaData;
    }

    public static long compareDates(Date dataMaisAntiga, Date dataMaisRecente) {
        long diaDiff = ChronoUnit.DAYS.between(dataMaisAntiga.toInstant(), dataMaisRecente.toInstant());

        return diaDiff;
    }

    public static Date somarDia(Date data, int numeroDeDias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, numeroDeDias);
        data = cal.getTime();

        return data;
    }

    public static Date somarMes(Date data, int numeroDeMeses) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MONTH, numeroDeMeses);
        data = cal.getTime();

        return data;
    }

    public static Date somarAno(Date data, int numeroDeAnos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.YEAR, numeroDeAnos);
        data = cal.getTime();

        return data;
    }

    public static String changeFormat(String date) throws ParseException {
        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "yyyy/MM/dd";

// August 12, 2010
        String oldDateString = date;
        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDateString);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        
        return newDateString;
    }

    public static void main(String[] args) {
        System.out.println("Data atual: " + dataAtual());
        System.out.println("Data stringToDate: " + stringToDate("10/12/1993"));
        System.out.println("Data dateToString: " + dateToString(dataAtual()));
        System.out.println("Data somar dias: " + dateToString(somarDia(dataAtual(), 7)));
        System.out.println("Data somar mes: " + dateToString(somarMes(dataAtual(), 7)));
        System.out.println("Data somar ano: " + dateToString(somarAno(dataAtual(), 7)));

        System.out.println("");
        System.out.println("Days between: " + compareDates(stringToDate("01/01/2020"), stringToDate("01/01/2021")));
    }
}
