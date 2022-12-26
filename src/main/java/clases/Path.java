package clases;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Path {
	long id;
	Train train;
	List<Carriage> carriages = new ArrayList<>();
	Timestamp dispatch_time;
	long returnPatch;
	Timestamp arrival_time;
	Station dispatch;
	Station arrival;
	int dispatch_number;
	int arrival_number;

	public Path(PathBuilder pathBuilder) {
		id = pathBuilder.id;
		train = pathBuilder.train;
		carriages = pathBuilder.carriages;
		dispatch_time = pathBuilder.dispatch_time;
		arrival_time = pathBuilder.arrival_time;
		dispatch = pathBuilder.dispatch;
		arrival = pathBuilder.arrival;
		dispatch_number = pathBuilder.dispatch_number;
		arrival_number = pathBuilder.arrival_number;
		returnPatch=pathBuilder.returnPatch;
	}

	public Path() {

	}

	public static class PathBuilder {
		long id;
		int returnPatch;
		Train train;
		List<Carriage> carriages = new ArrayList<>();
		Timestamp dispatch_time;
		Timestamp arrival_time;
		Station dispatch;
		Station arrival;
		int dispatch_number;
		int arrival_number;

		public PathBuilder() {
		}


		public PathBuilder setId(long id) {
			this.id = id;
			return this;
		}

		public PathBuilder setReturnPatch(int returnPatch) {
			this.returnPatch = returnPatch;
			return this;
		}

		public PathBuilder setDispatch_number(int dispatch_number) {
			this.dispatch_number = dispatch_number;
			return this;
		}

		public PathBuilder setArrival_number(int arrival_number) {
			this.arrival_number = arrival_number;
			return this;
		}

		public PathBuilder setTrain(Train train) {
			this.train = train;
			return this;
		}

		public PathBuilder setDispatch_time(Timestamp dispatch_time) {
			this.dispatch_time = dispatch_time;
			return this;
		}

		public PathBuilder setDispatch(Station dispatch) {
			this.dispatch = dispatch;
			return this;
		}

		public PathBuilder setArrival_time(Timestamp arrival_time) {
			this.arrival_time = arrival_time;
			return this;
		}

		public PathBuilder setArrival(Station arrival) {
			this.arrival = arrival;
			return this;
		}

		public PathBuilder setCarriages(List<Carriage> carriages) {
			this.carriages = carriages;
			return this;
		}

		public Path build() {
			return new Path(this);
		}
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setArrival(Station arrival) {
		this.arrival = arrival;
	}

	public void setArrival_time(Timestamp arrival_time) {
		this.arrival_time = arrival_time;
	}

	public void setDispatch(Station dispatch) {
		this.dispatch = dispatch;
	}

	public void setDispatch_time(Timestamp dispatch_time) {
		this.dispatch_time = dispatch_time;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Timestamp getArrival_time() {
		return arrival_time;
	}

	public Timestamp getDispatch_time() {
		return dispatch_time;
	}

	public String getNormalDispatch_time() {
		return dispatch_time.toString().substring(5, 16);
	}

	public String getNormalArrival_time() {
		return arrival_time.toString().substring(5, 16);
	}

	public List<Carriage> getCarriages() {
		return carriages;
	}

	public long getId() {
		return id;
	}

	public Station getArrival() {
		return arrival;
	}

	public Station getDispatch() {
		return dispatch;
	}

	public Train getTrain() {
		return train;
	}

	public void setCarriages(List<Carriage> carriages) {
		this.carriages = carriages;
	}

	public void setArrival_number(int arrival_number) {
		this.arrival_number = arrival_number;
	}

	public void setDispatch_number(int dispatch_number) {
		this.dispatch_number = dispatch_number;
	}

	public int getArrival_number() {
		return arrival_number;
	}

	public int getDispatch_number() {
		return dispatch_number;
	}

	public void setReturnPatch(long returnPatch) {
		this.returnPatch = returnPatch;
	}

	public long getReturnPatch() {
		return returnPatch;
	}

	public int getNumberOfFreePlaces() {
		int count = 0;
		for (Carriage carriage : carriages) {
			count += carriage.getFreePlaces();
		}
		return count;
	}
}
