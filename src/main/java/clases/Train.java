package clases;

public class Train {
	long id;
	String name;
	public Train(){

	}
	public Train(long id, String name){
		this.id=id;
		this.name=name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
