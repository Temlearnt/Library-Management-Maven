package com.sprinkle.models;

public class AdminModel {
    private int adminId;
    private String username;
    private String password;
    private String Name;
    private String email;
    private String phone;
    private String role;

    public AdminModel() {}

    // Constructor lengkap (dengan password)
    public AdminModel(int adminId, String username, String password, String Name, String email, String phone, String role) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.Name = Name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // ✅ Constructor tanpa password untuk query SELECT di DAO
    public AdminModel(int adminId, String username, String Name, String email, String phone, String role) {
        this.adminId = adminId;
        this.username = username;
        this.Name = Name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Getter & Setter ...
    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return Name + " (" + role + ")";
    }

}
