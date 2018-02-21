package kickstart.com.kickstarttask.Model;

public class AuthorItem{
	private Name name;
	private Email email;

	public void setName(Name name){
		this.name = name;
	}

	public Name getName(){
		return name;
	}

	public void setEmail(Email email){
		this.email = email;
	}

	public Email getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"AuthorItem{" + 
			"name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
