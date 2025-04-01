package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author José Antonio Padilla Ramallo
 * <p>
 * Clase Alumno, capaz de instanciar objetos de su mismo tipo y comprobar que sus atributos miembro
 * sean los adecuados a través de distintas comprobaciones que realizan sus métodos.
 * </p>
 */

// Apartado 1.1.
// Clase Alumno creada en el paquete indicado en el diagrama (dominio)
public class Alumno {

    // Apartado 1.2.
    // Atributos con visibilidad private

    // RegEx para comprobar la validez del atributo de instancia telefono
    private static final String ER_TELEFONO = "^\\d{9}$";

    // RegEX para comprobar la validez del atributo de instancia correo
    private static final String ER_CORREO = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";

    // RegEx para comprobar la validez del atributo de instancia dni
    private static final String ER_DNI = "^(\\d{8})([A-Z])$";

    // RegEx para comprobar la validez del formato del atributo de instancia fechaNacimiento
    public static final String FORMATO_FECHA = "dd/MM/yyyy";

    // RegEx para comprobar la validez del atributo de instancia nia
    private static final String ER_NIA = "^[a-z]{4}[0-9]{3}$";

    // Apartado 1.7.
    // Atributo de clase que establece la edad mínima que un alumno debe tener para que pueda matricularse
    private static final int MIN_EDAD_ALUMNO = 16;

    // Atibutos
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;

    private LocalDate fechaNacimiento;
    private String nia;

    // Atributo de clase para poder comprobar las letras válidas de un DNI español
    private static final String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";

    /**
     *
     * @param nombre Argumento pasado por el usuario de tipo String para que sea formateado.
     * @return El argumento pasado por parámetro con el formato adecuado, es decir, sin espacios al princpio ni al
     * final, con sus primeras letras en mayúsculas y el resto en minúsculas.
     * @throws NullPointerException Si se introduce un nombre nulo.
     * @throws IllegalArgumentException Si se introduce un nombre vacío o sólo con espacios en blanco.
     */
    // Apartado 1.3.
    // Método para normalizar nombres
    private String formateaNombre(String nombre)
            throws NullPointerException, IllegalArgumentException
    {
        // Comprobamos si el nombre es correcto
        if (nombre == null) {
            throw new NullPointerException
                    ("ERROR: el nombre es nulo");
        } else if (nombre.isBlank()){
            throw new IllegalArgumentException
                    ("ERROR: el nombre no puede estar vacía o contener sólo espacios en blanco.");
        }

        // Dividimos la cadena en un array de tipo String de palabras utilizando un espacio como delimitador.
        String[] palabras = nombre.split(" ");

        // Creamos un objeto StringBuilder para construir el nombre formateado
        StringBuilder nombreFormateado = new StringBuilder();

        // Recorremos cada palabra del array de palabras
        for (String palabra : palabras) {
            // Comprobamos si la palabra no está vacía
            if (!palabra.isBlank()) {
                // Añadimos la primera letra en mayúscula al StringBuilder
                nombreFormateado.append(palabra.substring(0, 1).toUpperCase())
                        // Añade el resto de la palabra en minúsculas al StringBuilder
                        .append(palabra.substring(1).toLowerCase())
                        // Añade un espacio después de la palabra formateada
                        .append(" ");
            }
        }
        // Convierte el StringBuilder en un String y elimina los espacios al principio y al final
        return nombreFormateado.toString().trim();
    }

