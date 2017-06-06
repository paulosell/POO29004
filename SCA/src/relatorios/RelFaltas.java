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
public class RelFaltas  {
    private String aluno;
    private String data;
   


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

    public RelFaltas(String aluno, String data) {
        this.aluno = aluno;
       
        this.data = data;
    }
    
    
    
}
