/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectdb;

/**
 *
 * @author Hp
 */
public class MenuResults {
    private String Item_Name;
    private String Item_Type;
    private String Offer;
    private String Price;

    public MenuResults(String Item_Name, String Item_Type, String Offer, String Price) {
        this.Item_Name = Item_Name;
        this.Item_Type = Item_Type;
        this.Offer = Offer;
        this.Price = Price;
    }

    public String getOffer() {
        return Offer;
    }

    public void setOffer(String Offer) {
        this.Offer = Offer;
    }


    public String getItem_Name() {
        return Item_Name;
    }

    public String getItem_Type() {
        return Item_Type;
    }

    public String getPrice() {
        return Price;
    }

    public void setItem_Name(String Item_Name) {
        this.Item_Name = Item_Name;
    }

    public void setItem_Type(String Item_Type) {
        this.Item_Type = Item_Type;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }
    
    
}
