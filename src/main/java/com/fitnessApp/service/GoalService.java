package com.fitnessApp.service;

import java.util.List;

import com.fitnessApp.model.Goal;
import com.fitnessApp.model.Users;

public interface GoalService {

	Goal save(Goal goal);

	List<Goal> findAllByUser(Users user);

	Goal findById(Integer id);

	void deleteGoalById(Integer id);

}
