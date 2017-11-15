
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
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

@DatabaseTable(tableName = "Tournament")
public class Tournament {
    
    public static List<Integer> boardList = new LinkedList<Integer>();
    
    @DatabaseField(id = true)
    private String date;
    
    @DatabaseField
    private String Name;
    
    private List<Player> playerList;
    
    private List<Group> groupList = new ArrayList<Group>();

    private int groupCount;
    
    @DatabaseField
    private int tax;
    
    public Tournament(){
        
    }
    
    // lista graczy
    public Tournament(String name, List<Player> playerList, int boardCount, int tax, int bestOfGroup){
        this.Name = name;
        this.date = getDate();
        this.playerList = playerList;
        this.tax = tax;
        
        //generowanie tablicy tarcz
        for(int i=1; i<=boardCount; i++){
            boardList.add(i);
        }
        // generowanie grup
        int playerCount = this.playerList.size();
        if(playerCount<6) groupCount = 1;
        if(playerCount>=6 && playerCount<12) groupCount = 2;
        if(playerCount>=12 && playerCount<24) groupCount = 4;
        if(playerCount>=24 && playerCount<48) groupCount = 8;
        if(playerCount>=48 && playerCount<96) groupCount = 16;
        
        //dodawanie turnieju do bazy dancyh
        //this.addToDatabase();
        
        generateGroup(bestOfGroup);
    }
    
    public void generateGroup(int bestOf){
        Collections.shuffle(playerList);
        for(int i=0; i<groupCount; i++){
            groupList.add(new Group(this,i+1,bestOf));
        }

        for(int i=0;i<playerList.size();i++){   // dla każdego gracza
            this.groupList.get(i % groupCount).addPlayer(playerList.get(i));   
        }
        for(Group group : groupList){        // dla każdej grupy
            group.generateStatistic();
            group.generateMatches();
            group.addToDatabase();       // zmodyfikuj
        }
    }
    
    public void showGroup(){
        new GroupFrame(groupList);
    }
    
    public static void reserveBoard(int nrBoard){
        Tournament.boardList.remove(nrBoard);
    }
    
    public static void unreserveBoard(int nrBoard){
        Tournament.boardList.add(nrBoard);
        //Collections.sort(boardList);
        
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
            Dao<Tournament, String> tournamentDao = DaoManager.createDao(connectionSource, Tournament.class);
            
            TableUtils.createTableIfNotExists(connectionSource, Tournament.class);

            tournamentDao.create(this);
            
            try {
                connectionSource.close();
            } catch (IOException ex) {
                Logger.getLogger(TournamentApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            //generowanie błędu
            JOptionPane.showMessageDialog(null, "Błąd dodawania turnieju do bazy danych.", "Błąd",JOptionPane.ERROR_MESSAGE );
            
        }
    }
    
    public static String getDate(){
        Date data = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setGregorianChange(data);
        String second = "";
        String minute = "";
        
        
        if(calendar.get(SECOND)<10){
            second = "0" + String.valueOf(calendar.get(SECOND));
        }else{
            second = String.valueOf(calendar.get(SECOND));
        }
        
        if(calendar.get(MINUTE)<10){
            minute = "0" + String.valueOf(calendar.get(MINUTE));
        }else{
            minute = String.valueOf(calendar.get(MINUTE));
        }
        
        String result = "" + calendar.get(DAY_OF_MONTH) + " " + 
                        Month[calendar.get(MONTH)] + " " +
                        calendar.get(YEAR) + " " +
                        calendar.get(HOUR)+ ":" +
                        minute + ":" +
                        second;
        
        return result;
    }
    
    private static String[] Month = {"STYCZEŃ","LUTY","MARZEC",
                             "KWIECIEŃ","MAJ","CZERWIEC",
                             "LIPIEC","SIERPIEŃ","WRZESIEŃ",
                             "PAŹDZIERNIK","LISTOPAD","GRUDZIEŃ"};
}
