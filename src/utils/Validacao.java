/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Fabricio Pretto
 */
public class Validacao {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validarCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }
        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean validarCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        }
        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    public static boolean validarDataDMA(int d, int m, int a) {
        boolean correto = true;
        int[] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (a < 0 || m < 1 || m > 12) {
            correto = false;
        } else {
            // valida o dia
            if (a % 4 == 0 && (a % 100 != 0 || a % 400 == 0)) {
                dias[1] = 29;
            }
            if (d < 1 || d > dias[m - 1]) {
                correto = false;
            }
        }
        return (correto);
    }

    public static boolean validarDataFormatada(String dataComFormato) {
        String[] data = dataComFormato.split("/");
        return (validarDataDMA(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    }

//    public static void formatarTelefone(JFormattedTextField campo) {
//        try {
//            MaskFormatter m = new MaskFormatter();
//            m.setPlaceholderCharacter(' ');
//            m.setMask("(##)#####-####");
//            campo.setFormatterFactory(null);
//            campo.setFormatterFactory(new DefaultFormatterFactory(m));
//            campo.setValue(null);
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//    }
//    
//    public static void validarTelefone(JFormattedTextField campo) {
//        if (campo.getText().trim().length() < 15) {
//            formatarTelefone(campo);
//        }
//    }
    public static boolean validarNome(String nome) {
        String regex = "^[\\p{L} .'-]+$";
        return nome.matches(regex);
    }

    public static String removerFormatacao(String dado) {
        String retorno = "";
        for (int i = 0; i < dado.length(); i++) {
            if (dado.charAt(i) != '.' && dado.charAt(i) != '/' && dado.charAt(i) != '-') {
                retorno = retorno + dado.charAt(i);
            }
        }
        return (retorno);
    }

    public static boolean validarISBN(String isbn) {
        if (isISBN(isbn)) {
            if (isISBN10(isbn) || isISBN13(isbn)) {
                return true;
            }
        }

        return false;
    }

    // method to check number is ISBN 
    public static boolean isISBN(String number) {

        // declare variable
        int length = 0;

        // remove all hyphens
        number = number.replace("-", "");
        // remove all spaces
        number = number.replace(" ", "");

        // check result string is a number or not
        try {
            // except for the case where
            // ISBN-10 ends with X or x
            char ch = number.charAt(9);
            ch = Character.toUpperCase(ch);
            if (ch != 'X') {
                // don't store, only check
                Long.parseLong(number);
            }
        } catch (NumberFormatException nfe) {
            // not a number
            return false;
        }

        // find length
        length = number.length();
        if (length == 13) {
            return isISBN13(number);
        } else if (length == 10) {
            return isISBN10(number);
        }

        return false;
    }

    // method to check ISBN-10
    private static boolean isISBN10(String number) {

        // declare variables
        int sum = 0;
        int digit = 0;
        char ch = '\0';

        // add upto 9th digit
        for (int i = 1; i <= 9; i++) {
            ch = number.charAt(i - 1);
            digit = Character.getNumericValue(ch);
            sum += (i * digit);
        }

        // last digit
        ch = number.charAt(9);
        ch = Character.toUpperCase(ch);
        if (ch == 'X') {
            sum += (10 * 10);
        } else {
            digit = Character.getNumericValue(ch);
            sum += (digit * 10);
        }

        // check sum 
        if (sum % 11 == 0) {
            return true;
        }

        return false;
    }

    // method to check ISBN-13
    private static boolean isISBN13(String number) {

        // declare variables
        int sum = 0;
        int multiple = 0;
        char ch = '\0';
        int digit = 0;

        // add digits
        for (int i = 1; i <= 13; i++) {

            if (i % 2 == 0) {
                multiple = 3;
            } else {
                multiple = 1;
            }

            // fetch digit
            ch = number.charAt(i - 1);
            // convert it to number
            digit = Character.getNumericValue(ch);

            // addition
            sum += (multiple * digit);
        }

        // check sum
        if (sum % 10 == 0) {
            return true;
        }
        return false;
    }
}
