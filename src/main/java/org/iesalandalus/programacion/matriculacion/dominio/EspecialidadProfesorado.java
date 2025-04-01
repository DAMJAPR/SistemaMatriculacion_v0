package org.iesalandalus.programacion.matriculacion.dominio;

/**
 *
 * @author José Antonio Padilla Ramallo
 * <p>
 * Enumerado, capaz de almacenar la Especialidad de cada profesor.
 * </p>
 */

// Apartado 5.1.
public enum EspecialidadProfesorado {

    INFORMATICA ("Informática"),
    SISTEMAS ("Sistemas"),
    FOL ("FOL");


    // Apartado 5.2.
    // atributo
    private final String cadenaAMostrar;

    /**
     *
     * @param cadenaAMostrar Argumento de tipo String para almacenar la especialidad del profesor.
     */
    // Apartado 5.3.
    // Constructor
    EspecialidadProfesorado (String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    /**
     *
     * @return La cadena que esperan los test.
     */
    // Apartado 5.4.
    // Método imprimir
    public String imprimir (){
        return String.format("%d. -%s", this.ordinal(), this.cadenaAMostrar);
    }

    /**
     *
     * @return La representación textual de una instancia del Enumerado
     */
    // Apartado 5.5.
    // Método toString
    @Override
    public String toString() {
        return cadenaAMostrar;
    }

}
