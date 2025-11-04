package com.fitnessApp.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnessApp.model.Users;
import com.fitnessApp.model.Workout;

@Repository
public interface WorkoutDAO extends JpaRepository<Workout, Integer>{

	List<Workout> findAllByUser(Users user);

}
