/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import relatorios.RelFaltas;
import relatorios.RelHorario;
import bancodedados.Bancos;
import bancodedados.Eventos;
import bancodedados.EventosAux;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import relatorios.RelSemana;
import relatorios.Relatorios;

/**
 *
 * @author pfsel
 */
public class ControleRelatorio extends Controle {

    private JTextField id;
    private JFormattedTextField min;
    private JFormattedTextField vezes;
    private JTextField diasemana;
    private int dia;
    private String diaFinal;
    private Calendar c1, c2;
    private Bancos ev;

    public ControleRelatorio(JTextField texto,
            JFormattedTextField vezes, Calendar c1, Calendar c2) {
        this.ev = new Eventos();
        this.ev.gerar();
        this.id = texto;
        this.c1 = c1;
        this.c2 = c2;
        this.vezes = vezes;

    }

    public ControleRelatorio(JTextField texto,
            JFormattedTextField min, JFormattedTextField vezes) {
        this.ev = new Eventos();
        this.ev.gerar();

        this.id = texto;
        this.min = min;
        this.vezes = vezes;

    }

    public ControleRelatorio(JTextField texto,
            JFormattedTextField vezes, JTextField diasemana, Calendar c1, Calendar c2) {
        this.ev = new Eventos();
        this.ev.gerar();

        this.id = texto;
        this.vezes = vezes;
        this.diasemana = diasemana;
        this.c1 = c1;
        this.c2 = c2;
        this.setDiaSemana(this.diasemana.getText().toUpperCase());

    }

    @Override
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

