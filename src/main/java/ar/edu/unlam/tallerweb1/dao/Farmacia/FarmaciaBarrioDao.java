package ar.edu.unlam.tallerweb1.dao.Farmacia;

import ar.edu.unlam.tallerweb1.dao.BaseDaoFarmacia;
import ar.edu.unlam.tallerweb1.interfaces.BarrioDao;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Comuna;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository("farmaciaBarrioDao")
public class FarmaciaBarrioDao extends BaseDaoFarmacia implements BarrioDao {

    @Autowired
    protected FarmaciaComunaDao farmaciaComunaDao;

    @Override
    @SuppressWarnings("unchecked")
    public List<Barrio> getBarrioByName(String name) {
        return sessionFactory.getCurrentSession().createCriteria(Barrio.class).add(
                Restrictions.eq("nombre", name)
        ).list();
    }

    @Override
    public Barrio getBarrioById(Long id) {
        return sessionFactory.getCurrentSession().get(Barrio.class, id);
    }

    @Override
    public boolean addBarrio(Barrio barrio) {
        List<Barrio> barrios = this.getBarrioByName(barrio.getNombre());
        if (barrios.isEmpty()) {
            boolean nuevaComuna = farmaciaComunaDao.addComuna(barrio.getComuna());
            if (nuevaComuna) {
                List<Comuna> comunas = farmaciaComunaDao.getComunaByName(barrio.getComuna().getNombre());
                barrio.setComuna(comunas.get(0));
            }
            this.saveBarrio(barrio);
            return true;
        } else {
            return false;
        }
    }

    private void saveBarrio(Barrio barrio) {

        sessionFactory.getCurrentSession().save(barrio);
    }
}
