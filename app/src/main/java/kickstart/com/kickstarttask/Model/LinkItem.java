package kickstart.com.kickstarttask.Model;

public class LinkItem{
	private String rel;
	private String href;
	private String type;

	public void setRel(String rel){
		this.rel = rel;
	}

	public String getRel(){
		return rel;
	}

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"LinkItem{" + 
			"rel = '" + rel + '\'' + 
			",href = '" + href + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}
