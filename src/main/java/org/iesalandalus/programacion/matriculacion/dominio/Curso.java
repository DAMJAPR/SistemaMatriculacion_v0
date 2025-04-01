package org.iesalandalus.programacion.matriculacion.dominio;

// Apartado 2.1.
public enum Curso {

    PRIMERO  ("Primero"),
    SEGUNDO ("Segundo");

    // Apartado 2.2.
    private final String cadenaAMostrar;

    // Apartado 2.3.
    // Constructor que no se especifica como private ya que se consiera redundante
    Curso(String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Apartado 2.4.
    // Método imprimir
    // (0.-Primero, 1.-Segundo)
    public String imprimir(){
        return String.format("%d. -%s", this.ordinal(), this.cadenaAMostrar);
    }

    // Apartado 2.5.
    // Método toString()
    @Override
    public String toString() {
        return cadenaAMostrar ;
    }
}
