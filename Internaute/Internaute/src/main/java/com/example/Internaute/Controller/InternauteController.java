package com.example.Internaute.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Internaute.Entities.Internaute;
import com.example.Internaute.Service.InternauteService;

@RestController
@RequestMapping("/api/internautes")
public class InternauteController {
    @Autowired
    private InternauteService internauteService;

    @PostMapping
    public Internaute ajouterInternaute(@RequestBody Internaute internaute) {
        return internauteService.ajouterInternaute(internaute);
    }

    @GetMapping
    public List<Internaute> listerInternautes() {
        return internauteService.listerInternautes();
    }

    @GetMapping("/{id}")
    public Internaute getInternaute(@PathVariable Long id) {
        return internauteService.getInternaute(id);
    }
}
