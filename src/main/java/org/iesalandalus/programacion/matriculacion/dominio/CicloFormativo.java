package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

/**
 *
 * @author José Antonio Padilla Ramallo
 * <p>
 * Clase CicloFormativo, capaz de instanciar objetos de su mismo tipo que además contiene como atributo privado una
 * instancia del Enumerado Grad y comprobar que sus atributos miembro sean los adecuados a través de distintas
 * comprobaciones que realizan sus métodos.
 * </p>
 */

// Apartado 4.1.
public class CicloFormativo {

    // Atributos
    public static final int MAXIMO_NUMERO_HORAS = 2000;
    private static final String REGEX_CODIGO = "^\\d{4}";
    private int codigo;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;

    // Apartado 4.2.
    // Método de acceso y modificación

    /**
     *
     * @return el número máximo de horas permitidas por curso en la clase CicloFormativo (2000)
     */
    // Método GET para el máximo de horas
    public int getMAXIMO_NUMERO_HORAS() {
        return MAXIMO_NUMERO_HORAS;
    }

    /**
     *
     * @return el atributo de instancia codigo
     */
    // Método GET para el código
    public int getCodigo() {
        return codigo;
    }
    // Método SET para el código

    /**
     *
     * @param codigo Argumento pasado por el usuario para setear el atributo de instancia codigo
     * @throws IllegalStateException Si el formato del parámetro codigo no coincide con la RegEx establecida.
     */
    private void setCodigo(int codigo)
            throws IllegalArgumentException
    {
        String codigoStr = String.format("%04d", codigo);
        if (!codigoStr.matches(REGEX_CODIGO)){
            throw new IllegalArgumentException
                    ("ERROR: El código del ciclo formativo debe ser un número de 4 dígitos.");
        }
        this.codigo = codigo;
    }

    /**
     *
     * @return una instancia de la clase familiaProfesional
     */
    // Método GET para la familia profesional
    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    /**
     *
     * @param familiaProfesional Argumento de tipo String para establecer la familiaProfesional
     * @throws NullPointerException Si el parámetro es nulo, lanzará esta excepción.
     * @throws IllegalStateException Si el parámetro se encuentra vacío o sólo contiene espacios en blanco.
     */
    // Método SET para la familia profesional
    public void setFamiliaProfesional(String familiaProfesional)
            throws NullPointerException, IllegalStateException
    {
        if (familiaProfesional == null){
            throw new NullPointerException
                    ("ERROR: la familia profesional no puede ser nula.");
        } else if (familiaProfesional.trim().isBlank()){
            throw new IllegalStateException
                    ("ERROR: la familia profesional no puede estar vacía o contener sólo espacios en blanco.");
        }
        this.familiaProfesional = familiaProfesional;
    }

    /**
     *
     * @return devuelve una instancia de tipo Grado
     */
    // Método GET para el grado
    public Grado getGrado() {
        return grado;
    }

    /**
     *
     * @param grado instancia de tipo Grado pasado por el usuario para setear el atributo grado
     * @throws NullPointerException Si el parámetro es nulo.
     */
    // Método SET para el grado
    public void setGrado(Grado grado) throws NullPointerException
    {
        if (grado == null){
            throw new NullPointerException
                    ("ERROR: el grado no puede ser nulo.");
        }
        this.grado = grado;
    }

    /**
     *
     * @return devuelve el valor del atributo de instancia nombre
     */
    // Método GET para el nombre
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre Argumento de tipo String pasado por el usuario para
     *               establecer el valor del atributo de instancia nombre.
     * @throws NullPointerException Si el argumento es nulo.
     * @throws IllegalStateException Si el argumento está vacío o sólo contiene espacios en blanco
     */
    // Método SET para el nombre
    public void setNombre(String nombre) throws NullPointerException, IllegalStateException{
        if (nombre == null){
            throw new NullPointerException("ERROR: el nombre no puede ser nulo.");
        } else if (nombre.isBlank()) {
            throw new IllegalStateException
                    ("ERROR: el nombre no puede estar vacío o contener sólo espacios en blanco.");
        }
        this.nombre = nombre;
    }

    /**
     *
     * @return el valor del atributo horas
     */
    // Método GET para las horas
    public int getHoras() {
        return horas;
    }

