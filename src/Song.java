
public class Song 
{
	private String title;
	private String path;
	private String album;
	
	public Song(String title, String path, String album)
	{
		this.setTitle(title);
		this.setPath(path);
		this.setAlbum(album);
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

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String toString()
	{
		return title + " from " + album;
	}
}
