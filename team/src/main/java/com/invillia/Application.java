package com.invillia;

import com.invillia.dao.MemberDao;
import com.invillia.dao.TeamDao;
import com.invillia.domain.Member;
import com.invillia.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("diogo");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("----------------------------------------------------------------------------------------------");
        final TeamDao teamDao = new TeamDao(entityManager);
        final MemberDao memberDao = new MemberDao(entityManager);

//        teamDao.insert(new Team(
//                "5B"
//        ));

//        System.out.println(teamDao.findAll());

//        final Team team = teamDao.findById(2L);
//        System.out.println(team);

//        team.setName("5º B");
//        teamDao.update(team);
//
//        System.out.println(teamDao.findById(2L));


        //Insert
//        final Team team = teamDao.findById(1L);
//        memberDao.insert(new Member(
//                "Diogo",
//                team
//        ));

        //findAll
//        System.out.println(memberDao.findAll());

        //findById
//        System.out.println(memberDao.findById(1L));

        //Update
//        final Member member = memberDao.findById(1L);
//        member.setName("Diogo N");
//        memberDao.update(member);
//
//        System.out.println(memberDao.findById(1L));

        System.out.println(memberDao.findAllByTeamId(1L));
    }
}
