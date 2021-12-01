import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;



public class javafyClient //implements Runnable

{
	static String currentSong = new String("");
	static Queue<String> trackQueue = new LinkedList<String>();
	static Queue<String> prevQueue = new LinkedList<String>();
	
//	public static void play(){
//		//Resumes the current song
//	}
//	
//	public static void play(Song song){
//		//Skips the queue and current song and plays the song specified. Must be formatted as /play.
//	}
//	
//	 public static void pause(){
//		 //Stops the current song. Must be formatted as /pause
//	}
//	 
//	 
//	 public static void queue(Song song, List playlist){//Puts the specified media in queue behind and adds a playlist to a queue
//		 //Queue<Song> trackQueue = new LinkedList<Song>();
//		 //playlist = bag(playlist);
//		 
//		 //may want to remove the list playlist parameter and leave playlist as a variable
//		 for(int i = 0; i <= playlist.size(); i++){
//			 trackQueue.offer((Song) playlist.get(i));
//		 }
//		 
//		 trackQueue.offer(song);
//	}
//	
//	 public static List bag(List playlist){
//	 	//Puts the specified media in bag for randomization
//	}
//	 
//	 public static void priorityQueue (Song song, List playlist){
//	 	//Puts the specified media before anything else in the queue
//	}
//
	 public static void clear (){//Clears the entire queue with the exception of the song currently playing
		 String s = trackQueue.poll();
		 
		 trackQueue.clear();
		 
		 trackQueue.add(s);
	 }
//	 
//	 public static void skip(String timestamp) {
//		 //Skips to the specified timestamp of current song
//		 //[Produces error if time specified is longer than song]
//	}
//	 
	 public static void next() {//Skips to the next song in queue
		 String s = trackQueue.poll();
		 
		 prevQueue.add(s);
		 
		 if(s == null){
			 System.out.println("No additional tracks in queue");
			 System.exit(1);
		 }
	}
//	 
	 public static void back() {//Plays the last song played
		 Queue<String> tempQueue = new LinkedList<String>();
		 String p = prevQueue.poll();
		  
		 if(p == null){
			 System.out.println("No previous tracks in queue");
			 System.exit(1);
		 }
		 
		 tempQueue.add(p);
		 tempQueue.addAll(trackQueue);
		 
		 trackQueue.clear();
		 trackQueue.addAll(tempQueue);
	}
//	 
//	 public static void restart() {
//		 //Starts the current song from the beginning
//	}
//	 
//	 public static void return() {//most likely type will change to song instead of void.
//		 //Shows the previous display [returns error if display is at start]
//		 
//	}
//	 
//	 public static void select(List playlist){
//	 //Shows the contents of the specified playlist or album
//	}
//	 
	public static void show(){//Shows the queue
		int tQ = trackQueue.size();
		Queue<String> tempQueue = new LinkedList<String>();
		
		for(int i = 0; i <= tQ; i++){
			String s = trackQueue.poll();
			tempQueue.add(s);
			System.out.printf("%d", s);
		}
	}
	
