/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bean;

/**
 *
 * @author Anton
 */
public class Domotik {
    private Integer cant = 1;
    private String desc = "";
    private Double pri = 1.0;
    private Double sub = 1.0;

    public Domotik() {
    }
    
    public Domotik(Integer cant, String desc, Double pri, Double sub){
        this.cant = cant;
        this.desc = desc;
        this.pri = pri;
        this.sub = sub;
    }

    /**
     * @return the cant
     */
    public Integer getCant() {
        return cant;
    }

    /**
     * @param cant the cant to set
     */
    public void setCant(Integer cant) {
        this.cant = cant;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the pri
     */
    public Double getPri() {
        return pri;
    }

    /**
     * @param pri the pri to set
     */
    public void setPri(Double pri) {
        this.pri = pri;
    }

    /**
     * @return the sub
     */
    public Double getSub() {
        return sub;
    }

    /**
     * @param sub the sub to set
     */
    public void setSub(Double sub) {
        this.sub = sub;
    }
}
