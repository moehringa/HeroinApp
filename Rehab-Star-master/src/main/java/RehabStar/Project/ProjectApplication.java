package RehabStar.Project;

import RehabStar.Project.auxilary.StoryFeed;
import RehabStar.Project.dal.*;
import RehabStar.Project.domain.FollowingPair;
import RehabStar.Project.domain.Story;
import RehabStar.Project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;

// Created by David Terkula 10/2/2017
@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
