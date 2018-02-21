package kickstart.com.kickstarttask.Model;

import java.util.List;

public class EntryItem{
	private List<LinkItem> link;
	private Id id;
	private List<CategoryItem> category;
	private Title title;
	private Updated updated;
	private Content content;

	public void setLink(List<LinkItem> link){
		this.link = link;
	}

	public List<LinkItem> getLink(){
		return link;
	}

	public void setId(Id id){
		this.id = id;
	}

	public Id getId(){
		return id;
	}

	public void setCategory(List<CategoryItem> category){
		this.category = category;
	}

	public List<CategoryItem> getCategory(){
		return category;
	}

	public void setTitle(Title title){
		this.title = title;
	}

	public Title getTitle(){
		return title;
	}

	public void setUpdated(Updated updated){
		this.updated = updated;
	}

	public Updated getUpdated(){
		return updated;
	}

	public void setContent(Content content){
		this.content = content;
	}

	public Content getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"EntryItem{" + 
			"link = '" + link + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",title = '" + title + '\'' + 
			",updated = '" + updated + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}