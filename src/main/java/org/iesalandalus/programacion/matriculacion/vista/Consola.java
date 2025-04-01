package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author José Antonio Padilla Ramallo
 */

// Apartado 13.1.
public class Consola {

    // Apartado 13.2.
    // Constructor privado
    private Consola()
            throws IllegalArgumentException{
        throw new IllegalArgumentException
                ("ERROR: no se puede instanciar esta clase.");
    }

    // Apartado 13.3.
    // Método para mostrar el menú
    public static void mostrarMenu(){
        System.out.println("\n|| MENÚ DE OPCIONES ||\n");
        for (Opcion o : Opcion.values()){
            System.out.println(o);
        }
    }

    // Apartado 13.4.
    // Método para elegir un opción del menú
    public static Opcion elegirOpcion()
            throws IllegalArgumentException{
        int opcion;
        do {
            System.out.println("Elige una opción (introduce un número entre 0 y 19): ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Opcion.values().length);

        return Opcion.values()[opcion];

    }

    // Apartado 13.5.
    // Método para leer un Alumno
    public static Alumno leerAlumno()
            throws OperationNotSupportedException {
        Alumno alumno = null;
        boolean lecturaCorrecta = false;
        do {
            try {
                System.out.println("Introduce el nombre: ");
                String nombre = Entrada.cadena();

                System.out.println("Introduce el DNI: ");
                String dni = Entrada.cadena();

                System.out.println("Introduce el e-mail: ");
                String correo = Entrada.cadena();

                System.out.println("Introduce el teléfono: ");
                String telefono = Entrada.cadena();

                LocalDate fechaNacimiento = leerFecha("Introduce la fecha de nacimiento("
                        + Alumno.FORMATO_FECHA + "): ");

                alumno = new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
                lecturaCorrecta = true;
            } catch (Exception e) {
                System.out.println("ERROR: se ha capturado una excepción.");
                System.out.println(e.getMessage());
                System.out.println("Inténtelo de nuevo.");
            }
        } while (!lecturaCorrecta);
        return alumno;
    }

    // Apartado 13.6.
    // Método para leer un Alumno por su DNI
    public static Alumno getAlumnoPorDni() {

        System.out.println("Introduce el DNI del Alumno: ");
        String dniAlumno = Entrada.cadena();

        return new Alumno("Ficticio", dniAlumno, "emailFicticio@gmail.com",
                "123456789", LocalDate.now().minusYears(20));
    }

    // Apartado 13.7.
    // Método para leer una fecha
    public static LocalDate leerFecha(String mensaje){

        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            System.out.println(mensaje);
            String fechaString = Entrada.cadena();
            try {
                fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto, inténtalo de nuevo." + e.getMessage());
            }
        }

        return fecha;
    }

    // Apartado 13.8.
    // Método para leer el Grado
    public static Grado leerGrado(){

        System.out.println("Selecciona un Grado: ");
        for (Grado grado : Grado.values()) {
            System.out.println(grado.ordinal() + ".- " + grado);
        }

        int opcion;

        do {
            System.out.println("Elige una opción de las mostradas: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Grado.values().length);

        return Grado.values()[opcion];

    }

    // Apartado 13.9.
    // Método para leer el ciclo formativo
    public static CicloFormativo leerCicloFormativo(){

        System.out.println("Introduce el código del Ciclo Formativo: ");
        int codigo = Entrada.entero();

        System.out.println("Introduce la familia profesional: ");
        String familiaProfesional = Entrada.cadena();

        System.out.println("Introduce el grado: ");
        Grado grado = leerGrado();

        System.out.println("Introduce el nombre: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduce las horas: ");
        int horas = Entrada.entero();

        return new CicloFormativo(codigo, familiaProfesional,grado, nombre, horas);
    }

    // Apartado 13.10.
    // Método para mostrar los Ciclos Formativos registrados en el sistema
    public static void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos){
        if (ciclosFormativos.getTamano() == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            System.out.println("Lista de Ciclos Formativos actuales: ");
            for (CicloFormativo ciclos : ciclosFormativos.get()) {
                System.out.println(ciclos);
            }
        }
    }

    // Apartado 13.11.
    // Método para obtener un Ciclo Formativo por código
    public static CicloFormativo getCicloFormativoPorCodigo()
            throws IllegalArgumentException{

        System.out.println("Introduce el código del Ciclo Formativo: ");
        int codigo = Entrada.entero();

        return new CicloFormativo(codigo, "Familia Ficticia", Grado.GDCFGS,
                "Nombre Ficticio", 2000);
    }

    // Apartado 13.12.
    // Método para leer un Curso
    public static Curso leerCurso(){

        System.out.println("Lista de Cursos existentes: \n");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.ordinal() + ".- " + curso);
        }

        int opcion;
        do {
            System.out.println("Elige una opción: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Curso.values().length);

        return Curso.values()[opcion];
    }

    // Apartado 13.13.
    // Método para leer la especialidad del profesorado
    public static EspecialidadProfesorado leerEspecialidadProfesorado(){

        System.out.println("Elige la especialidad del profesor: ");

        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad.ordinal() + ".- " + especialidad);
        }

        int opcion = Entrada.entero();

        return EspecialidadProfesorado.values()[opcion];

    }

