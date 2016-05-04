/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmovie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carpediem
 */
public class DataFilmsDB implements DAOFilms {
    private Connection con;
    
    @Override
    public void open() {
        String driverClassName = "";
        String dbURL = "";
        String user = "";
        String password = "";
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(dbURL, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataFilmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void close() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataFilmsDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public ResultSet getData(String... param) {
        ResultSet rs = null;
        try {
            PreparedStatement stm = con.prepareStatement(param[0]);
            for(int i=1; i<param.length; i++){
                stm.setString(i, param[i]);
            }
            rs = stm.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataFilmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    @Override
    public void setData(String... param) {
        try {
            PreparedStatement stm = con.prepareStatement(param[0]);
            for(int i=1; i<param.length; i++){
                stm.setString(i, param[i]);
            }
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataFilmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
