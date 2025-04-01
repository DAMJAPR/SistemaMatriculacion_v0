package org.iesalandalus.programacion.matriculacion.negocio;


import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;

// Apartado 10.1.
public class Asignaturas {

    // Atributos
    private final int capacidad; // Capacidad máxima para el array de Asignaturas
    private int tamano; // Número actual de elementos del array de Asignaturas
    private final Asignatura[] asignaturas; // Array interno para gestionar las asignaturas

    // Métodos GET para los atributos
    public int getTamano() {
        return tamano;
    }
    public int getCapacidad() {
        return capacidad;
    }

    // Apartado 10.1.i.
    // Constructor con parámetros
    public Asignaturas (int capacidad)
            throws IllegalArgumentException
    {
        if(capacidad <= 0){
            throw new IllegalArgumentException
                    ("ERROR: la capacidad no puede ser menor o igual a cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.asignaturas = new Asignatura[capacidad];
    }

    // Método para hacer una copia profunda del array de asignaturas
    private Asignatura[] copiaProfundaAsignaturas(){
        // Creamos un nuevo array con la misma longitud que el original
        Asignatura[] copiaAsignaturas = new Asignatura[tamano];

        // Recorremos el array original y copiamos cada objeto
        for (int i = 0; i < tamano - 1; i++){
            copiaAsignaturas[i] = new Asignatura(asignaturas[i]); // Usamos el constructor copia
        }
        return copiaAsignaturas;
    }

    // Apartado 10.1.ii.
    // Método GET que devuelve una copia profunda del array
    public Asignatura[] get(){
        return copiaProfundaAsignaturas();
    }

    // Apartado 10.1.iii.
    // Método para insertar asignaturas
    public void insertar(Asignatura asignatura)
            throws NullPointerException, OperationNotSupportedException {
        if (asignatura == null){
            throw new NullPointerException
                    ("ERROR: la asignatura introducida es nula.");
        }
        if (capacidadSuperada(tamano)){
            throw new OperationNotSupportedException
                    ("ERROR: no se aceptan más asignaturas");
        }
        if (buscar(asignatura) != null){
            throw new OperationNotSupportedException
                    ("ERROR: ya existe una asignatura con ese código.");
        }
        asignaturas[tamano++] = new Asignatura(asignatura); // Insertamos con copia profunda
    }

    // Método para saber si se ha superado el tamaño
    private boolean tamanoSuperado(int indice){
        return indice > getTamano();
    }

    // Método para saber si se ha superado la capacidad
    private boolean capacidadSuperada(int indice){
        return indice > getCapacidad();
    }

    // Apartado 10.1.iv.
    // Método para buscar asignaturas
    public Asignatura buscar(Asignatura asignatura){
        int indice = buscarIndice(asignatura);
        if (indice == -1){
            return null;
        }
        return new Asignatura(asignaturas[indice]); // Devolvemos una copia
    }

    // Método para buscar índices de Asignatura
    private int buscarIndice(Asignatura asignatura){
        for(int i = 0; i < tamano; i++){
            if (this.asignaturas[i] != null && this.asignaturas[i].equals(asignatura)) {
                return i; // Si se encuentra al mismo Alumno, devolverá la posición
            }
        }
        return -1;
    }

    // Apartado 10.1.v.
    // Método para borrar asignaturas
    public void borrar(Asignatura asignatura)
            throws NullPointerException, OperationNotSupportedException {
        if (asignatura == null){
            throw new NullPointerException
                    ("ERROR: no se puede borrar una asignatura nula.");
        }

        int indice = buscarIndice(asignatura);

        if (tamanoSuperado(indice)){
            throw new OperationNotSupportedException
                    ("ERROR: no existe ninguna asignatura como la indicada.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    // Método para desplazar elementos hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i = indice; i < tamano - 1 ; i++){
            asignaturas[i] = asignaturas[i+1];
        }
        asignaturas[tamano - 1] = null; // Eliminamos la última referencia
    }
}
