package com.my;

import DBF.*;
import clases.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainFunctions implements TrainFunctionsDAO {
	private static final Logger log = LogManager.getLogger(Login.class);

	public List<Station> getAllStations() {
		Connection con = null;
		List<Station> stations = new ArrayList<>();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from stations");
			while (rs.next())
				stations.add(getStationById(rs.getLong(StationF.ID)));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


		return stations;
	}

	public Train getTrainById(long id) {

		Connection con = null;

		Train train = new Train();
		try {
			con = MyConnections.getInstance().getDs().getConnection();

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM train join train_type on train." + TrainF.TYPE_ID + "=train_type." + TrainTF.ID + " where train." + TrainF.ID + " ='" + id + "'");
			if (rs.next()) {

				train.setId(rs.getLong(TrainF.ID));
				train.setName(rs.getString(TrainTF.NAME));

			} else {
				return null;
			}
			rs.close();


		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return train;
	}


	public Path getPathByCarriageId(long id) {
		Connection con = null;
		Path path = new Path();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * FROM schedule where " + PathF.ID + " = (select railway_composition." + CarriageF.TIME_ID + " from railway_composition where railway_composition.id ='" + id + "')");
			if (rs.next()) {
				path = getPathById(rs.getLong(PathF.ID));
				return path;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	public Station getStationByName(String name) {
		Connection con = null;

		Station station = new Station();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from stations where name='" + name + "'");
			if (rs.next())
				station = getStationById(rs.getLong(StationF.ID));
			else station = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return station;
	}

	public Station getStationById(long id) {
		Connection con = null;

		Station station = new Station();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from stations where id='" + id + "'");
			if (rs.next()) {
				station.setId(rs.getLong(StationF.ID));
				station.setCountpl(rs.getInt(StationF.COUNTPLE));
				station.setName(rs.getString(StationF.NAME));
				station.setLocation(rs.getString(StationF.LOCATION));
				station.setAddress(rs.getString(StationF.ADRES));

			} else {
				station = null;
			}

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return station;
	}

	public List<Carriage> getCarriagesByPathId(long id) {
		Connection con = null;

		List<Carriage> carriage = new ArrayList<>();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from railway_composition join carriage_type on carriage_type." + CarriageTF.ID + " = railway_composition." + CarriageF.TYPE + " where railway_composition." + CarriageF.TIME_ID + " ='" + id + "' ORDER BY railway_composition." + CarriageF.NUMBER);
			while (rs.next()) {
				Carriage carriage1 = new Carriage();
				carriage1.setId(rs.getLong(CarriageF.ID));
				carriage1.setSpecial(rs.getBoolean(CarriageTF.SPECIAL));
				carriage1.setNumber_of_seats(rs.getInt(CarriageTF.NUMBER_OF_SEATS));
				carriage1.setName(rs.getString(CarriageTF.NAME));
				carriage1.setInfo(rs.getString(CarriageTF.INFO));
				carriage1.setPrice(rs.getInt(CarriageF.PRICE));
				carriage1.setNumber(rs.getInt(CarriageF.NUMBER));
				carriage1.setType_id(rs.getLong(CarriageTF.ID));
				carriage.add(carriage1);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return carriage;
	}

	public Carriage getCarriagesById(long id) {
		Connection con = null;

		Carriage carriage = new Carriage();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from railway_composition where railway_composition." + CarriageF.ID + " ='" + id + "' ");
			while (rs.next()) {

				carriage.setId(rs.getLong(CarriageF.ID));


				carriage.setPrice(rs.getInt(CarriageF.PRICE));
				System.out.println("numberDS=" + rs.getInt(CarriageF.NUMBER));
				carriage.setNumber(rs.getInt(CarriageF.NUMBER));


			}
		} catch (SQLException e) {

		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return carriage;
	}

	public Path getPathById(long id) {
		Connection con = null;

		Path path = new Path();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from schedule where " + PathF.ID + " = '" + id + "' ");
			while (rs.next()) {
				path.setTrain(getTrainById(rs.getLong(PathF.TRAIN_ID)));
				path.setArrival(getStationById(rs.getLong(PathF.ARRIAVAL_STATION)));
				path.setDispatch(getStationById(rs.getLong(PathF.DISPATCH_STATION)));
				path.setId(rs.getLong(PathF.ID));
				path.setArrival_time(rs.getTimestamp(PathF.ARRIAVAL_TIME));
				path.setDispatch_time(rs.getTimestamp(PathF.DISPATCH_TIME));
				path.setCarriages(getCarriagesByPathId(rs.getLong(PathF.ID)));
				path.setArrival_number(rs.getInt(PathF.ARRIAVAL_STATION_NUMBER));
				path.setDispatch_number(rs.getInt(PathF.DISPATCH_STATION_NUMBER));
				path.setReturnPatch(rs.getInt(PathF.RETURN_PATCH_ID));
			}

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return path;
	}

	public void addPath(Path path) {
		Connection con = null;
		try {
			con = MyConnections.getInstance().getDs().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try (PreparedStatement ps = con.prepareStatement("INSERT INTO schedule(" + PathF.TRAIN_ID + "," + PathF.ARRIAVAL_STATION + "," + PathF.ARRIAVAL_TIME + "," + PathF.DISPATCH_STATION + "," + PathF.DISPATCH_TIME + "," + PathF.AVAILABLE + "," + PathF.ARRIAVAL_STATION_NUMBER + "," + PathF.DISPATCH_STATION_NUMBER + "," + PathF.RETURN_PATCH_ID + ") Values(?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
			con = MyConnections.getInstance().getDs().getConnection();
			ps.setLong(1, path.getTrain().getId());
			ps.setLong(2, path.getArrival().getId());
			ps.setTimestamp(3, path.getArrival_time());
			ps.setLong(4, path.getDispatch().getId());
			ps.setTimestamp(5, path.getDispatch_time());
			ps.setBoolean(6, true);
			ps.setInt(7, path.getArrival_number());
			ps.setInt(8, path.getDispatch_number());
			ps.setLong(9, path.getReturnPatch());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				path.setId(rs.getInt(1));
			}
			AddCarriagesByPathId(path.getId(), path.getCarriages());//!!!!!!!!!!!!!!!!!!!!!!!!!!!

			con.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		try {
			con.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public void AddCarriagesByPathId(long PathId, List<Carriage> carriages) {

		Connection con = null;
		try {
			con = MyConnections.getInstance().getDs().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		try (PreparedStatement ps = con.prepareStatement("INSERT INTO railway_composition(" + CarriageF.TIME_ID + "," + CarriageF.TYPE + "," + CarriageF.NUMBER + "," + CarriageF.PRICE + ") Values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

			con.setAutoCommit(false);
			for (Carriage carriage : carriages) {
				ps.setLong(1, PathId);
				ps.setLong(2, carriage.getType_id());
				ps.setInt(3, carriage.getNumber());
				ps.setInt(4, carriage.getPrice());
				ps.executeUpdate();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}


			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Path> GetAllPath() {

		Connection con = null;

		List<Path> paths = new ArrayList<>();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from schedule");
			while (rs.next()) {
				paths.add(getPathById(rs.getLong(PathF.ID)));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return paths;
	}

	public List<CarriageType> getCarriageTypeList() {
		Connection con = null;
		List<CarriageType> carriageTypes = new ArrayList<>();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from carriage_type");
			while (rs.next()) {
				CarriageType a = new CarriageType();
				a.setId(rs.getLong(CarriageTF.ID));
				a.setName(rs.getString(CarriageTF.NAME));
				a.setInfo(rs.getString(CarriageTF.INFO));
				a.setSpecial(rs.getBoolean(CarriageTF.SPECIAL));
				a.setNumber_of_seats(rs.getInt(CarriageTF.NUMBER_OF_SEATS));
				carriageTypes.add(a);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return carriageTypes;
	}

	public CarriageType getCarriageTypeById(long id) {
		Connection con = null;
		CarriageType carriageTypes = new CarriageType();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from carriage_type where " + CarriageTF.ID + "='" + id + "'");
			if (rs.next()) {
				carriageTypes.setId(rs.getLong(CarriageTF.ID));
				carriageTypes.setName(rs.getString(CarriageTF.NAME));
				carriageTypes.setInfo(rs.getString(CarriageTF.INFO));
				carriageTypes.setSpecial(rs.getBoolean(CarriageTF.SPECIAL));
				carriageTypes.setNumber_of_seats(rs.getInt(CarriageTF.NUMBER_OF_SEATS));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return carriageTypes;
	}


}
