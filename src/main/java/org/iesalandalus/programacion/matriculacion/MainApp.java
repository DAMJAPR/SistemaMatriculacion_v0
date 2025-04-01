package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.*;
import org.iesalandalus.programacion.matriculacion.vista.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;

// Apartado 14.1.

// Apartado 14.1.xxii.
// Método Main
public class MainApp {

    // Apartado 14.1.i.
    // Atributos con la visibilidad adecuada
    public static final int CAPACIDAD = 3;
    private static final Alumnos alumnos = new Alumnos(CAPACIDAD);
    private static final CiclosFormativos ciclosFormativos = new CiclosFormativos(CAPACIDAD);
    private static final Asignaturas asignaturas = new Asignaturas(CAPACIDAD);
    private static final Matriculas matriculas = new Matriculas(CAPACIDAD);

    public static void main(String[] args){

        Opcion opcionSeleccionada = null; // Variable para almacenar la opción elegida

        do {
            try {
                Consola.mostrarMenu();
                opcionSeleccionada = Consola.elegirOpcion();
                ejecutarOpcion(opcionSeleccionada);
            } catch (Exception e) {
                System.out.println("ERROR: Excepción capturada.\n" + e.getMessage());
            }
        } while (opcionSeleccionada != Opcion.SALIR);

    }

    // Apartado 14.1.ii.
    // Método para ejecutar una opción
    private static void ejecutarOpcion(Opcion opcion){
        try {
            switch (opcion) {
                case SALIR -> System.out.println("|| ¡Gracias por utilizar el Sistema de Matriculación! ||\n" +
                        "||-- ...Saliendo de la aplicación... --||");
                case INSERTAR_ALUMNO -> insertarAlumno();
                case BUSCAR_ALUMNO -> buscarAlumno();
                case BORRAR_ALUMNO -> borrarAlumno();
                case MOSTRAR_ALUMNOS -> mostrarAlumnos();
                case INSERTAR_CICLO_FORMATIVO -> insertarCicloFormativo();
                case BUSCAR_CICLO_FORMATIVO -> buscarCicloFormativo();
                case BORRAR_CICLO_FORMATIVO -> borrarCicloFormativo();
                case MOSTRAR_CICLOS_FORMATIVOS -> mostrarCiclosFormativos();
                case INSERTAR_ASIGNATURA -> insertarAsignatura();
                case BUSCAR_ASIGNATURA -> buscarAsignatura();
                case BORRAR_ASIGNATURA -> borrarAsignatura();
                case MOSTRAR_ASIGNATURAS -> mostrarAsignaturas();
                case INSERTAR_MATRICULA -> insertarMatricula();
                case BUSCAR_MATRICULA -> buscarMatricula();
                case ANULAR_MATRICULA -> anularMatricula();
                case MOSTRAR_MATRICULAS -> mostrarMatriculas();
                case MOSTRAR_MATRICULAS_ALUMNO -> mostrarMatriculasPorAlumno();
                case MOSTRAR_MATRICULAS_CICLO_FORMATIVO -> mostrarMatriculasPorCicloFormativo();
                case MOSTRAR_MATRICULAS_CURSO_ACADEMICO -> mostrarMatriculasPorCursoAcademico();
            }
        } catch (IllegalArgumentException | OperationNotSupportedException e){
            System.out.println("ERROR: excepción capturada. " + e.getMessage());
        }
    }

