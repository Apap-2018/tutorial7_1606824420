package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;
//import com.apap.tutorial7.model.DealerModel;

/**
 * CarService
 */
public interface CarService {
	CarModel addCar(CarModel car);
	public void deleteById(Long id);
	CarModel getCar(Long id);
	void updateCar(long id,CarModel car);
	//void deleteById(CarModel car);
	Optional<CarModel> getCarDetailById(Long id);
	void deleteCar(CarModel car);
	List<CarModel> viewAllCar();
	
}
