import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;



public class javafyClient <T> extends Thread

{
	
	static Queue<Song> trackQueue = new LinkedList<Song>();
	static List<Song> prevList = new LinkedList<Song>();
	public static Song currentSong = null;
	public static Song previousSong = prevList.get(0);
	
	private static String currentLevel;
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Start of main
	public static void main(String[] args) {
		
		SongManager.setUp();
		currentSong = SongManager.cloud;
	    javafyClient thread = new javafyClient();
	    thread.start();
	    

		Scanner sc = new Scanner(System.in);
		//keeps track of current level for return command
		//topLevel, playlistLevel, albumLevel, songLevel
		setCurrentLevel("topLevel");
		//playlist / album / song selection level
		
		
		System.out.println("Welcome to Javafy!");
		System.out.println("use /select # to choose");
		
		while (getCurrentLevel().equals("topLevel"))
		{
			setCurrentLevel("topLevel");
			
			System.out.println("[1]Playlists:		[2]Albums:		[3]Songs:");
			String in1 = sc.nextLine();
			commandTaker(in1);
		}
	}
	
	 public static void queue(Song song){//Puts the specified media in queue behind and adds a playlist to a queue
		 //Queue<Song> trackQueue = new LinkedList<Song>();
		 //playlist = bag(playlist);
		 trackQueue.offer(song);
		 refresh();
	}
	 
	 public static void queue(List playlist){//Puts the specified media in queue behind and adds a playlist to a queue
		 //Queue<Song> trackQueue = new LinkedList<Song>();
		 //playlist = bag(playlist);
		 
		 //may want to remove the list playlist parameter and leave playlist as a variable
		 for(int i = 0; i <= playlist.size(); i++){
			 trackQueue.offer((Song) playlist.get(i));
		 }
		 
		 refresh();
	}

	
	 public static Bag bag(List playlist){
		 Bag bag = new Bag();
		 for(int i = 0; i <= playlist.size(); i++){
			 double index = Math.floor(Math.random() * playlist.size());
			 bag.bagadd(playlist.get((int) index));
		 }
	 	//Puts the specified media in bag for randomization
	}
	 

	 public static void priorityQueue (Song song){//Puts the specified media before anything else in the queue
		 Queue<Song> tempQueue = new LinkedList<Song>();
		 tempQueue.offer(song);
		 tempQueue.addAll(trackQueue);
		 trackQueue.clear();
		 trackQueue.addAll(tempQueue);
		 refresh();
	 }
	 
	 public static void priorityQueue (List playlist){//Puts the specified media before anything else in the queue
		 Queue<Song> tempQueue = new LinkedList<Song>();
		 for(int i = 0; i <= playlist.size(); i++){
			 tempQueue.offer((Song) playlist.get(i));
		 }
		 tempQueue.addAll(trackQueue);
		 trackQueue.clear();
		 trackQueue.addAll(tempQueue);
		 refresh();
	 }

	 public static void clear(){//Clears the entire queue with the exception of the song currently playing
		 trackQueue.clear();
		 refresh();
	 }
//	 
//	 public static void skip(String timestamp) {
//		 //Skips to the specified timestamp of current song
//		 //[Produces error if time specified is longer than song]
//	}
	 

	 public static void play(String parameter)
	 {
		 switch(currentLevel)
		 {
		 	case "songLevel":
				
			break;
			case "playlist1":
				
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
			default :
				System.out.println("Please select a menu before using /play");
				refresh();
		}
	} 
		 
	 
	 

	 public static Song next() {//Skips to the next song in queue
		 Song s = trackQueue.poll();
		 
		 prevList.add(0, s);
		 
		 refresh();
		 return s;
	}
	 

	 public static void last() {//Plays the last song played
		 Queue<Song> tempQueue = new LinkedList<Song>();
		 Song p = prevList.get(0);
		 prevList.remove(0);
		  
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
		Queue<Song> tempQueue = new LinkedList<Song>();
		
		for(int i = 0; i <= tQ; i++){
			Song s = trackQueue.poll();
			tempQueue.add(s);
			System.out.printf("%d", s);
		}
		refresh();
	}
	 
public static void select(String parameter){
 //Shows the contents of the specified playlist or album
	 String lvl = getCurrentLevel();
	 
	 switch(lvl) {
		case "topLevel":

			switch (parameter) {
			case "1":
				setCurrentLevel("playlistLevel");
				playlistLevel();
				break;
			case "2":
				setCurrentLevel("albumLevel");
				albumLevel();
				break;
			case "3":
				setCurrentLevel("songLevel");
				songLevel();
				break;
			default:
				System.out.println("Ivalid input: " + parameter);
				System.out.println("Please enter a valid number (1-3)\"/return\"");
			break;
			}
		break;
		case "playlistLevel":
			switch (parameter) {
			case "1":
				setCurrentLevel("playlist1");
				playlist1();
				break;
			case "2":
				setCurrentLevel("playlist2");
				playlist2();
				break;
			}
		break;
		case "albumLevel":
			switch (parameter) {
			case "1":
				setCurrentLevel("album1");
				album1();
				break;
			case "2":
				setCurrentLevel("album2");
				album2();
				break;
			}
			break;
		}
	 //return null;
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
		int spaceIndex = command.indexOf(" ");
		String parameter = "";
		if(spaceIndex != -1)
		{
			parameter = command.substring(spaceIndex + 1);
			command = command.substring(0,spaceIndex);
//			System.out.println(parameter);
//			System.out.println(command);
		}
		
		switch(command) {
		case "/play":
			play(parameter);
		break;
		case "/select":
			select(parameter);
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
			//priortiyQueue(select(parameter));
			
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
	
//	public static Song getCurrentSong()
//	{
//		return currentSong;
//	}
//	
	//commandTaker(playlistCommand);
	//playlistCommand = sc.nextLine();
	
	@Override
	public void run() 
	{
		audioPlayer player = new audioPlayer();
		while(true)
		{
			while(!trackQueue.isEmpty())
			{
				//currentSong = 
				Application.launch(audioPlayer.class);
			}
			
		}
		
	    
		
	}

}