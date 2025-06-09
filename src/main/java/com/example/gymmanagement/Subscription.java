package com.example.gymmanagement;

import java.time.LocalDate;

public class Subscription {
    private int id;
    private int memberId;
    private LocalDate subscriptionDate;
    private double amount;

    // Constructors, getters, and setters

    public Subscription(int id, int memberId, LocalDate subscriptionDate, double amount) {
        this.id = id;
        this.memberId = memberId;
        this.subscriptionDate = subscriptionDate;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
