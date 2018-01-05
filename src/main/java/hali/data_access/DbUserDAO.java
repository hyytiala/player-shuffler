/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hali.data_access;

import hali.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Otto
 */
public class DbUserDAO {
    private final Database database;

    public DbUserDAO(Database database) {
        this.database = database;
    }

    public void save(User user) {
        ArrayList values = new ArrayList();

        values.add(user.getName());

        database.executeQueryUpdate("INSERT INTO User "
                    + "(name)"
                    + " VALUES (?)", values);
        database.closeConnection();
    }

    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList();

        ResultSet rS = database.executeQuerySelect("SELECT * FROM User", new ArrayList());

        try {
            while (rS.next()) {
                User user = new User(rS.getString("name"));
                
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        try {
            if (rS != null) {
                rS.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        database.closeConnection();
        
        return users;
    }
    
    public void delete(){
        database.executeQuery("DELETE FROM User");
        database.closeConnection();
    }
    
    public void deleteByName(String name){
        ArrayList values = new ArrayList();

        values.add(name);

        database.executeQueryUpdate(" DELETE FROM "
                    + "User"
                    + " WHERE name=?", values);
        database.closeConnection();
    }
}
