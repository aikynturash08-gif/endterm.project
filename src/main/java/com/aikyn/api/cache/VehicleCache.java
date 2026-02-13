package com.aikyn.api.cache;

import com.aikyn.api.model.vehicle.Vehicle;

import java.util.List;

public final class VehicleCache {

    private static volatile VehicleCache instance;

    private List<Vehicle> cachedVehicles;

    private VehicleCache() {
    }

    public static VehicleCache getInstance() {
        if (instance == null) {
            synchronized (VehicleCache.class) {
                if (instance == null) {
                    instance = new VehicleCache();
                }
            }
        }
        return instance;
    }

    public List<Vehicle> get() {
        return cachedVehicles;
    }

    public void put(List<Vehicle> vehicles) {
        this.cachedVehicles = vehicles;
    }

    public boolean isEmpty() {
        return cachedVehicles == null;
    }

    public void clear() {
        cachedVehicles = null;
    }
}
