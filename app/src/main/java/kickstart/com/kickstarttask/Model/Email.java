package kickstart.com.kickstarttask.Model;

public class Email{
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
			"Email{" + 
			"$t = '" + T + '\'' + 
			"}";
		}
}
