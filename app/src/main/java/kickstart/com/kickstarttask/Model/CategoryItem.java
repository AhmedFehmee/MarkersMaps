package kickstart.com.kickstarttask.Model;

public class CategoryItem{
	private String scheme;
	private String term;

	public void setScheme(String scheme){
		this.scheme = scheme;
	}

	public String getScheme(){
		return scheme;
	}

	public void setTerm(String term){
		this.term = term;
	}

	public String getTerm(){
		return term;
	}

	@Override
 	public String toString(){
		return 
			"CategoryItem{" + 
			"scheme = '" + scheme + '\'' + 
			",term = '" + term + '\'' + 
			"}";
		}
}
