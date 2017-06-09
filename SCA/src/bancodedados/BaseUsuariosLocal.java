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
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe para manipular os arquivos de controle de login de usuário (extends
 * Bancos)
 *
 * @author pfsel
 */
public class BaseUsuariosLocal extends Bancos {

    private String usuario;
    private String senha;
    private String login;
    private boolean autenticacao = false;

    /**
     * Construtor para login do usuário
     *
     * @param l = login
     * @param s = senha
     */
    public BaseUsuariosLocal(String l, String s) {
        this.login = l;
        this.senha = s;

    }

    /**
     * Construtor para cadastro do usuário
     *
     * @param u = nome do usuário
     * @param l = login
     * @param s = senha
     */
    public BaseUsuariosLocal(String u, String l, String s) {
        this.login = l;
        this.usuario = u;
        this.senha = s;

    }

    @Override
    public void abrir() {

    }

    @Override
    /**
     * método que consulta se o login e senha informado no construtor existe no
     * banco de dados caso existir, é a variavel booleana é mudada para true
     */
    public void consultar() {
        File arquivo;
        try {

            arquivo = new File(getClass().getResource("/db/baselocal.csv").getPath());
            Scanner leitor = new Scanner(arquivo);
            ArrayList<String> l = new ArrayList<String>();

            while (leitor.hasNextLine()) {
                String proximaLinha = leitor.nextLine();
                if (proximaLinha.length() == 0) {
                    proximaLinha = leitor.nextLine();
                }
                proximaLinha = proximaLinha.substring(proximaLinha.indexOf(",") + 1, proximaLinha.length());
                l.add(proximaLinha);
            }
            boolean b = l.contains(this.login + "," + this.senha);
            if (b) {
                autenticacao = true;
            }
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRO" + e.toString());
        }
    }

    @Override
    /**
     * método que envia os dados gerados no construtor de cadastro para o banco
     * de dados
     */
    public void modificar() {
        File arquivo;
        try {
            arquivo = new File(getClass().getResource("/db/baselocal.csv").getPath());
            FileWriter fwArquivo = null;

            if (arquivo.exists() == true) {
                fwArquivo = new FileWriter(arquivo, true);
            } else {
                fwArquivo = new FileWriter(arquivo);
            }
            BufferedWriter bw = new BufferedWriter(fwArquivo);

            String q[] = new String[3];
            q[0] = this.usuario;
            q[1] = this.login;
            q[2] = this.senha;

            bw.write('\n');
            bw.write(q[0] + "," + q[1] + "," + q[2]);

            bw.close();
            fwArquivo.close();
        } catch (IOException e) {
            System.out.println("ERRO" + e);
        }
    }

    /**
     * método que informa se o usuário foi autenticado no sistema ou nao
     *
     * @return autenticação
     */
    public boolean autenticado() {
        return autenticacao;
    }

    @Override
    public void gerar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EventosAux> retornaListaEventos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Alerta> retornaListaAlertas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
