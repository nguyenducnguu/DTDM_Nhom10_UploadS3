package bt_amazon.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="post")
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String title;
	private String content;
	private String file;
	
	public Post(){}
	public Post(String title, String content, String file)
	{
		super();
		this.title = title;
		this.content = content;
		this.file = file;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + getId() + ", title=" + title + ", content=" + content + ", file=" + file + "]";
	}
}