    /**
     *
     * @param dni Parámetro pasado por el usuario de tipo String para comprobar su validez.
     * @return Verdadero sí y sólo sí la letra del DNI es la esperada después de la respectiva comrpobación,
     * devuelve false en cualquier otro caso.
     * @throws NullPointerException Si el DNI pasado como parámetro es nulo.
     * @throws IllegalArgumentException Si el DNI pasado como parámetro está vacío o sólo contiene espacios en blanco.
     */
    // Apartado 1.4.
    // Método para comprobar la validez del DNI
    private boolean comprobarLetraDni (String dni)
            throws NullPointerException, IllegalArgumentException
    {
        if (dni == null){
            throw new NullPointerException
                    ("ERROR: el nombre no puede ser nulo.");
        } else if (dni.isBlank()){
            throw new IllegalArgumentException
                    ("ERROR: el nombre no puede estar vacío o contener sólo espacios en blanco.");
        }
        // Compilamos la expresión regular
        Pattern pattern = Pattern.compile(ER_DNI);
        // Aplicamos la expresión regular al DNI proporcionado
        Matcher matcher = pattern.matcher(dni);

        // Comprobamos si coincide con el formato esperado
        if (matcher.matches()) {
            // Grupo 1: Números del DNI
            String numeros = matcher.group(1);
            // Grupo 2: Letra del DNI
            String letra = matcher.group(2).toUpperCase();

            int numeroDni = Integer.parseInt(numeros);
            char letraEsperada = letrasDni.charAt(numeroDni % 23);
            // Comparamos la letra proporcionada con la esperada
            return letra.charAt(0) == letraEsperada;
        }
        return false;
    }

    // Apartado 1.5
    // Métodos de acceso y modificación para cada atributo

    /**
     *
     * @return Devuelve el atributo nombre de la instancia.
     */
    // Método GET para el nombre
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre Argumento pasado por el usuario de tipo String para establecer el atributo nombre de la instancia.
     *               Hechas las comprobaciones pertinentes, formateará el nombre y lo seteara.
     * @throws NullPointerException Si el nombre pasado como parámetro es nulo.
     * @throws IllegalArgumentException Si el nombre pasado como parámetro está vacío o sólo contiene espacios en blanco.
     */
    // Método SET para el nombre
    public void setNombre(String nombre)
            throws NullPointerException, IllegalArgumentException
    {
        if (nombre == null) {
            throw new NullPointerException
                    ("ERROR: El nombre no puede ser nulo.");
        }
        if (nombre.isBlank()){
            throw new IllegalArgumentException
                    ("ERROR: el nombre no puede estar vacío o contener sólo espacios.");
        }

        this.nombre = formateaNombre(nombre);
    }

    /**
     *
     * @return Devuelve el NIA de la instancia.
     */
    // Método GET para el NIA
    public String getNia() {
        return nia;
    }
    // Método SET para el NIA
    private void setNia() {
        String iniciales = getIniciales();
        String ultimosDigitosDNI = getDni().substring(5,8);
        nia = iniciales+ultimosDigitosDNI;
    }

    /**
     *
     * @param nia Argumento pasado por el usuario de tipo String que representa el Número de Identificación del Alumno.
     * @throws NullPointerException Si el argumento es nulo.
     * @throws IllegalArgumentException Si el argumento está vacío, sólo contiene espacios en blanco o no coincide con
     * la REGEX correspondiente.
     */
    private void setNia(String nia)
            throws NullPointerException, IllegalArgumentException{
        if (nia == null ){
            throw new NullPointerException
                    ("ERROR: el NIA no puede ser nulo.");
        }
        if (nia.isBlank()){
            throw new IllegalArgumentException
                    ("ERROR: el NIA puede estar vacío o contener sólo espacios.");
        }
        if (nia.matches(ER_NIA)){
            this.nia = nia;
        } else {
            throw new IllegalArgumentException
                    ("ERROR: el formato del NIA no es válido.");
        }

    }

    /**
     *
     * @return Devuelve el DNI de la instancia.
     */
    // Método GET para el DNI
    public String getDni() {
        return dni;
    }

