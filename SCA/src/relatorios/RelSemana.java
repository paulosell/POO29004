/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;


/**
 * Classe para estruturar os relatorios que necessitam de dia da semana para 
 * criação do relatorio pelo jasper
 * @author pfsel
 */
public class RelSemana  {
    private String aluno;
    private String data;
    private String semana;

     /**
     * Cosntrutor da classe
     * @param aluno = aluno
     * @param data = data
     * @param semana  = dia da semana
     */
    public RelSemana(String aluno, String data, String semana) {
        this.aluno = aluno;
        this.semana=semana;
        this.data = data;
    }
    
    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
   
    
    
    
}
