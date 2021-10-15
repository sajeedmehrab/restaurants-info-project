/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectdb;

/**
 *
 * @author User
 */
public class UserOrder {

    private String ORDER_ID;
    private String Restaurant_Name;
    private String Location_Name;
    private String Address;
    private String Contact_Number;
    private String ITEM_TYPE;
    private String ITEM_NAME;
    private String AMOUNT;
    private String ORDER_DATE;
    private String ORDER_STATUS;

    public UserOrder(String ORDER_ID, String Restaurant_Name, String Location_Name, String Address, String Contact_Number, String ITEM_TYPE, String ITEM_NAME, String AMOUNT, String ORDER_DATE, String ORDER_STATUS) {
        this.ORDER_ID = ORDER_ID;
        this.Restaurant_Name = Restaurant_Name;
        this.Location_Name = Location_Name;
        this.Address = Address;
        this.Contact_Number = Contact_Number;
        this.ITEM_TYPE = ITEM_TYPE;
        this.ITEM_NAME = ITEM_NAME;
        this.AMOUNT = AMOUNT;
        this.ORDER_DATE = ORDER_DATE;
        this.ORDER_STATUS = ORDER_STATUS;
    }

    public String getORDER_STATUS() {
        return ORDER_STATUS;
    }

    public void setORDER_STATUS(String ORDER_STATUS) {
        this.ORDER_STATUS = ORDER_STATUS;
    }


    public String getORDER_ID() {
        return ORDER_ID;
    }

    public String getRestaurant_Name() {
        return Restaurant_Name;
    }

    public String getLocation_Name() {
        return Location_Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getContact_Number() {
        return Contact_Number;
    }

    public String getITEM_TYPE() {
        return ITEM_TYPE;
    }

    public String getITEM_NAME() {
        return ITEM_NAME;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public String getORDER_DATE() {
        return ORDER_DATE;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public void setRestaurant_Name(String Restaurant_Name) {
        this.Restaurant_Name = Restaurant_Name;
    }

    public void setLocation_Name(String Location_Name) {
        this.Location_Name = Location_Name;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setContact_Number(String Contact_Number) {
        this.Contact_Number = Contact_Number;
    }

    public void setITEM_TYPE(String ITEM_TYPE) {
        this.ITEM_TYPE = ITEM_TYPE;
    }

    public void setITEM_NAME(String ITEM_NAME) {
        this.ITEM_NAME = ITEM_NAME;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public void setORDER_DATE(String ORDER_DATE) {
        this.ORDER_DATE = ORDER_DATE;
    }
    
    
    
}
