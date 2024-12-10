package com.example.cine.Repository.Services;
import com.example.cine.Entity.Buy.Sala;
import com.example.cine.Repository.Interfaces.ISalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SalaService {

    private ISalaRepository salaRepository;

    @Autowired
    public SalaService(ISalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Sala findByid(long id) {
        return salaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Sala sala) {
        salaRepository.save(sala);
    }

    @Transactional
    public void deleteById(long id){
        salaRepository.deleteById(id);
    }

}
