package org.iesalandalus.programacion.matriculacion.dominio;

// Apartado 3.1.
public enum Grado {

    GDCFGB ("Grado de Ciclo Formativo de Grado Básico"),
    GDCFGM ("Grado de Ciclo Formativo de Grado Medio"),
    GDCFGS ("Grado de Ciclo Formativo de Grado Superior");

    // Apartado 3.2.
    // atributo
    private final String cadenaAMostrar;

    // Apartado 3.3.
    // Constructor
    Grado(String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Apartado 3.4.
    // Método imprimir
    public String imprimir(){
        return String.format("%d. -%s", this.ordinal(), this.cadenaAMostrar);
    }

    // Apartado 3.5.
    // Método toString
    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}
