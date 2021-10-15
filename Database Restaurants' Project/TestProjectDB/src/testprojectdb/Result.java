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
public class Result {
    private String Restaurant_Name;
    private String Location_Name;    
    private String Address;
    private String Item_Type;
    private String Item_Name;
    private String Price;
    private String Offer;
    private String Rating;

    public Result(String Restaurant_Name, String Location_Name, String Address, String Item_Type, String Item_Name, String Price, String Offer, String Rating) {
        this.Restaurant_Name = Restaurant_Name;
        this.Location_Name = Location_Name;
        this.Address = Address;
        this.Item_Type = Item_Type;
        this.Item_Name = Item_Name;
        this.Price = Price;
        this.Offer = Offer;
        this.Rating = Rating;
    }
        
    public String getRestaurant_Name() {
        return Restaurant_Name;
    }

    public String getLocation_Name() {
        return Location_Name;
    }

    public String getItem_Type() {
        return Item_Type;
    }
    

    public String getAddress() {
        return Address;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public String getPrice() {
        return Price;
    }

    public String getOffer() {
        return Offer;
    }

    public String getRating() {
        return Rating;
    }
    

    public void setRestaurant_Name(String Restaurant_Name) {
        this.Restaurant_Name = Restaurant_Name;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setItem_Name(String Item_Name) {
        this.Item_Name = Item_Name;
    }

    public void setLocation_Name(String Location_Name) {
        this.Location_Name = Location_Name;
    }

    public void setItem_Type(String Item_Type) {
        this.Item_Type = Item_Type;
    }
    

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public void setOffer(String Offer) {
        this.Offer = Offer;
    }

    public void setRating(String Rating) {
        this.Rating = Rating;
    }
    
    
    
    
}
