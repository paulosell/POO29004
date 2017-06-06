/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import bancodedados.EventosAux;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author pfsel
 */
public class Relatorios {

    public void imprimir(ArrayList<RelFaltas> lista, int i) throws Exception {
        if (i == 1) {

            String t = Relatorios.class.getResource("faltasCon.jrxml").getPath();
            JasperReport report = JasperCompileManager.compileReport(t);
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
            JasperViewer.viewReport(print, false);
        }
        if (i == 2) {
            String t = Relatorios.class.getResource("faltasInt.jrxml").getPath();
            JasperReport report = JasperCompileManager.compileReport(t);
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
            JasperViewer.viewReport(print, false);
        }

       

      
    }
    
        public void imprimir(ArrayList<RelHorario> lista, String i) throws Exception {
             String t = Relatorios.class.getResource(i).getPath();
            JasperReport report = JasperCompileManager.compileReport(t);
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
            JasperViewer.viewReport(print, false);
               

        }

          public void imprimir(ArrayList<RelSemana> lista) throws Exception {
            String t = Relatorios.class.getResource("faltasSemana.jrxml").getPath();
            JasperReport report = JasperCompileManager.compileReport(t);
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
            JasperViewer.viewReport(print, false);
               

        }
}