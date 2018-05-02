package ar.edu.unlam.tallerweb1.queries;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.dao.Farmacia.FarmaciaBarrioDao;
import ar.edu.unlam.tallerweb1.dao.Farmacia.FarmaciaComunaDao;
import ar.edu.unlam.tallerweb1.dao.Farmacia.FarmaciaDaoImpl;
import ar.edu.unlam.tallerweb1.dao.Farmacia.FarmaciaPuntoDao;
import ar.edu.unlam.tallerweb1.interfaces.PuntoDao;
import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class QueriesTest extends SpringTest {

    @Autowired
    private FarmaciaDaoImpl farmaciaDao;

    @Autowired
    private FarmaciaPuntoDao farmaciaPuntoDao;

    @Autowired
    private FarmaciaComunaDao comunaDao;

    @Autowired
    private FarmaciaBarrioDao barrioDao;

    @Inject
    private SessionFactory sessionFactory;

    @Before
    public void mockData() {
        List<Comuna> comunas = new ArrayList<>();
        comunas.add(new Comuna("Uno"));
        comunas.add(new Comuna("Dos"));
        comunas.add(new Comuna("Tres"));
        comunas.add(new Comuna("Veinte"));
        for (Comuna comuna : comunas) {
            comunaDao.addComuna(comuna);
        }
        List<Farmacia> farmacias = new ArrayList<>();
        Direccion direccionUno = new Direccion("Av. de Mayo", 123, "BarrioUno", "Uno");
        Farmacia farmaciaUno = new Farmacia("Prueba", "123123123", "martes", direccionUno);
        Direccion direccionDos = new Direccion("Juan", 3333, "BarioDos", "Dos");
        Farmacia farmaciaDos = new Farmacia("PruebaDos", "313213", "jueves", direccionDos);
        Direccion direccionTres = new Direccion("Perez", 4444, "BarrioUno", "Uno");
        Farmacia farmaciaTres = new Farmacia("PruebaTres", "1111", "martes", direccionTres);
        Direccion direccionCuatro = new Direccion("Av. De Mayo", 125, "BarrioUno", "Uno");
        Farmacia farmaciaCuatro = new Farmacia ("Vilela", "12345678", "jueves", direccionCuatro);
        farmacias.add(farmaciaUno);
        farmacias.add(farmaciaDos);
        farmacias.add(farmaciaTres);
        for (Farmacia farm : farmacias) {
            farmaciaDao.addFarmacia(farm);
        }


    }

    @Test
    @Transactional
    @Rollback()
    public void leerFarmaciasMartes() {
        String turno = "martes";
        List<Farmacia> farmaciaDeMartes = farmaciaDao.getFarmaciasByTurno(turno);
        for (Farmacia farmacia : farmaciaDeMartes) {
            assertThat(farmacia.getDiaDeTurno()).isEqualTo(turno);
        }
    }

    @Test
    @Transactional
    @Rollback
    public void buscarTodasFarmaciasDeUnaCalle(){
        String  calle = "Av.De Mayo";
        List <Farmacia> farmaciaDeUnaCalle = farmaciaDao.getTodasLasFarmaciasDeUnaCalle(calle);
        for (Farmacia farmacia : farmaciaDeUnaCalle){
            assertThat(farmacia.getDireccion().getCalle()). isEqualTo (calle);
        }
    }

    @Test
    @Transactional
    @Rollback
    public void buscarTodasFarmaciasDeUnBarrio(){
        String  nombre = "Av.De Mayo";
        List <Farmacia> farmaciasDeUnBarrio = farmaciaDao.getTodasLasFarmaciasDeUnBarrio(nombre);
        for (Farmacia farmacia : farmaciasDeUnBarrio){
            assertThat(farmacia.getBarrio().getNombre()). isEqualTo (nombre);
        }
    }

}
