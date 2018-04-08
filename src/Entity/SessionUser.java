/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author aziz
 */
public class SessionUser {
    
    private static Integer id;
    private static String username;
    private static String email;
    private static String password;
    private static String roles;
    private static String phoneNumber;
    private static String addresse;

    public SessionUser(Integer id, String username, String email, String password, String roles, String phoneNumber, String addresse) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.phoneNumber = phoneNumber;
        this.addresse = addresse;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        SessionUser.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SessionUser.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionUser.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SessionUser.password = password;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        SessionUser.roles = roles;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        SessionUser.phoneNumber = phoneNumber;
    }

    public static String getAddresse() {
        return addresse;
    }

    public static void setAddresse(String addresse) {
        SessionUser.addresse = addresse;
    }

    @Override
    public String toString() {
        return "SessionUser{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", roles=" + roles + ", phoneNumber=" + phoneNumber + ", addresse=" + addresse + '}';
    }
    
}
