package com.assign1.brianlu.sizebook;




/**
 * Created by brianlu on 2017-02-01.
 */

public class Record {
    private String name;
    private String date;
    private Double neck;
    private Double bust;
    private Double chest;
    private Double waist;
    private Double hip;
    private Double inseam;
    private String comment;

    public Record(String name, String date, Double neck, Double bust, Double chest, Double waist, Double hip, Double inseam, String comment){
        this.date = date;
        this.name = name;
        this.neck = neck;
        this.bust = bust;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.inseam = inseam;
        this.comment = comment;
    }
//    public Record(String name){
//        this.name = name;
////        this.date = null;
////        this.neck = null;
////        this.bust = null;
////        this.chest = null;
////        this.waist = null;
////        this.hip = null;
////        this.inseam = null;
////        this.comment = null;
//    }

    public String getName(){
        return this.name;
    }
    public String getDate(){
        return this.date;
    }
    public String getNeck(){
        return String.format("%.1f", this.neck);
    }
    public String getBust(){
        return String.format("%.1f", this.bust);
    }
    public String getChest(){
        return String.format("%.1f", this.chest);
    }
    public String getWaist(){
        return String.format("%.1f", this.waist);
    }
    public String getHip(){
        return String.format("%.1f", this.hip);
    }
    public String getInseam(){
        return String.format("%.1f", this.inseam);
    }
    public String getComment(){
        return this.comment;
    }

    public String stringDate(){
        return date +";";}
    public String stringName(){ return name;}
    public String stringNeck() {
        if (neck >= 0.0) {
            return " Neck->" + String.format("%.1f", neck)+" inches;";
        } else{
            return "";
        }
    }
    public String stringBust(){
        if (bust >= 0.0) {
            return " Bust->" + String.format("%.1f", bust) +" inches;";
        } else{
            return "";
        }
    }
    public String stringChest(){
        if (chest >= 0.0) {
            return " Chest->" + String.format("%.1f", chest) +" inches;";
        } else{
            return "";
        }
    }
    public String stringWaist(){
        if (waist >= 0.0) {
            return " Waist->" + String.format("%.1f", waist) +" inches;";
        } else{
            return "";
        }
    }
    public String stringHip(){
        if (hip >= 0.0) {
            return " Hip->" + String.format("%.1f", hip) +" inches;";
        } else{
            return "";
        }
    }
    public String stringInseam(){
        if (inseam >= 0.0) {
            return " Inseam->" + String.format("%.1f", inseam) +" inches;";
        } else{
            return "";
        }
    }
    public String stringComment(){ return comment;}


    public void setDate(String date){
        this.date = date;
    }

    public void setName(String name) throws StringTooLongException {
        if(name.length() > 100){
            throw new StringTooLongException();
        } else {
            this.name = name;
        }
    }

    public void setNeck(Double neck) throws NoNegativeValueException{
        if(neck < 0.0){
            throw new NoNegativeValueException();
        }else{
            this.neck = neck;
        }
    }

    public void setBust(Double bust) throws NoNegativeValueException{
        if(bust < 0.0){
            throw new NoNegativeValueException("None negative number please!");
        }else{
            this.bust = bust;
        }
    }
    public void setChest(Double chest) throws NoNegativeValueException{
        if(chest < 0.0){
            throw new NoNegativeValueException("None negative number please!");
        }else{
            this.chest = chest;
        }
    }
    public void setWaist(Double waist) throws NoNegativeValueException{
        if(waist < 0.0){
            throw new NoNegativeValueException("None negative number please!");
        }else{
            this.waist = waist;
        }
    }
    public void setHip(Double hip) throws NoNegativeValueException{
        if(hip < 0.0){
            throw new NoNegativeValueException("None negative number please!");
        }else{
            this.hip = hip;
        }
    }
    public void setComment(String comment) throws StringTooLongException{
        if(comment.length() > 200){
            throw new StringTooLongException();
        }else{
            this.comment = comment;
        }
    }
    public void setInseam(Double inseam) throws NoNegativeValueException{
        if(inseam < 0.0){
            throw new NoNegativeValueException("None negative number please!");
        }else{
            this.inseam = inseam;
        }
    }


    @Override
    public String toString(){
        return this.stringName() + ": "+ this.stringDate() + this.stringNeck() + this.stringBust() + this.stringChest() + this.stringWaist() +
                this.stringHip() + this.stringInseam() + this.stringComment();
    }


}

