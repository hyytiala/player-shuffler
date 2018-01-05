package main;

import hali.data_access.*;

import java.sql.SQLException;

import hali.ui.TerminalIO;
import hali.ui.TextUI;

        public class Main {

        public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Database database = new Database("murhaajat.db");
            DbUserDAO dao = new DbUserDAO(database);
            TextUI ui = new TextUI(new TerminalIO(), dao);
            ui.run();
        }
    }