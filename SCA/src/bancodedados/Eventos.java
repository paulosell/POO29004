/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pfsel
 */
public class Eventos extends Bancos {

   private ArrayList<EventosAux> lista;
   private Alunos aluno;
   
   public Eventos(){
       lista = new ArrayList<EventosAux>();
   }
   

    @Override
    public void abrir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultar() {
        

    }

    @Override
    public void gerar() {
        File arquivo;
        try {
            arquivo = new File(getClass().getResource("/db/db-catraca-eventos-alterado.csv").getPath());
            Scanner leitor = new Scanner(arquivo);
            String lixo = leitor.nextLine();
            while (leitor.hasNextLine()) {
                String proximaLinha = leitor.nextLine();
                   String separados[] = proximaLinha.split(",");
                String sentido = (separados[2] + "," + separados[3]);
                String aluno = separados[6];
                String t[] = separados[1].split("-");
                int ano = Integer.parseInt(t[0]);
                int mes = Integer.parseInt(t[1]);
                String separaHora[] = t[2].split(" ");
                int dia = Integer.parseInt(separaHora[0]);
                String separaTotal[] = separaHora[1].split(":");
                int hora = Integer.parseInt(separaTotal[0]);
                int min = Integer.parseInt(separaTotal[1]);
                Calendar c = Calendar.getInstance();
                
                 c.set(Calendar.YEAR, ano);
         c.set(Calendar.MONTH, mes);
         c.set(Calendar.DAY_OF_MONTH, dia);
         c.set(Calendar.HOUR_OF_DAY, hora);
         c.set(Calendar.MINUTE, min);
         
               
                EventosAux ev = new EventosAux(aluno,sentido, c);
                lista.add(ev);
            }
                

         

    }   catch (FileNotFoundException ex) {
            Logger.getLogger(Eventos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    
    
    public void modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean autenticar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<EventosAux> retornaLista() {
        return lista;
    }

}