    // Apartado 14.1.iii.
    // Método para insertar un Alumno
    private static void insertarAlumno(){
        boolean insercionCorrecta = false;
        while (!insercionCorrecta) {
            try {
                System.out.println("Introduce los datos del Alumno a insertar: ");
                Alumno a = Consola.leerAlumno();
                alumnos.insertarAlumno(a);
                insercionCorrecta = true;
            } catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
                System.out.println("ERROR: No ha sido posible insertar el alumno.\n");
                System.out.println(e.getMessage());
                System.out.println("Inténtelo de nuevo o escriba 'salir' para cancelar.");

                // Opción para salir si el usuario no quiere seguir intentando
                System.out.print("¿Desea volver a intentarlo? (Sí/No): ");
                String respuesta = "";
                while (respuesta == null || respuesta.isBlank()) {
                    System.out.print("¿Desea volver a intentarlo? (Sí/No): ");
                    respuesta = Entrada.cadena();
                }
                if (respuesta.equals("no") || respuesta.equals("salir")) {
                    System.out.println("Inserción cancelada.");
                    return; // Sale del método sin volver a intentar
                }
            } catch (Exception e) {
                System.out.println("ERROR: se ha capturado una excepción.\n" + e.getMessage());
                return;
            }
            System.out.println("\nAlumno insertado correctamente.");
        }
    }

    // Apartado 14.1.iv.
    // Método para buscar un alumno
    private static void buscarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno encontrado = alumnos.buscar(alumno);
            if (encontrado != null) {
                System.out.println("Alumno encontrado: " + encontrado);
            } else {
                System.out.println("Alumno no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    // Apartado 14.1.v.
    // Método para eliminar un alumno
    private static void borrarAlumno() throws OperationNotSupportedException {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumnos.borrar(alumno);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }

    }

    // Apartado 14.1.vi.
    // Método para mostrar los alumnos almacenados
    private static void mostrarAlumnos(){
        if (alumnos.getTamano() > 0) {
            int contador = 1;
            for (Alumno a : alumnos.get()) {
                System.out.printf("%d.- %s" , contador,a);
                contador++;
            }
        } else {
            System.out.println("No hay alumnos registados.");
        }
    }

    // Apartado 14.1.vii.
    // Método para insertar una asignatura
    private static void insertarAsignatura(){
        try {
            Asignatura asignatura = Consola.leerAsignatura(ciclosFormativos);
            asignaturas.insertar(asignatura);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    // Apartado 14.1.viii.
    // Método para buscar una asignatura
    private static void buscarAsignatura(){
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignatura encontrada = asignaturas.buscar(asignatura);
            if (encontrada != null) {
                System.out.println("Asignatura encontrada: " + asignatura);
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: Excepción capturada.\n" + e.getMessage());
        }
    }

    // Apartado 14.1.vix.
    // Método para borrar una asignatura
    private static void borrarAsignatura(){
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignaturas.borrar(asignatura);
        } catch (Exception e) {
            System.out.println("ERROR: se ha capturado una excepción.\n" + e.getMessage());
        }
    }

    // Apartado 14.1.x.
    // Método para mostrar las Asignaturas
    private static void mostrarAsignaturas(){
        if (asignaturas.getTamano() > 0) {
            int contador = 1;
            for (Asignatura a : asignaturas.get()) {
                System.out.printf("%d.- %s" , contador, a);
                contador++;
            }
        } else {
            System.out.println("No hay asignaturas registradas.");
        }
    }

    // Apartado 14.1.xi.
    // Método para insertar un Ciclo Formativo
    private static void insertarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
            ciclosFormativos.insertarCiclo(cicloFormativo);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    // Apartado 14.1.xii.
    // Método para buscar un Ciclo Formativo
    private static void buscarCicloFormativo(){
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            CicloFormativo encontrado = ciclosFormativos.buscar(cicloFormativo);
            if (encontrado != null) {
                System.out.printf("Ciclo Formativo encontrado: %s", encontrado);
            } else {
                System.out.println("Ciclo formativo no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    // Apartado 14.1.xiii.
    // Método para borrar un Ciclo Formativo
    private static void borrarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            ciclosFormativos.borrar(cicloFormativo);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    // Apartado 14.1.xiv.
    // Método para mostrar los Ciclos Formativos
    private static void mostrarCiclosFormativos(){
        if (ciclosFormativos.getTamano() > 0) {
            for (CicloFormativo cicloFormativo : ciclosFormativos.get()) {
                System.out.printf("%s", cicloFormativo);
            }
        } else {
            System.out.println("Ciclo Formativo no encontrado.");
        }
    }

    // Apartado 14.1.xv.
    // Método para insertar una Matrícula
    private static void insertarMatricula() throws OperationNotSupportedException {
        try {
           Matricula matricula = Consola.leerMatricula(alumnos, asignaturas);
           matriculas.insertar(matricula);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }

    }

    // Apartado 14.1.xvi.
    // Método para buscar una Matrícula
    private static void buscarMatricula() throws OperationNotSupportedException {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula encontrada = matriculas.buscar(matricula);
            if (encontrada != null) {
                System.out.printf("Matrícula encontrada: %s", encontrada);
            } else {
                System.out.println("Matrícula no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    // Apartado 14.1.xvii.
    // Método para anular una Matrícula
    private static void anularMatricula() throws OperationNotSupportedException {
        try {
            System.out.println("Mostrando la lista de matrículas: ");
            mostrarMatriculas();

            System.out.println("Por favor, introduce el identificador de la matrícula que desea anular: ");
            Matricula matriculaAAnular = Consola.getMatriculaPorIdentificador();

            matriculaAAnular.setFechaAnulacion
                    (Consola.leerFecha("Introduce la fecha de anulación (dd/MM/yyyy): "));
            matriculas.borrar(matriculaAAnular);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    // Apartado 14.1.xviii.
    // Método para mostrar las Matrículas
    private static void mostrarMatriculas(){
        if (matriculas.getTamano() > 0) {
            for (Matricula matricula : matriculas.get()) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("Aún no hay matriículas registradas.");
        }
    }


    // Apartado 14.1.xvix.
    // Método para mostrar Matrículas por Alumno
    private static void mostrarMatriculasPorAlumno() {
        Alumno alumno = Consola.getAlumnoPorDni();
        Matricula[] resultado = matriculas.get(alumno);
        if (resultado.length > 0) {
            for (Matricula matricula : resultado) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("No hay matrículas registradas para este Alumno.");
        }
    }

    // Apartado 14.1.xx.
    // Método para mostrar Matrículas por Ciclo Formativo
    private static void mostrarMatriculasPorCicloFormativo(){
        CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
        Matricula[] resultado = matriculas.get(cicloFormativo);
        if (resultado.length > 0) {
            for (Matricula matricula : resultado) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("No hay matrículas registradas para este Ciclo Formativo");
        }
    }

    // Apartado 14.1.xxi.
    // Método para mostrar Matrículas por Curso Académico
    private static void mostrarMatriculasPorCursoAcademico(){
        System.out.print("Introduce el curso académico (ej. 23-24): ");
        Curso curso = Consola.leerCurso();
        Matricula[] resultado = matriculas.get(curso.toString());
        if (resultado.length > 0) {
            for (Matricula matricula : resultado) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("No hay matrículas registrada para este Curso Académico.");
        }

    }
}
