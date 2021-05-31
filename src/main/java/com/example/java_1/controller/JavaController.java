package com.example.java_1.controller;
import com.example.java_1.model.Usuario;
import com.example.java_1.repo.IMercanciaRepo;
import com.example.java_1.repo.IUsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Los controladores que se utilizan para los servicios estan en la carpeta res

@Controller
public class JavaController {
    @Autowired
    private IUsuarioRepo repo;
    @Autowired
    private IMercanciaRepo Merrepo;


    @GetMapping("/home")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        Usuario p = new Usuario();
        p.setNombre("jean");
        //p.setIdUsuario(1);
        repo.save(p);

        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/listar")
    public String greeting(Model model) {


        model.addAttribute("name", "pruebas");
        model.addAttribute("usuario", Merrepo.findAll());
        return "listar";
    }


}
