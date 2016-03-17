package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    Hashtable<String, SoccerPlayer> names = new Hashtable<String, SoccerPlayer>();

    public String nameToKey(String firstN, String lastN) {
        return firstN + "##" + lastN;
    }

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        String fullName = nameToKey(firstName, lastName);

        if (names.containsKey(fullName)) {
            return false;
        }
        else {
            names.put(fullName, new SoccerPlayer(firstName, lastName, uniformNumber, teamName));
            return true;
        }
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String fullName = nameToKey(firstName, lastName);

        if (names.containsKey(fullName)) {
            names.remove(fullName);
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        String fullName = nameToKey(firstName, lastName);

        return names.get(fullName);
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String fullName = nameToKey(firstName, lastName);

        SoccerPlayer player = names.get(fullName);

        if (player == null) {
            return false;
        }
        else {
            player.bumpGoals();
            return true;
        }
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String fullName = nameToKey(firstName, lastName);

        SoccerPlayer player = names.get(fullName);

        if (player == null) {
            return false;
        }
        else {
            player.bumpAssists();
            return true;
        }
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String fullName = nameToKey(firstName, lastName);

        SoccerPlayer player = names.get(fullName);

        if (player == null) {
            return false;
        } else {
            player.bumpShots();
            return true;
        }
    }
    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String fullName = nameToKey(firstName, lastName);

        SoccerPlayer player = names.get(fullName);

        if (player == null) {
            return false;
        }
        else {
            player.bumpSaves();
            return true;
        }
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String fullName = nameToKey(firstName, lastName);

        SoccerPlayer player = names.get(fullName);

        if (player == null) {
            return false;
        }
        else {
            player.bumpFouls();
            return true;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String fullName = nameToKey(firstName, lastName);

        SoccerPlayer player = names.get(fullName);

        if (player == null) {
            return false;
        }
        else {
            player.bumpYellowCards();
            return true;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String fullName = nameToKey(firstName, lastName);

        SoccerPlayer player = names.get(fullName);

        if (player == null) {
            return false;
        }
        else {
            player.bumpRedCards();
            return true;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        int count = 0;

        if (teamName == null) {
            for (String name : names.keySet()){
                count++;
            }
        }
        else {
            for (SoccerPlayer player : names.values()){
                if (player.getTeamName().equals(teamName)){
                    count++;
                }
            }
        }

        return count;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {

        int count = 0;
        if (teamName == null) {
            for (String name : names.keySet()){
                if (count == idx) {
                    return names.get(name);
                }
               count++;
            }
        }
        else {
            for (String name : names.keySet()){
                if (names.get(name).getTeamName().equals(teamName)){
                    if (count == idx) {
                        return names.get(name);
                    }
                   count++;
                }
            }
        }

        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {

        try {
            Scanner scan = new Scanner(file);

            String firstN;
            String lastN;
            String team;
            String uniformN;
            String goals;
            String assists;
            String shots;
            String saves;
            String fouls;
            String yellowC;
            String redC;

            while(scan.hasNextLine()) {
                firstN = scan.nextLine();
                lastN = scan.nextLine();
                team = scan.nextLine();
                uniformN = scan.nextLine();
                goals = scan.nextLine();
                assists = scan.nextLine();
                shots = scan.nextLine();
                saves = scan.nextLine();
                fouls = scan.nextLine();
                yellowC = scan.nextLine();
                redC = scan.nextLine();

                String fullName = nameToKey(firstN, lastN);




                    SoccerPlayer play = new SoccerPlayer(firstN, lastN, Integer.parseInt(uniformN),team);

                    for(int i = 0; i < Integer.parseInt(goals); i++) {
                        play.bumpGoals();
                    }

                    for(int i = 0; i < Integer.parseInt(assists); i++) {
                        play.bumpAssists();

                    }

                    for(int i = 0; i < Integer.parseInt(shots); i++) {
                        play.bumpShots();

                    }

                    for(int i = 0; i < Integer.parseInt(saves); i++) {
                        play.bumpSaves();

                    }

                    for(int i = 0; i < Integer.parseInt(fouls); i++) {
                        play.bumpFouls();

                    }

                    for(int i = 0; i < Integer.parseInt(yellowC); i++) {
                        play.bumpYellowCards();

                    }

                    for(int i = 0; i < Integer.parseInt(redC); i++) {
                        play.bumpRedCards();

                    }

                    Log.i("write String", firstN);
                    names.put(fullName,play);

            }
        }
        catch (FileNotFoundException e) {

        }


        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {

        try {
            PrintWriter pw = new PrintWriter(file);

            for (SoccerPlayer player : names.values()) {
                pw.println(logString(player.getFirstName()));
                pw.println(logString(player.getLastName()));
                pw.println(logString(player.getTeamName()));
                pw.println(logString("" + player.getUniform()));
                pw.println(logString("" + player.getGoals()));
                pw.println(logString("" + player.getAssists()));
                pw.println(logString("" + player.getShots()));
                pw.println(logString("" + player.getSaves()));
                pw.println(logString("" + player.getFouls()));
                pw.println(logString("" + player.getYellowCards()));
                pw.println(logString("" + player.getRedCards()));
            }

            pw.close();
            return true;
        }
        catch (FileNotFoundException e) {
            return false;
        }
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
//        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        HashSet<String> teamN = new HashSet<String>();

        for (SoccerPlayer player : names.values()) {

                teamN.add(player.getTeamName());

        }


        return teamN;
	}

}
