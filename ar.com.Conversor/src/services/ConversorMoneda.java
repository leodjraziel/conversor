package services;

import javax.swing.JOptionPane;

/**
 * Esta clase representa un conversor de monedas que permite convertir entre
 * pesos argentinos, dólares, euros, libras, yenes y wones.
 */
public class ConversorMoneda {

    // Tasas de conversión fijas
    final double CAMBIO_PESO_A_DOLAR = 0.00286d;
    final double CAMBIO_PESO_A_EURO = 0.00264d;
    final double CAMBIO_PESO_A_LIBRAS = 0.00226;
    final double CAMBIO_PESO_A_YEN = 0.4163d;
    final double CAMBIO_PESO_A_WON = 3.76642d;

    // Opciones de conversión disponibles
    String[] opciones = {"Pesos a Dólares", "Pesos a Euros", "Pesos a Libras", "Pesos a Yenes", "Pesos a Wones",
        "Dólares a Pesos", "Euros a Pesos", "Libras a Pesos", "Yenes a Pesos", "Wones a Pesos"};

    /**
     * Realiza una operación de conversión de moneda.
     */
    public void operar() {
        double montoIngresado = obtenerMontoIngresado();
        if (montoIngresado <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un valor válido para operar");
            return;
        }

        String opcionSeleccionada = seleccionarOpcionConversion();
        if (opcionSeleccionada == null) {
            return; // Si el usuario cancela la selección, se termina el programa.
        }

        double valorConversion = realizarConversion(montoIngresado, opcionSeleccionada);
        mostrarResultado(valorConversion, opcionSeleccionada);
    }

    /**
     * Obtiene el monto de dinero ingresado por el usuario.
     *
     * @return El monto ingresado o 0 si el usuario cancela la operación.
     */
    private double obtenerMontoIngresado() {
        String entrada = JOptionPane.showInputDialog("Ingresa la cantidad de dinero que deseas convertir");
        double montoIngresado = -1;

        if (entrada == null) {
            return 0; // Si el usuario cancela, se asume un monto de 0.
        }

        try {
            montoIngresado = Double.parseDouble(entrada);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor no válido");
            return 0; 
        }

        return montoIngresado;
    }

    /**
     * Muestra un cuadro de diálogo para que el usuario seleccione una opción de
     * conversión.
     *
     * @return La opción seleccionada o null si el usuario cancela.
     */
    private String seleccionarOpcionConversion() {
        String opcionSeleccionada = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una opción de conversión:",
                "Menu Monedas",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0] // Opción predeterminada
        );

        return opcionSeleccionada;
    }

    /**
     * Realiza la conversión de moneda según la opción seleccionada.
     *
     * @param montoIngresado El monto de dinero ingresado por el usuario.
     * @param opcionSeleccionada La opción de conversión seleccionada.
     * @return El valor convertido.
     */
    private double realizarConversion(double montoIngresado, String opcionSeleccionada) {
        double valorConversion = 0;

        switch (opcionSeleccionada) {
            case "Pesos a Dólares":
                valorConversion = montoIngresado * CAMBIO_PESO_A_DOLAR;
                break;
            case "Pesos a Euros":
                valorConversion = montoIngresado * CAMBIO_PESO_A_EURO;
                break;
            case "Pesos a Libras":
                valorConversion = montoIngresado * CAMBIO_PESO_A_LIBRAS;
                break;
            case "Pesos a Yenes":
                valorConversion = montoIngresado * CAMBIO_PESO_A_YEN;
                break;
            case "Pesos a Wones":
                valorConversion = montoIngresado * CAMBIO_PESO_A_WON;
                break;
            case "Dólares a Pesos":
                valorConversion = montoIngresado / CAMBIO_PESO_A_DOLAR;
                break;
            case "Euros a Pesos":
                valorConversion = montoIngresado / CAMBIO_PESO_A_EURO;
                break;
            case "Libras a Pesos":
                valorConversion = montoIngresado / CAMBIO_PESO_A_LIBRAS;
                break;
            case "Yenes a Pesos":
                valorConversion = montoIngresado / CAMBIO_PESO_A_YEN;
                break;
            case "Wones a Pesos":
                valorConversion = montoIngresado / CAMBIO_PESO_A_WON;
                break;
        }

        return valorConversion;
    }

    /**
     * Muestra el resultado de la conversión al usuario.
     *
     * @param valorConversion El valor convertido.
     * @param opcionSeleccionada La opción de conversión seleccionada.
     */
    private void mostrarResultado(double valorConversion, String opcionSeleccionada) {
        String salidaMoneda = opcionSeleccionada.endsWith("Pesos") ? "Pesos" : opcionSeleccionada;
        JOptionPane.showMessageDialog(null, "Tienes $" + String.format("%.2f", valorConversion)
                .replace(",", ".") + " " + salidaMoneda);
    }
}