package com.example.queries.repositories;

import com.example.queries.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad,String>{
    List<Ad> findByDescription(String description);

}