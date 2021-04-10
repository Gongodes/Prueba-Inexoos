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
import java.util.Optional;
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

    @GetMapping("/agendar")
    public String agendar(ModelMap m, @RequestParam(name = "id") int id, @RequestParam(name = "tipo") String tipo) {

        if (tipo.equals("Pediatria")) {

            Iterable<PNinnos> ninno = pn.findgravedad();
            m.addAttribute("ninno", ninno);
            m.addAttribute("id", id);
            return "agendarpediatria";
        } else if (tipo.equals("CGI")) {

            List<PJovenes> joven = pj.findgravedad();
            List<PAncianos> anciano = pa.findgravedad();

            m.addAttribute("joven", joven);
            m.addAttribute("anciano", anciano);
            m.addAttribute("id", id);
            return "agendarcgi";
        }

        return null;

    }

    @GetMapping("/agendarped")
    public String agendarped(@RequestParam(name = "id", required = false) Integer id, @RequestParam(name = "idnin", required = false) Integer idnin) {

        if (id == null || idnin == null) {
            return "agendarpediatria";
        } else {

            cc.aumentar(id);

            pn.deleteById(idnin);
        }

        return "redirect:/consultas";
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

        Iterable<PJovenes> joven = pj.findAll();
        Iterable<PAncianos> anciano = pa.findAll();

        m.addAttribute("joven", joven);
        m.addAttribute("anciano", anciano);

        return "lista";

    }

    @GetMapping("/atender")
    public String atender(@RequestParam(name = "ngrav", required = false, defaultValue = "0") int ngrav, @RequestParam(name = "idnin", required = false, defaultValue = "0" ) int idnin, @RequestParam(name = "idanc", required = false, defaultValue = "0") Integer idanc, @RequestParam(name = "idjov", required = false , defaultValue = "0") Integer idjov, @RequestParam(name = "agrav", required = false, defaultValue = "0") Integer agrav, @RequestParam(name = "jgrav", required = false, defaultValue = "0") Integer jgrav) {
      
        System.out.println(pj.max());
        System.out.println(pa.max());
        System.out.println(cc.findbycantidad());
        if (idnin == 0 && idanc == 0 && idjov == 0) {

            return "nohaypaci";

        }

        //ANCIANO
        if (pj.max() < pa.max()) {
            System.out.println("entre en la 1");
            Integer cantidad = cc.findbycantidad();
            
            if (cantidad ==0) {  return "nohay";
                
            }else{
            
              cc.findCGIOcupado();
             pa.deleteById(pa.maxid());    
           
            }
        }

        // JOVEN
        if (pj.max() > pa.max()) {
            System.out.println("entre en la 2");
            Integer cantidad = cc.findbycantidad();
            
            if (cantidad ==0) { return "nohay";
                
            }else{
            
              cc.findCGIOcupado();

            pj.deleteById(pj.maxid());
            }
        }

        

        return "redirect:/lista";
    }

}
