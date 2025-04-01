package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;

// Apartado 9.1.
public class CiclosFormativos {

    // Atributos
    private final int capacidad; // capacidad máxima permitida
    private int tamano; // número actual de Ciclos Formativos
    private final CicloFormativo[] ciclosFormativos; // Array interno para gestionar los ciclos formativos

    // Apartado 9.1.i.
    // Constructor con parámetros
    public CiclosFormativos(int capacidad)
            throws IllegalArgumentException
    {
        if (capacidad <= 0){
            throw new IllegalArgumentException
                    ("ERROR: la capacidad no puede ser menor o igual a cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.ciclosFormativos = new CicloFormativo[capacidad]; // inicializa el array
    }

    // Apartado 9.1.ii.
    // Método GET para devolver una copia profunda del array
    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    // método para la Copia Profunda de Alumnos
    private CicloFormativo[] copiaProfundaCiclosFormativos(){
        // Creamos un nuevo array con la misma longitud que el original
        CicloFormativo[] copiaCiclos = new CicloFormativo[tamano];

        // Recorremos el array original y copiamos cada objeto
        for (int i = 0; i < tamano; i++){
            copiaCiclos[i] = new CicloFormativo(ciclosFormativos[i]);
        }
        return copiaCiclos;
    }

    // Métodos GETTER para el tamaño y la capacidad
    public int getTamano() {
        return tamano;
    }
    public int getCapacidad() {
        return capacidad;
    }

    // Apartado 9.1.iii.
    // Método para insertar ciclos formativos
    public void insertarCiclo (CicloFormativo cicloFormativo)
            throws NullPointerException, OperationNotSupportedException {
        if (cicloFormativo == null){
            throw new NullPointerException
                    ("ERROR: no se pueden insertar ciclos formativos nulos.");
        }
        if (capacidadSuperada(tamano)){
            throw new OperationNotSupportedException
                    ("ERROR: no se aceptan más ciclos formativos.");
        }
        if (buscar(cicloFormativo) != null){
            throw new OperationNotSupportedException
                    ("ERROR: ya existe un ciclo formativo con ese código.");
        }
        ciclosFormativos[tamano++] = new CicloFormativo(cicloFormativo); // Inserta al final y copia profunda
    }

    // Apartado 9.1.iv.
    // Método para buscar un ciclo formativo con un índice
    public CicloFormativo buscar(CicloFormativo cicloFormativo){
        int indice = buscarIndice(cicloFormativo);
        // Si encuentra el ciclo formativo devuelve una copia, en caso contrario devuelve null
        return (indice != -1) ? new CicloFormativo(ciclosFormativos[indice]) : null;
    }

    // Método para buscar el índice de un ciclo formativo
    private int buscarIndice(CicloFormativo cicloFormativo) {
        for (int i = 0; i < tamano; i++) {
            if (this.ciclosFormativos[i] != null && this.ciclosFormativos[i].equals(cicloFormativo)) {
                return i; // Si se encuentra al mismo Alumno, devolverá la posición
            }
        }
        return -1; // Si no se encuentra el Alumno devolverá -1
    }

    //  Apartado 9.1.v.
    // Método para borrar un ciclo formativo
    public void borrar(CicloFormativo cicloFormativo)
            throws NullPointerException, IllegalArgumentException {
        if (cicloFormativo == null){
            throw new NullPointerException
                    ("ERROR: no se puede borrar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)){
            throw new IllegalArgumentException
                    ("ERROR: no existe ningín ciclo formativo como el indicado.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    // Método para desplazar una posición hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        for (int i = indice; i < tamano - 1; i++){
            ciclosFormativos[indice] = ciclosFormativos[indice+1]; // Desplaza elementos a la izquierda
        }
        ciclosFormativos[tamano-1] = null; // Elimina el último elemento duplicado
    }

    // Método para saber si se ha superado el tamaño
    private boolean tamanoSuperado(int indice){
        return indice > getTamano();
    }

    // Método para saber si se ha superado la capacidad
    private boolean capacidadSuperada(int indice){
        return indice > getCapacidad();
    }
}
