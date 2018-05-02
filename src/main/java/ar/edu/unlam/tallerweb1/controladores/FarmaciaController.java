package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Farmacia;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FarmaciaController {
@RequestMapping(path = "/ver-farmacia", method = RequestMethod.GET)
    public ModelAndView mosrarFarmacia() {

        ModelMap modelo = new ModelMap();

        Farmacia miFarmacia = new Farmacia("Tu mama", "3123123123", "tu vieja");

        modelo.put("farmacia", miFarmacia);

        return new ModelAndView("farmacia", modelo); // farmacia = vista. modelo = modelo ah re
    }
}
