package com.fitnessApp.service;

import java.util.List;

import com.fitnessApp.model.Users;
import com.fitnessApp.model.Workout;

public interface WorkoutService {

	Workout save(Workout workout);

	List<Workout> getAll();

	List<Workout> findAllByUser(Users user);

	Workout findById(Integer id);

	void deleteWorkoutById(Integer id);

}
