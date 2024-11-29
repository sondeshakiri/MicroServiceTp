
package com.example.Evenement.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.Evenement.Entities.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, String> {
	
}
