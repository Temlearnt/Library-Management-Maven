package com.sprinkle.models;

import java.sql.Date;

public class MemberModel {
    private int memberId;
    private String username;
    private String Name;
    private String email;
    private String phone;
    private String address;
    private Date registrationDate;


    // Konstruktor kosong
    public MemberModel() {}

    // Konstruktor lengkap
    public MemberModel(int memberId, String username, String Name, String email, String phone, String address, Date registrationDate) {
        this.memberId = memberId;
        this.username = username;
        this.Name = Name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    // Getter & Setter
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public Date getRegisteredAt() {
    return registrationDate;
}
    
    @Override
    public String toString() {
        return Name;  // atau username + " - " + Name
    }

}