    /**
     *
     * @param dni Argumento pasado por el usuario para setear el atributo de instancia DNI.
     * @throws NullPointerException Si el argumento es nulo.
     * @throws IllegalArgumentException Si el argumento está vacío, sólo contiene espacios en blanco, si no coincide
     * con la REGEX correspondiente o si su letra no es la correcta.
     */
    // Método SET para el DNI
    public void setDni(String dni)
            throws NullPointerException,
            IllegalArgumentException {

        if (dni == null){
            throw new NullPointerException
                    ("ERROR: el nombre DNI no puede ser nulo.");
        }
        if (dni.isBlank()){
            throw new IllegalArgumentException
                    ("ERROR: el DNI no puede estar vacío o contener sólo espacios en blanco.");
        }
        if (!dni.matches(ER_DNI)){
            throw new IllegalArgumentException
                    ("ERROR: el formato del DNI no es válido.");
        }
        if(!comprobarLetraDni(dni)){
            throw new IllegalArgumentException
                    ("ERROR: la letra del DNI no es válida.");
        }
        this.dni = dni;
    }

    /**
     *
     * @return Devuelve el atributo de instancia telefono.
     */
    // Método GET para el teléfono
    public String getTelefono() {
        return telefono;
    }
    // Método SET para el teléfono

    /**
     *
     * @param telefono Argumento pasado por el usuario para setear el atributo de instancia telefono.
     * @throws NullPointerException Si el argumento es nulo.
     * @throws IllegalArgumentException Si el argumento está vacío, sólo contiene espacios en blanco o no coincide con
     * la REGEX correspondiente.
     */
    public void setTelefono(String telefono) throws NullPointerException, IllegalArgumentException{
        if(telefono == null){
            throw new NullPointerException
                    ("ERROR: el teléfono no puede ser nulo.");
        }
        if (telefono.isBlank()){
            throw new IllegalArgumentException
                    ("ERROR: el teléfono no puede estar vacío o contener sólo espacios en blanco.");
        }
        if(!telefono.matches(ER_TELEFONO)){
            throw new IllegalArgumentException
                    ("ERROR: el teléfono del alumno no tiene un formato adecuado.");
        }
        this.telefono = telefono;
    }

    /**
     *
     * @return Devuelve el atributo de instancia correo
     */
    // Método GET para el correo
    public String getCorreo() {
        return correo;
    }
    // Método SET para el correo

    /**
     *
     * @param correo Argumento pasado por el usuario para setear el atributo de instancia correo
     * @throws NullPointerException Si el argumento es nulo.
     * @throws IllegalArgumentException Si el argumento está vacío, sólo contiene espacios en blanco o no coincide con
     * la REGEX correspondiente.
     */
    public void setCorreo(String correo) throws NullPointerException, IllegalArgumentException{
        if (correo == null){
            throw new NullPointerException
                    ("ERROR: el correo no puede ser nulo.");
        }
        if (correo.isBlank()){
            throw new IllegalArgumentException
                    ("ERROR: el correo no puede estar vacío o contener sólo espoacios en blanco.");
        }
        if (!correo.matches(ER_CORREO)){
            throw new IllegalArgumentException
                    ("ERROR: el correo del alumno no tiene un formato válido.");
        }
        this.correo = correo;
    }

