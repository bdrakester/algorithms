/**
 * The BaseballElimination class solves the baseball elimination problem by
 * modeling it as a maxflow problem.
 * 
 * @author Brian Drake
 */

import edu.princeton.cs.algs4.In;
import java.util.HashMap;

public class BaseballElimination {
    private final int numTeams;
    private final HashMap<String,Integer> teams;
    private final int[] wins;
    private final int[] losses;
    private final int[] remaining;
    private final int[][] gamesLeft;
    
    /** 
     * Create a baseball division from given filename containing the 
     * standings.
     * @param name of the file containing the standings.
     */
    public BaseballElimination(String filename) {                    
        In file = new In(filename);
        
        numTeams = file.readInt();
        teams = new HashMap<>();
        wins = new int[numTeams];
        losses = new int[numTeams];
        remaining = new int[numTeams];
        gamesLeft = new int[numTeams][numTeams];
        
        for (int i = 0; i < numTeams; i++) {
            teams.put(file.readString(), i);
            wins[i] = file.readInt();
            losses[i] = file.readInt();
            remaining[i] = file.readInt();
            for (int j = 0; j < numTeams; j++) {
                gamesLeft[i][j] = file.readInt();
            }
        }
    }
    
    /**
     * Return the number of teams.
     * @return the number of teams.
     */
    public int numberOfTeams() {
        return numTeams;
        
    }
    
    /**
     * Return an iterable of all the team names.
     * @return a String iterable of all the team names
     * 
     */
    public Iterable<String> teams() {                             
        return teams.keySet();       
    }
    
    /**
     * Number of wins for given team
     * @param team
     * @return
     */
    public int wins(String team) {
        return 0;
    }
    
    /**
     * Number of losses for given team
     * @param team
     * @return
     */
    public int losses(String team) {
        return 0;    
    }
    
    /**
     * Number of remaining games for given team
     * @param team
     * @return
     */
    public int remaining(String team) {
        return 0;
        
    }
    
    /**
     * Number of remaining games between team1 and team2
     * @param team1
     * @param team2
     * @return
     */
    public int against(String team1, String team2) {
       return 0;
    }
    
    /**
     * Is given team eliminated?
     * @param team
     * @return
     */
    public boolean isEliminated(String team) {
        return false;
    }
    
    /**
     * Subset R of teams that eliminates given team; null if not eliminated
     * @param team
     * @return
     */
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }

    /**
     * Unit tests.
     * @param args
     */
    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        
        System.out.println("division.numberOfTeams() = " + division.numberOfTeams());
        
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                System.out.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    System.out.print(t + " ");
                }
                System.out.println("}");
            }
            else {
                System.out.println(team + " is not eliminated");
            }
        }
       
        
    }
}
