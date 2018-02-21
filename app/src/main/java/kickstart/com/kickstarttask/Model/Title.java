package kickstart.com.kickstarttask.Model;

public class Title{
	private String T;
	private String type;

	public void setT(String T){
		this.T = T;
	}

	public String getT(){
		return T;
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
			"Title{" + 
			"$t = '" + T + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}
