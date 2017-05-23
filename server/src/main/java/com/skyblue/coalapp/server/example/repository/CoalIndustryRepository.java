package com.skyblue.coalapp.server.example.repository;

import com.skyblue.coalapp.server.example.domain.Factory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/19.
 */
public interface CoalIndustryRepository extends CrudRepository<Factory, Long> {

    Factory findByCode(String code);

    List<Factory> findAllByName(String name);

    @Override
    <S extends Factory> S save(S s);

    @Override
    void delete(Factory coalIndustry);
}
