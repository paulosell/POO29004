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
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pfsel
 */
public class EscreveAlerta extends Bancos {

    private Alerta a;
    private ArrayList<Alerta> lista;

    public EscreveAlerta(Alerta q) {
        this.a = q;

    }

    public EscreveAlerta() {
        lista = new ArrayList<Alerta>();
    }

    @Override
    public void abrir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int mes = 0;

    public int retMes(String t) {
        if (t.equals("Jan")) {
            mes = 0;
        }
        if (t.equals("Feb")) {
            mes = 1;
        }
        if (t.equals("Mar")) {
            mes = 2;
        }
        if (t.equals("Apr")) {
            mes = 3;
        }
        if (t.equals("May")) {
            mes = 4;
        }
        if (t.equals("Jun")) {
            mes = 5;
        }
        if (t.equals("Jul")) {
            mes = 6;
        }
        if (t.equals("Aug")) {
            mes = 7;
        }
        if (t.equals("Sep")) {
            mes = 8;
        }
        if (t.equals("Oct")) {
            mes = 9;
        }
        if (t.equals("Nov")) {
            mes = 10;
        }
        if (t.equals("Dec")) {
            mes = 11;
        }
        return mes;
    }

    @Override
    public void gerar() {
        File arquivo;
        try {

            arquivo = new File(getClass().getResource("/db/db-alertas.csv").getPath());
            Scanner leitor = new Scanner(arquivo);
            String lixo = leitor.nextLine();
            while (leitor.hasNextLine()) {
                
                String proximaLinha = leitor.nextLine();
                if(proximaLinha.length() ==0){
                    proximaLinha = leitor.nextLine();
                }
                String separados[] = proximaLinha.split(",");
                Calendar c = Calendar.getInstance();
                String data[] = separados[0].split(" ");
                System.out.println(data[1]);
                int t = this.retMes(data[1]);
                c.set(Calendar.YEAR, Integer.parseInt(data[5]));
                c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[2]));
                c.set(Calendar.MONTH, t-2);
                // System.out.println(c.getTime().toString());
               // System.out.println(lista.size());
                Alerta al = new Alerta(separados[5], c, separados[1], separados[2], separados[3], separados[4]);
                //System.out.println(al.toString());
                lista.add(al);

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EscreveAlerta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modificar() {
        File arquivo;
        try {
            arquivo = new File(getClass().getResource("/db/db-alertas.csv").getPath());
            FileWriter fwArquivo = null;

// Se o arquivo existir, abre para adicionar dados
            if (arquivo.exists() == true) {
                fwArquivo = new FileWriter(arquivo, true);
            } else { // se n~ao existir, ent~ao cria o arquivo
                fwArquivo = new FileWriter(arquivo);
            }
            BufferedWriter bw = new BufferedWriter(fwArquivo);

            String separa[] = new String[6];
            separa[0] = a.getInicio().getTime().toString();
            separa[1] = a.getVezes();
            separa[2] = a.getNomeAlerta();
            separa[3] = a.getTipo();
            separa[4] = a.getMinutos();
            separa[5] = a.getAluno();
            

            bw.write('\n'+separa[0] + "," + separa[1] + "," + separa[2] + "," + separa[3] + "," + separa[4] + ","+
                    separa[5]);
            
            // fechando arquivo
            bw.close();
            fwArquivo.close();
        } catch (IOException e) {
            System.out.println("ERRO" + e);
        }

    }

    public boolean autenticado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<EventosAux> retornaListaEventos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Alerta> retornaListaAlertas() {
        return lista;
    }


}
