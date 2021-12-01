import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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



public class javafyClient extends Thread

{
	static String currentSong = new String("");
	static Queue<String> trackQueue = new LinkedList<String>();
	static Queue<String> prevQueue = new LinkedList<String>();
	private static String currentLevel;
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Start of main
	public static void main(String[] args) {
		
		currentSong = "Music/mmad.wav";
	    javafyClient thread = new javafyClient();
	    thread.start();


			Scanner sc = new Scanner(System.in);
			//keeps track of current level for return command
			//topLevel, playlistLevel, albumLevel, songLevel
			setCurrentLevel("topLevel");
			//playlist / album / song selection level
			
			
			System.out.println("Welcome to Javafy!");
			System.out.println("use #/select to choose");
			
			while (getCurrentLevel().equals("topLevel")){
				setCurrentLevel("topLevel");
				
				System.out.println("[1]Playlists:		[2]Albums:		[3]Songs:");
				String in1 = sc.nextLine();
				commandTaker(in1);
				}
	}
//	public static void play(){
//		//Resumes the current song
//	}
//	
//	public static void play(String song){
//		//Skips the queue and current song and plays the song specified. Must be formatted as /play.
//	}
//	
//	 public static void pause(){
//		 //Stops the current song. Must be formatted as /pause
//	}
//	 
//	 
	 public static void queue(String song, List playlist){//Puts the specified media in queue behind and adds a playlist to a queue
		 //Queue<Song> trackQueue = new LinkedList<Song>();
		 //playlist = bag(playlist);
		 
		 //may want to remove the list playlist parameter and leave playlist as a variable
		 for(int i = 0; i <= playlist.size(); i++){
			 trackQueue.offer((String) playlist.get(i));
		 }
		 
		 trackQueue.offer(song);
		 refresh();
	}
//	
//	 public static List bag(List playlist){
//	 	//Puts the specified media in bag for randomization
//	}
//	 
	 public static void priorityQueue (String song, List playlist){//Puts the specified media before anything else in the queue
		 trackQueue.offer(song);
		 
		 for(int i = 0; i <= playlist.size(); i++){
			 trackQueue.offer((String) playlist.get(i));
		 }
		 
		 refresh();
	 }

	 public static void clear (){//Clears the entire queue with the exception of the song currently playing
		 String s = trackQueue.poll();
		 
		 trackQueue.clear();
		 
		 trackQueue.add(s);
		 refresh();
	 }
//	 
//	 public static void skip(String timestamp) {
//		 //Skips to the specified timestamp of current song
//		 //[Produces error if time specified is longer than song]
//	}
	 
	 public static void next() {//Skips to the next song in queue
		 String s = trackQueue.poll();
		 
		 prevQueue.add(s);
		 
		 if(s == null){
			 System.out.println("No additional tracks in queue");
			 System.exit(1);
		 }
		 refresh();
	}
	 

	 public static void last() {//Plays the last song played
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
		 refresh();
	}
 
//	 public static void restart() {
//		 //Starts the current song from the beginning
//	} 

	 public static void back(){//most likely type will change to song instead of void.
		 //Shows the previous display [returns error if display is at start]
		 String lvl = getCurrentLevel();
		 
		 switch(lvl) {
			case "topLevel":
				setCurrentLevel("topLevel");
				System.out.print("You're already at the home page and con't go back any further...");
				home();
			break;
			case "playlistLevel":
				setCurrentLevel("topLevel");
				home();
			break;
			case "albumLevel":
				setCurrentLevel("topLevel");
				home();
			break;
			case "songLevel":
				setCurrentLevel("topLevel");
				home();
			break;
			case "playlist1":
				setCurrentLevel("playlistLevel");
				playlistLevel();
			break;
			case "playlist2":
				setCurrentLevel("playlistLevel");
				playlistLevel();
			break;
			case "album1":
				setCurrentLevel("albumLevel");
				albumLevel();
			break;
			case "album2":
				setCurrentLevel("albumLevel");
				albumLevel();
			break;
			}
		} 
	 
