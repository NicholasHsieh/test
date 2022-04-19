package com.abc.demo2.repository;

import com.abc.demo2.entity.Currentprice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentpriceRepository extends CrudRepository<Currentprice, Long> {

public List<Currentprice> findByEncu(String encu);

@Override
List<Currentprice> findAll();

}
