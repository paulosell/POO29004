/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.util.Calendar;

/**
 *
 * @author pfsel
 */
public class EventosAux {
    
    private String aluno;
    private String sentido;
    private Calendar c;

    public EventosAux(String aluno, String sentido,  Calendar c) {
        this.aluno = aluno;
        this.sentido = sentido;
       
       this.c = c;
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

    public Calendar getC() {
        return c;
    }

    public void setC(Calendar c) {
        this.c = c;
    }

   
    
}
