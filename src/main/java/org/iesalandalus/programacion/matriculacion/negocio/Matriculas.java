package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import javax.naming.OperationNotSupportedException;

// Punto 11.1.
public class Matriculas {

    // Atributos
    private int capacidad; // Capacidad máxima para el Array de Matrículas
    private int tamano; // Número actual de elementos del Array de Matrículas
    private Matricula[] matriculas; // Array interno para gestionar Matrículas

    // Métodos GETTER para los atributos
    public int getCapacidad() {
        return capacidad;
    }
    public int getTamano() {
        return tamano;
    }

    // Punto 11.1.i.
    // Constructor con parámetros
    public Matriculas(int capacidad)
            throws IllegalArgumentException
    {
        if (capacidad <= 0){
            throw new IllegalArgumentException
                    ("ERROR: la capacidad no puede ser menor o igual a cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.matriculas = new Matricula[capacidad];
    }

    // Punto 11.1.ii.
    // Método GET que devuelve una copia profunda
    public Matricula[] get(){
        return copiaProfundaMatriculas();
    }

    // Método para realizar copias profundas
    private Matricula[] copiaProfundaMatriculas(){
        Matricula[] copiaMatriculas = new Matricula[tamano];
        for (int i = 0; i < tamano; i++){
            copiaMatriculas[i] = new Matricula(matriculas[i]); // Usamos el constructor copia
        }
        return copiaMatriculas;
    }

    // Apartado 11.1.iii.
    // Método para insertar matrículas
    public void insertar(Matricula matricula)
            throws NullPointerException, OperationNotSupportedException {
        if (matricula == null){
            throw new NullPointerException
                    ("ERROR: no se puede insertar una matrícula nula.");
        }
        if (capacidadSuperada(tamano)){
            throw new OperationNotSupportedException
                    ("ERROR: no se aceptan más matrículas.");
        }
        if (buscar(matricula) != null){
            throw new OperationNotSupportedException
                    ("ERROR: ya existe una matrícula con ese identificador.");
        }
        matriculas[tamano++] = new Matricula(matricula); // Copia profunda
    }

    // Método para saber si se ha superado el tamaño
    private boolean tamanoSuperado(int indice){
        return indice > getTamano();
    }

    // Método para saber si se ha superado la capacidad
    private boolean capacidadSuperada(int indice){
        return indice > getCapacidad();
    }

    // Apartado 11.1.iv.
    // Método para buscar una matrícula
    public Matricula buscar(Matricula matricula){
        if (matricula == null){
            return null;
        }
        int indice = buscarIndice(matricula);
        return (indice == -1) ? null : new Matricula(matriculas[indice]);
    }

    // Método para buscar un índice
    private int buscarIndice(Matricula matricula){
        for (int i = 0; i < tamano; i++){
            if (this.matriculas[i] != null && this.matriculas[i].equals(matricula)) {
                return i; // Si se encuentra al mismo Alumno, devolverá la posición
            }
        }
        return -1;
    }

    // Apartado 11.1.v.
    // Método para borrar una matrícula
    public void borrar(Matricula matricula)
            throws NullPointerException, OperationNotSupportedException{
        if (matricula == null){
            throw new NullPointerException
                    ("ERROR: no se puede borrar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)){
            throw new OperationNotSupportedException
                    ("ERROR: no existe ninguna matrícula como la indicada.");
        }
        desplazarUnaPosicionHaciaLaIzquierda(indice);
        tamano--;
    }

    private void desplazarUnaPosicionHaciaLaIzquierda(int indice){
        for (int i = indice; i < tamano - 1; i++){
            matriculas[i] = matriculas[i+1];
        }
        matriculas[tamano-1] = null; // Limpieza del último elemento
    }

    // Apartado 2.
    // Métodos GET sobrecargados

    // Método GET que devuelve las matrículas de un alumno pasado por parámetro
    public Matricula[] get(Alumno alumno){
        int contador = 0;
        for (int i = 0; i < tamano; i++){
            if (matriculas[i].getAlumno().equals(alumno)){
                contador++;
            }
        }
        Matricula[] resultado = new Matricula[contador];
        contador = 0;
        for (int i = 0; i < tamano; i++){
            if (matriculas[i].getAlumno().equals(alumno)){
                resultado[contador++] = new Matricula(matriculas[i]); // Copia profunda
            }
        }
        return resultado;
    }

    // Método GET que devuelve las matrículas de un curso académico pasado por parámetro
    public Matricula[] get(String cursoAcademico){
        int contador = 0;
        for (int i = 0; i < tamano; i++){
            if (matriculas[i].getCursoAcademico().equals(cursoAcademico)){
                contador++;
            }
        }
        Matricula[] resultado = new Matricula[contador];
        contador = 0;
        for (int i = 0; i < tamano; i++){
            if (matriculas[i].getCursoAcademico().equals(cursoAcademico)){
                resultado[contador++] = new Matricula(matriculas[i]); // Copia profunda
            }
        }
        return resultado;
    }

    // Método GET que devuelve las matrículas de un ciclo formativo pasado por parámetro
    public Matricula[] get(CicloFormativo cicloFormativo){
        int contador = 0;
        for (int i = 0; i < tamano; i++){
            for(int j = 0; j < matriculas[i].getColeccionAsignaturas().length; j++){
                if (matriculas[i].getColeccionAsignaturas()[j].getCicloFormativo().equals(cicloFormativo)){
                    contador++;
                    break;
                }
            }
        }
        Matricula[] resultado = new Matricula[contador];
        contador = 0;
        for (int i = 0; i < tamano; i++){
            for(int j = 0; j < matriculas[i].getColeccionAsignaturas().length; j++){
                if (matriculas[i].getColeccionAsignaturas()[j].getCicloFormativo().equals(cicloFormativo)){
                    resultado[contador++] = new Matricula(matriculas[i]); // Copia profunda
                    break;
                }
            }
        }
        return resultado;
    }
}
