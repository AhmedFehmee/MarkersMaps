package kickstart.com.kickstarttask.Model;

public class Updated{
	private String T;

	public void setT(String T){
		this.T = T;
	}

	public String getT(){
		return T;
	}

	@Override
 	public String toString(){
		return 
			"Updated{" + 
			"$t = '" + T + '\'' + 
			"}";
		}
}
