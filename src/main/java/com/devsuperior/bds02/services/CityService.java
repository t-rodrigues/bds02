package com.devsuperior.bds02.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAllOrderByName() {
        return cityRepository.findAllByOrderByNameAsc();
    }

    public CityDTO create(CityDTO cityDTO) {
        var city = new City(null, cityDTO.getName());
        cityRepository.save(city);
        return new CityDTO(city);
    }
}
