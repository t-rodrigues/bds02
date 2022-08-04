package com.devsuperior.bds02.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getCities() {
        var cities = cityService.findAllOrderByName().stream().map(CityDTO::new).toList();

        return ResponseEntity.ok(cities);
    }

    @PostMapping
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) {
        var city = cityService.create(cityDTO);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cityId}").buildAndExpand(city.getId()).toUri();

        return ResponseEntity.created(location).body(city);
    }
}
