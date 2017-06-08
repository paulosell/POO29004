/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import controles.ControleAlerta;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 *
 * @author pfsel
 */
public class Alerta{
    private String aluno;
    private Calendar inicio;
    private String vezes;
    private String nomeAlerta;
    private String tipo;

    public Alerta(String aluno, Calendar inicio, String vezes, String nomeAlerta, String tipo) {
        this.aluno = aluno;
        this.tipo=tipo;
        this.inicio = inicio;
        this.vezes = vezes;
        this.nomeAlerta = nomeAlerta;
      
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
    
    public String toString(){
        return(this.inicio.getTime().toString()+","+this.vezes+","+this.tipo
                +","+this.nomeAlerta+","+this.aluno);
    }
    
    
}