    /**
     *
     * @param horas Argumento de tipo int pasado por el usuario para setear el atributo de instancia horas
     * @throws IllegalArgumentException Si el argumento no es menor o igual al número máximo de horas permitido
     * o no es mayor que 0.
     */
    // Método SET para las horas
    public void setHoras(int horas)
            throws IllegalArgumentException
    {
        if (horas > 0 && horas <= MAXIMO_NUMERO_HORAS){
            this.horas = horas;
        } else {
            throw new IllegalArgumentException
                    ("ERROR: el número de horas no puede ser menor o igual a 0 ni superar el máximo permitido (2000).");
        }
    }

    /**
     *
     * @param codigo Parámetro de tipo int pasado por el usuario al constructor y setear el atributo de instancia
     *               codigo.
     * @param familiaProfesional Parámetro tipo String pasado por el usuario al constructor y setear el atributo de
     *                           instancia familiaProfesional.
     * @param grado Parámetro de tipo Grado pasado por el usuario al constructor y setear el atributo de
     *              instancia grado.
     * @param nombre Parámetro de tipo String pasado por el usuario al constructor y setear el atributo de instancia
     *               nombre.
     * @param horas Parámetro de tipo int pasado por el usuario al constructor y setear el atributo de instancia horas
     * @throws NullPointerException Si algún parámetro es nulo.
     * @throws IllegalStateException Si alguno parámetro está vacío o sólo contiene espacios en blanco.
     * @throws IllegalArgumentException Si algún parámetro resulta no válido para setearlo.
     */
    // Apartado 4.3.
    // método Constructor
    public CicloFormativo(int codigo, String familiaProfesional,
                          Grado grado, String nombre, int horas)
            throws
            NullPointerException, IllegalStateException, IllegalArgumentException
            {
                try {
                    setCodigo(codigo);
                    setFamiliaProfesional(familiaProfesional);
                    setGrado(grado);
                    setNombre(nombre);
                    setHoras(horas);
                } catch (NullPointerException | IllegalStateException | IllegalArgumentException ex) {
                    System.out.printf("Se ha producido un error: \n%s", ex.getMessage());
                }
            }

    /**
     *
     * @param otroCicloFormativo Parámetro pasado por el usuario para poder construir una instancia que sea una copia
     *                           de la pasada como argumento.
     */
    // Apartado 4.4.
    // método Constructor copia
    public CicloFormativo(CicloFormativo otroCicloFormativo)
            throws NullPointerException
    {
        if (otroCicloFormativo == null){
            throw new NullPointerException("ERROR: No es posible copiar un ciclo formativo nulo.");
        }
        this.codigo = otroCicloFormativo.getCodigo();
        this.familiaProfesional = otroCicloFormativo.getFamiliaProfesional();
        this.grado = otroCicloFormativo.getGrado();
        this.nombre = otroCicloFormativo.getNombre();
        this.horas = otroCicloFormativo.getHoras();
    }

    /**
     *
     * @param o Parámetro pasado por el usuario de tipo Object que servirá para poder hacer la comparación.
     * @return Verdadero sí y sólo si los dos objetos resultan ser iguales comparando sus respectivos atributos codigo,
     * falso en cualquier otro caso.
     */
    // Apartado 4.5.
    // Métodos equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloFormativo that = (CicloFormativo) o;
        return codigo == that.codigo;
    }

    /**
     *
     * @return El código hash correspondiente al atributo de instancia codigo
     */
    // Método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /**
     *
     * @return La cadena que esperan los test.
     */
    // Apartado 4.6.
    // Método imprimir
    public String imprimir(){
        return String.format("Código del Ciclo Formativo: %d, %nNombre del Ciclo Formativo: %s",
                codigo,
                nombre);
    }

    /**
     *
     * @return La representación textual de una instancia de la clase CicloFormativo.
     */
    // Apartado 4.6.
    // Método toString
    @Override
    public String toString() {
        return String.format("CicloFormativo{" +
                "%nCodigo= %d\n" +
                "Familia Profesional= %s\n" +
                "Grado= %s\n" +
                "Nombre= %s\n" +
                "Horas= %d\n" +
                '}',
                codigo,
                familiaProfesional,
                grado,
                nombre,
                horas);
    }

}
