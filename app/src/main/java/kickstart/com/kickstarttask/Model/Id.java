package kickstart.com.kickstarttask.Model;

public class Id{
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
			"Id{" + 
			"$t = '" + T + '\'' + 
			"}";
		}
}
