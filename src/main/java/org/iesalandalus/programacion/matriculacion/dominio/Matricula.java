package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

// Apartado 7.1.
public class Matricula {

    // Constantes
    public final int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public final int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public final int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    private static final String ER_CURSO_ACADEMICO = "^(\\d-){2}(\\d-){2}$";
    public final String FORMATO_FECHA = "dd/MM/yyyy";

    // Atributos
    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Alumno alumno;
    private Asignatura[] coleccionAsignaturas;

    // Apartado 7.2.
    // Métodos de acceso y modificación de los atributos

    // Método GET para el ID de cada matrícula
    public int getIdMatricula() {
        return idMatricula;
    }
    // Método SET para el ID de cada matrícula
    public void setIdMatricula(int idMatricula) {
        if (idMatricula <= 0){
            throw new IllegalArgumentException
                    ("ERROR: el ID de la matrícula debe ser un número positivo.");
        } else {
            this.idMatricula = idMatricula;
        }
    }

    // Método GET para el curso académico
    public String getCursoAcademico() {
        return cursoAcademico;
    }
    // Método SET para el curso académico
    public void setCursoAcademico(String cursoAcademico) {
        if (cursoAcademico == null){
            throw new
                    NullPointerException
                        ("ERROR: el curso académico de una matrícula no puede ser nulo.");
        }
        if (!cursoAcademico.matches(getER_CURSO_ACADEMICO())){
            throw new
                    IllegalArgumentException
                        ("ERROR: el formato del curso académico no es correcto.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    // Método GET para la fecha de matriculación
    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    // Método SET para la fecha de matriculación
    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        if (fechaMatriculacion == null){
            throw new NullPointerException
                    ("ERROR: la fecha de matriculación no puede ser nula.");
        }
        if (fechaMatriculacion.isAfter(LocalDate.now())){
            throw new IllegalArgumentException
                    ("ERROR: la fecha de matriculación no puede ser posterior a hoy.");
        }
        if (fechaMatriculacion.isBefore(LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA))){
            throw new IllegalArgumentException
                    ("ERROR: La fecha de matriculación no puede ser anterior a " +
                            getMAXIMO_DIAS_ANTERIOR_MATRICULA() + " días.");

        }
        this.fechaMatriculacion = fechaMatriculacion;

    }

    // Método GET para la fecha de anulación de matrícula
    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    // Método SET para la fecha de anulación de matrícula
    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion == null){
            this.fechaMatriculacion = null;
            return;
        }
        if (fechaAnulacion.isAfter(LocalDate.now())){
            throw new IllegalArgumentException
                    ("ERROR: la fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }
        if (fechaAnulacion.isBefore(fechaMatriculacion)){
            throw new IllegalArgumentException
                    ("ERROR: la fecha de anulación de una matriculación no puede ser anterior a la fecha de matriculación");
        }
        if (fechaAnulacion.isBefore(fechaMatriculacion.minusMonths(MAXIMO_MESES_ANTERIOR_ANULACION))){
            throw new IllegalArgumentException
                    ("ERROR: la fecha de anulación no puede ser anterior a " +
                            getMAXIMO_MESES_ANTERIORES_ANULACION() + " meses.");
        }
        this.fechaAnulacion = fechaAnulacion;
    }

    // Método GET para el máximo de meses anteriores a la anulación de la matrícula permitidos
    public int getMAXIMO_MESES_ANTERIORES_ANULACION() {
        return MAXIMO_MESES_ANTERIOR_ANULACION;
    }

    // Método GET para el máximo de días anteriores al registro de la matrícula permitidos
    public int getMAXIMO_DIAS_ANTERIOR_MATRICULA() {
        return MAXIMO_DIAS_ANTERIOR_MATRICULA;
    }

    // Método GET para el máximo de horas por matrícula permitidos
    public int getMAXIMO_NUMERO_HORAS_MATRICULA() {
        return MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    // Método GET para el máximo de asignaturas por matrícula permitidos
    public int getMAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA() {
        return MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA;
    }

    // Método GET para la regex del curso académico
    public String getER_CURSO_ACADEMICO() {
        return ER_CURSO_ACADEMICO;
    }

    // Método GET para la regex del formate de fechas
    public String getFORMATO_FECHA() {
        return FORMATO_FECHA;
    }

    // Método GET para el alumno
    public Alumno getAlumno(){
        return alumno;
    }
    // Método SET para el alumno
    public void setAlumno(Alumno alumno){
        if (alumno == null){
            throw new NullPointerException
                    ("ERROR: el alumno no puede ser nulo");
        }
        this.alumno = alumno;
    }

    // Método GET para la colección de asignaturas
    public Asignatura[] getColeccionAsignaturas() {
        return Arrays.copyOf(coleccionAsignaturas, coleccionAsignaturas.length);
    }

    // Método SET para la colección de asignaturas
    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas){
        if (coleccionAsignaturas == null) {
            throw new NullPointerException
                    ("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        }
        if (coleccionAsignaturas.length > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            throw new IllegalArgumentException
                    ("ERROR: No se pueden registrar más de " +
                            MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA + " asignaturas.");
        }
        if (superaMaximoNumeroHorasMatricula(coleccionAsignaturas)) {
            throw new IllegalArgumentException
                    ("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas (" +
                            MAXIMO_NUMERO_HORAS_MATRICULA + " horas).");
        }
        this.coleccionAsignaturas = Arrays.copyOf(coleccionAsignaturas, coleccionAsignaturas.length);
    }

    // Apartado 7.3.
    // Método para chequear el número de horas por matrícula
    private boolean superaMaximoNumeroHorasMatricula(Asignatura[] asignaturasMatricula){
        int totalHoras = 0;
        for (Asignatura asignatura : asignaturasMatricula){
            if (asignatura != null){
                totalHoras += asignatura.getHorasAnuales();
            }
        }
        return totalHoras > MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    // Apartado 7.4.
    // Método Constructor con parámetros
    public Matricula (int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion,
                      Alumno alumno, Asignatura[] coleccionAsignaturas){
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);
    }

    // Apartado 7.5.
    // Método Constructor Copia
    public Matricula(Matricula otraMatricula){
        if(otraMatricula == null){
            throw new NullPointerException("ERROR: no es posible copiar una matrícula nula.");
        }
        this.idMatricula = otraMatricula.getIdMatricula();
        this.cursoAcademico = otraMatricula.getCursoAcademico();
        this.fechaMatriculacion = otraMatricula.getFechaMatriculacion();
        this.fechaAnulacion = otraMatricula.getFechaAnulacion();
        this.alumno = new Alumno(otraMatricula.alumno);
        this.coleccionAsignaturas = Arrays.copyOf(otraMatricula.coleccionAsignaturas, otraMatricula.coleccionAsignaturas.length);
    }

    // Apartado 7.6.
    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return idMatricula == matricula.idMatricula;
    }

    // Método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(idMatricula);
    }

    // En este punto aparece un método privado llamado asignaturasMatricula() pero que
    // no se especifica su codificación en el archivo README.md
    private String asignaturasMatricula(){
        return Arrays.toString(getColeccionAsignaturas());
    }

    // Apartado 7.7.
    // Método imprimir
    public String imprimir (){
        return String.format("ID Matrícula= %d, Curso Académico= %s, Fecha Matriculación= %s, Alumno= {%s}",
                idMatricula,
                cursoAcademico,
                fechaMatriculacion.format(DateTimeFormatter.ofPattern(getFORMATO_FECHA())),
                alumno.imprimir());
    }

    // Apartado 7.8.
    // Método toString()
    @Override
    public String toString() {
        return
                "Matricula{" +
                "ID Matricula=" + idMatricula +
                ", Curso Academico='" + cursoAcademico + '\'' +
                ", Fecha Matriculacion=" + fechaMatriculacion +
                ", Fecha Anulacion=" + fechaAnulacion +
                ", Alumno=" + alumno +
                ", Colección Asignaturas=" + Arrays.toString(getColeccionAsignaturas()) +
                '}';
    }
}
