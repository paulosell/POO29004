/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;


/**
 *
 * @author pfsel
 */
public class RelSemana  {
    private String aluno;
    private String data;
    private String semana;

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

    public RelSemana(String aluno, String data, String semana) {
        this.aluno = aluno;
        this.semana=semana;
        this.data = data;
    }
    
    
    
}
