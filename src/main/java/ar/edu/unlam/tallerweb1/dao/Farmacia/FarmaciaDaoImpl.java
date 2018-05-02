package ar.edu.unlam.tallerweb1.dao.Farmacia;

import ar.edu.unlam.tallerweb1.dao.BaseDaoFarmacia;
import ar.edu.unlam.tallerweb1.interfaces.FarmaciaDao;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository("FarmaciaDaoImpl")
public class FarmaciaDaoImpl extends BaseDaoFarmacia implements FarmaciaDao {

    @Autowired
    protected FarmaciaDireccionDao farmaciaDireccionDao;

    @Autowired
    protected FarmaciaPuntoDao farmaciaPuntoDao;

    public boolean addFarmacia(Farmacia farmacia) {
        @SuppressWarnings("unchecked")
        List<Farmacia> farmacias = sessionFactory.getCurrentSession().createCriteria(Farmacia.class).add(
                Restrictions.eq("nombre", farmacia.getNombre())
        ).list();
        if (farmacias.isEmpty()) {
            this.saveFarmacia(farmacia);
            return true;
        } else {
            return false;
        }
    }

    private void saveFarmacia(Farmacia farmacia) {
        farmaciaPuntoDao.addPunto(farmacia.getGeoLocalizacion());
        List<Direccion> direcciones = farmaciaDireccionDao.getDireccionByContent(farmacia.getDireccion().getCalle(), farmacia.getDireccion().getNumero());
        if (direcciones.isEmpty()) {
            farmaciaDireccionDao.addDireccion(farmacia.getDireccion());
        }
        sessionFactory.getCurrentSession().save(farmacia);
    }

    @SuppressWarnings("unchecked")
    public List<Farmacia> getFarmaciasByName(String nombre) {
        return sessionFactory.getCurrentSession().createCriteria(Farmacia.class).add(
                Restrictions.eq("nombre", nombre)
        ).list();
    }

    @SuppressWarnings("unchecked")
    public List<Farmacia> getFarmaciasByTurno(String turno) {
        return sessionFactory.getCurrentSession().createCriteria(Farmacia.class).add(
                Restrictions.eq("diaDeTurno", "martes")).list();
    }

    @SuppressWarnings("unchecked")
    public List <Farmacia> getTodasLasFarmaciasDeUnaCalle(String calle){
        return sessionFactory.getCurrentSession().createCriteria(Farmacia.class). createAlias("direccion", "direccion") .add(
                Restrictions.eq("direccion.calle", "Av.De Mayo")). list();
    }

    @SuppressWarnings("unchecked")
    public List <Farmacia> getTodasLasFarmaciasDeUnBarrio(String nombre){
        return sessionFactory.getCurrentSession().createCriteria(Farmacia.class). createAlias("barrio", "barrio") .add(
                Restrictions.eq("barrio.nombre", "BarrioUno")). list();
    }

}
