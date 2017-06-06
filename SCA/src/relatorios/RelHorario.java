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
public class RelHorario  {
    private String aluno;
    private String horario;
    private String data;

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
