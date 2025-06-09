package com.example.gymmanagement;

public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private String phone;
    private String membershipType;
    private String email;

    public Member(int memberId, String firstName, String lastName, String phone, String membershipType, String email) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.membershipType = membershipType;
        this.email = email;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", membershipType='" + membershipType + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
