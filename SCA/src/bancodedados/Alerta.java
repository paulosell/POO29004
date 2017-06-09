/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;


import java.util.Calendar;


/**
 * Classe que monta os objetos dos alertas
 * @author pfsel
 */
public class Alerta{
    private String aluno;
    private Calendar inicio;
    private String vezes;
    private String nomeAlerta;
    private String tipo;
    private String minutos;
    
    /**
     * Construtor para alertas que não precisam do parametro de minutos
     * @param aluno = aluno que recebe o alerta
     * @param inicio = data que o alerta começa a ser válido
     * @param vezes = número de vezes que determinada situação deve ocorrer
     * @param nomeAlerta = identificação do alerta
     * @param tipo = tipo de alerta a ser gerado
     */
    public Alerta(String aluno, Calendar inicio, String vezes, String nomeAlerta, String tipo) {
        this.aluno = aluno;
        this.tipo=tipo;
        this.inicio = inicio;
        this.vezes = vezes;
        this.nomeAlerta = nomeAlerta;
      
    }
    
    /**
     * Construtor para alertas que precisam do parametro de minutos
     * @param aluno = aluno que recebe o alerta
     * @param inicio = data que o alerta começa a ser válido
     * @param vezes = número de vezes que determinada situação deve ocorrer
     * @param nomeAlerta = identificação do alerta
     * @param tipo = tipo de alerta a ser gerado
     * @param minutos = minutos para os alertas que envolvem horarios
     */
     public Alerta(String aluno, Calendar inicio, String vezes, String nomeAlerta, String tipo, String minutos) {
        this.aluno = aluno;
        this.tipo=tipo;
        this.inicio = inicio;
        this.vezes = vezes;
        this.nomeAlerta = nomeAlerta;
        this.minutos=minutos;
      
    }

    public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

     
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public String getVezes() {
        return vezes;
    }

    public void setVezes(String vezes) {
        this.vezes = vezes;
    }

    public String getNomeAlerta() {
        return nomeAlerta;
    }

    public void setNomeAlerta(String nomeAlerta) {
        this.nomeAlerta = nomeAlerta;
    }
    
    @Override
    public String toString(){
        return(this.inicio.getTime().toString()+","+this.vezes+","+this.tipo
                +","+this.nomeAlerta+","+this.aluno);
    }
    
    
}
