package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author José Antonio Padilla Ramallo
 * <p>
 * Clase Asignatura, capaz de instanciar objetos de su mismo tipo que además contiene como atributos privados una
 * instancia de la clase CicloFormativo y de los Enumerados Curso, EspecialdiadProfesorado, tambien puede comprobar
 * que sus atributos miembro sean los adecuados a través de distintas comprobaciones que realizan sus métodos.
 * </p>
 */
// Apartado 6.1.
public class Asignatura {

    // Atributos
    public final int MAX_NUM_HORAS_ANUALES = 300;
    public final int MAX_NUM_HORAS_DESDOBLES = 6;
    private static final String ER_CODIGO = "[A-Za-z0-9]{4}";
    // Objeto de tipo Pattern para almacenar la regex del código
    Pattern pattern_Codigo = Pattern.compile(ER_CODIGO);
    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Curso curso;
    private int horasDesdoble;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;

    // Apartado 6.2.
    // Métodos de acceso y modificación

    /**
     *
     * @return el atributo instancia cicloFormativo
     */
    // Método GET para el ciclo formativo
    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }
    // Método SET para el ciclo formativo
    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null){
            throw new NullPointerException
                    ("ERROR: el ciclo formativo no puede ser nulo.");
        }
        this.cicloFormativo = cicloFormativo;
    }

    // Método GET para el máximo de horas anuales
    public int getMAX_NUM_HORAS_ANUALES() {
        return MAX_NUM_HORAS_ANUALES;
    }
    // Método GET para el máximo de horas de desdobles
    public int getMAX_NUM_HORAS_DESDOBLES() {
        return MAX_NUM_HORAS_DESDOBLES;
    }

    // Método GET para el código
    public String getCodigo() {
        return codigo;
    }
    // Método SET para el código
    public void setCodigo(String codigo) {
        if (codigo == null){
            throw new NullPointerException
                    ("ERROR: el código no puede ser nulo.");
        } else if (codigo.isBlank()){
            throw new IllegalArgumentException
                    ("ERROR: el código no puede estar vacío o contener sólo espacios en blanco.");
        }
        Matcher matcher = pattern_Codigo.matcher(codigo);
        if (matcher.matches()){
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException
                    ("ERROR: el código debe tener exactamente cuatro dígitos.");
        }
    }

    // Método GET para el nombre
    public String getNombre() {
        return nombre;
    }
    // Método SET para el nombre
    public void setNombre(String nombre) {
        if (nombre == null){
            throw new NullPointerException
                    ("ERROR: el nombre no puede ser nulo.");
        } else if (nombre.isBlank()){
            throw new IllegalArgumentException
                    ("ERROR: el nombre no puede estar vacío ni contener sólo espacios en blanco.");
        }
        this.nombre = nombre;
    }

    // Método GET para las horas anuales
    public int getHorasAnuales() {
        return horasAnuales;
    }
    // Método SET para las horas anuales
    public void setHorasAnuales(int horasAnuales) {
        if (horasAnuales <= 0 || horasAnuales > MAX_NUM_HORAS_ANUALES){
            throw new IllegalArgumentException
                    ("ERROR: el número de horas anuales no puede ser menor o igual a 0 o superar el límite máximo permitido.");
        }
        this.horasAnuales = horasAnuales;
    }

    // Método GET para el curso
    public Curso getCurso() {
        return curso;
    }
    // Método SET para el curso
    public void setCurso(Curso curso) {
        if (curso == null){
            throw new NullPointerException
                    ("ERROR: el curso no puede ser nulo.");
        }
        this.curso = curso;
    }

    // Método GET para las horas de desdoble
    public int getHorasDesdoble() {
        return horasDesdoble;
    }
    // Método SET para las horas de desdoble
    public void setHorasDesdoble(int horasDesdoble) {
        if (horasDesdoble > MAX_NUM_HORAS_DESDOBLES || horasDesdoble < 0){
            throw new IllegalArgumentException
                    ("ERROR: las horas de desdoble no pueden ser negativas o superiores a " + getHorasDesdoble());
        }
        this.horasDesdoble = horasDesdoble;
    }

    // Método GET para la especialidad del profesorado
    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }
    // Método SET para la especialidad del profesorado
    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        if (especialidadProfesorado == null){
            throw new NullPointerException
                    ("ERROR: la especialidad del profesor no puede ser nula.");
        }
        this.especialidadProfesorado = especialidadProfesorado;
    }

    // Apartado 6.3.
    // Constructor por defecto
    public Asignatura(){

    }
    // Método Constructor con parámetros
    public Asignatura (String codigo, String nombre, int horasAnuales, Curso curso, int horasDesdoble,
                       EspecialidadProfesorado espcialidadProfesorado, CicloFormativo cicloFormativo){
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        setCurso(curso);
        setHorasDesdoble(horasDesdoble);
        setEspecialidadProfesorado(espcialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    // Apartado 6.4
    // Método Constructor copia
    public Asignatura (Asignatura otraAsignatura){
        if (otraAsignatura == null){
            throw new NullPointerException
                    ("ERROR: no se puede copiar una asignatura nula.");
        }
        this.codigo = otraAsignatura.getCodigo();
        this.nombre = otraAsignatura.getNombre();
        this.horasAnuales = otraAsignatura.getHorasAnuales();
        this.curso = otraAsignatura.getCurso();
        this.horasDesdoble = otraAsignatura.getHorasDesdoble();
        this.especialidadProfesorado = otraAsignatura.getEspecialidadProfesorado();
        this.cicloFormativo = otraAsignatura.getCicloFormativo();
    }

    // Apartado 6.5.
    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return Objects.equals(codigo, that.codigo);
    }
    // Método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    // Apartado 6.6.
    // Método imprimir
    public String imprimir (){
        return String.format("Código de Asignatura: %s, %nNombre de Asignatura: %s, %nCiclo Formativo: %s",
                codigo, nombre, cicloFormativo);
    }

    // Apartado 6.7.
    // Método toString()
    @Override
    public String toString() {
        return String.format("Código: %s, %nNombre: %s, %nHoras Anuales: %s, %nCurso: %s," +
                "%nHoras Desdoble: %d, %nCiclo Formativo: %s, %nEspecialidad Profesorado: %s",
                codigo, nombre, horasAnuales, curso, horasDesdoble, cicloFormativo, especialidadProfesorado);
    }
}
