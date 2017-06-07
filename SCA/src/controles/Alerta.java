/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import bancodedados.Bancos;
import bancodedados.EscreveAlerta;
import bancodedados.EventosAux;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import relatorios.RelFaltas;
import relatorios.Relatorios;

/**
 *
 * @author pfsel
 */
public class Alerta extends Controle{
    
    
    private JTextField id;
    private JFormattedTextField min;
    private JFormattedTextField vezes;
    private JTextField diasemana;
    private int dia;
    private String diaFinal;
    private Bancos ev;
    private Calendar c;

    public Alerta(JTextField id, JFormattedTextField vezes) {
        this.id = id;
        this.vezes = vezes;
    }
    
    
    
    

    @Override
    public String auxiliaDia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saidaAntecipada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void chegadaTardia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void faltasSemana() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void faltasCon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void faltasInt() {
  ArrayList<RelFaltas> listaReport = new ArrayList<RelFaltas>();
        ArrayList<EventosAux> primeiraLista = this.ev.retornaLista();
        ArrayList<EventosAux> listaAuxiliar = new ArrayList<EventosAux>();
        ArrayList<Integer> controlaDias = new ArrayList<Integer>();
        ArrayList<EventosAux> listaFinal = new ArrayList<EventosAux>();

        for (EventosAux monitorado : primeiraLista) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                listaAuxiliar.add(monitorado);
            }
        }
        
        int t = listaAuxiliar.size();
        
       Bancos q = new EscreveAlerta(t);
        q.modificar();
        
        
        
    }

    @Override
    public int setDiaSemana(String diaSema) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
