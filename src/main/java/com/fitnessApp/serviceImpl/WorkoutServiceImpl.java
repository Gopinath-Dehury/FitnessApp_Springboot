package com.fitnessApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessApp.DAO.WorkoutDAO;
import com.fitnessApp.model.Users;
import com.fitnessApp.model.Workout;
import com.fitnessApp.service.WorkoutService;
@Service
public class WorkoutServiceImpl implements WorkoutService {

	@Autowired
	WorkoutDAO workoutDAO; 
	
	
	@Override
	public Workout save(Workout workout) {
		
		return workoutDAO.save(workout);
	}


	@Override
	public List<Workout> getAll() {
				
		return workoutDAO.findAll();
	}


	@Override
	public List<Workout> findAllByUser(Users user) {
		
		return workoutDAO.findAllByUser(user);
	}


	@Override
	public Workout findById(Integer id) {

		return workoutDAO.findById(id).orElse(null);
	}


	@Override
	public void deleteWorkoutById(Integer id) {
		workoutDAO.deleteById(id);
		
	}

}
