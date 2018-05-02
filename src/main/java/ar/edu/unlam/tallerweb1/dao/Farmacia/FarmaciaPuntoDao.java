package ar.edu.unlam.tallerweb1.dao.Farmacia;

import ar.edu.unlam.tallerweb1.interfaces.PuntoDao;
import ar.edu.unlam.tallerweb1.modelo.Punto;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.List;

public class FarmaciaPuntoDao implements PuntoDao {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Punto> getPuntoByName(String name) {
        return sessionFactory.getCurrentSession().createCriteria(Punto.class).add(
                Restrictions.eq("nombre", name)
        ).list();
    }

    @Override
    public Punto getPuntoById(Long id) {
        return sessionFactory.getCurrentSession().get(Punto.class, id);
    }

    @Override
    public void addPunto(Punto punto) {
        sessionFactory.getCurrentSession().save(punto);
    }
}
