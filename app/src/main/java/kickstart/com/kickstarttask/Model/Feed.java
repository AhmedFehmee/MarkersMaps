package kickstart.com.kickstarttask.Model;

import java.util.List;

public class Feed{
	private OpenSearchTotalResults openSearchTotalResults;
	private List<EntryItem> entry;
	private String xmlns;
	private OpenSearchStartIndex openSearchStartIndex;
	private String xmlnsGsx;
	private String xmlnsOpenSearch;
	private List<AuthorItem> author;
	private List<LinkItem> link;
	private Id id;
	private List<CategoryItem> category;
	private Title title;
	private Updated updated;

	public void setOpenSearchTotalResults(OpenSearchTotalResults openSearchTotalResults){
		this.openSearchTotalResults = openSearchTotalResults;
	}

	public OpenSearchTotalResults getOpenSearchTotalResults(){
		return openSearchTotalResults;
	}

	public void setEntry(List<EntryItem> entry){
		this.entry = entry;
	}

	public List<EntryItem> getEntry(){
		return entry;
	}

	public void setXmlns(String xmlns){
		this.xmlns = xmlns;
	}

	public String getXmlns(){
		return xmlns;
	}

	public void setOpenSearchStartIndex(OpenSearchStartIndex openSearchStartIndex){
		this.openSearchStartIndex = openSearchStartIndex;
	}

	public OpenSearchStartIndex getOpenSearchStartIndex(){
		return openSearchStartIndex;
	}

	public void setXmlnsGsx(String xmlnsGsx){
		this.xmlnsGsx = xmlnsGsx;
	}

	public String getXmlnsGsx(){
		return xmlnsGsx;
	}

	public void setXmlnsOpenSearch(String xmlnsOpenSearch){
		this.xmlnsOpenSearch = xmlnsOpenSearch;
	}

	public String getXmlnsOpenSearch(){
		return xmlnsOpenSearch;
	}

	public void setAuthor(List<AuthorItem> author){
		this.author = author;
	}

	public List<AuthorItem> getAuthor(){
		return author;
	}

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

	@Override
 	public String toString(){
		return 
			"Feed{" + 
			"openSearch$totalResults = '" + openSearchTotalResults + '\'' + 
			",entry = '" + entry + '\'' + 
			",xmlns = '" + xmlns + '\'' + 
			",openSearch$startIndex = '" + openSearchStartIndex + '\'' + 
			",xmlns$gsx = '" + xmlnsGsx + '\'' + 
			",xmlns$openSearch = '" + xmlnsOpenSearch + '\'' + 
			",author = '" + author + '\'' + 
			",link = '" + link + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",title = '" + title + '\'' + 
			",updated = '" + updated + '\'' + 
			"}";
		}
}