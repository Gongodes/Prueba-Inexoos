package cl.gonzalo.prueba.controller;

import cl.gonzalo.prueba.model.Consultas;
import cl.gonzalo.prueba.model.PAncianos;
import cl.gonzalo.prueba.model.PJovenes;
import cl.gonzalo.prueba.model.PNinnos;
import cl.gonzalo.prueba.repository.IConsultas;
import cl.gonzalo.prueba.repository.IPAncianos;
import cl.gonzalo.prueba.repository.IPJovenes;
import cl.gonzalo.prueba.repository.IPNinnos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Inicio {

    int cont = 0;

    @Autowired
    private IConsultas cc;

    @Autowired
    private IPNinnos pn;

    @Autowired
    private IPJovenes pj;

    @Autowired
    private IPAncianos pa;

    @GetMapping("/")
    public String inicio() {

        return "inicio";
    }

    @GetMapping("/consultas")
    public String inicio(ModelMap m) {

        List<Consultas> consultas = cc.findAllByEstado("En Espera");
        m.addAttribute("consulta", consultas);

        return "inicio2";

    }

    @GetMapping("/ocupados")
    public String ocupados(Model m) {
        List<Consultas> consultas = cc.findAllByEstado("Ocupada");
        m.addAttribute("consulta", consultas);

        return "ocupados";

    }

    @GetMapping("/desocupar")
    public String desocupar() {

        cc.findOcupados();

        return "redirect:/ocupados";
    }

    @GetMapping("/desocupar1")
    public String desocupar1(@RequestParam(name = "ids", required = false) Integer ids) {

        cc.findOcupados1(ids);

        return "redirect:/ocupados";
    }

    @PostMapping("/crear")

    public String crear(Model m, Consultas consulta) {

        cc.save(consulta);

        return "redirect:/consultas";

    }

    @GetMapping("/crearp")

    public String crear2() {

        return "pacientes";

    }

    @PostMapping("/tipo")

    public String tipo(@RequestParam(name = "edad") int edad, RedirectAttributes m) {

        if (edad >= 1 && edad <= 15) {

            m.addFlashAttribute("edad", edad);

            return "redirect:/pacientencalculo";

        } else if (edad >= 16 && edad <= 40) {

            m.addFlashAttribute("edad", edad);
            return "redirect:/paciejtencalculo";

        } else if (edad >= 41) {
            m.addFlashAttribute("edad", edad);
            return "redirect:/pacienteacalculo";
        }

        return null;

    }

    @GetMapping("/pacientencalculo")
    public String pacientencalculo() {

        return "pacientencalculo";
    }

    @GetMapping("/pacienteacalculo")
    public String pacienteacalculo() {

        return "pacienteacalculo";
    }

    @GetMapping("/paciejtencalculo")
    public String paciejtencalculo() {

        return "pacientejcalculo";
    }

    @PostMapping("/calculandon")

    public String calculando(RedirectAttributes m, @RequestParam(name = "peso") int peso, @RequestParam(name = "altura") int altura, @RequestParam(name = "edad") int edad) {

        if (edad >= 1 && edad <= 5) {

            int condicion = (peso / altura) + 3;

            m.addFlashAttribute("relacion", condicion);
            m.addFlashAttribute("edad", edad);
        }
        if (edad >= 6 && edad <= 12) {

            int condicion = (peso / altura) + 2;

            m.addFlashAttribute("relacion", condicion);
            m.addFlashAttribute("edad", edad);

        }
        if (edad >= 13 && edad <= 15) {

            int condicion = (peso / altura) + 1;

            m.addFlashAttribute("relacion", condicion);
            m.addFlashAttribute("edad", edad);
        }

        return "redirect:/pacienten";

    }

    @PostMapping("/calculandoj")

    public String calculandoj(RedirectAttributes m, @RequestParam(name = "fuma") String fuma, @RequestParam(name = "annio") int annio, @RequestParam(name = "edad") int edad) {

        if (fuma.equals("fuma")) {

            int condicion = annio / 4 + 2;

            int gravedad = (edad * condicion) / 100;
            m.addFlashAttribute("condicion", condicion);
            m.addFlashAttribute("fumador", true);
            m.addFlashAttribute("edad", edad);
        } else {

            int condicion = 2;

            int gravedad = (edad * condicion) / 100;
            m.addFlashAttribute("condicion", condicion);
            m.addFlashAttribute("fumador", false);
            m.addFlashAttribute("edad", edad);
        }

        return "redirect:/pacientej";

    }

    @PostMapping("/calculandoa")

    public String calculandoa(RedirectAttributes m, @RequestParam(name = "dieta") int dieta, @RequestParam(name = "edad") int edad) {

        if ((dieta == 1) && (edad >= 60 && edad <= 100)) {

            int condicion = edad / 20 + 4;
            int gravedad = (int) ((edad * condicion) / 100 + 5.3);
            m.addFlashAttribute("condicion", condicion);
            m.addFlashAttribute("edad", edad);
            m.addFlashAttribute("dieta", true);
        } else {
            int condicion = edad / 30 + 3;
            int gravedad = (int) ((edad * condicion) / 100 + 5.3);
            m.addFlashAttribute("condicion", condicion);
            m.addFlashAttribute("edad", edad);
            m.addFlashAttribute("dieta", false);
        }
        return "redirect:/pacientea";

    }

    @PostMapping("/crearn")

    public String crearn(PNinnos pnino) {
        pn.save(pnino);

        return "redirect:/";

    }

    @PostMapping("/crearj")

    public String crearj(PJovenes pjoven) {
        pj.save(pjoven);

        return "redirect:/";

    }

    @PostMapping("/creara")

    public String creara(PAncianos pancia) {
        pa.save(pancia);

        return "redirect:/";

    }

    @GetMapping("/pacienten")

    public String pacienten() {

        return "pacienten";

    }

    @GetMapping("/pacientej")

    public String pacientej() {

        return "pacientej";

    }

    @GetMapping("/pacientea")

    public String pacientea() {

        return "pacientea";

    }

    @GetMapping("/lista")
    public String lista(Model m) {

        Iterable<PJovenes> joven = pj.findgravedad();
        Iterable<PAncianos> anciano = pa.findgravedad();
        Iterable<PNinnos> ninno = pn.findgravedad();
        Iterable<PJovenes> pjmax = pj.findgravedadmax();
        Iterable<PAncianos> pamax = pa.findgravedadmax();
        Iterable<PNinnos> pnmax = pn.findgravedadmax();
        m.addAttribute("joven", joven);
        m.addAttribute("anciano", anciano);
        m.addAttribute("ninno", ninno);
        m.addAttribute("ninmax", pnmax);
        m.addAttribute("ancmax", pamax);
        m.addAttribute("jovmax", pjmax);
        return "lista";

    }

    @GetMapping("/mayor")
    public String MAyorR() {

        return "mayor";

    }

    @GetMapping("/mayor2")
    public String MAyorRC(RedirectAttributes m, @RequestParam(name = "mayor", required = false) Integer mayor) {

        Iterable<PAncianos> pamax = pa.findMayorRiesgo(mayor);
        Iterable<PNinnos> pnmax = pn.findMayorRiesgo(mayor);
        Iterable<PJovenes> pjmax = pj.findMayorRiesgo(mayor);

        m.addFlashAttribute("ninmax", pnmax);
        m.addFlashAttribute("ancmax", pamax);
        m.addFlashAttribute("jovmax", pjmax);

        return "redirect:/mayor";

    }

    @GetMapping("/atenderadulto")
    public String atenderadulto(@RequestParam(name = "idanc", required = false, defaultValue = "0") Integer idanc, @RequestParam(name = "idjov", required = false, defaultValue = "0") Integer idjov) {

        System.out.println(idanc);
        System.out.println(idjov);

        if (idanc == 0 && idjov == 0) {

            return "nohaypaci";

        }

        if (pj.max() < pa.max()) {

            Integer cantidad = cc.findbycantidadCGE();

            if (cantidad == 0) {
                return "nohay";

            } else {
                cc.findCGIOcupado();
                pa.deleteById(pa.maxid());

            }
        }else

        if (pj.max() > pa.max()) {

            Integer cantidad = cc.findbycantidadCGE();

            if (cantidad == 0) {
                return "nohay";

            } else {

                cc.findCGIOcupado();

                pj.deleteById(pj.maxid());
            }
        }
        else
        
        
         if (pj.max() == pa.max()){  Integer cantidad = cc.findbycantidadCGE();

            if (cantidad == 0) {
                return "nohay";

            } else {
                cc.findCGIOcupado();
                pa.deleteById(pa.maxid());

            }    }
        
        
        
        
        
        

        return "redirect:/lista";
    }

    @GetMapping("/atendernino")
    public String atendernino(@RequestParam(name = "idnin", required = false, defaultValue = "0") Integer idnino) {

        if (idnino == 0) {

            return "nohaypaci";

        }

        Integer cantidad = cc.findbycantidadPED();

        if (cantidad == 0) {
            return "nohay";

        } else {

            cc.findPEDcupado();

            pn.deleteById(pn.maxid());
        }

        return "redirect:/lista";
    }

    @GetMapping("/atendercritico")
    public String atendercritico() {

        System.out.println(pa.maxgrave());

        System.out.println(pj.maxgrave());
        System.out.println(pn.maxgrave());

        if (pa.maxgrave() == 0 && pj.maxgrave() == 0 && pn.maxgrave() == 0) {

            return "nohaypaci";

        }

        if (pj.maxgrave() < pa.maxgrave() && pn.maxgrave() < pa.maxgrave()) {

            Integer cantidad = cc.findbycantidadURG();

            if (cantidad == 0) {
                return "nohay";

            } else {
                cc.findURGcupado();
                pa.deleteById(pa.maxidgrave());

            }
        } else if (pa.maxgrave() < pj.maxgrave() && pn.maxgrave() < pj.maxgrave()) {

            Integer cantidad = cc.findbycantidadURG();

            if (cantidad == 0) {
                return "nohay";

            } else {
                cc.findURGcupado();
                pj.deleteById(pj.maxidgrave());

            }
        } else if (pa.maxgrave() < pn.maxgrave() && pj.maxgrave() < pn.maxgrave()) {

            Integer cantidad = cc.findbycantidadURG();

            if (cantidad == 0) {
                return "nohay";

            } else {
                cc.findURGcupado();
                pn.deleteById(pn.maxidgrave());

            }
        } else if (pa.maxgrave() == pn.maxgrave() || pj.maxgrave() == pn.maxgrave()) {

            Integer cantidad = cc.findbycantidadURG();

            if (cantidad == 0) {
                return "nohay";

            } else {
                cc.findURGcupado();
                pn.deleteById(pn.maxidgrave());

            }
        } else if (pj.maxgrave() == pa.maxgrave()) {

            Integer cantidad = cc.findbycantidadURG();

            if (cantidad == 0) {
                return "nohay";

            } else {
                cc.findURGcupado();
                pa.deleteById(pa.maxidgrave());

            }
        }

        return "redirect:/lista";
    }

    @GetMapping("/fumador")
    public String fumador(Model m) {
        Iterable<PJovenes> fumador = pj.findfumador();
        m.addAttribute("jovmax", fumador);
        return "fumador";

    }

    @GetMapping("/anciano")
    public String anciano(Model m) {
        Iterable<PAncianos> panc = pa.findMasAnciano();
        m.addAttribute("panc", panc);
        return "masanciano";

    }

    @GetMapping("/masol")
    public String masol(Model m) {
        Iterable<Consultas> consulta = cc.findMassol();
        m.addAttribute("consulta", consulta);
        return "masol";

    }

}
