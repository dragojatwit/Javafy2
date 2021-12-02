import java.util.List;

//For playlists and albums
public class SongList 
{
	private List<Song> songs;
	private String listTitle;
	
	public SongList(List<Song> songs, String listTitle)
	{
		this.setSongs(songs);
		this.setTitle(listTitle);
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
	 * @return the listTitle
	 */
	public String getTitle() {
		return listTitle;
	}

	/**
	 * @param listTitle the listTitle to set
	 */
	public void setTitle(String listTitle) {
		this.listTitle = listTitle;
	}
	
	public String toString()
	{
		String t = listTitle + "\n";
		for(int i = 0; i < songs.size(); i++)
		{
			t += "[" + i + "] " + songs.get(0) + "\n";
		}
		return t;
	}
}
