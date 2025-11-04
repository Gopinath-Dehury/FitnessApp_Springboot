package com.fitnessApp.controller;



import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fitnessApp.DAO.UsersDAO;
import com.fitnessApp.DTO.GoalDTO;
import com.fitnessApp.DTO.LoginUserDTO;
import com.fitnessApp.DTO.UserDTO;
import com.fitnessApp.DTO.WorkoutDTO;
import com.fitnessApp.model.Goal;
import com.fitnessApp.model.Users;
import com.fitnessApp.model.Workout;
import com.fitnessApp.service.GoalService;
import com.fitnessApp.service.UserService;
import com.fitnessApp.service.WorkoutService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/fitness")
public class UserController {

	
	@Autowired
	UserService userService;
	@Autowired
	WorkoutService workoutService; 
	@Autowired
	GoalService goalService; 
	@Autowired
	UsersDAO   usersDAO; 
	
	
	
	@GetMapping("/showRegistrationPage")
		public String getRegisterPage(Model model) {
			model.addAttribute("user",new UserDTO());
			return "register";
			
		}
	
	
	@GetMapping("/loginform")
	public String loginform(Model model)
	{
		System.out.println("redirecting login from register page ");
		model.addAttribute("user",new LoginUserDTO());
		return "login";
	}
	
	@PostMapping("/registerUsers")
	public String registerUser(Model model,UserDTO useDTO) {
		
		
		Users userEntity = new Users();
		BeanUtils.copyProperties(useDTO,userEntity );
		
		Users save = userService.save(userEntity);
		
		if(save!=null) {
			model.addAttribute("user",new LoginUserDTO());
			return "login";
		}else {
			model.addAttribute("user",new LoginUserDTO());
			model.addAttribute("msg","Register failed.....");

			return "register";
		}
		
	}
	
	@PostMapping("/login")
	public String login(Model model,HttpSession httpSession,LoginUserDTO loginUserDTO) {
		
		String username = loginUserDTO.getUsername();
		String password = loginUserDTO.getPassword();
		
//		Optional<Users> findById = usersDAO.findById(username);
		Users userEntity=null;
//		try {
			userEntity = userService.findByUserName(username);

//		}catch (NoSuchElementException e) {
//			model.addAttribute("user",new LoginUserDTO());
//			return "login";
//		}
			String userNameFromDB=null;
			String passwordFromDB=null;
			String name=null;
			if(userEntity!=null) {
			 userNameFromDB = userEntity.getUsername();
			 passwordFromDB = userEntity.getPassword();
			 name=userEntity.getFirstName();
			}else {
				model.addAttribute("user",new LoginUserDTO());
				model.addAttribute("msg","Wrong Username /Notfound Empty Object");
				return "login";
			}
			
		if(username.equals(userNameFromDB) && password.equals(passwordFromDB)) {
			System.out.println("User is validated...from db");
			httpSession.setAttribute("user", userNameFromDB);
			httpSession.setMaxInactiveInterval(600);
			httpSession.setAttribute("name", name);
			model.addAttribute("name",userEntity.getFirstName());
			return "home";
		}else {
			model.addAttribute("user",new LoginUserDTO());
			model.addAttribute("msg","Password or Username Wrong Credentials");
			return "login";
		}		
		
	}
	
	
	//workout
	
	@GetMapping("/showWorkoutForm")
	public String showCreateWorkoutForm(Model model) {
		model.addAttribute("workout",new WorkoutDTO());
		return "createWorkout";
		
	}
	
	@PostMapping("/saveworkout")
	public String saveWorkout(WorkoutDTO workoutDTO,Model model,HttpSession httpSession) {
		
		String username=(String)httpSession.getAttribute("user");
		Users user = userService.findByUserName(username);
		Workout workout = new Workout();
		BeanUtils.copyProperties(workoutDTO,workout );
		workout.setUser(user);
		Workout save = workoutService.save(workout);
		if(save!=null) {
			model.addAttribute("msg","Workout added sucessfully");
			return "home";
		}else {
			model.addAttribute("msg","Failed Adding workout...");
			model.addAttribute("workout",new WorkoutDTO());
			return "createWorkout";
		}	
	}
	
