package com.slobodanbabic.socialnetwork;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.slobodanbabic.socialnetwork.entity.User;
import com.slobodanbabic.socialnetwork.repository.UserRepository;

@Component
public class Scheduler {

	@Autowired
	UserRepository userRepository;
		 
    @Scheduled(cron="0 0 0 * * ?") //at 12:00 AM every day
	public void cronJobSch() {

		List<User> bannedUsers = userRepository.getBannedUser();
		for (User u : bannedUsers) {
			System.out.println("Time: "+new Date());
			System.out.println("Banned user: " + u.getFirstName() + " " + u.getLastName() + " time: " + u.getBan());
			u.setBan(null);
			User user = userRepository.save(u);
			System.out.println(u.getFirstName() + " " + user.getLastName() + " " + " ban time: " + u.getBan());
		}

	}
}
