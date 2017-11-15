
package tournamentapp;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

@DatabaseTable(tableName="Group")
public class Group {
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(foreign = true)
    private Tournament tournament;
    
    @DatabaseField
    private int nrGroup;
    
    @DatabaseField
    private int bestOfScore;
    
    private List<Player> playerList = new ArrayList<Player>(); // lista zawodników w grupie
    
    private List<Statistic> statisticList = new ArrayList<Statistic>(); // lista staystyk w grupie
    
    private List<Match> matchList = new ArrayList<Match>(); //lista meczów w grupie
    
    public Group(){
        
    }

    public Group(Tournament tournament, int nrGroup, int bestOfScore) {
        this.tournament = tournament;
        this.nrGroup = nrGroup;
        this.bestOfScore = bestOfScore;
    }
    
    // numer grupy i lista zawodników w grupie
    public Group(Tournament tournament, int nrGroup, List playerList, int bestOfScore){
        this.tournament = tournament;
        this.nrGroup = nrGroup;
        this.playerList = playerList;
        this.bestOfScore = bestOfScore;
        
        generateStatistic();
    }

    public int getNrGroup() {
        return nrGroup;
    }

    public void setNrGroup(int nrGroup) {
        this.nrGroup = nrGroup;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Statistic> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(List<Statistic> statisticList) {
        this.statisticList = statisticList;
    }
    
    public void addPlayer(Player player){
        playerList.add(player);
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }
    
    
    
    public void generateMatches(){
        //generowanie strutkury meczów
        int playerCount = playerList.size();
        
        List<Player> tmpPlayerList = new ArrayList<Player>(playerList);
        for(int i=0; i<playerCount-1; i++){
            for(int j=1; j<tmpPlayerList.size(); j++){
                matchList.add(new Match(tmpPlayerList.get(0),tmpPlayerList.get(j),this.bestOfScore));
            }
            tmpPlayerList.remove(0);
        }
    }
    
    // generowanie tablicy statystyk
    public void generateStatistic(){
        for(Player player: this.playerList){
            statisticList.add(new Statistic(this.tournament, player,0,0,0,0));
        }
        
        //dodawanie do bazy danych
        for(Statistic statistic : statisticList){
            statistic.addToDatabase();
        }
    }
    
    // uaktualnianie statystyk
    public void updateStatistic(){
        
        this.resetAllStatistic();
        
        for(Match match : matchList){
            if(match.isEnd() && !match.isBadScore()){
                Player player1 = match.getPlayer1();
                Player player2 = match.getPlayer2();

                for(Statistic statistic : statisticList){
                    if(statistic.getPlayer() == player1){
                        statistic.setNrMatches(statistic.getNrMatches()+1);
                        statistic.setLegsPlus(statistic.getLegsPlus()+match.getScore1());
                        statistic.setLegsMinus(statistic.getLegsMinus()+match.getScore2());
                        statistic.setLegsPlusMinus(statistic.getLegsPlus()-statistic.getLegsMinus());
                        if(player1 == match.getWinner()) {
                            statistic.setPoints(statistic.getPoints()+2);
                        }
                    }
                }

                for(Statistic statistic : statisticList){
                    if(statistic.getPlayer() == player2){
                        statistic.setNrMatches(statistic.getNrMatches()+1);
                        statistic.setLegsPlus(statistic.getLegsPlus()+match.getScore2());
                        statistic.setLegsMinus(statistic.getLegsMinus()+match.getScore1());
                        statistic.setLegsPlusMinus(statistic.getLegsPlus()-statistic.getLegsMinus());
                        if(player2 == match.getWinner()) {
                            statistic.setPoints(statistic.getPoints()+2);
                        }
                    }
                }
            }
        }
        this.generatePlace();
        
        for(Statistic statistic : statisticList){
            statistic.updateInDatabase();
        }
    }
    
    // metoda generująca wpisywanie w statystykach numer miejsca i sortująca statystyki
    public void generatePlace(){
        Collections.sort(statisticList);
        
        int nrPlace = 1;
        for(Statistic statistic : statisticList){
            statistic.setNrPlace(nrPlace);
            nrPlace++;
        }
    }
    
    // reset statystyk
    public void resetAllStatistic(){
        for(Statistic statistic : statisticList){
            statistic.resetStatistic();
        }
    }
    
    // dodawanie grupy do bazy danych
    public void addToDatabase(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionSource connectionSource;
        try {
            connectionSource = new JdbcConnectionSource(TournamentApp.DB_NAME);
            Dao<Group, String> groupDao = DaoManager.createDao(connectionSource, Group.class);
            
            TableUtils.createTableIfNotExists(connectionSource, Group.class);

            groupDao.create(this);
            
            try {
                connectionSource.close();
            } catch (IOException ex) {
                Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            //generowanie błędu
            JOptionPane.showMessageDialog(null, "Błąd dodawania grupy do bazy danych.", "Błąd",JOptionPane.ERROR_MESSAGE );
            
        }
    }
    
}
