/**
 * The BaseballElimination class solves the baseball elimination problem by
 * modeling it as a maxflow problem.
 * 
 * @author Brian Drake
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FordFulkerson;
import java.util.HashMap;
import java.util.ArrayList;

public class BaseballElimination {
    private final int numTeams;
    private final HashMap<String, Integer> teams;
    private final int[] wins;
    private final int[] losses;
    private final int[] remaining;
    private final int[][] gamesLeft;
    private final ArrayList<ArrayList<String>> eliminators;
    private final boolean[] eliminated;
    // private int totalRemaining;
    
    /** 
     * Create a baseball division from given filename containing the 
     * standings.
     * @param name of the file containing the standings.
     */
    public BaseballElimination(String filename) {                    
        In file = new In(filename);
        
        // Read the number to teams from file
        numTeams = file.readInt();
        
        // Initialize instance variables
        teams = new HashMap<>();
        wins = new int[numTeams];
        losses = new int[numTeams];
        remaining = new int[numTeams];
        gamesLeft = new int[numTeams][numTeams];
        eliminators = new ArrayList<>(numTeams);
        eliminated = new boolean[numTeams];
        for (int i = 0; i < numTeams; i++) {
            eliminators.add(i, new ArrayList<>());
            eliminated[i] = false;
        }
        // totalRemaining = 0;
        
                
        for (int i = 0; i < numTeams; i++) {
            teams.put(file.readString(), i);
            wins[i] = file.readInt();
            losses[i] = file.readInt();
            remaining[i] = file.readInt();
            // totalRemaining += remaining[i];
            for (int j = 0; j < numTeams; j++) {
                gamesLeft[i][j] = file.readInt();
            }
        }
        
        // Determine if each team is eliminated
        for (String team : teams()) {
            if (isTrivialEliminated(team)) {
                eliminated[teams.get(team)] = true;    
            }
            else if (isNonTrivialEliminated(team)) {
                eliminated[teams.get(team)] = true;
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
     * Number of wins for given team.
     * @param team the name of the team.
     * @return the team's number of wins.
     */
    public int wins(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("Team does not exist.");
        }
        
        return wins[teams.get(team)];
    }
    
    /**
     * Number of losses for given team.
     * @param team the name of the team.
     * @return the team's number of losses.
     */
    public int losses(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("Team does not exist.");
        }
        
        return losses[teams.get(team)];    
    }
    
    /**
     * Number of remaining games for given team.
     * @param team the name of the team.
     * @return the team's number of remaining games.
     */
    public int remaining(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("Team does not exist.");
        }
        
        return remaining[teams.get(team)];
        
    }
    
    /**
     * Number of remaining games between team1 and team2.
     * @param team1 the name of the first team.
     * @param team2 the name of the second team.
     * @return the number of games remaining between team1 and team2.
     */
    public int against(String team1, String team2) {
        if (!teams.containsKey(team1) || !teams.containsKey(team2)) {
            throw new IllegalArgumentException("Team does not exist.");
        }
        
       return gamesLeft[teams.get(team1)][teams.get(team2)];
    }
    
    /**
     * Is given team eliminated?
     * @param team
     * @return
     */
    public boolean isEliminated(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("Team does not exist.");
        }
        
        return eliminated[teams.get(team)];
    }
    
    /**
     * If the team is trivially eliminated return true, else return false.
     * A team is trivially eliminated if the maximum number of games the 
     * team can win is less than the number of wins of another team.
     * @param team the name of the team.
     * @return true if team is trivially elimnated, else false.
     */
    private boolean isTrivialEliminated(String team) {
        int maxWins = wins[teams.get(team)] + remaining[teams.get(team)];
        
        for (String t : teams()) {
            if (maxWins < wins(t)) {
                eliminators.get(teams.get(team)).add(t);               
            }
        }
        
        return !eliminators.get(teams.get(team)).isEmpty();
    }
    
    /**
     * If the team is nontrivially eliminated return true, else return false.
     * The method models the problem as a flow network and solve the max flow
     * problem in it.
     * @param team the name of the team.
     * @return true if team is nontrivially eliminated, else false.
     */
    private boolean isNonTrivialEliminated(String team) {
        int combinations = (numTeams-1) * (numTeams-2) / 2;
        int v = combinations + numTeams + 1;
        // double totalGamesLeft = 0;
        
        // BEGIN DEBUG
        // System.out.println("\nisNonTrivial(" + team + ") ...");
        // System.out.println("combinations = " + combinations);
        // System.out.println("v = " + v);
        // System.out.println("numTeams = " + numTeams);
        // END DEBUG
        
        FlowNetwork network = new FlowNetwork(v);

        int w = 1;
        int incI = 0;
        for (int i = 0; i < numTeams - 2; i++) {
            if (i == teams.get(team)) incI = 1;
            int incJ = 0;
            for (int j = i + 1; j < numTeams - 1; j++) {
                if (j >= teams.get(team)) incJ = 1;
                network.addEdge(new FlowEdge(0, w, (double) gamesLeft[i + incI][j + incJ]));
                // totalGamesLeft += (double) gamesLeft[i + incI][j + incJ];
                // BEGIN DEBUG
                // System.out.println("w = " + w + " i = " + i + " incI =  " + incI + " j = " + j + "  incJ = " + incJ);
                // END DEBUG
                network.addEdge(new FlowEdge(w, combinations + 1 + i, Double.POSITIVE_INFINITY));
                network.addEdge(new FlowEdge(w, combinations + 1 + j, Double.POSITIVE_INFINITY));
                w++;
            }
        }
        
        incI = 0;
        int maxWins = wins[teams.get(team)] + remaining[teams.get(team)];
        for (int i = 0; i < numTeams - 1; i++) {
            if (i == teams.get(team)) incI = 1;
            network.addEdge(new FlowEdge(combinations + 1 + i, v-1, maxWins - wins[i + incI]));
        }

        // BEGIN DEBUG
        // System.out.println("network = " + network.toString());
        // END DEBUG
        
        FordFulkerson ff = new FordFulkerson(network, 0, v-1);
        
        // BEGIN DEBUG
        // System.out.println("totalGamesLeft = " + totalGamesLeft);
        // System.out.println("totalRemaining = " + totalRemaining);
        // System.out.println("maxflow = " + ff.value());
        // System.out.println("network after ff = " + network.toString());
        // END DEBUG
        
        
        
        // if (ff.value() == totalGamesLeft) return false;
        
        // If any edge pointing from s is not full, team is eliminated
        for (FlowEdge e : network.adj(0)) {
            if (e.flow() != e.capacity()) {
                // Team is eliminated, populate eliminators with mincut
                for (String t : teams()) {
                    if (t.equals(team)) continue;
                    if (teams.get(t) < teams.get(team)) {
                        if (ff.inCut(combinations + 1 + teams.get(t))) {
                            eliminators.get(teams.get(team)).add(t);
                        }
                    }
                    else if (ff.inCut(combinations + teams.get(t))) {
                        eliminators.get(teams.get(team)).add(t);
                    }
                }
                return true;
            }
        }
        // Every edge was full, team not eliminated
        return false;
    } 
    
    /**
     * Subset R of teams that eliminates given team; null if not eliminated.
     * @param team the name of the team.
     * @return an iterable of team names that eliminate team.
     */
    public Iterable<String> certificateOfElimination(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("Team does not exist.");
        }
        
        if (eliminators.get(teams.get(team)).isEmpty()) return null;
        else return eliminators.get(teams.get(team));
    }

    /**
     * Unit tests.
     * @param args
     */
    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        
        // System.out.println("division.numberOfTeams() = " + division.numberOfTeams());
        
        /*
        for (String team : division.teams()) {
            System.out.println("division.wins(" + team + ") = " + division.wins(team));
            System.out.println("division.losses(" + team + ") = " + division.losses(team));
            System.out.println("division.reamaining(" + team + ") = " + division.remaining(team));
        }
        
        for (String team1 : division.teams()) {
            for (String team2 : division.teams()) {
                System.out.println("division.against(" + team1 + ", " + team2 + ") = " + division.against(team1, team2));
            }
        }
        */
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
