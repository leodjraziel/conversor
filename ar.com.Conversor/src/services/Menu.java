package services;

import javax.swing.JOptionPane;

/**
 * Esta clase representa un menú de selección que permite al usuario elegir
 * entre dos opciones: Conversor Moneda y Conversor Temperatura. Una vez
 * seleccionada una opción, redirige al usuario a la clase correspondiente para
 * realizar la conversión deseada.
 */
public class Menu {

    /**
     * Muestra el menú de selección de opciones y permite al usuario realizar
     * conversiones de moneda o temperatura.
     */
    public void Mostar() {

        String[] opciones = {"Conversor Moneda", "Conversor Temperatura"};
        ConversorMoneda converMon = new ConversorMoneda();
        ConversorTemperatura converTemp = new ConversorTemperatura();
        boolean continuar = true;

        while (continuar) {
            String opcionSeleccionada = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción de conversión:",
                    "Menu",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0] // Opción predeterminada
            );

            if (opcionSeleccionada == null) {
                int opcionDefault = JOptionPane.showConfirmDialog(null, "¿Desea continuar?",
                        "Seleccione una opción", JOptionPane.YES_NO_OPTION);

                continuar = (opcionDefault == JOptionPane.YES_OPTION);
            } else {
                switch (opcionSeleccionada) {
                    case "Conversor Moneda":
                        converMon.operar();
                        break;
                    case "Conversor Temperatura":
                        converTemp.operar();
                        break;
                }

            }
        }

        JOptionPane.showMessageDialog(null, "Programa Terminado");
    }
}