    @Override
    public void saidaAntecipada() {

        ArrayList<EventosAux> primeiraLista = this.ev.retornaListaEventos();
        ArrayList<EventosAux> listaAuxiliar = new ArrayList<EventosAux>();
        ArrayList<EventosAux> listaFinal = new ArrayList<EventosAux>();
        ArrayList<RelHorario> listaReport = new ArrayList<RelHorario>();

        for (EventosAux monitorado : primeiraLista) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                listaAuxiliar.add(monitorado);
            }
        }

        if (Integer.parseInt(this.min.getText()) > 30) {
            for (EventosAux hora : listaAuxiliar) {
                if (hora.getSentido().equals("Interna,Externa")) {
                    if (((hora.getC().get(Calendar.HOUR_OF_DAY) > 7) && (hora.getC().get(Calendar.HOUR) < 12))
                            || ((hora.getC().get(Calendar.HOUR_OF_DAY) > 13) && (hora.getC().get(Calendar.HOUR) < 18))) {
                        listaFinal.add(hora);
                    }
                }
            }
        } else {
            for (EventosAux monitorado : listaAuxiliar) {
                if (monitorado.getSentido().equals("Interna,Externa")) {
                    if (monitorado.getC().get(Calendar.MINUTE) < 30) {
                        if ((monitorado.getC().get(Calendar.MINUTE) + (Integer.parseInt(this.min.getText()))) < 30) {
                            listaFinal.add(monitorado);
                        }
                    }
                }
            }
        }
        if (listaFinal.size() >= Integer.parseInt(this.vezes.getText())) {
            for (EventosAux fim : listaFinal) {
                if (fim.getC().get(Calendar.MINUTE) < 10) {
                    String horario = (fim.getC().get(Calendar.HOUR_OF_DAY) + ":0" + fim.getC().get(Calendar.MINUTE));
                    String aluno = fim.getAluno();
                    String data = (fim.getC().get(Calendar.DAY_OF_MONTH) + "/" + fim.getC().get(Calendar.MONTH) + "/"
                            + fim.getC().get(Calendar.YEAR));
                    RelHorario monitorado = new RelHorario(aluno, horario, data);
                    listaReport.add(monitorado);

                } else {
                    String horario = (fim.getC().get(Calendar.HOUR_OF_DAY) + ":" + fim.getC().get(Calendar.MINUTE));
                    String aluno = fim.getAluno();
                    String data = (fim.getC().get(Calendar.DAY_OF_MONTH) + "/" + fim.getC().get(Calendar.MONTH) + "/"
                            + fim.getC().get(Calendar.YEAR));
                    RelHorario monitorado = new RelHorario(aluno, horario, data);
                    listaReport.add(monitorado);
                }
            }
            Relatorios t = new Relatorios();
            try {
                t.imprimir(listaReport, "/relatorios/faltasSaidas.jrxml");

            } catch (Exception ex) {
                Logger.getLogger(ControleRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aluno não se atrasou mais que " + this.min.getText()
                    + " minutos por " + this.vezes.getText() + " vezes.",
                    "Opa!",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    @Override
    public void chegadaTardia() {

        ArrayList<EventosAux> primeiraLista = this.ev.retornaListaEventos();
        ArrayList<EventosAux> listaAuxiliar = new ArrayList<EventosAux>();
        ArrayList<EventosAux> listaFinal = new ArrayList<EventosAux>();
        ArrayList<RelHorario> listaReport = new ArrayList<RelHorario>();

        for (EventosAux monitorado : primeiraLista) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                listaAuxiliar.add(monitorado);
            }
        }

        if (Integer.parseInt(this.min.getText()) > 30) {
            for (EventosAux monitorado : listaAuxiliar) {
                if (monitorado.getSentido().equals("Externa,Interna")) {
                    if (((monitorado.getC().get(Calendar.HOUR_OF_DAY) > 7) && (monitorado.getC().get(Calendar.HOUR) < 12))
                            || ((monitorado.getC().get(Calendar.HOUR_OF_DAY) > 13) && (monitorado.getC().get(Calendar.HOUR) < 18))) {
                        listaFinal.add(monitorado);
                    }
                }
            }
        } else {
            for (EventosAux monitorado : listaAuxiliar) {
                if (monitorado.getSentido().equals("Externa,Interna")) {
                    if (monitorado.getC().get(Calendar.MINUTE) > 30) {
                        if ((monitorado.getC().get(Calendar.MINUTE) - (Integer.parseInt(this.min.getText()))) > 30) {
                            listaFinal.add(monitorado);
                        }
                    }
                }
            }
        }

        if (listaFinal.size() >= Integer.parseInt(this.vezes.getText())) {
            for (EventosAux fim : listaFinal) {
                if (fim.getC().get(Calendar.MINUTE) < 10) {
                    String horario = (fim.getC().get(Calendar.HOUR_OF_DAY) + ":0" + fim.getC().get(Calendar.MINUTE));
                    String aluno = fim.getAluno();
                    String data = (fim.getC().get(Calendar.DAY_OF_MONTH) + "/" + fim.getC().get(Calendar.MONTH) + "/"
                            + fim.getC().get(Calendar.YEAR));
                    RelHorario monitorado = new RelHorario(aluno, horario, data);
                    listaReport.add(monitorado);

                } else {
                    String horario = (fim.getC().get(Calendar.HOUR_OF_DAY) + ":" + fim.getC().get(Calendar.MINUTE));
                    String aluno = fim.getAluno();
                    String data = (fim.getC().get(Calendar.DAY_OF_MONTH) + "/" + fim.getC().get(Calendar.MONTH) + "/"
                            + fim.getC().get(Calendar.YEAR));
                    RelHorario monitorado = new RelHorario(aluno, horario, data);
                    listaReport.add(monitorado);
                }
            }
            Relatorios t = new Relatorios();
            try {
                t.imprimir(listaReport, "/relatorios/faltasChegadas.jrxml");

            } catch (Exception ex) {
                Logger.getLogger(ControleRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aluno não se atrasou mais que " + this.min.getText()
                    + " minutos por " + this.vezes.getText() + " vezes.",
                    "Opa!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void faltasSemana() {

        String t = this.auxiliaDia();

        ArrayList<RelSemana> listaReport = new ArrayList<RelSemana>();
        ArrayList<EventosAux> primeiraLista = this.ev.retornaListaEventos();
        ArrayList<EventosAux> listaAuxiliar = new ArrayList<EventosAux>();
        ArrayList<Integer> controlaDias = new ArrayList<Integer>();
        ArrayList<EventosAux> preliminar = new ArrayList<EventosAux>();

        for (EventosAux monitorado : primeiraLista) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                listaAuxiliar.add(monitorado);
            }
        }

       
        Calendar aux1 = Calendar.getInstance();

        aux1.set(Calendar.YEAR, this.c1.get(Calendar.YEAR));
        aux1.set(Calendar.MONTH, this.c1.get(Calendar.MONTH));
        aux1.set(Calendar.DAY_OF_MONTH, this.c1.get(Calendar.DAY_OF_MONTH));

        for (EventosAux monitorado : listaAuxiliar) {
            while (aux1.before(this.c2)) {
                aux1.add(Calendar.DAY_OF_MONTH, +1);
                Calendar aux2 = Calendar.getInstance();
                aux2.set(Calendar.YEAR, aux1.get(Calendar.YEAR));
                aux2.set(Calendar.MONTH, (aux1.get(Calendar.MONTH)));
                aux2.set(Calendar.DAY_OF_MONTH, aux1.get(Calendar.DAY_OF_MONTH));
                EventosAux re = new EventosAux(monitorado.getAluno(), monitorado.getSentido(), aux2);
                preliminar.add(re);

            }
        }

        for (EventosAux monitorado : listaAuxiliar) {
            for (int i = 0; i < preliminar.size(); i++) {

                if ((preliminar.get(i).getC().get(Calendar.DAY_OF_MONTH) == monitorado.getC().get(Calendar.DAY_OF_MONTH))
                        && (preliminar.get(i).getC().get(Calendar.MONTH) == monitorado.getC().get(Calendar.MONTH))) {
                    preliminar.remove(i);
                }
            }
        }

        ArrayList<EventosAux> fim = new ArrayList<EventosAux>();
        for (EventosAux filtra : preliminar) {
            String separa[] = filtra.getC().getTime().toString().split(" ");

            if (separa[0].equals(t)) {
                filtra.getC().set(Calendar.MONTH, filtra.getC().get(Calendar.MONDAY) + 1);
                fim.add(filtra);
            }

        }
        if (fim.size() < Integer.parseInt(this.vezes.getText())) {
            JOptionPane.showMessageDialog(null, "Aluno não faltou mais que " + this.vezes.getText()
                    + " vezes.",
                    "Opa!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            for (EventosAux monitorado : fim) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String data = (monitorado.getC().get(Calendar.DAY_OF_MONTH) + "/" + monitorado.getC().get(Calendar.MONTH) + "/"
                        + monitorado.getC().get(Calendar.YEAR));

                RelSemana rel = new RelSemana(monitorado.getAluno(), data, this.diasemana.getText());
                listaReport.add(rel);

            }

            Relatorios tu = new Relatorios();
            try {
                tu.imprimir(listaReport);

            } catch (Exception ex) {
                Logger.getLogger(ControleRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public void faltasCon() {

        ArrayList<RelFaltas> listaReport = new ArrayList<RelFaltas>();
        ArrayList<EventosAux> primeiraLista = this.ev.retornaListaEventos();
        ArrayList<EventosAux> listaAuxiliar = new ArrayList<EventosAux>();
        ArrayList<Integer> controlaDias = new ArrayList<Integer>();
        ArrayList<EventosAux> listaFinal = new ArrayList<EventosAux>();

        for (EventosAux monitorado : primeiraLista) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                listaAuxiliar.add(monitorado);
            }
        }

        Calendar aux1 = Calendar.getInstance();

        aux1.set(Calendar.YEAR, this.c1.get(Calendar.YEAR));
        aux1.set(Calendar.MONTH, this.c1.get(Calendar.MONTH));
        aux1.set(Calendar.DAY_OF_MONTH, this.c1.get(Calendar.DAY_OF_MONTH));

        for (EventosAux monitorado : listaAuxiliar) {
            while (aux1.before(this.c2)) {
                aux1.add(Calendar.DAY_OF_MONTH, +1);
                Calendar aux2 = Calendar.getInstance();
                aux2.set(Calendar.YEAR, aux1.get(Calendar.YEAR));
                aux2.set(Calendar.MONTH, (aux1.get(Calendar.MONTH)));
                aux2.set(Calendar.DAY_OF_MONTH, aux1.get(Calendar.DAY_OF_MONTH));
                EventosAux re = new EventosAux(monitorado.getAluno(), monitorado.getSentido(), aux2);
                listaFinal.add(re);

            }
        }

        for (EventosAux monitorado : listaAuxiliar) {
            for (int i = 0; i < listaFinal.size(); i++) {
                if ((listaFinal.get(i).getC().get(Calendar.DAY_OF_MONTH) == monitorado.getC().get(Calendar.DAY_OF_MONTH))
                        && (listaFinal.get(i).getC().get(Calendar.MONTH) == monitorado.getC().get(Calendar.MONTH))) {
                    listaFinal.remove(i);
                }
            }
        }

        ArrayList<ArrayList<EventosAux>> filtro = new ArrayList<ArrayList<EventosAux>>();

        for (int i = 0; i < listaFinal.size() - 1; i++) {
            ArrayList<EventosAux> aux = new ArrayList<EventosAux>();
            int k = i;

            while ((listaFinal.get(k + 1).getC().get(Calendar.DAY_OF_MONTH))
                    == (listaFinal.get(k).getC().get(Calendar.DAY_OF_MONTH) + 1)) {
                aux.add(listaFinal.get(k));
                aux.add(listaFinal.get(k + 1));

                k++;
                if (k == listaFinal.size() - 1) {
                    break;
                }
            }
            filtro.add(aux);
        }

        ArrayList<EventosAux> faltasTot = new ArrayList<EventosAux>();

        for (ArrayList<EventosAux> preTotal : filtro) {

            if (preTotal.size() >= Integer.parseInt(this.vezes.getText())) {

                for (EventosAux pre : preTotal) {

                    if (!faltasTot.contains(pre)) {
                        faltasTot.add(pre);
                    }
                }
            }
        }
        System.out.println(faltasTot.size());
        if (faltasTot.size() < Integer.parseInt(this.vezes.getText())) {
            JOptionPane.showMessageDialog(null, "Aluno não faltou mais que " + this.vezes.getText()
                    + " vezes.",
                    "Opa!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            for (EventosAux monitorado : faltasTot) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                //monitorado.getC().set(Calendar.MONTH, (monitorado.getC().get(Calendar.MONTH) + 1));
                int tu = monitorado.getC().get(Calendar.MONTH) + 1;
                if (tu == 0) {
                    tu = 12;
                }
                String data = (monitorado.getC().get(Calendar.DAY_OF_MONTH) + "/" + tu + "/"
                        + monitorado.getC().get(Calendar.YEAR));

                RelFaltas rel = new RelFaltas(monitorado.getAluno(), data);
                listaReport.add(rel);

            }

            Relatorios t = new Relatorios();
            try {
                t.imprimir(listaReport, 1);

            } catch (Exception ex) {
                Logger.getLogger(ControleRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    @SuppressWarnings("empty-statement")
    public void faltasInt() {

        ArrayList<RelFaltas> listaReport = new ArrayList<RelFaltas>();
        ArrayList<EventosAux> primeiraLista = this.ev.retornaListaEventos();
        ArrayList<EventosAux> listaAuxiliar = new ArrayList<EventosAux>();
        ArrayList<Integer> controlaDias = new ArrayList<Integer>();
        ArrayList<EventosAux> listaFinal = new ArrayList<EventosAux>();

        for (EventosAux monitorado : primeiraLista) {
            if (monitorado.getAluno().equals(this.id.getText().toUpperCase())) {
                listaAuxiliar.add(monitorado);
            }
        }

        Calendar aux1 = Calendar.getInstance();

        aux1.set(Calendar.YEAR, this.c1.get(Calendar.YEAR));
        aux1.set(Calendar.MONTH, this.c1.get(Calendar.MONTH));
        aux1.set(Calendar.DAY_OF_MONTH, this.c1.get(Calendar.DAY_OF_MONTH));

        for (EventosAux monitorado : listaAuxiliar) {
            while (aux1.before(this.c2)) {
                aux1.add(Calendar.DAY_OF_MONTH, +1);
                Calendar aux2 = Calendar.getInstance();
                aux2.set(Calendar.YEAR, aux1.get(Calendar.YEAR));
                aux2.set(Calendar.MONTH, (aux1.get(Calendar.MONTH)));
                aux2.set(Calendar.DAY_OF_MONTH, aux1.get(Calendar.DAY_OF_MONTH));
                EventosAux re = new EventosAux(monitorado.getAluno(), monitorado.getSentido(), aux2);
                listaFinal.add(re);

            }
        }

        for (EventosAux monitorado : listaAuxiliar) {
            for (int i = 0; i < listaFinal.size(); i++) {

                if ((listaFinal.get(i).getC().get(Calendar.DAY_OF_MONTH) == monitorado.getC().get(Calendar.DAY_OF_MONTH))
                        && (listaFinal.get(i).getC().get(Calendar.MONTH) == monitorado.getC().get(Calendar.MONTH))) {
                    listaFinal.remove(i);
                }
            }
        }

        if (listaFinal.size() < Integer.parseInt(this.vezes.getText())) {
            JOptionPane.showMessageDialog(null, "Aluno não faltou mais que " + this.vezes.getText()
                    + " vezes.",
                    "Opa!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            for (EventosAux monitorado : listaFinal) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                int tu = monitorado.getC().get(Calendar.MONTH) + 1;
                if (tu == 0) {
                    tu = 12;
                }
                String data = (monitorado.getC().get(Calendar.DAY_OF_MONTH) + "/" + tu + "/"
                        + monitorado.getC().get(Calendar.YEAR));

                RelFaltas rel = new RelFaltas(monitorado.getAluno(), data);
                listaReport.add(rel);

            }

            Relatorios t = new Relatorios();
            try {
                t.imprimir(listaReport, 2);

            } catch (Exception ex) {
                Logger.getLogger(ControleRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
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
