
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
import java.util.logging.Level;
import java.util.logging.Logger;

@DatabaseTable(tableName = "Matches")
public class Match {
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(foreign = true)
    private Player player1;
    
    @DatabaseField(foreign = true)
    private Player player2;
    
    @DatabaseField
    private int score1;
    
    @DatabaseField
    private int score2;
    
    @DatabaseField(foreign = true)
    private Match nextMatch;
    
    @DatabaseField
    private int bestOfScore;
    
    @DatabaseField
    private int boardNumber;

    public Match() {
    }

    public Match(Player player1, Player player2, int score1, int score2, Match nextMatch, int bestOfScore) {
        this.player1 = player1;
        this.player2 = player2;
        this.score1 = score1;
        this.score2 = score2;
        this.nextMatch = nextMatch;
        this.bestOfScore = bestOfScore;
    }

    public Match(Player player1, Player player2, int score1, int score2, int bestOfScore) {
        this.player1 = player1;
        this.player2 = player2;
        this.score1 = score1;
        this.score2 = score2;
        this.bestOfScore = bestOfScore;
    }

    public Match(Player player1, Player player2, int bestOfScore) {
        this.player1 = player1;
        this.player2 = player2;
        this.bestOfScore = bestOfScore;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public Match getNextMatch() {
        return nextMatch;
    }

    public void setNextMatch(Match nextMatch) {
        this.nextMatch = nextMatch;
    }

    public int getBestOfScore() {
        return bestOfScore;
    }

    public void setBestOfScore(int bestOfScore) {
        this.bestOfScore = bestOfScore;
    }

    public int getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(int boardNumber) {
        this.boardNumber = boardNumber;
    }
    
    
    
    public Player getWinner(){
        if(score1 > score2){
            return player1;
        }else if (score2 > score1){
            return player2;
        } else return new Player();
    }
    
    //do poprawki
    public boolean isEnd(){
        if(score1 == bestOfScore || score2 == bestOfScore && !(score1 > bestOfScore || score2 > bestOfScore || score1 < 0 || score2 < 0 )){
            return true;
        }else {
            return false;
        }
    }
    
    public boolean isBadScore(){
        if(score1 > bestOfScore || score2 > bestOfScore || score1 < 0 || score2 < 0 ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return player1 + " vs " + player2;
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
            Dao<Match, String> matchDao = DaoManager.createDao(connectionSource, Match.class);
            TableUtils.createTableIfNotExists(connectionSource, Match.class);
            
            matchDao.create(this);
            
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
            Dao<Match, String> matchDao = DaoManager.createDao(connectionSource, Match.class);
            TableUtils.createTableIfNotExists(connectionSource, Match.class);
            
            matchDao.update(this);
            
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
