/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import bancodedados.Bancos;
import bancodedados.Eventos;
import bancodedados.EventosAux;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import relatorios.relatorioFaltasInt;

/**
 *
 * @author pfsel
 */
public class controleRelatorio extends Controle {

    private JTextField id;
    private JFormattedTextField min;
    private JFormattedTextField vezes;
    private JTextField diasemana;
    private int dia;
    private String diaFinal;
    private Calendar c1, c2;

    public controleRelatorio(JTextField texto,
            JFormattedTextField vezes, Calendar c1, Calendar c2) {

        this.id = texto;
        this.c1 = c1;
        this.c2 = c2;
        this.vezes = vezes;

    }

    public controleRelatorio(JTextField texto,
            JFormattedTextField min, JFormattedTextField vezes) {

        this.id = texto;
        this.min = min;
        this.vezes = vezes;

    }

    public controleRelatorio(JTextField texto,
            JFormattedTextField vezes, JTextField diasemana, Calendar c1, Calendar c2) {

        this.id = texto;
        this.vezes = vezes;
        this.diasemana = diasemana;
        this.c1 = c1;
        this.c2 = c2;
        this.setDiaSemana(this.diasemana.getText().toUpperCase());

    }

    public String auxiliaDia() {

        if (this.dia == 1) {
            this.diaFinal = "Mon";
        }
        if (this.dia == 2) {
            this.diaFinal = "Tue";

        }

        if (this.dia == 3) {
            this.diaFinal = "Wed";
        }
        if (this.dia == 4) {
            this.diaFinal = "Thu";
        }
        if (this.dia == 5) {
            this.diaFinal = "Fri";
        }
        return this.diaFinal;
    }

