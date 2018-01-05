/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hali.ui;

import hali.data_access.DbUserDAO;
import hali.domain.User;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Otto
 */
public class TextUI {

    private IO io;
    private DbUserDAO dao;

    public TextUI(IO io, DbUserDAO dao) {
        this.io = io;
        this.dao = dao;
    }

    public void run() throws SQLException {

        io.println("Halimurhaaja generaattori");

        loop:
        while (true) {

            io.println("Komento (1=uusi pelaaja, 2=listaa pelaajat, 3=poista lista, 4=luo peli, \"\"=lopeta):");
            String input = io.nextLine();

            switch (input) {

                case "1":
                    io.println();
                    add();
                    break;

                case "2":
                    io.println();
                    list();
                    break;

                case "3":
                    io.println();
                    deleteRun();
                    break;
                    
                case "4":
                    io.println();
                    peli();
                    break;
                    
                case "":

                    break loop;

                default:
                    io.println("Tuntematon komento.");
                    break;

            }
        }
            io.println("Loppu");
        
    }
    
     private void deleteRun() throws SQLException {
        deleteloop:
        while (true) {
            io.println("Komento (1=kaikki, 2=yksi pelaaja, \"\"=palaa):");
            String input = io.nextLine();

            switch (input) {

                case "1":
                    poisto();
                    break deleteloop;
                case "2":
                    poistaNimi();
                    break deleteloop;
                case "":
                    break deleteloop;
                default:
                    io.println("Tuntematon komento.");
                    break;
            }
        }
    }
    
    private void add() throws SQLException {
        io.println("Pelaajan nimi: ");
        String name = io.nextLine();
        User user = new User(name);
        dao.save(user);
    }
    
    private void list() throws SQLException{
        ArrayList<User> users = new ArrayList();
        users = dao.getAll();
        for (User u : users){
            io.println(u.getName());
        }
    }
    
    private void poisto() throws SQLException{
        dao.delete();
        io.println("Pelaajat poistettu!");
    }
    
    private void poistaNimi() throws SQLException {
        io.println("Poistettavan nimi: ");
        String name = io.nextLine();
        dao.deleteByName(name);
        io.println("Poistettu!");
    }
    
    private void peli() throws SQLException{
        ArrayList<User> users = new ArrayList();
        users = dao.getAll();
        Collections.shuffle(users);
        int size = users.size();
        for (int i = 0; i<size-1; i++){
            String s = "";
            User u = users.get(i);
            User v = users.get(i+1);
            s += u.getName();
            s += " --> ";
            s += v.getName();
            io.println(s);
        }
        User u = users.get(size-1);
        User v = users.get(0);
        String s = "";
        s += u.getName();
        s += " --> ";
        s += v.getName();
        io.println(s);
        io.println();
    }
}
