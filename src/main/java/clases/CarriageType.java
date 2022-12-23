package clases;

public class CarriageType {
	private long id;
	private String name;
	private int number_of_seats;
	private boolean special;
	private String info;
	public CarriageType(){

	}
	public CarriageType(CarriageTypeBuilder carriageTypeBuilder){
		id=carriageTypeBuilder.id;
		name=carriageTypeBuilder.name;
		number_of_seats=carriageTypeBuilder.number_of_seats;
		special=carriageTypeBuilder.special;
		info=carriageTypeBuilder.info;
	}
	public static class CarriageTypeBuilder{
		private long id;
		private String name;
		private int number_of_seats;
		private boolean special;
		private String info;
		CarriageTypeBuilder(){

		}

		public void setId(long id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		public void setNumber_of_seats(int number_of_seats) {
			this.number_of_seats = number_of_seats;
		}

		public void setSpecial(boolean special) {
			this.special = special;
		}
		public CarriageType build() {
			return new CarriageType(this);
		}

	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public void setNumber_of_seats(int number_of_seats) {
		this.number_of_seats = number_of_seats;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public int getNumber_of_seats() {
		return number_of_seats;
	}

	public boolean isSpecial() {
		return special;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}
}
