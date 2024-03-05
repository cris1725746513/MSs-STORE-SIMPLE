package cl.pixysoft.sysreserva.utils;

public class Validadores {

    public static boolean VerificarCedula(String identificacion) {
        boolean estado = false;

        char[] valced = new char[13];
        try {
            int provincia;
            if (identificacion.length() >= 10) {
                valced = identificacion.trim().toCharArray();
                provincia = Integer.parseInt((String.valueOf(valced[0]) + String.valueOf(valced[1])));
                if (provincia > 0 && provincia < 25) {
                    if (Integer.parseInt(String.valueOf(valced[2])) < 6) {
                        estado = VerificaCedula(valced);
                    } else if (Integer.parseInt(String.valueOf(valced[2])) == 6) {
                        estado = VerificaSectorPublico(valced);
                    } else if (Integer.parseInt(String.valueOf(valced[2])) == 9) {
                        estado = VerificaPersonaJuridica(valced);
                    }
                }
            }
        } catch (NumberFormatException e) {
            return estado;
        }
        return estado;
    }

    private static boolean VerificaPersonaJuridica(char[] validarCedula) {
        int aux = 0, prod, veri;
        try {
            veri = Integer.parseInt(String.valueOf(validarCedula[10])) + Integer.parseInt(String.valueOf(validarCedula[11])) + Integer.parseInt(String.valueOf(validarCedula[12]));
            if (veri > 0) {
                int[] coeficiente = new int[]{4, 3, 2, 7, 6, 5, 4, 3, 2};
                for (int i = 0; i < 9; i++) {
                    prod = Integer.parseInt(String.valueOf(validarCedula[i])) * coeficiente[i];
                    aux += prod;
                }
                if (aux % 11 == 0) {
                    veri = 0;
                } else if (aux % 11 == 1) {
                    return false;
                } else {
                    aux = aux % 11;
                    veri = 11 - aux;
                }

                if (veri == Integer.parseInt(String.valueOf(validarCedula[9]))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean VerificaSectorPublico(char[] validarCedula) {
        int aux = 0, prod, veri;
        try {
            veri = Integer.parseInt(String.valueOf(validarCedula[9])) + Integer.parseInt(String.valueOf(validarCedula[10])) + Integer.parseInt(String.valueOf(validarCedula[11])) + Integer.parseInt(String.valueOf(validarCedula[12]));
            if (veri > 0) {
                int[] coeficiente = new int[]{3, 2, 7, 6, 5, 4, 3, 2};

                for (int i = 0; i < 8; i++) {
                    prod = Integer.parseInt(String.valueOf(validarCedula[i])) * coeficiente[i];
                    aux += prod;
                }

                if (aux % 11 == 0) {
                    veri = 0;
                } else if (aux % 11 == 1) {
                    return false;
                } else {
                    aux = aux % 11;
                    veri = 11 - aux;
                }

                if (veri == Integer.parseInt(String.valueOf(validarCedula[8]))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean VerificaCedula(char[] validarCedula) {
        int aux = 0, par = 0, impar = 0, verifi;
        for (int i = 0; i < 9; i += 2) {
            aux = 2 * Integer.parseInt(String.valueOf(validarCedula[i]));
            if (aux > 9) {
                aux -= 9;
            }
            par += aux;
        }
        for (int i = 1; i < 9; i += 2) {
            impar += Integer.parseInt(String.valueOf(validarCedula[i]));
        }

        aux = par + impar;
        if (aux % 10 != 0) {
            verifi = 10 - (aux % 10);
        } else {
            verifi = 0;
        }
        if (verifi == Integer.parseInt(String.valueOf(validarCedula[9]))) {
            return true;
        } else {
            return false;
        }
    }
}
