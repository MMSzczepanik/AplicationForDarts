
package tournamentapp;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@DatabaseTable(tableName = "Statistics")
public class Statistic implements Comparable<Statistic>{
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(foreign = true)
    private Player player;
    
    @DatabaseField
    private int nrMatches;
    
    @DatabaseField
    private int legsPlus;
    
    @DatabaseField
    private int legsMinus;
    
    @DatabaseField
    private int legsPlusMinus;
    
    @DatabaseField
    private int points;
    
    @DatabaseField
    private int nrPlace;
    
    @DatabaseField(foreign = true)
    private Tournament tournament;

    public Statistic() {
        
    }

    public Statistic(Tournament tournament, Player player, int nrMatches, int legsPlus, int legsMinus, int points) {
        this.player = player;
        this.nrMatches = nrMatches;
        this.legsPlus = legsPlus;
        this.legsMinus = legsMinus;
        this.points = points;
        this.legsPlusMinus = 0;
        this.tournament = tournament;
    }
    
    

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getNrMatches() {
        return nrMatches;
    }

    public void setNrMatches(int nrMatches) {
        this.nrMatches = nrMatches;
    }

    public int getLegsPlus() {
        return legsPlus;
    }

    public void setLegsPlus(int legsPlus) {
        this.legsPlus = legsPlus;
    }

    public int getLegsMinus() {
        return legsMinus;
    }

    public void setLegsMinus(int legsMinus) {
        this.legsMinus = legsMinus;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLegsPlusMinus() {
        return legsPlusMinus;
    }

    public void setLegsPlusMinus(int legsPlusMinus) {
        this.legsPlusMinus = legsPlusMinus;
    }

    public int getNrPlace() {
        return nrPlace;
    }

    public void setNrPlace(int nrPlace) {
        this.nrPlace = nrPlace;
    }
    
    

    @Override
    public int compareTo(Statistic o) {
        if(points < o.points){
            return 1;
        }else if(points > o.points){
            return -1;
        }else if(legsPlusMinus < o.legsPlusMinus){
            return 1;
        }else if(legsPlusMinus > o.legsPlusMinus){
            return -1;
        }else return 0;                         // dokończyć bezpośredni mecz
    }
    
    public void resetStatistic(){
        this.setLegsMinus(0);
        this.setLegsPlus(0);
        this.setLegsPlusMinus(0);
        this.setNrMatches(0);
        this.setNrPlace(0);
        this.setPoints(0);
    }
     
    public void addToDatabase(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionSource connectionSource;
        try {
            connectionSource = new JdbcConnectionSource(TournamentApp.DB_NAME);
            Dao<Statistic, String> statisticDao = DaoManager.createDao(connectionSource, Statistic.class);
            TableUtils.createTableIfNotExists(connectionSource, Statistic.class);
            
            statisticDao.create(this);
            
            try {
                connectionSource.close();
            } catch (IOException ex) {
                Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateInDatabase(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionSource connectionSource;
        try {
            connectionSource = new JdbcConnectionSource(TournamentApp.DB_NAME);
            Dao<Statistic, String> statisticDao = DaoManager.createDao(connectionSource, Statistic.class);
            TableUtils.createTableIfNotExists(connectionSource, Statistic.class);
            
            statisticDao.update(this);
            
            try {
                connectionSource.close();
            } catch (IOException ex) {
                Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
