package com.abc.demo2.service;

import com.abc.demo2.entity.Currentprice;
import com.abc.demo2.repository.CurrentpriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentpriceService {

    @Autowired
    private CurrentpriceRepository currentpriceRepository;

}
