package com.example.Internaute.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Internaute.Entities.Internaute;

@Repository
public interface InternauteRepository extends JpaRepository<Internaute, Long> {}

