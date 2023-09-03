package services;

import javax.swing.JOptionPane;

/**
 * Esta clase representa un conversor de temperatura que permite convertir
 * entre diferentes escalas de temperatura, incluyendo Celsius, Fahrenheit,
 * Kelvin y Rankine.
 */
public class ConversorTemperatura {

    /**
     * Inicia el conversor de temperatura y permite al usuario realizar una conversión
     * de temperatura.
     */
    public void operar() {
        String entrada = JOptionPane.showInputDialog(null, "Ingrese la temperatura a convertir:", "Conversor de Temperatura", JOptionPane.PLAIN_MESSAGE);

        if (entrada == null) {
            return; // Si el usuario cancela, se termina el programa.
        }

        double temperatura = validarEntrada(entrada);
        if (Double.isNaN(temperatura)) {
            JOptionPane.showMessageDialog(null, "Valor no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        EscalaTemperatura escalaEntrada = seleccionarEscala("Seleccione la escala de temperatura de entrada:");
        EscalaTemperatura escalaSalida = seleccionarEscala("Seleccione la escala de temperatura de salida:");

        if (escalaEntrada == null || escalaSalida == null) {
            return; // Si el usuario cancela la selección de escalas, se termina el programa.
        }

        double resultado = convertirTemperatura(temperatura, escalaEntrada, escalaSalida);

        JOptionPane.showMessageDialog(null, "Resultado de la conversión: " + resultado + " " + escalaSalida.getNombre(), "Conversión de Temperatura", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Valida la entrada del usuario y devuelve un valor numérico.
     *
     * @param entrada La entrada del usuario como una cadena.
     * @return El valor numérico o NaN si la entrada no es válida.
     */
    private double validarEntrada(String entrada) {
        try {
            return Double.parseDouble(entrada);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    /**
     * Muestra un cuadro de diálogo para que el usuario seleccione una escala de temperatura.
     *
     * @param mensaje El mensaje a mostrar al usuario.
     * @return La escala de temperatura seleccionada por el usuario.
     */
    private EscalaTemperatura seleccionarEscala(String mensaje) {
        EscalaTemperatura[] escalas = EscalaTemperatura.values();
        String[] nombresEscalas = new String[escalas.length];
        for (int i = 0; i < escalas.length; i++) {
            nombresEscalas[i] = escalas[i].getNombre();
        }

        String escalaSeleccionada = (String) JOptionPane.showInputDialog(
            null, mensaje, "Conversor de Temperatura", JOptionPane.PLAIN_MESSAGE, null, nombresEscalas, nombresEscalas[0]
        );

        if (escalaSeleccionada != null) {
            for (EscalaTemperatura escala : escalas) {
                if (escala.getNombre().equals(escalaSeleccionada)) {
                    return escala;
                }
            }
        }

        return null;
    }

    /**
     * Convierte una temperatura de una escala de entrada a una escala de salida.
     *
     * @param temperatura     La temperatura a convertir.
     * @param escalaEntrada   La escala de temperatura de entrada.
     * @param escalaSalida    La escala de temperatura de salida.
     * @return La temperatura convertida en la escala de salida.
     */
    private double convertirTemperatura(double temperatura, EscalaTemperatura escalaEntrada, EscalaTemperatura escalaSalida) {
        return escalaEntrada.convertirA(temperatura, escalaSalida);
    }

    /**
     * Enumeración que representa las escalas de temperatura disponibles.
     */
    public enum EscalaTemperatura {
        CELSIUS("Celsius"),
        FAHRENHEIT("Fahrenheit"),
        KELVIN("Kelvin"),
        RANKINE("Rankine");

        private final String nombre;

        EscalaTemperatura(String nombre) {
            this.nombre = nombre;
        }

        /**
         * Obtiene el nombre de la escala de temperatura.
         *
         * @return El nombre de la escala.
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Convierte una temperatura desde esta escala a otra escala especificada.
         *
         * @param temperatura    La temperatura a convertir.
         * @param escalaSalida   La escala de temperatura de salida.
         * @return La temperatura convertida en la escala de salida.
         */
        public double convertirA(double temperatura, EscalaTemperatura escalaSalida) {
            switch (this) {
                case CELSIUS:
                    return escalaSalida.convertirDesdeCelsius(temperatura);
                case FAHRENHEIT:
                    return escalaSalida.convertirDesdeFahrenheit(temperatura);
                case KELVIN:
                    return escalaSalida.convertirDesdeKelvin(temperatura);
                case RANKINE:
                    return escalaSalida.convertirDesdeRankine(temperatura);
                default:
                    throw new UnsupportedOperationException("Conversión no soportada");
            }
        }

        private double convertirDesdeCelsius(double temperatura) {
            switch (this) {
                case FAHRENHEIT:
                    return (temperatura * 9 / 5) + 32;
                case KELVIN:
                    return temperatura + 273.15;
                case RANKINE:
                    return (temperatura * 9 / 5) + 491.67;
                default:
                    return temperatura;
            }
        }

        private double convertirDesdeFahrenheit(double temperatura) {
            switch (this) {
                case CELSIUS:
                    return (temperatura - 32) * 5 / 9;
                case KELVIN:
                    return ((temperatura - 32) * 5 / 9) + 273.15;
                case RANKINE:
                    return temperatura + 459.67;
                default:
                    return temperatura;
            }
        }

        private double convertirDesdeKelvin(double temperatura) {
            switch (this) {
                case CELSIUS:
                    return temperatura - 273.15;
                case FAHRENHEIT:
                    return ((temperatura - 273.15) * 9 / 5) + 32;
                case RANKINE:
                    return temperatura * 9 / 5;
                default:
                    return temperatura;
            }
        }

        private double convertirDesdeRankine(double temperatura) {
            switch (this) {
                case CELSIUS:
                    return ((temperatura - 491.67) * 5 / 9);
                case FAHRENHEIT:
                    return temperatura - 459.67;
                case KELVIN:
                    return temperatura * 5 / 9;
                default:
                    return temperatura;
            }
        }
    }
}
