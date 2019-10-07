package com.invillia.dao;

import com.invillia.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class MemberDao {
    private EntityManager entityManager;

    public MemberDao(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Metodos
    public void insert(final Member member) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(member);
        transaction.commit();
    }

    public List<Member> findAll() {
        final TypedQuery<Member> query = entityManager.createQuery(
                "SELECT m FROM Member m JOIN FETCH m.team",
                Member.class
        );

        return query.getResultList();
    }

    public Member findById(final Long id) {
        return entityManager.find(Member.class, id);
    }

    public void update(final Member member) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.merge(member);
        transaction.commit();
    }

    public void delete(final Long id) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        final Member member = findById(id);
        entityManager.remove(member);
        transaction.commit();
    }

    public List<Member> findAllByTeamId(final Long teamId) {
        final TypedQuery<Member> query = entityManager.createQuery(
                "SELECT m FROM Member m JOIN FETCH m.team t where t.id = :teamId",
                Member.class
        );

        query.setParameter("teamId", teamId);

        return query.getResultList();
    }
}
