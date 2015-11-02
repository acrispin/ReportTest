/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.data;

import com.bean.Asistente;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Anton
 */
public class AsistenteDataSource implements JRDataSource{

    private List<Asistente> listaAsistente = new ArrayList<Asistente>();
    private int indiceParticipanteActual = -1;
    
    @Override
    public boolean next() throws JRException {
       return ++indiceParticipanteActual < listaAsistente.size();
    }
    
    public void addAsistente(Asistente Asistente){

        this.listaAsistente.add(Asistente);

    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
       Object valor = null;

        if ("id".equals(jrf.getName())){

            valor = listaAsistente.get(indiceParticipanteActual).getId();

        }
        else if ("nombre".equals(jrf.getName())){

            valor = listaAsistente.get(indiceParticipanteActual).getNombre();

        }
        else if ("apellidos".equals(jrf.getName())){

            valor = listaAsistente.get(indiceParticipanteActual).getApellidos();

        }
        else if ("dni".equals(jrf.getName())){
            valor = listaAsistente.get(indiceParticipanteActual).getDni();
        }

        return valor;
    }
    
}