    /**
     *
     * @return Devuelve el atributo de instancia fechaNacimiento
     */
    // Método GET para la fecha de nacimiento
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *
     * @param fechaNacimiento Parámetro de tipo LocalDate pasado por el usuario para establecer el atributo de instancia
     *                        fechaNacimiento
     * @throws NullPointerException Si el argumento es nulo.
     * @throws IllegalArgumentException Si se comprueba que la edad del alumno es menor a la permitida.
     */
    // Método SET para la fecha de nacimiento
    public void setFechaNacimiento(LocalDate fechaNacimiento) throws NullPointerException, IllegalArgumentException{
        if (fechaNacimiento == null){
            throw new NullPointerException
                    ("ERROR: la fecha de nacimiento no puede ser nula.");
        }

        if (Period.between(fechaNacimiento, LocalDate.now()).getYears() < MIN_EDAD_ALUMNO){
            throw new IllegalArgumentException
                    ("ERROR: La edad del alumno debe ser mayor o igual a 16 años.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     *
     * @return Las iniciales del nombre del alumno.
     * @throws NullPointerException Si el nombre es nulo.
     * @throws IllegalArgumentException Si el nombre está o sólo contiene espacios en blanco.
     */
    // Apartado 1.6.
    // Método para obtener las iniciales del nombre del alumno
    private String getIniciales()
            throws NullPointerException, IllegalArgumentException{
        nombre = getNombre();
        if (nombre == null){
            throw new NullPointerException
                    ("ERROR: No se puede obtener las iniciales de un nombre nulo.");
        } else if (nombre.isBlank()){
            throw new IllegalArgumentException
                    ("ERROR: No se puede obtener las iniciales de un nombre vacío o " +
                            "que contiene sólo espacios en blanco.");
        }
        String [] palabras = nombre.split(" ");
        StringBuilder iniciales = new StringBuilder();

        for (String palabra : palabras){
            iniciales.append(palabra.substring(0, 1).toLowerCase());
        }
        return iniciales.toString();
    }

    /**
     * Constructor por defecto
     */
    // Apartado 1.8.
    public Alumno(){

    }

    /**
     *
     * @param nombre Argumento pasado por el usuario para establecer el atributo de instancia nombre.
     * @param dni Argumento pasado por el usuario para establecer el atributo de instancia DNI.
     * @param correo Argumento pasado por el usuario para establecer el atributo de instancia correo.
     * @param telefono Argumento pasado por el usuario para establecer el atributo de instancia telefono.
     * @param fechaNacimiento Argumento pasado por el usuario para establecer el atributo de instancia fecha de
     *                        nacimiento.
     */
    // Constructor con parámetros
    public Alumno (String nombre, String dni,
                   String correo, String telefono,
                   LocalDate fechaNacimiento)
            throws NullPointerException,
            IllegalArgumentException
    {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    /**
     *
     * @param otroAlumno Instancia de Alumno pasado por parámetro para clonar sus atributos.
     * @throws NullPointerException Si la referencia instancia pasado por parámetro es nula.
     */
    // Apartado 1.9.
    // Constructor copia
    public Alumno (Alumno otroAlumno)
            throws NullPointerException
    {
        if (otroAlumno == null){
            throw new NullPointerException
                    ("ERROR: El alumno no puede ser nulo.");
        }
        this.nombre = otroAlumno.getNombre();
        this.dni = otroAlumno.getDni();
        this.correo = otroAlumno.getCorreo();
        this.telefono = otroAlumno.getTelefono();
        this.fechaNacimiento = otroAlumno.getFechaNacimiento();
    }

    /**
     *
     * @param o Instancia de la clase Object pasada por parámetro que servirá para compararla con otra instancia de
     *          Alumno y saber si son iguales.
     * @return Verdadero sí y solo sí los dos objetos comparados son los mismos teniendo en cuenta sus DNI´s, devolverá
     * falso en cualquier otro caso.
     */
    // Apartado 1.10.
    // Métodos equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(dni, alumno.dni);
    }

    /**
     *
     * @return Devuelve el código hash correspondiente al atributo DNI del alumno
     */
    // Métodos hashCode
    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }

    // Apartado 1.11.
    // Método Imprimir
    public String imprimir(){
        return String.format("NIA: %s, %nNombre: %s, %nDNI: %s, " +
                "%nCorreo: %s, %nTeléfono: %s, " + "%nFecha de Nacimiento: %s",
                getNia(), getNombre(), getDni(),
                getCorreo(), getTelefono(), getFechaNacimiento());
    }

    // Apartado 1.12.
    // Método toString
    @Override
    public String toString() {
        return String.format("""
                        Alumno { Nombre: %s
                        Teléfono: %s
                        Correo: %s
                        DNI: %s
                        Fecha de Nacimiento: %s
                        NIA: %s }""",
                this.nombre,
                this.telefono,
                this.correo,
                this.dni,
                this.fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)),
                this.nia
        );
    }

}
