package com.example.Internaute.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Internaute.Entities.Internaute;
import com.example.Internaute.Service.InternauteService;

@Service
public class InternauteServiceImpl implements InternauteService {

    private final InternauteRepository internauteRepository;

    // Injection de dépendances via le constructeur
    @Autowired
    public InternauteServiceImpl(InternauteRepository internauteRepository) {
        this.internauteRepository = internauteRepository;
    }

    @Override
    public Internaute ajouterInternaute(Internaute internaute) {
        return internauteRepository.save(internaute);
    }

    @Override
    public List<Internaute> listerInternautes() {
        return internauteRepository.findAll();
    }

    @Override
    public Internaute getInternaute(Long id) {
        return internauteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internaute non trouvé avec l'ID : " + id));
    }
}
