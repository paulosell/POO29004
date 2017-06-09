/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;


/**
 * Classe para estruturar os relatórios que necessitam de horario para
 * criação do relatorio jasper
 * @author pfsel
 */
public class RelHorario  {
    private String aluno;
    private String horario;
    private String data;
    
    /**
     * Construtor da classe
     * @param aluno = aluno
     * @param horario = horario de entrada/saida
     * @param data = data
     */
    public RelHorario(String aluno, String horario, String data) {
        this.aluno = aluno;
        this.horario = horario;
        this.data = data;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
    
}
