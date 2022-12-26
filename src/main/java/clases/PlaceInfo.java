package clases;

public class PlaceInfo {
	long buyer_id;
	String name;
	String surname;
	int carriage_id;
	int place;
	public PlaceInfo(){
	}
	public PlaceInfo(PlaceInfoBuilder placeInfoBuilder){
		buyer_id=placeInfoBuilder.buyer_id;
		name=placeInfoBuilder.name;
		surname=placeInfoBuilder.surname;
		carriage_id=placeInfoBuilder.carriage_id;
		place=placeInfoBuilder.place;
	}
	public static class PlaceInfoBuilder{
		long buyer_id;
		String name;
		String surname;
		int carriage_id;
		int place;
		PlaceInfoBuilder(){}

		public void setName(String name) {
			this.name = name;
		}

		public void setPlace(int place) {
			this.place = place;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public void setBuyer_id(long buyer_id) {
			this.buyer_id = buyer_id;
		}

		public void setCarriage_id(int carriage_id) {
			this.carriage_id = carriage_id;
		}
		public PlaceInfo build() {
			return new PlaceInfo(this);
		}

	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getPlace() {
		return place;
	}

	public long getBuyer_id() {
		return buyer_id;
	}

	public int getCarriage_id() {
		return carriage_id;
	}

	public void setCarriage_id(int carriage_id) {
		this.carriage_id = carriage_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setBuyer_id(long buyer_id) {
		this.buyer_id = buyer_id;
	}
}
