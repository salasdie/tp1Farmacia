package ar.edu.unlam.tallerweb1.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository("sessionFactory")
public abstract class BaseDaoFarmacia {
    @Inject
    protected SessionFactory sessionFactory;
}
