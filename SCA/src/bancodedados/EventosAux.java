/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

/**
 *
 * @author pfsel
 */
public class EventosAux {
    
    private String aluno;
    private String sentido;
    private int hora;
    private int min;
    private int mes;
    private int dia;
    private int ano;

    public EventosAux(String aluno, String sentido, int hora, int min, int mes, int dia, int ano) {
        this.aluno = aluno;
        this.sentido = sentido;
        this.hora = hora;
        this.min = min;
        this.mes = mes;
        this.dia = dia;
        this.ano = ano;
    }

   

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    
}