	private static String currentLevel;
	public static void main(String[] args) {
	    currentSong = "Music/mmad.wav";
	    audioPlayer player = new audioPlayer();
	    player.changeSong(currentSong);
	    Application.launch(audioPlayer.class,args);
			Scanner sc = new Scanner(System.in);
			//keeps track of current level for return command
			//topLevel, playlistLevel, albumLevel, songLevel
			setCurrentLevel("topLevel");
			//playlist / album / song selection level
			int topLevel = 0;
			
			
			System.out.println("Welcome to Javafy!");
			System.out.println("input the number of which menu you'd prefer to open:");
			
			//make method later for return statement to call!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			while (getCurrentLevel().equals("topLevel")){
				setCurrentLevel("topLevel");
				
				System.out.println("[1]Playlists:		[2]Albums:							[3]Songs:");
				int in1 = sc.nextInt();
				if (in1 == 1||in1 == 2||in1 == 3) {
				topLevel = in1;
				}
				else {
				System.out.println("Ivalid input: " + in1);
				System.out.println("Please enter a valid number (1-3)");
				}
			
			
	
			switch (topLevel) {
			case 1:
				setCurrentLevel("playlistLevel");
				playlistLevel();
				break;
			case 2:
				currentLevel = "albumLevel";
				albumLevel();
				break;
			case 3:
				currentLevel = "songLevel";
				songLevel();
				break;
			}
		}
	}
		
	public static void playlistLevel(){
		Scanner sc = new Scanner(System.in);
		System.out.println("[1]playlist 1");
		System.out.println("[2]playlist 2");
		System.out.println("");
		System.out.println("To view the contents of the playlist you want to view enter the playlist #/select");
		System.out.println("To view the list of commands for interacting with the playlist type \"/help\"");
		
		String playlistCommand = sc.nextLine();
		
		switch(playlistCommand) {
		case "1/select":
			playlist1();
		break;
		case "2/select":
			playlist2();
		break;
		case "/return":
			setCurrentLevel("TopLevel");
			break;
		default:
			commandTaker(playlistCommand);
			playlistCommand = sc.nextLine();
			playlistLevel();
		break;
		}
	}

	public static void playlist1(){
		setCurrentLevel("playlist1");
		Scanner sc = new Scanner(System.in);
		System.out.println("[1]Feels like summer - Childish Gambino");
		System.out.println("[2]Summertime magic - Childish Gambino");
		System.out.println("[3]Once in a Lifetime - Talking Heads");
		System.out.println("");
		System.out.println("To view the list of commands for interacting with the playlist's songs type \"/help\"");
		
		String playlistSongCommand = sc.nextLine();
		
		switch(playlistSongCommand) {
		case "/return":
			setCurrentLevel("playlistLevel");
		break;
		default:
			commandTaker(playlistSongCommand);
			playlist1();
		break;
		}
	}

	public static void playlist2() {
			setCurrentLevel("playlist2");
			Scanner sc = new Scanner(System.in);
			System.out.println("[1]Feels like summer - Childish Gambino");
			System.out.println("[2]Summertime magic - Childish Gambino");
			System.out.println("");
			System.out.println("To view the list of commands for interacting with the playlist's songs type \"/help\"");
			
			String playlistSongCommand = sc.nextLine();
			
			switch(playlistSongCommand) {
			case "/return":
				setCurrentLevel("playlistLevel");
			break;
			default:
				commandTaker(playlistSongCommand);
				playlist2();
			break;
			}
	}

	public static void albumLevel() {
		setCurrentLevel("albumLevel");
		Scanner sc = new Scanner(System.in);
		System.out.println("[1]Summer Pack - Childish Gambino");
		System.out.println("[2]");
		System.out.println("");
		System.out.println("To view the contents of the album you want to view enter the album #/select");
		System.out.println("To view the list of commands for interacting with the album type \"/help\"");
		
		String albumCommand = sc.nextLine();
		
		switch(albumCommand) {
		case "1/select":
			album1();
		break;
		case "2/select":
			album2();
		break;
		case "/return":
			setCurrentLevel("topLevel");
		break;
		default:
			commandTaker(albumCommand);
			albumCommand = sc.nextLine();
		break;
		}
	}

	private static void album1() {
		// TODO Auto-generated method stub
		
	}

	private static void album2() {
		// TODO Auto-generated method stub
		
	}

	public static void songLevel() {
		setCurrentLevel("songLevel");
		Scanner sc = new Scanner(System.in);
		System.out.println("[1]Feels like summer - Childish Gambino");
		System.out.println("[2]Summertime magic - Childish Gambino");
		System.out.println("[3]Once in a Lifetime - Talking Heads");
		System.out.println("");
		System.out.println("To view the list of commands for interacting with the album type \"/help\"");
		
		String albumCommand = sc.nextLine();
		
		switch(albumCommand) {
		case "/return":
			setCurrentLevel("topLevel");
		break;
		default:
			commandTaker(albumCommand);
			albumCommand = sc.nextLine();
		break;
		}
	}


	//redirects commands
	public static void commandTaker(String command){
		if (command.equals("/help")) {
			help();
			}
		
		if (command.equals("/")) {
			//commandmethod();
			}
		}
		
	public static void help(){
		System.out.println("\"/help\" - Shows list of commands");
		System.out.println("\"/command\" - ");
		System.out.println("\"/command\" - ");
		System.out.println("\"/command\" - ");
		System.out.println("\"/command\" - ");
		System.out.println("\"/command\" - ");
		return;
	}
	
	public static String getCurrentLevel(){
		return currentLevel;
		
	}
	public static void setCurrentLevel(String lvl){
		currentLevel = lvl;
	}
	
	//commandTaker(playlistCommand);
	//playlistCommand = sc.nextLine();

}