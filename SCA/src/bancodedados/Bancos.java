/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.util.ArrayList;

/**
 * Classe abstrata para utilizar os bancos de dados
 *
 * @author pfsel
 */
public abstract class Bancos {

    public abstract void abrir();

    public abstract void consultar();

    public abstract void gerar();

    public abstract void modificar();

    public abstract boolean autenticado();

    public abstract ArrayList<EventosAux> retornaListaEventos();

    public abstract ArrayList<Alerta> retornaListaAlertas();

}
