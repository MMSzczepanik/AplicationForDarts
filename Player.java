
package tournamentapp;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@DatabaseTable(tableName = "Players")
public class Player {
    
    @DatabaseField
    private String firstName;
    
    @DatabaseField
    private String lastName;
    
    @DatabaseField(id = true)
    private String nickName;
    
    @DatabaseField
    private String city;


    public Player(String firstName, String lastName, String nickName, String City) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        if(nickName.isEmpty()){
            this.nickName = firstName + " " + lastName;
        } 
        this.city = City;
    }
    
    public Player(){};

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String City) {
        this.city = City;
    }

    @Override
    public String toString() {
        return nickName;
    }
    
    public void addToDatabase() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionSource connectionSource;

            connectionSource = new JdbcConnectionSource(TournamentApp.DB_NAME);
            Dao<Player, String> playerDao = DaoManager.createDao(connectionSource, Player.class);
            TableUtils.createTableIfNotExists(connectionSource, Player.class);
            
            playerDao.create(this);
            
            try {
                connectionSource.close();
            } catch (IOException ex) {
                Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    
    public void updateInDatabase() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionSource connectionSource;
        
            connectionSource = new JdbcConnectionSource(TournamentApp.DB_NAME);
            Dao<Player, String> playerDao = DaoManager.createDao(connectionSource, Player.class);
            TableUtils.createTableIfNotExists(connectionSource, Player.class);
            
            playerDao.update(this);
            
            try {
                connectionSource.close();
            } catch (IOException ex) {
                Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
    }
    
    public void deleteInDatabase() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionSource connectionSource;
        
            connectionSource = new JdbcConnectionSource(TournamentApp.DB_NAME);
            Dao<Player, String> playerDao = DaoManager.createDao(connectionSource, Player.class);
            TableUtils.createTableIfNotExists(connectionSource, Player.class);
            
            playerDao.delete(this);
            
            try {
                connectionSource.close();
            } catch (IOException ex) {
                Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
