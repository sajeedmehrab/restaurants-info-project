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
public class Report {
    
    private String Item_Type;
    private String Item_Name;
    private String Total_Sold;

    public Report(String Item_Type, String Item_Name, String Total_Sold) {
        this.Item_Type = Item_Type;
        this.Item_Name = Item_Name;
        this.Total_Sold = Total_Sold;
    }

    public String getItem_Type() {
        return Item_Type;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public String getTotal_Sold() {
        return Total_Sold;
    }

    public void setItem_Type(String Item_Type) {
        this.Item_Type = Item_Type;
    }

    public void setItem_Name(String Item_Name) {
        this.Item_Name = Item_Name;
    }

    public void setTotal_Sold(String Total_Sold) {
        this.Total_Sold = Total_Sold;
    }
    
    
    



    
}
