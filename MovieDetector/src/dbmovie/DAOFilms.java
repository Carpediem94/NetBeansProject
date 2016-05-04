/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmovie;

import java.sql.ResultSet;

/**
 *
 * @author carpediem
 */
public interface DAOFilms {
    public void open();
    
    public void close();
    
    public ResultSet getData(String... param);
        
    public void setData(String... param);
}