	@GetMapping("/showWorkoutTable")
	public String viewWorkout(Model model,HttpSession httpSession) {
		String username=(String)httpSession.getAttribute("user");
		Users user = userService.findByUserName(username);
		
		List<Workout> all = workoutService.findAllByUser(user);
		model.addAttribute("workout",all);
		return "viewWorkout";
		
	}
	
	
	//Goal
	
	@GetMapping("/showGoalForm")
	public String showCreateGoalForm(Model model) {
		model.addAttribute("goal",new GoalDTO());
		return "createGoal";
		
	}
	
	@PostMapping("/savegoal")
	public String saveWorkout(GoalDTO goalDTO,Model model,HttpSession httpSession) {
		
		String username=(String)httpSession.getAttribute("user");
		
		Users user = userService.findByUserName(username);
		
		Goal goal = new Goal();
		BeanUtils.copyProperties(goalDTO,goal );
		goal.setUser(user);
		Goal save = goalService.save(goal);
		if(save!=null) {
			model.addAttribute("msg","Goal added sucessfully");
			return "home";
		}else {
			model.addAttribute("msg","Failed Adding Goat...");
			model.addAttribute("goal",new GoalDTO());
			return "createGoal";
		}	
	}
	
	
	
	@GetMapping("/showGoalTable")
	public String viewGoal(Model model,HttpSession httpSession) {
		String username=(String)httpSession.getAttribute("user");
		Users user = userService.findByUserName(username);
		
		List<Goal> all = goalService.findAllByUser(user);
		model.addAttribute("goal",all);
		return "viewGoal";
		
	}
	@GetMapping("/homee")
	public String home(HttpSession httpSession,Model model) {
		String name=(String)httpSession.getAttribute("name");
//		Users user = userService.findByUserName(username);
		model.addAttribute("name",name);
		return "home";
	}
	
	
	
	
//	Delete goal
	@GetMapping("/goal/delete")
	public String deleteGoal(@RequestParam("id") Integer id, HttpSession httpSession) {
	    // Get the current user from the session
	    String username = (String) httpSession.getAttribute("user");
	    Users user = userService.findByUserName(username);

	    // Find the goal by its ID
	    Goal goal = goalService.findById(id);

	    // Verify the goal exists and belongs to the current user before deleting
	    if (goal != null && goal.getUser().equals(user)) {
	    	goal.setUser(null);
	        goalService.deleteGoalById(id);
	    }

	    // Redirect back to the goal table page
	    System.out.println("redirected");
	    return "redirect:/fitness/showGoalTable";
	}
	
	@GetMapping("/workout/delete")
	public String deleteWorkout(@RequestParam("id") Integer id, HttpSession httpSession) {
	    // Get the current user from the session
	    String username = (String) httpSession.getAttribute("user");
	    Users user = userService.findByUserName(username);

	    // Find the workout by its ID
	    Workout workout = workoutService.findById(id);

	    // Verify the workout exists and belongs to the current user before deleting
	    if (workout != null && workout.getUser().equals(user)) {
	    	workout.setUser(null);
	        workoutService.deleteWorkoutById(id);
	    }

	    // Redirect back to the workout table page
	    return "redirect:/fitness/showWorkoutTable";
	}
	
	
	
	
	
//LOGOUT LOGIC
	
	@GetMapping("/logout")
	public String logout(HttpSession httpSession,Model model) {
		System.out.println("Logouting .......");
		httpSession.invalidate();
		model.addAttribute("msg","logout sucessfully");
		model.addAttribute("user",new UserDTO());
		System.out.println("Logouting ....sucessfully");

		return "login";
	}
	}

