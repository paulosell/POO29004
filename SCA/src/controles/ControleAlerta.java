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
        ArrayList<Alerta> listaAlerta = al.retornaListaAlertas();
        ArrayList<EventosAux> listaEventos = ev.retornaListaEventos();

        for (Alerta a : listaAlerta) {
            if (a.getTipo().equals("2")) {

                ArrayList<EventosAux> auxilia = new ArrayList<EventosAux>();
                for (EventosAux monitorado : listaEventos) {
                    if (monitorado.getAluno().equals(a.getAluno())) {
                        auxilia.add(monitorado);
                    }
                }
                ArrayList<EventosAux> aux2 = new ArrayList<EventosAux>();

                for (EventosAux monitorado : auxilia) {
                    if (monitorado.getC().after(a.getInicio())) {
                        aux2.add(monitorado);
                    }
                }
                ArrayList<EventosAux> listaFinal = new ArrayList<EventosAux>();

                if (Integer.parseInt(a.getMinutos()) > 30) {
                    for (EventosAux monitorado : aux2) {
                        if (monitorado.getSentido().equals("Interna,Externa")) {
                            if (((monitorado.getC().get(Calendar.HOUR_OF_DAY) > 7) && (monitorado.getC().get(Calendar.HOUR) < 12))
                                    || ((monitorado.getC().get(Calendar.HOUR_OF_DAY) > 13) && (monitorado.getC().get(Calendar.HOUR) < 18))) {
                                listaFinal.add(monitorado);
                            }
                        }
                    }
                } else {
                    for (EventosAux monitorado : aux2) {
                        if (monitorado.getSentido().equals("Interna,Externa")) {
                            if (monitorado.getC().get(Calendar.MINUTE) < 30) {
                                if ((monitorado.getC().get(Calendar.MINUTE) + (Integer.parseInt(a.getMinutos()))) < 30) {
                                    listaFinal.add(monitorado);
                                    System.out.println((monitorado.getC().get(Calendar.MINUTE) + (Integer.parseInt(a.getMinutos()))));
                                }
                            }
                        }
                    }
                }
                System.out.println(listaFinal.size());
                if (listaFinal.size() >= Integer.parseInt(a.getVezes())) {
                    System.out.println("Alerta " + a.getNomeAlerta() + " disparado!");
                }
            }
        }
    }

    @Override
    public void chegadaTardia() {
        ArrayList<Alerta> listaAlerta = al.retornaListaAlertas();
        ArrayList<EventosAux> listaEventos = ev.retornaListaEventos();

        for (Alerta a : listaAlerta) {
            if (a.getTipo().equals("1")) {
                ArrayList<EventosAux> auxilia = new ArrayList<EventosAux>();
                for (EventosAux monitorado : listaEventos) {
                    if (monitorado.getAluno().equals(a.getAluno())) {
                        auxilia.add(monitorado);
                    }
                }
                ArrayList<EventosAux> aux2 = new ArrayList<EventosAux>();

                for (EventosAux monitorado : auxilia) {
                    if (monitorado.getC().after(a.getInicio())) {
                        aux2.add(monitorado);
                    }
                }
                ArrayList<EventosAux> listaFinal = new ArrayList<EventosAux>();
                if (Integer.parseInt(a.getMinutos()) > 30) {
                    for (EventosAux monitorado : aux2) {
                        if (monitorado.getSentido().equals("Externa,Interna")) {
                            if (((monitorado.getC().get(Calendar.HOUR_OF_DAY) > 7) && (monitorado.getC().get(Calendar.HOUR) < 12))
                                    || ((monitorado.getC().get(Calendar.HOUR_OF_DAY) > 13) && (monitorado.getC().get(Calendar.HOUR) < 18))) {
                                listaFinal.add(monitorado);
                            }
                        }
                    }
                } else {
                    for (EventosAux monitorado : aux2) {
                        if (monitorado.getSentido().equals("Externa,Interna")) {
                            if (monitorado.getC().get(Calendar.MINUTE) > 30) {
                                if ((monitorado.getC().get(Calendar.MINUTE) - (Integer.parseInt(a.getMinutos())) > 30)) {
                                    listaFinal.add(monitorado);
                                }
                            }
                        }
                    }
                }
             

                if (listaFinal.size() >= Integer.parseInt(a.getVezes())) {
                    System.out.println("Alerta " + a.getNomeAlerta() + " disparado!");
                }
            }
        }
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
    }

    @Override
    public int setDiaSemana(String diaSema) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
