package com.mywork.vipramilk.entity;


import androidx.annotation.NonNull;

import androidx.room.ColumnInfo;

import androidx.room.Entity;

import androidx.room.PrimaryKey;


import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = "customer_table")
public class CustomerData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("customer_id")
    @Expose
    @ColumnInfo(name = "customer_id")
    private int customerId = 0;

    @SerializedName("customer_serial_no")
    @Expose
    @ColumnInfo(name = "customer_serial_no")
    private int customerSerialNo;

    @SerializedName("customer_name")
    @Expose
    @ColumnInfo(name = "customer_name")
    private String customerName;

    @SerializedName("address")
    @Expose
    @ColumnInfo(name = "address")
    private String address;

    @SerializedName("contact_one")
    @Expose
    @ColumnInfo(name = "contact_one")
    private String contactOne;

    @SerializedName("contact_two")
    @Expose
    @ColumnInfo(name = "contact_two")
    private String contactTwo;

    @SerializedName("contact_whatsapp")
    @Expose
    @ColumnInfo(name = "contact_whatsapp")
    private String contactWhatsapp;

    @SerializedName("contact_email")
    @Expose
    @ColumnInfo(name = "contact_email")
    private String contactEmail;

    @SerializedName("qty_one_ltr")
    @Expose
    @ColumnInfo(name = "qty_one_ltr")
    private int qtyOneLtr;

    @SerializedName("qty_half_ltr")
    @Expose
    @ColumnInfo(name = "qty_half_ltr")
    private int qtyHalfLtr;

    @SerializedName("route_id")
    @Expose
    @ColumnInfo(name = "route_id")
    private int routeId;

    @SerializedName("route_sequence")
    @Expose
    @ColumnInfo(name = "route_sequence")
    private int routeSequence;

    @SerializedName("delivery_on")
    @Expose
    @ColumnInfo(name = "delivery_on")
    private String deliveryOn;

    @SerializedName("rate")
    @Expose
    @ColumnInfo(name = "rate")
    private double rate;

    @SerializedName("delivery_charges")
    @Expose
    @ColumnInfo(name = "delivery_charges")
    private double deliveryCharges;

    @SerializedName("date")
    @Expose
    @ColumnInfo(name = "date")
    private String date;

    @SerializedName("is_active")
    @Expose
    @ColumnInfo(name = "is_active")
    private boolean isActive;


    public int getcustomerId() {
        return customerId;

    }

    public void setcustomerId(int customerId) {
        this.customerId = customerId;

    }

    public int getcustomerSerialNo() {
        return customerSerialNo;

    }

    public void setcustomerSerialNo(int customerSerialNo) {
        this.customerSerialNo = customerSerialNo;

    }

    public String getcustomerName() {
        return customerName;

    }

    public void setcustomerName(String customerName) {
        this.customerName = customerName;

    }

    public String getAddress() {
        return address;

    }

    public void setAddress(String address) {
        this.address = address;

    }

    public String getContactOne() {
        return contactOne;

    }

    public void setContactOne(String contactOne) {
        this.contactOne = contactOne;

    }

    public String getContactTwo() {
        return contactTwo;

    }

    public void setContactTwo(String contactTwo) {
        this.contactTwo = contactTwo;

    }

    public String getContactWhatsapp() {
        return contactWhatsapp;

    }

    public void setContactWhatsapp(String contactWhatsapp) {
        this.contactWhatsapp = contactWhatsapp;

    }

    public int getQtyOneLtr() {
        return qtyOneLtr;

    }

    public void setQtyOneLtr(int qtyOneLtr) {
        this.qtyOneLtr = qtyOneLtr;

    }

    public int getQtyHalfLtr() {
        return qtyHalfLtr;

    }

    public void setQtyHalfLtr(int qtyHalfLtr) {
        this.qtyHalfLtr = qtyHalfLtr;

    }

    public int getRouteId() {
        return routeId;

    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;

    }

    public int getRouteSequence() {
        return routeSequence;

    }

    public void setRouteSequence(int routeSequence) {
        this.routeSequence = routeSequence;

    }

    public String getDeliveryOn() {
        return deliveryOn;

    }

    public void setDeliveryOn(String deliveryOn) {
        this.deliveryOn = deliveryOn;

    }

    public double getRate() {
        return rate;

    }

    public void setRate(double rate) {
        this.rate = rate;

    }

    public double getDeliveryCharges() {
        return deliveryCharges;

    }

    public void setDeliveryCharges(double deliveryCharges) {
        this.deliveryCharges = deliveryCharges;

    }

    public String getDate() {
        return date;

    }

    public void setDate(String date) {
        this.date = date;

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerSerialNo() {
        return customerSerialNo;
    }

    public void setCustomerSerialNo(int customerSerialNo) {
        this.customerSerialNo = customerSerialNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

