/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test;

import com.bean.PersonBean;
import com.bean.Asistente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Anton
 */
public class TestFactory {
    public static Collection getCollection(){
        Vector coll = new Vector();
        coll.add(new PersonBean("Jose", 12));
        coll.add(new PersonBean("Juan", 10));
        coll.add(new PersonBean("Pepe", 15));
        coll.add(new PersonBean("Mario", 13));
        coll.add(new PersonBean("Pedro", 21));
        coll.add(new PersonBean("Alvaro", 19));
        coll.add(new PersonBean("Rodrigo", 17));
        return coll;
    }
    
    public static PersonBean[] getArray(){
        PersonBean[] list = new PersonBean[6];
        list[0]= (new PersonBean("Ted", 20));
        list[1]= (new PersonBean("Jack", 34));
        list[2]= (new PersonBean("Bob", 56));
        list[3]= (new PersonBean("Alice", 12));
        list[4]= (new PersonBean("Robin", 22));
        list[5]= (new PersonBean("Peter", 28));
        return list;
    }
    
    public static List<Asistente> getAsistentes(){
        List<Asistente> listAsistente = new ArrayList<>();
        
        for(int i = 0; i<=5; i++){
            
            Asistente asist;
            asist = new Asistente(i, "Name : "+i, "LastName : " + i, "DNI : " + i);
            listAsistente.add(asist);
        }
        
        return listAsistente;
    }
}
