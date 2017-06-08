/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import bancodedados.Alerta;
import bancodedados.Bancos;
import bancodedados.EscreveAlerta;
import bancodedados.Eventos;
import bancodedados.EventosAux;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author pfsel
 */
public class ControleAlerta extends Controle {

    private String aluno;
    private Calendar inicio;
    private String vezes;
    private String nomeAlerta;
    private String tipo;
    private Bancos ev;
    private Bancos al;

    public ControleAlerta() {
        ev = new Eventos();
        al = new EscreveAlerta();
        ev.gerar();
        al.gerar();

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
        ArrayList<Alerta> listaAlerta = al.retornaListaAlertas();
        ArrayList<EventosAux> listaEventos = ev.retornaListaEventos();
     
    

    }

    @Override
    public int setDiaSemana(String diaSema) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
