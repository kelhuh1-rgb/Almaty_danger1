package com.Almaty_danger.Almaty_danger.repository;

import com.Almaty_danger.Almaty_danger.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
}