/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package currencyconvertortest;

/**
 *
 * @author lab_services_student
 */
public class UsersClass 
{

    public String getName() {
        return Name;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }
    private String Name;
    private String UserName;
    private String Password;
    
    public UsersClass(String name, String username, String password)
    {
        this.Name = name;
        this.UserName = username;
        this.Password = password;
    }
}
