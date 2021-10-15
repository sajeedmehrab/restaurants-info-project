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
public class Orders {

    private String ORDER_ID;
    private String FULL_NAME;
    private String USER_NUMBER;
    private String EMAIL;
    private String ITEM_TYPE;
    private String ITEM_NAME;
    private String AMOUNT;
    private String ORDER_DATE;
    private String COMMENTS;
    private String ORDER_STATUS;

    public Orders(String ORDER_ID, String FULL_NAME, String USER_NUMBER, String EMAIL, String ITEM_TYPE, String ITEM_NAME, String AMOUNT, String ORDER_DATE, String COMMENTS, String ORDER_STATUS) {
        this.ORDER_ID = ORDER_ID;
        this.FULL_NAME = FULL_NAME;
        this.USER_NUMBER = USER_NUMBER;
        this.EMAIL = EMAIL;
        this.ITEM_TYPE = ITEM_TYPE;
        this.ITEM_NAME = ITEM_NAME;
        this.AMOUNT = AMOUNT;
        this.ORDER_DATE = ORDER_DATE;
        this.COMMENTS = COMMENTS;
        this.ORDER_STATUS = ORDER_STATUS;
    }

    public String getORDER_STATUS() {
        return ORDER_STATUS;
    }

    public void setORDER_STATUS(String ORDER_STATUS) {
        this.ORDER_STATUS = ORDER_STATUS;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public void setUSER_NUMBER(String USER_NUMBER) {
        this.USER_NUMBER = USER_NUMBER;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
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

    public void setCOMMENTS(String COMMENTS) {
        this.COMMENTS = COMMENTS;
    }

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public String getUSER_NUMBER() {
        return USER_NUMBER;
    }

    public String getEMAIL() {
        return EMAIL;
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

    public String getCOMMENTS() {
        return COMMENTS;
    }

}
