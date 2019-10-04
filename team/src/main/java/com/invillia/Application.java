package com.invillia;

import com.invillia.dao.TeamDao;
import com.invillia.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("team");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("----------------------------------------------------------------------------------------------");
        final TeamDao teamDao = new TeamDao(entityManager);

//        teamDao.insert(new Team(
//                "5B"
//        ));

//        System.out.println(teamDao.findAll());

        final Team team = teamDao.findById(2L);
//        System.out.println(team);

        team.setName("5º B");
        teamDao.update(team);

        System.out.println(teamDao.findById(2L));

    }
}
