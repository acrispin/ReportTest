/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.main;

import com.bean.Asistente;
import com.bean.Decortina;
import com.bean.Domotik;
import com.bean.OtraMarca;
import com.data.AsistenteDataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Anton
 */
public class Reporte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AsistenteDataSource datasource = new AsistenteDataSource();
        List<Asistente> listAsistente = new ArrayList<>();
        
        for(int i = 0; i<=5; i++){
            
            Asistente asist;
            asist = new Asistente(i, "Nombre: "+i, "Apellido: " + i, "Dni: " + i);
            datasource.addAsistente(asist);
            listAsistente.add(asist);
        }
        
       Map<String, Object> params = new HashMap<>();
       
       File reportFile;
       byte[] bytes = null; 
        
       try {            
           ////////////////////////////////////////////////// generacion reporte 01
           params.put("listAsistente", listAsistente);
           reportFile = new File("src/reports/report1.jasper");
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), params, new JRBeanCollectionDataSource(listAsistente));
            if(bytes != null){
                try (OutputStream out = new FileOutputStream("src/reports/report1.pdf")) {
                    out.write(bytes);
                }
            }
            System.out.println("Report 01");
            
            ////////////////////////////////////////////////// generacion reporte 03
            params = new HashMap<>();
            reportFile = new File("src/reports/report2.jasper");
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), params, new JRBeanCollectionDataSource(listAsistente));
            if(bytes != null){
                try (OutputStream out = new FileOutputStream("src/reports/report2.pdf")) {
                    out.write(bytes);
                }
            }
            System.out.println("Report 02");
            
            ////////////////////////////////////////////////// generacion reporte 03
            params.put("nroCot", "A-1092387");
            params.put("cortinas", getDecortinas());
            params.put("domotik", getDomotik());
            params.put("otramarca", getOtros());
            reportFile = new File("src/reports/report3.jasper");
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), params, new JRBeanCollectionDataSource(listAsistente));
            if(bytes != null){
                try (OutputStream out = new FileOutputStream("src/reports/report3.pdf")) {
                    out.write(bytes);
                }
            }
            System.out.println("Report 03");
            
        } catch (FileNotFoundException ex) {
           System.out.println(ex.getMessage());
        } catch (JRException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static List<Decortina> getDecortinas(){
        List<Decortina> list = new ArrayList<>();        
        list.add(new Decortina(1, "Cortina 1", 2.5, 2.5));
        list.add(new Decortina(1, "Cortina 2", 45.00, 45.00));
        list.add(new Decortina(5, "Cortina 4", 8.00, 40.00));
        return list;
    }
    
    public static List<Domotik> getDomotik(){
        List<Domotik> list = new ArrayList<>();        
        list.add(new Domotik(1, "Domotik 2", 45.00, 45.00));
        return list;
    }
    
    public static List<OtraMarca> getOtros(){
        List<OtraMarca> list = new ArrayList<>();        
        list.add(new OtraMarca(1, "OtraMarca 1", 2.5, 2.5));
        list.add(new OtraMarca(1, "OtraMarca 2", 45.00, 45.00));
        return list;
    }
    
}