    // Apartado 13.14.
    // Método para leer una asignatura
    public static Asignatura leerAsignatura(CiclosFormativos ciclosFormativos) {
        System.out.println("Introduce el código: ");
        String codigo = Entrada.cadena();

        System.out.println("Introduce el nombre: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduce las horas anuales: ");
        int horasAnuales = Entrada.entero();

        System.out.println("Introduce el Curso: ");
        Curso curso = leerCurso();

        System.out.println("Introduce las horas de desdoble: ");
        int horasDesdoble = Entrada.entero();

        System.out.println("Introduce la especialidad del profesorado: ");
        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();

        CicloFormativo[] listaCiclos = ciclosFormativos.get();

        System.out.println("Mostrando los Ciclos Formativos existentes: ");
        for (int i = 0; i < listaCiclos.length; i++) {
            System.out.println("[" + i+1 +"]: " + listaCiclos[i]);
        }
        System.out.println("Selecciona el número del Ciclo Formativo: ");

        int cicloElegido = Entrada.entero()-1;
        if (cicloElegido < 0 || cicloElegido >= listaCiclos.length) {
            System.out.println("ERROR: selección no válida. No se creó la asignatura");
            return null;
        }

        CicloFormativo cicloFormativo = listaCiclos[cicloElegido];

        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble,
                especialidadProfesorado, cicloFormativo);
    }

    // Apartado 13.15.
    // Método para obtener una asignatura por código
    public static Asignatura getAsignaturaPorCodigo() {

        CicloFormativo cicloFormativo = new CicloFormativo(1234, "Informática",
                Grado.GDCFGS, "Desarrollo de Aplicaciones Web", 2000);

        System.out.println("Introduce el código de la asignatura: ");
        String codigo = Entrada.cadena();

        return new Asignatura(codigo, "Nombre Ficticio", 300, Curso.PRIMERO, 6,
                EspecialidadProfesorado.INFORMATICA, cicloFormativo);
    }

    // Apartado 13.16.
    // Método para mostrar las asignaturas
    private void mostrarAsignaturas(Asignaturas asignaturas){
        if (asignaturas.getTamano() == 0) {
            System.out.println("Aún no hay asignaturas registradas.");
        } else {
            System.out.println("Mostrando la lista actual de asignaturas: ");
            for (Asignatura asignatura : asignaturas.get()) {
                System.out.println(asignatura);
            }
        }
    }

    // Apartado 13.17.
    // Método para mostrar si existe la asignatura
    private boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura){
        for (Asignatura a : asignaturasMatricula){
            if (a != null && a.equals(asignatura)){
                return true;
            }
        }
        return false;
    }

    // Apartado 13.18
    // Método para leer una matrícula
    public static Matricula leerMatricula(Alumnos alumnos, Asignaturas asignaturas)
            throws OperationNotSupportedException {

        System.out.println("Introduce el ID de la matrícula: ");
        int idMatricula = Entrada.entero();

        System.out.println("Introduce el Curso Académico: ");
        String cursoAcademico = Entrada.cadena();

        System.out.println("Introduce la fecha de matriculación: ");
        LocalDate fechaMatriculacion = leerFecha(Entrada.cadena());

        System.out.println("Introduce el Alumno: ");
        Alumno alumno = leerAlumno();

        if (alumnos.buscar(alumno) == null) {
            throw new OperationNotSupportedException
                    ("ERROR: no se encuentra el alumno especificado.");
        }

        int contador = 0;
        for (int i = 0; i < asignaturas.getTamano(); i++) {
            if (asignaturas.get()[i].equals(asignaturas.get()[i+1])) {
                contador++;
            }
        }

        if (contador > 0) {
            throw new IllegalArgumentException
                    ("ERROR: No puede haber dos asignaturas iguales en la misma matrícula.");
        }

        return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion,
                alumno, asignaturas.get());
    }

    public static Matricula getMatriculaPorIdentificador(){

        System.out.println("Introduce el ID de la Matrícula: ");
        int idMatricula = Entrada.entero();

        Alumno alumno = new Alumno("Jose", "12345678N", "correo@gmail.com",
                "123456789", LocalDate.of(1990, 1, 1));

        int numAsignaturas = 2;
        Asignatura[] coleccionAsignaturas = new Asignatura[numAsignaturas];
        coleccionAsignaturas[0] = new Asignatura();
        coleccionAsignaturas[1] = new Asignatura();

        return new Matricula(idMatricula, "23-24", LocalDate.now().minusYears(13),
                alumno, coleccionAsignaturas);
    }

}
