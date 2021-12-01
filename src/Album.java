import java.util.List;

public class Album 
{
	private List<Song> songs;
	private String title;
	
	public Album(List<Song> songs, String title)
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
		return title;
	}
}
