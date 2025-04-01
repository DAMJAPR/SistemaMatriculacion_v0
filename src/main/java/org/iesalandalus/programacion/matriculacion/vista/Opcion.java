package org.iesalandalus.programacion.matriculacion.vista;

// Apartado 12.
public enum Opcion {

    // Apartado 12.1.
    // Opciones para el Alumno
    SALIR("SALIR"),
    INSERTAR_ALUMNO("INSERTAR ALUMNO"),
    BUSCAR_ALUMNO("BUSCAR ALUMNO"),
    BORRAR_ALUMNO("BORRAR ALUMNO"),
    MOSTRAR_ALUMNOS("MOSTRAR ALUMNOS"),

    // Opciones para el Ciclo Formativo
    INSERTAR_CICLO_FORMATIVO("INSERTAR CICLO FORMATIVO"),
    BUSCAR_CICLO_FORMATIVO("BUSCAR CICLO FORMATIVO"),
    BORRAR_CICLO_FORMATIVO("BORRAR CICLO FORMATIVO"),
    MOSTRAR_CICLOS_FORMATIVOS("MOSTRAR CICLOS FORMATIVOS"),

    // Opciones para la Asignatura
    INSERTAR_ASIGNATURA("INSERTAR ASIGNATURA"),
    BUSCAR_ASIGNATURA("BUSCAR ASIGNATURA"),
    BORRAR_ASIGNATURA("BORRAR ASIGNATURA"),
    MOSTRAR_ASIGNATURAS("MOSTRAR ASIGNATURA"),

    // Opciones para la Matrícula
    INSERTAR_MATRICULA("INSERTAR MATRICULA"),
    BUSCAR_MATRICULA("BUSCAR MATRICULA"),
    ANULAR_MATRICULA("ANULAR MATRICULA"),
    MOSTRAR_MATRICULAS("MOSTRAR MATRICULAS"),

    // Opciones para mostrar las Matrículas
    MOSTRAR_MATRICULAS_ALUMNO("MOSTRAR MATRICULAS ALUMNO"),
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO("MOSTRAR MATRICULAS CICLO FORMATIVO"),
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO("MOSTRAR MATRICULAS CURSO ACADÉMICO");

    private final String cadenaAMostrar;

    Opcion (String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String toString(){
        return String.format("%d.- %s", this.ordinal(), this.cadenaAMostrar);
    }

}
