package com.my;

import clases.*;

import java.util.List;

public interface TrainFunctionsDAO {
    public List<Station> getAllStations();

    public Train getTrainById(long id);

    public Path getPathByCarriageId(long id);

    public Station getStationByName(String name);

    public Station getStationById(long id);

    public List<Carriage> getCarriagesByPathId(long id);

    public Carriage getCarriagesById(long id);

    public Path getPathById(long id);

    public void addPath(Path path);

    public void AddCarriagesByPathId(long PathId, List<Carriage> carriages);

    public List<Path> GetAllPath();

    public List<CarriageType> getCarriageTypeList();

    public CarriageType getCarriageTypeById(long id);
}
