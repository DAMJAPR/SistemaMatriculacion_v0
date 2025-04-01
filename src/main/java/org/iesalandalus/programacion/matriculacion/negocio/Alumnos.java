package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

// Apartado 8.1.
public class Alumnos {

    // Atributos
    private final int capacidad; // Capacidad máxima de alumnos permitidos
    private int tamano; // Número actual de alumnos registrados

    private final Alumno[] alumnos ; // Array interno para gestionar los alumnos

    // Apartado 8.1.i.
    // Constructor con parámetros
    public Alumnos(int capacidad)
            throws IllegalArgumentException
    {
        if (capacidad <= 0){
            throw new IllegalArgumentException
                    ("ERROR: la capacidad no puede ser menor o igual a cero.");
        } else {
            this.capacidad = capacidad;
            tamano = 0;
            alumnos = new Alumno[capacidad];
        }
    }

    // Apartado 8.1.ii.
    // método GET para el array de tipo Alumno
    public Alumno[] get(){
        return copiaProfundaAlumnos();
    }

    // método para la Copia Profunda de Alumnos
    private Alumno[] copiaProfundaAlumnos(){
        // Creamos un nuevo array con el mismo tamaño que el original
        Alumno[] copiaAlumnos = new Alumno[tamano];

        // Recorremos el array original y copiamos cada objeto
        for (int i = 0; i < tamano; i++){
            // Copia profunda usando el constructor copia de Alumno
            copiaAlumnos[i] = new Alumno(alumnos[i]);
        }
        return copiaAlumnos;
    }

    // Métodos GETTER para el tamaño y la capacidad
    public int getTamano() {
        return tamano;
    }
    public int getCapacidad() {
        return capacidad;
    }

    // Apartado 8.1.iii.
    // Método para insertar alumnos
    public void insertarAlumno (Alumno alumno)
            throws NullPointerException, OperationNotSupportedException {
        if (alumno == null){
            throw new NullPointerException
                    ("ERROR: no se pueden insertar alumnos nulos.");
        }
        if (capacidadSuperada(tamano)){
            throw new OperationNotSupportedException
                    ("ERROR: no se aceptan más alumnos.");
        }
        if (buscar(alumno) != null){
            throw new OperationNotSupportedException
                    ("ERROR: ya existe un Alumno con ese dni.");
        }
        alumnos[tamano++] = new Alumno(alumno); // Añade el Alumno al final y copia profunda
       }

    // Apartado 8.1.iv.
    // Método para buscar un alumno
    public Alumno buscar(Alumno alumno){
        int indice = buscarIndice(alumno);
        // Devuelve una copia del Alumno encontrado o null en caso contrario
        return (indice != -1) ? new Alumno(alumnos[indice]) : null;
    }

    // Método para buscar el índce de alumnos
    private int buscarIndice(Alumno alumno) {
        for (int i = 0; i < alumnos.length; i++) {
            if (this.alumnos[i] != null && this.alumnos[i].equals(alumno)) {
                return i; // Si se encuentra al mismo Alumno, devolverá la posición
            }
        }
        return -1; // Si no se encuentra el Alumno devolverá -1
    }

    // Apartado 8.1.v.
    // Método para borrar alumnos
    public void borrar(Alumno alumno)
            throws NullPointerException, OperationNotSupportedException{
        if (alumno == null){
            throw new NullPointerException
                    ("ERROR: el alumno es nulo.");
        }

        int indice = buscarIndice(alumno);

        if (tamanoSuperado(indice)){
            throw new OperationNotSupportedException
                    ("ERROR: no existe ningún alumno como el indicado.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    // Método para desplazar una posición hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        for (int i = indice; i < tamano - 1; i++){
            alumnos[i] = alumnos[i+1]; // Desplaza elementos hacia la izquierda
        }
        alumnos[tamano-1] = null; // Elimina el último elemento duplicado
    }

    // Método para saber si se ha superado el tamaño
    private boolean tamanoSuperado(int indice){
        return indice >= tamano;
    }

    // Método para saber si se ha superado la capacidad
    private boolean capacidadSuperada(int indice){
        return indice >= getCapacidad();
    }
}

