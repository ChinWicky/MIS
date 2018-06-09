package com.example.demo.model;

import com.example.demo.entity.SevSalesDetails;

public class Item {
    String queck;
    String proName;
    SevSalesDetails sevSalesDetails;
    public Item(String queck, SevSalesDetails sevSalesDetails){
        this.queck=queck;
        this.sevSalesDetails=sevSalesDetails;
    }
    public Item( SevSalesDetails sevSalesDetails){
        this.sevSalesDetails=sevSalesDetails;
    }
    public Item(){
    }
            public String getCheck(){
        return queck;
    }
    public void setCheck(String queck){
       this.queck=queck;
    }
    public String getProName(){
        return proName;
    }
    public void setProName(String proName){
        this.proName=proName;
    }
       public void SetSevSalesDetails(SevSalesDetails sevSalesDetails){
        this.sevSalesDetails=sevSalesDetails;
    }
    public SevSalesDetails gettSevSalesDetails(){
        return sevSalesDetails;
    }


}
