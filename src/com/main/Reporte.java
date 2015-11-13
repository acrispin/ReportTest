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
import com.lowagie.text.pdf.codec.Base64;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
            sendRequestPdf(bytes);
            
            ////////////////////////////////////////////////// generacion reporte 02
//            params = new HashMap<>();
//            reportFile = new File("src/reports/report2.jasper");
//            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), params, new JRBeanCollectionDataSource(listAsistente));
//            if(bytes != null){
//                try (OutputStream out = new FileOutputStream("src/reports/report2.pdf")) {
//                    out.write(bytes);
//                }
//            }
//            System.out.println("Report 02");
            
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
            sendRequestPdf(bytes);
            
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
   
 
    public static void sendRequestPdf(byte[] bytes) {
        try {
            // com.lowagie.text.pdf.codec.Base64;
            String encodedString = Base64.encodeBytes(bytes);
            //System.out.println(encodedString);
            
            URL url = new URL("http://localhost:8000/api/rest/data/save/pdf");
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("cod", "348");
            params.put("nom", "test nom");
            params.put("pdf", encodedString);
            
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Authorization", "Token 2ccb6e398170f60d172cfc671735c11f3a3c2e0f");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            
            /*
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            for ( int c = in.read(); c != -1; c = in.read() ){
                System.out.print((char)c);
            }
            */
            
            BufferedReader in2 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = in2.readLine()) != null){
                System.out.println(line);
            }
            
        } catch (MalformedURLException | UnsupportedEncodingException  ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
