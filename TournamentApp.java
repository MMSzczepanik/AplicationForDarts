
package tournamentapp;

import java.util.ArrayList;
import java.util.List;


public class TournamentApp {
    
    public static final String DB_NAME = "jdbc:sqlite:tournament.db";

    public static void main(String[] args) {
        
        
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(new Player("","","Yogi",""));
        playerList.add(new Player("","","Karton",""));
        playerList.add(new Player("","","Biszkopt",""));
        playerList.add(new Player("","","Mati",""));
        playerList.add(new Player("","","Młody",""));
        playerList.add(new Player("","","Włodek",""));
        playerList.add(new Player("","","Kubek",""));
        playerList.add(new Player("","","Kasprzyk",""));
        playerList.add(new Player("","","Zielony",""));
        playerList.add(new Player("","","Sobol",""));
        playerList.add(new Player("","","Liściu",""));
        playerList.add(new Player("","","Strażak",""));
        playerList.add(new Player("","","Strażak2",""));
        
        //Tournament ligue1 = new Tournament("RJ",playerList, 4, 10, 3);
        //ligue1.showGroup();
            new MainFrame();
        //    new PlayerViewFrame();
        //    new PlayerAddFrame();
        
        
        
    }
    
}
