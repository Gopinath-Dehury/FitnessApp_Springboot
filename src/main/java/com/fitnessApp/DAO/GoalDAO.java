package com.fitnessApp.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnessApp.model.Goal;
import com.fitnessApp.model.Users;

@Repository
public interface GoalDAO extends JpaRepository<Goal, Integer> {

	List<Goal> findAllByUser(Users user);

}
