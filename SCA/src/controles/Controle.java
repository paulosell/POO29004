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

/**
 *
 * @author pfsel
 */
public abstract class Controle {
    public abstract String auxiliaDia();

    public abstract void saidaAntecipada();

    public abstract void chegadaTardia();

    public abstract void faltasSemana();

    public abstract void faltasCon();

    public abstract void faltasInt();

    public abstract int setDiaSemana(String diaSema);

}
