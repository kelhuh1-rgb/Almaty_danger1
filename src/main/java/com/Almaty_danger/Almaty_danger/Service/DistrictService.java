package com.Almaty_danger.Almaty_danger.Service;

import com.Almaty_danger.Almaty_danger.model.District;
import com.Almaty_danger.Almaty_danger.model.Incident;
import com.Almaty_danger.Almaty_danger.repository.DistrictRepository;
import com.Almaty_danger.Almaty_danger.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.List;

@Service

public class DistrictService {

    private final DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Transactional(readOnly = true)
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Transactional(readOnly = true)
    public DistrictStats getMostDangerousDistrict() {
        List<District> districts = districtRepository.findAll();

        return districts.stream()
                .filter(d -> d.getIncidents() != null && !d.getIncidents().isEmpty())
                .map(district -> new DistrictStats(district, calculateDangerScore(district)))
                .max((s1, s2) -> Double.compare(s1.dangerScore, s2.dangerScore))
                .orElse(null);
    }

    private double calculateDangerScore(District district) {
        List<Incident> incidents = district.getIncidents();
        if (incidents == null || incidents.isEmpty()) {
            return 0.0;
        }

        int totalIncidents = incidents.size();
        double averageDanger = incidents.stream()
                .mapToInt(Incident::getDangerLevel)
                .average()
                .orElse(0.0);

        return averageDanger * totalIncidents;
    }


    public static class DistrictStats {
        public final District district;
        public final double dangerScore;
        public final double dangerPercent;

        public DistrictStats(District district, double dangerScore) {
            this.district = district;
            this.dangerScore = dangerScore;
            this.dangerPercent = Math.min(100.0, dangerScore * 5);
        }
    }
}
