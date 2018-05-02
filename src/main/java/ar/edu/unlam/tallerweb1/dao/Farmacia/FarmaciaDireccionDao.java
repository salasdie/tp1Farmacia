package ar.edu.unlam.tallerweb1.dao.Farmacia;

import ar.edu.unlam.tallerweb1.dao.BaseDaoFarmacia;
import ar.edu.unlam.tallerweb1.interfaces.DireccionDao;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Direccion;

import ar.edu.unlam.tallerweb1.modelo.Farmacia;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("FarmaciaDireccionDao")
public class FarmaciaDireccionDao extends BaseDaoFarmacia implements DireccionDao {

    @Autowired
    protected FarmaciaBarrioDao farmaciaBarrioDao;

    public boolean addDireccion(Direccion direccion) {
        List<Direccion> direcciones = this.getDireccionByContent(direccion.getCalle(), direccion.getNumero());
        if (direcciones.isEmpty()) {
            this.saveDireccion(direccion);
            return true;
        } else {
            return false;
        }
    }

    private void saveDireccion(Direccion direccion) {
        boolean nuevoBarrio = farmaciaBarrioDao.addBarrio(direccion.getBarrio());
        if (!nuevoBarrio) {
            List<Barrio> barrioGuardado = farmaciaBarrioDao.getBarrioByName(direccion.getBarrio().getNombre());
            direccion.setBarrio(barrioGuardado.get(0));
        }
        sessionFactory.getCurrentSession().save(direccion);
    }

    @Override
    public Direccion getUbicacionById(Long id) {
        return sessionFactory.getCurrentSession().get(Direccion.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Direccion> getDireccionByContent(String calle, Number numero) {
        // Crea una Criteria
        return sessionFactory.getCurrentSession().createCriteria(Direccion.class)
                .add(Restrictions.eq("calle", calle))
                .add(Restrictions.eq("numero", numero))
                .list();
    }


}