    public void saidaAntecipada() {

        Bancos ev = new Eventos();
        ev.gerar();
        ArrayList<EventosAux> al = ev.retornaLista();
        ArrayList<EventosAux> al2 = new ArrayList<EventosAux>();
        ArrayList<EventosAux> saidas = new ArrayList<EventosAux>();
        for (EventosAux monitorado : al) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                al2.add(monitorado);
            }
        }

        if (Integer.parseInt(this.min.getText()) > 30) {
            for (EventosAux hora : al2) {
                if (hora.getSentido().equals("Interna,Externa")) {
                    if (((hora.getC().get(Calendar.HOUR_OF_DAY) > 7) && (hora.getC().get(Calendar.HOUR) < 12))
                            || ((hora.getC().get(Calendar.HOUR_OF_DAY) > 13) && (hora.getC().get(Calendar.HOUR) < 18))) {
                        saidas.add(hora);
                    }
                }
            }
        } else {
            for (EventosAux hora : al2) {
                if (hora.getSentido().equals("Interna,Externa")) {

                    System.out.println(hora.getC().get(Calendar.MINUTE));
                    System.out.println(hora.getC().get(Calendar.HOUR_OF_DAY));
                    if (hora.getC().get(Calendar.MINUTE) < 30) {
                        if ((hora.getC().get(Calendar.MINUTE) + (Integer.parseInt(this.min.getText()))) < 30) {
                            saidas.add(hora);
                        }
                    }
                }
            }
        }
        System.out.println(saidas.size());
        if (saidas.size() >= Integer.parseInt(this.vezes.getText())) {
            for (EventosAux fim : saidas) {
                if (fim.getC().get(Calendar.MINUTE) < 10) {
                    System.out.println(fim.getC().get(Calendar.HOUR_OF_DAY) + ":0" + fim.getC().get(Calendar.MINUTE));

                } else {
                    System.out.println(fim.getC().get(Calendar.HOUR_OF_DAY) + ":" + fim.getC().get(Calendar.MINUTE));

                }
            }
        } else {
            System.out.println("nada");
        }

    }

    public void chegadaTardia() {
        Bancos ev = new Eventos();
        ev.gerar();
        ArrayList<EventosAux> al = ev.retornaLista();
        ArrayList<EventosAux> al2 = new ArrayList<EventosAux>();
        ArrayList<EventosAux> chegadas = new ArrayList<EventosAux>();
        for (EventosAux monitorado : al) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                al2.add(monitorado);
            }
        }

        if (Integer.parseInt(this.min.getText()) > 30) {
            for (EventosAux hora : al2) {
                if (hora.getSentido().equals("Externa,Interna")) {
                    if (((hora.getC().get(Calendar.HOUR_OF_DAY) > 7) && (hora.getC().get(Calendar.HOUR) < 12))
                            || ((hora.getC().get(Calendar.HOUR_OF_DAY) > 13) && (hora.getC().get(Calendar.HOUR) < 18))) {
                        chegadas.add(hora);
                    }
                }
            }
        } else {
            for (EventosAux hora : al2) {
                if (hora.getSentido().equals("Externa,Interna")) {

                    System.out.println(hora.getC().get(Calendar.MINUTE));
                    System.out.println(hora.getC().get(Calendar.HOUR_OF_DAY));
                    if (hora.getC().get(Calendar.MINUTE) > 30) {
                        if ((hora.getC().get(Calendar.MINUTE) - (Integer.parseInt(this.min.getText()))) > 30) {
                            chegadas.add(hora);
                        }
                    }
                }
            }
        }
        System.out.println(chegadas.size());
        if (chegadas.size() >= Integer.parseInt(this.vezes.getText())) {
            for (EventosAux fim : chegadas) {
                if (fim.getC().get(Calendar.MINUTE) < 10) {
                    System.out.println(fim.getC().get(Calendar.HOUR_OF_DAY) + ":0" + fim.getC().get(Calendar.MINUTE));

                } else {
                    System.out.println(fim.getC().get(Calendar.HOUR_OF_DAY) + ":" + fim.getC().get(Calendar.MINUTE));

                }
            }
        } else {
            System.out.println("nada");
        }
    }

    public void faltasSemana() {

        String t = this.auxiliaDia();
        System.out.println(t);

        Bancos ev = new Eventos();
        ev.gerar();
        ArrayList<EventosAux> al = ev.retornaLista();
        ArrayList<EventosAux> al2 = new ArrayList<EventosAux>();
        ArrayList<Integer> falta = new ArrayList<Integer>();

        for (EventosAux monitorado : al) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                al2.add(monitorado);
            }

        }

        for (EventosAux monitorado : al2) {
            for (int i = this.c2.get(Calendar.DAY_OF_MONTH); i > 0; i--) {
                if (monitorado.getC().get(Calendar.DAY_OF_MONTH) != i) {
                    if (i > this.c1.get(Calendar.DAY_OF_MONTH)) {
                        if (!falta.contains(i)) {
                            falta.add(i);
                        }
                    }
                }
            }
        }

        for (EventosAux monitorado : al2) {
            for (int i = 0; i < falta.size(); i++) {

                if (falta.get(i) == monitorado.getC().get(Calendar.DAY_OF_MONTH)) {
                    falta.remove(i);
                }
            }
        }
        ArrayList<Integer> fim = new ArrayList<Integer>();
        for (Integer filtra : falta) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, filtra);
            c.set(Calendar.YEAR, this.c1.get(Calendar.YEAR));
            c.set(Calendar.MONTH, this.c1.get(Calendar.MONTH));

            System.out.println("MES:  " + this.c1.get(Calendar.MONTH));
            System.out.println(c.getTime().toString());
            String separa[] = c.getTime().toString().split(" ");
            if (separa[0].equals(t)) {
                fim.add(filtra);
            }

        }

        if (fim.size() < Integer.parseInt(this.vezes.getText())) {
            System.out.println("Não faltou mais que 5 dias");
        } else {
            for (Integer te : fim) {
                System.out.println(te);
            }
        }
    }

    public void faltasCon() {
        Bancos ev = new Eventos();
        ev.gerar();
        ArrayList<EventosAux> al = ev.retornaLista();
        ArrayList<EventosAux> al2 = new ArrayList<EventosAux>();
        ArrayList<Integer> faltas = new ArrayList<Integer>();
        for (EventosAux monitorado : al) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                al2.add(monitorado);
            }
        }

        for (EventosAux monitorado : al2) { //dias que veio
            for (int i = this.c2.get(Calendar.DAY_OF_MONTH); i > 0; i--) {
                if (monitorado.getC().get(Calendar.DAY_OF_MONTH) != i) {
                    if (i > this.c1.get(Calendar.DAY_OF_MONTH)) {
                        if (!faltas.contains(i)) {
                            faltas.add(i);
                        }
                    }
                }
            }
        }

        for (EventosAux monitorado : al2) { //remove os dias que veio
            for (int i = 0; i < faltas.size(); i++) {

                if (faltas.get(i) == monitorado.getC().get(Calendar.DAY_OF_MONTH)) {
                    faltas.remove(i);
                }
            }
        }

        ArrayList<ArrayList<Integer>> filtro = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < faltas.size() - 1; i++) {
            ArrayList<Integer> aux = new ArrayList<Integer>();
            int k = i;

            while ((faltas.get(k + 1) == (faltas.get(k) - 1))) {
                aux.add(faltas.get(k));
                aux.add(faltas.get(k + 1));
                k++;
                if (k == faltas.size() - 1) {
                    break;
                }
            }
            filtro.add(aux);
        }

        ArrayList<Integer> faltasTot = new ArrayList<Integer>();

        for (ArrayList<Integer> preTotal : filtro) {
            if (preTotal.size() >= Integer.parseInt(this.vezes.getText())) {

                for (Integer pre : preTotal) {
                    if (!faltasTot.contains(pre)) {
                        faltasTot.add(pre);
                    }
                }
            }
        }

        if (faltasTot.size() < Integer.parseInt(this.vezes.getText())) {
            System.out.println("Não faltou mais que 5 dias");
        } else {
            for (Integer te : faltasTot) {
                System.out.println("faltas:");
                System.out.println(te);
            }
        }

    }

    public void faltasInt() {

        Bancos ev = new Eventos();
        ev.gerar();
        ArrayList<EventosAux> al = ev.retornaLista();
        ArrayList<EventosAux> al2 = new ArrayList<EventosAux>();
        ArrayList<Integer> faltas = new ArrayList<Integer>();
        for (EventosAux monitorado : al) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                al2.add(monitorado);
            }
        }

        for (EventosAux monitorado : al2) {
            for (int i = this.c2.get(Calendar.DAY_OF_MONTH); i > 0; i--) {
                if (monitorado.getC().get(Calendar.DAY_OF_MONTH) != i) {
                    if (i > this.c1.get(Calendar.DAY_OF_MONTH)) {
                        if (!faltas.contains(i)) {
                            faltas.add(i);
                        }
                    }
                }
            }
        }

        for (EventosAux monitorado : al2) {
            for (int i = 0; i < faltas.size(); i++) {

                if (faltas.get(i) == monitorado.getC().get(Calendar.DAY_OF_MONTH)) {
                    faltas.remove(i);
                }
            }
        }
        System.out.println(Integer.parseInt(this.vezes.getText()));
        if (faltas.size() < Integer.parseInt(this.vezes.getText())) {
            System.out.println("Não faltou mais que 5 dias");
        } else {
            for (Integer te : faltas) {
                System.out.println(te);
            }
        }
        
        relatorioFaltasInt t = new relatorioFaltasInt();
        try {
            t.gerar(faltas);
        } catch (JRException ex) {
            Logger.getLogger(controleRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public int setDiaSemana(String diaSema) {

        if (diaSema.equals("SEGUNDA-FEIRA")) {
            this.dia = 1;
        }

        if (diaSema.equals("TERÇA-FEIRA")) {
            this.dia = 2;

        }
        if (diaSema.equals("QUARTA-FEIRA")) {
            this.dia = 3;

        }
        if (diaSema.equals("QUINTA-FEIRA")) {
            this.dia = 4;

        }
        if (diaSema.equals("SEXTA-FEIRA")) {
            this.dia = 5;

        }
        return this.dia;
    }

}
