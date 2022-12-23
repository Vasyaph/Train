package clases;

public class Station {
	long id;
	String name;
	String location;
	int countpl;
	String address;
	public Station(){
	}
	public Station(StationBuilder stationBuilder){
		id=stationBuilder.id;
		name=stationBuilder.name;
		location=stationBuilder.location;
		countpl=stationBuilder.countpl;
		address =stationBuilder.address;
	}
	public static class StationBuilder{
		long id;
		String name;
		String location;
		int countpl;
		String address;
		public  StationBuilder(){
		}

		public StationBuilder setId(long id) {
			this.id = id;
			return this;
		}

		public StationBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public StationBuilder setAddress(String address) {
			this.address = address;
			return this;
		}

		public StationBuilder setCountpl(int countpl) {
			this.countpl = countpl;
			return this;
		}

		public StationBuilder setLocation(String location) {
			this.location = location;
			return this;
		}
		public Station build() {
			return new Station(this);
		}

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCountpl(int countpl) {
		this.countpl = countpl;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public int getCountpl() {
		return countpl;
	}

	public String getAddress() {
		return address;
	}

	public String getLocation() {
		return location;
	}


}
