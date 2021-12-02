import java.util.List;

//For playlists and albums
public class SongList 
{
	private List<Song> songs;
	private String title;
	
	public SongList(List<Song> songs, String title)
	{
		this.setSongs(songs);
		this.setTitle(title);
	}

	/**
	 * @return the songs
	 */
	public List<Song> getSongs() {
		return songs;
	}

	/**
	 * @param songs the songs to set
	 */
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String toString()
	{
		String t = "Title\n";
		for(int i = 0; i < songs.size(); i++)
		{
			t += "[" + i + "] " + songs.get(0) + "\n";
		}
		return t;
	}
}
