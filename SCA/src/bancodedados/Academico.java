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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pfsel
 */
public class Academico extends Bancos {

    public ArrayList<Alunos> lista;
    public String aluno;
    private boolean t;

    public Academico(String s) {
        lista = new ArrayList<Alunos>();
        this.aluno = s;
    }

    public String getAluno() {
        return aluno;
    }

    public void gerar() {
        File arquivo;
        try {

            arquivo = new File(getClass().getResource("/db/db-academico-alterado.csv").getPath());
            Scanner leitor = new Scanner(arquivo);
            String lixo = leitor.nextLine();
            while (leitor.hasNextLine()) {
                String proximaLinha = leitor.nextLine();
                   if(proximaLinha.length() == 0){
                    proximaLinha = leitor.nextLine();
                }
                String separados[] = proximaLinha.split(",");
                Alunos a = new Alunos(separados[0], separados[1], separados[2]);
                lista.add(a);

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Academico.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void abrir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultar() {

        for (Alunos aluno : lista) {

            if (aluno.getNome().equals(this.aluno) || aluno.getMatricula().equals(this.aluno)) {

                t = true;
            }
        }
    }

    @Override
    public void modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean autenticado() {
        return t;
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
