package kickstart.com.kickstarttask.Model;

public class Response{
	private Feed feed;
	private String encoding;
	private String version;

	public void setFeed(Feed feed){
		this.feed = feed;
	}

	public Feed getFeed(){
		return feed;
	}

	public void setEncoding(String encoding){
		this.encoding = encoding;
	}

	public String getEncoding(){
		return encoding;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return version;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"feed = '" + feed + '\'' + 
			",encoding = '" + encoding + '\'' + 
			",version = '" + version + '\'' + 
			"}";
		}
}