	public static void show(){//Shows the queue
		int tQ = trackQueue.size();
		Queue<String> tempQueue = new LinkedList<String>();
		
		for(int i = 0; i <= tQ; i++){
			String s = trackQueue.poll();
			tempQueue.add(s);
			System.out.printf("%d", s);
		}
		refresh();
	}
	 
public static void select(String command){
 //Shows the contents of the specified playlist or album
	 String lvl = getCurrentLevel();
	 
	 switch(lvl) {
		case "topLevel":

			switch (command) {
			case "1/select":
				setCurrentLevel("playlistLevel");
				playlistLevel();
				break;
			case "2/select":
				setCurrentLevel("albumLevel");
				albumLevel();
				break;
			case "3/select":
				setCurrentLevel("songLevel");
				songLevel();
				break;
			default:
				System.out.println("Ivalid input: " + command);
				System.out.println("Please enter a valid number (1-3)\"/return\"");
			break;
			}
		break;
		case "playlistLevel":
			switch (command) {
			case "1/select":
				setCurrentLevel("playlist1");
				playlist1();
				break;
			case "2/select":
				setCurrentLevel("playlist2");
				playlist2();
				break;
			}
		break;
		case "albumLevel":
			switch (command) {
			case "1/select":
				setCurrentLevel("album1");
				album1();
				break;
			case "2/select":
				setCurrentLevel("album2");
				album2();
				break;
			}
			break;
		}
}

//goes on the end of anything that is not a select or return function/ method (refresh)
public static void refresh(){
	 String lvl = getCurrentLevel();
	 
	 switch(lvl) {
		case "topLevel":
			setCurrentLevel("topLevel");
			home();
		break;
		case "playlistLevel":
			setCurrentLevel("playlistLevel");
			playlistLevel();
		break;
		case "albumLevel":
			setCurrentLevel("albumLevel");
			albumLevel();
		break;
		case "songLevel":
			setCurrentLevel("songLevel");
			songLevel();
		break;
		case "playlist1":
			setCurrentLevel("playlist1");
			playlist1();
		break;
		case "playlist2":
			setCurrentLevel("playlist2");
			playlist2();
		break;
		case "album1":
			setCurrentLevel("album1");
			album1();
		break;
		case "album2":
			setCurrentLevel("album2");
			album2();
		break;
		}

}
	
//calls top level
	public static void home(){
		Scanner sc = new Scanner(System.in);
		while (getCurrentLevel().equals("topLevel")){
			setCurrentLevel("topLevel");
			
			System.out.println("[1]Playlists:		[2]Albums:		[3]Songs:");
			String in1 = sc.nextLine();
			commandTaker(in1);
			}
		}
//calls playlists
	public static void playlistLevel(){
		Scanner sc = new Scanner(System.in);
		System.out.println("[1]playlist 1");
		System.out.println("[2]playlist 2");
		System.out.println("");
		System.out.println("To view the contents of the playlist you want to view enter the playlist #/select");
		System.out.println("To view the list of commands for interacting with the playlist type \"/help\"");
		
		String playlistCommand = sc.nextLine();
		
			commandTaker(playlistCommand);

	}
//playlist 1 menu
	public static void playlist1(){
		setCurrentLevel("playlist1");
		Scanner sc = new Scanner(System.in);
		System.out.println("[1]Summertime magic - Childish Gambino");
		System.out.println("[2]MOUNTAIN - Monsune");
		System.out.println("[3]JADE - Monsune");
		System.out.println("");
		System.out.println("To view the list of commands for interacting with the playlist's songs type \"/help\"");
		
		String playlistSongCommand = sc.nextLine();
		
			commandTaker(playlistSongCommand);

	}
//playlist 2 menu
	public static void playlist2() {
			setCurrentLevel("playlist2");
			Scanner sc = new Scanner(System.in);
			System.out.println("[1]Feels like summer - Childish Gambino");
			System.out.println("[2]CLOUD - Monsune");
			System.out.println("[3]OUTTA MY MIND - Monsune");
			System.out.println("[4]1998 - Monsune");
			System.out.println("");
			System.out.println("To view the list of commands for interacting with the playlist's songs type \"/help\"");
			
			String playlistSongCommand = sc.nextLine();
			
			commandTaker(playlistSongCommand);
	}
//album menu
	public static void albumLevel() {
		setCurrentLevel("albumLevel");
		Scanner sc = new Scanner(System.in);
		System.out.println("[1]Summer Pack - Childish Gambino");
		System.out.println("[2]Tradition - Monsune");
		System.out.println("");
		System.out.println("To view the contents of the album you want to view enter the album #/select");
		System.out.println("To view the list of commands for interacting with the album type \"/help\"");
		
		String albumCommand = sc.nextLine();
			commandTaker(albumCommand);
	}
	//album 2 menu
		public static void album2() {
			setCurrentLevel("album2");
			Scanner sc = new Scanner(System.in);
			System.out.println("[1]1998 - Monsune");
			System.out.println("[2]CLOUD - Monsune");
			System.out.println("[3]OUTTA MY MIND - Monsune");
			System.out.println("[4]MOUNTAIN - Monsune");
			System.out.println("[5]JADE - Monsune");
			System.out.println("");
			System.out.println("To view the list of commands for interacting with the album type \"/help\"");
			
			String albumCommand = sc.nextLine();
				commandTaker(albumCommand);
			
		}
//album 1 menu
	public static void album1() {
		setCurrentLevel("album1");
		Scanner sc = new Scanner(System.in);
		System.out.println("[1]Feels like summer - Childish Gambino");
		System.out.println("[2]Summertime magic - Childish Gambino");
		System.out.println("");
		System.out.println("To view the list of commands for interacting with the album type \"/help\"");
		
		String albumCommand = sc.nextLine();
			commandTaker(albumCommand);
		
	}

//song menu / picker
	public static void songLevel() {
		setCurrentLevel("songLevel");
		Scanner sc = new Scanner(System.in);
		System.out.println("[1]Feels like summer - Childish Gambino");
		System.out.println("[2]Summertime magic - Childish Gambino");
		System.out.println("[3]Once in a Lifetime - Talking Heads");
		System.out.println("[4]1998 - Monsune");
		System.out.println("[5]CLOUD - Monsune");
		System.out.println("[6]OUTTA MY MIND - Monsune");
		System.out.println("[7]MOUNTAIN - Monsune");
		System.out.println("[8]JADE - Monsune");
		System.out.println("");
		System.out.println("To view the list of commands for interacting with the album type \"/help\"");
		
		String songCommand = sc.nextLine();
			commandTaker(songCommand);
	}


//redirects commands
	public static void commandTaker(String command){
		switch(command) {
		case "1/select":
			select(command);
		break;
		case "2/select":
			select(command);
		break;
		case "3/select":
			select(command);
		break;
		case "/return":
			back();
		break;
		case "/help":
			help();
		break;
		case "/queue":
			//queue(command);
		break;
		case "/priorityQueue":
			//priorityQueue(command);
		break;
		case "/next":
			next();
		break;
		case "/last":
			last();
		break;
		case "/show":
			show();
		break;
		default:
			System.out.println("error: enter valid command");
			refresh();
		break;
		}
	}
		
		
	public static void help(){
		System.out.println("\"/help\" - Shows list of commands");
		System.out.println("\"/command\" - ");
		System.out.println("\"/command\" - ");
		System.out.println("\"/command\" - ");
		System.out.println("\"/command\" - ");
		System.out.println("\"/command\" - ");
		refresh();
		return;
	}
	
	public static String getCurrentLevel(){
		return currentLevel;
		
	}
	public static void setCurrentLevel(String lvl){
		currentLevel = lvl;
	}
	
	public static String getCurrentSong()
	{
		return currentSong;
	}
	//commandTaker(playlistCommand);
	//playlistCommand = sc.nextLine();
	@Override
	public void run() 
	{
		audioPlayer player = new audioPlayer();
	    Application.launch(audioPlayer.class);
		
	}

}