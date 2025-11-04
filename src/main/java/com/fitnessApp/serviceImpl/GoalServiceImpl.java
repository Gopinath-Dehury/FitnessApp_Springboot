package com.fitnessApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessApp.DAO.GoalDAO;
import com.fitnessApp.model.Goal;
import com.fitnessApp.model.Users;
import com.fitnessApp.service.GoalService;
@Service
public class GoalServiceImpl implements GoalService{

	@Autowired
	GoalDAO goalDAO;
	
	@Override
	public Goal save(Goal goal) {// TODO Auto-generated method stub
		return goalDAO.save(goal);
	}

	@Override
	public List<Goal> findAllByUser(Users user) {
		List<Goal> list = goalDAO.findAllByUser(user);
		return list;
	}

	@Override
	public Goal findById(Integer id) {
		// TODO Auto-generated method stub
		return goalDAO.findById(id).orElse(null);
	}

	@Override
	public void deleteGoalById(Integer id) {
		goalDAO.deleteById(id);
		
	}

}
