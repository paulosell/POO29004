/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import java.util.ArrayList;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Classe que gera os relatórios utilizando o JasperReports
 *
 * @author pfsel
 */
public class Relatorios {

    /**
     * método que gera os relatorios de faltas
     *
     * @param lista = lista de RelFalktas
     * @param i = falta intercalada ou consecutiva
     * @throws Exception
     */
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

    /**
     * método que gera os relatorios de entrada/saida
     *
     * @param lista = lista de RelHorario
     * @param i = arquivo .jrxml que o jasper irá usar de base
     * @throws Exception
     */
    public void imprimir(ArrayList<RelHorario> lista, String i) throws Exception {
        String t = Relatorios.class.getResource(i).getPath();
        JasperReport report = JasperCompileManager.compileReport(t);
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
        JasperViewer.viewReport(print, false);

    }

    /**
     * método que gere os relatórios de faltas por dia da semana
     *
     * @param lista = lista de RelSemana
     * @throws Exception
     */
    public void imprimir(ArrayList<RelSemana> lista) throws Exception {
        String t = Relatorios.class.getResource("faltasSemana.jrxml").getPath();
        JasperReport report = JasperCompileManager.compileReport(t);
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
        JasperViewer.viewReport(print, false);

    }
}
