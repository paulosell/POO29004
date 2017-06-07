/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pfsel
 */
public class EscreveAlerta extends  Bancos{
    private int t;
    public EscreveAlerta(int t){
        this.t = t;
    }
    @Override
    public void abrir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gerar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar() {
        File arquivo;
        try {
            arquivo = new File(getClass().getResource("alerta.txt").getPath());
            FileWriter fwArquivo = null;

// Se o arquivo existir, abre para adicionar dados
            if (arquivo.exists() == true) {
                fwArquivo = new FileWriter(arquivo, true);
            } else { // se n~ao existir, ent~ao cria o arquivo
                fwArquivo = new FileWriter(arquivo);
            }
            BufferedWriter bw = new BufferedWriter(fwArquivo);

            bw.write(t);

            // fechando arquivo
            bw.close();
            fwArquivo.close();
        } catch (IOException e) {
            System.out.println("ERRO" + e);
        }
      
    }

    @Override
    public boolean autenticado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EventosAux> retornaLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
