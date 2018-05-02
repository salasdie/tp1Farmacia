package ar.edu.unlam.tallerweb1.dao.Farmacia;

import ar.edu.unlam.tallerweb1.interfaces.ComunaDao;
import ar.edu.unlam.tallerweb1.modelo.Comuna;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository("FarmaciaComunaDao")
public class FarmaciaComunaDao implements ComunaDao {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public Comuna getComunaById(Long id) {
        return sessionFactory.getCurrentSession().get(Comuna.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comuna> getComunaByName(String name) {
        return sessionFactory.getCurrentSession().createCriteria(Comuna.class).add(
                Restrictions.eq("nombre", name)
        ).list();
    }

    @Override
    public boolean addComuna(Comuna comuna) {
        List<Comuna> comunas = this.getComunaByName(comuna.getNombre());
        if (comunas.isEmpty()) {
            this.saveComuna(comuna);
            return true;
        } else {
            return false;
        }
    }

    private void saveComuna(Comuna comuna) {
        sessionFactory.getCurrentSession().save(comuna);
    }
}
