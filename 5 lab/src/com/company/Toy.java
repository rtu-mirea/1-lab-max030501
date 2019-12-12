package com.company;


public class Toy  {
    private String manufacturer;
    private float cost;
    private int kidLimitation;
    private int[] ageInterval = new int[2];
    Toy(){
        manufacturer = "None";
        cost = -1;
        kidLimitation = -1;
        ageInterval = new int[]{-1, -1};
    }
    Toy(String manufacturer, float cost, int kidLimitation, int[] ageInterval){
        this.manufacturer = manufacturer;
        this.cost = cost;
        this.kidLimitation = kidLimitation;
        this.ageInterval = ageInterval;
    }
    void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }
    void setCost(float cost){
        this.cost = cost;
    }
    void setKidLimitation(int kidLimitation){
        this.kidLimitation = kidLimitation;
    }
    void setAgeInterval(int[] ageInterval){
        this.ageInterval = ageInterval;
    }
    String getManufacturer(){
        return this.manufacturer;
    }
    float getCost(){ return this.cost;}
    int getKidLimitation(){ return this.kidLimitation;}
    int[] getAgeInterval(){ return this.ageInterval;}
    @Override
    public String toString(){
        return "Производитель: " + manufacturer +"\n"+"Стоимость: " + cost + "\n" + "Возрастное ограничение: " + kidLimitation + "+" + "\n" + "Возрастной интервал - от " + ageInterval[0] + " до " + ageInterval[1];
    }
}
