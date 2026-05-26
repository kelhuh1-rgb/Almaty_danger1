package com.Almaty_danger.Almaty_danger.controller;

import com.Almaty_danger.Almaty_danger.model.District;
import com.Almaty_danger.Almaty_danger.Service.DistrictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DistrictController {

    private final DistrictService districtService;

    // Явный конструктор
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/districts")
    public List<DistrictDto> getDistricts() {
        List<District> districts = districtService.getAllDistricts();

        return districts.stream()
                .map(d -> new DistrictDto(
                        d.getName(),
                        calculateDangerLevel(d),
                        d.getDescription()
                ))
                .collect(Collectors.toList());
    }

    private int calculateDangerLevel(District district) {
        if (district.getIncidents() == null || district.getIncidents().isEmpty()) {
            return 2; // безопасно по умолчанию
        }

        double avg = district.getIncidents().stream()
                .mapToInt(inc -> inc.getDangerLevel() != null ? inc.getDangerLevel() : 0)
                .average()
                .orElse(2.0);

        return Math.min(5, Math.max(1, (int) Math.round(avg)));
    }

    // DTO класс
    public static class DistrictDto {
        public final String name;
        public final int dangerLevel;
        public final String description;

        public DistrictDto(String name, int dangerLevel, String description) {
            this.name = name;
            this.dangerLevel = dangerLevel;
            this.description = description;
        }
    }
}