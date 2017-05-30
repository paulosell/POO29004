/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pfsel
 */
public abstract class Bancos {
    
    public abstract void abrir();
    public abstract void consultar();

    
    public abstract void gerar();
    public abstract void modificar();
    public abstract boolean autenticar();

    public abstract ArrayList<EventosAux> retornaLista();
    
    
    
    
}
