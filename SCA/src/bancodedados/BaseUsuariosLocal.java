/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import telas.JPSelecao;

/**
 *
 * @author pfsel
 */
public class BaseUsuariosLocal extends Bancos {

    private String usuario;

    private String senha;
    private String login;
    private boolean t = false;

    public BaseUsuariosLocal(String l, String s) {
        this.login = l;
        this.senha = s;

    }

    public BaseUsuariosLocal(String u, String l, String s) {
        this.login = l;
        this.usuario = u;
        this.senha = s;

    }

    @Override
    public void abrir() {

    }

    @Override
    public void consultar() {
        File arquivo;
        try {

            arquivo = new File(getClass().getResource("/db/baselocal.csv").getPath());
            Scanner leitor = new Scanner(arquivo);
            System.out.println(this.senha);
            ArrayList<String> l = new ArrayList<String>();

            while (leitor.hasNextLine()) {
                String proximaLinha = leitor.nextLine();
                proximaLinha = proximaLinha.substring(proximaLinha.indexOf(",") + 1, proximaLinha.length());
                l.add(proximaLinha);
            }
            boolean b = l.contains(this.login + "," + this.senha);

            if (b) {
                t = true;
            }
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRO" + e.toString());
        }
    }

    @Override
    public void modificar() {
        File arquivo;
        try {
            arquivo = new File(getClass().getResource("/db/baselocal.csv").getPath());
            FileWriter fwArquivo = null;

// Se o arquivo existir, abre para adicionar dados
            if (arquivo.exists() == true) {
                fwArquivo = new FileWriter(arquivo, true);
            } else { // se n~ao existir, ent~ao cria o arquivo
                fwArquivo = new FileWriter(arquivo);
            }
            BufferedWriter bw = new BufferedWriter(fwArquivo);

            String q[] = new String[3];
            q[0] = this.usuario;
            q[1] = this.login;
            q[2] = this.senha;
            
            bw.write('\n');
            bw.write(q[0] + "," + q[1] + "," + q[2]);

            // fechando arquivo
            bw.close();
            fwArquivo.close();
        } catch (IOException e) {
            System.out.println("ERRO" + e);
        }
    }

    public boolean autenticar() {
        return t;
    }

    @Override
    public void gerar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EventosAux> retornaLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
