package com.example.cine.Repository.Services;

import com.example.cine.Entity.Buy.Tarjeta;
import com.example.cine.Repository.Interfaces.ITarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TarjetaService {

    private ITarjetaRepository tarjetaRepository;

    @Autowired
    public TarjetaService(ITarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public List<Tarjeta> findAll() {
        return tarjetaRepository.findAll();
    }

    public Tarjeta findByid(long id) {
        return tarjetaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Tarjeta tarjeta) {
        tarjetaRepository.save(tarjeta);
    }

    @Transactional
    public void deleteById(long id){
        tarjetaRepository.deleteById(id);
    }

}
