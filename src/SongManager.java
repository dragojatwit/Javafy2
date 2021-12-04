import java.util.LinkedList;
import java.util.List;

public class SongManager
{
	static public Song ninetyEight = new Song("1998","Music/1998.wav","MONSUNE");
	static public Song cloud = new Song("CLOUD","Music/CLOUD.wav","MONSUNE");
	static public Song outMind = new Song("OUTTA MY MIND","Music/OUTTA MY MIND.wav","MONSUNE");
	static public Song mountain = new Song("MOUNTAIN","Music/MOUNTAIN.wav","MONSUNE");
	static public Song jade = new Song("JADE","Music/JADE.wav","MONSUNE");
	static public Song summertimeMagic = new Song("Summertime Magic","Music/Childish Gambino - Summertime Magic.wav","Summer Pack");
	static public Song feelsLikeSummer = new Song("Feels Like Summer","Music/Childish Gambino - Feels Like Summer.wav","Summer Pack");
	
	static private List<Song> list;
	static public SongList monsune;
	static public SongList summerPack;
	static public SongList playlist1;
	static public SongList playlist2;
	
	public static void setUp()
	{
		list = new LinkedList<Song>();
		list.add(ninetyEight);
		list.add(cloud);
		list.add(outMind);
		list.add(mountain);
		list.add(jade);
		monsune = new SongList(list,"MONSUNE");
		list.clear();
		list.add(summertimeMagic);
		list.add(feelsLikeSummer);
		summerPack = new SongList(list, "Summer Pack");
		
		Tree tree = new Tree();
		tree.insert("","Tree");
		tree.insert("Tree", "Playlists");
		tree.insert("Tree", "Albums");
		tree.insert("Tree", "Songs");
		
		tree.insert("Albums",monsune);
		tree.insert("Albums",summerPack);
		
		tree.insert("Playlists", playlist1);
		tree.insert("Playlists", playlist2);
		
		tree.insert("Songs",ninetyEight);
		tree.insert("Songs",cloud);
		tree.insert("Songs",outMind);
		tree.insert("Songs",mountain);
		tree.insert("Songs",jade);
		tree.insert("Songs",summertimeMagic);
		tree.insert("Songs",feelsLikeSummer);
	}
	
	
}