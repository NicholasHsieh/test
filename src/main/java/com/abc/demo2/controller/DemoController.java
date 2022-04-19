package com.abc.demo2.controller;

import com.abc.demo2.repository.CurrentpriceRepository;
import com.abc.demo2.service.CurrentpriceService;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.demo2.entity.Currentprice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@RestController
public class DemoController {

	@Autowired
    private CurrentpriceService currentpriceService;

    @Autowired
    private CurrentpriceRepository currentpriceRepository;


    //insert API
    @PostMapping("/insert_Currency")
    public String insert(@RequestBody Currentprice currentprice) {

        currentpriceRepository.save(currentprice);

        return "Insert 資料庫操作完成!" + currentpriceRepository.count();
    }

    //update API
    @PutMapping("/update_Currency/{ID}")
    public String update(@PathVariable long ID,
                         @RequestBody Currentprice currentprice) {

        currentprice.setID(ID);
        Currentprice currentprice1 = currentpriceRepository.save(currentprice);

        return "Update 資料庫操作完成!";
    }

    //delete API
    @DeleteMapping("/delete_Currency/{ID}")
    public String delete(@PathVariable long ID) {

        currentpriceRepository.deleteById(ID);

        return "Delete 資料庫操作完成!" + currentpriceRepository.count();
    }

    //select API
    @GetMapping("/select_Currency/{ID}")
    public Currentprice read(@PathVariable long ID) {

        Currentprice currentprice = currentpriceRepository.findById(ID).orElse((null));

        return currentprice;
    }

    //selectall API
    @GetMapping("/selectall_Currency")
    public List<Currentprice> read_all() {

        List<Currentprice> currentprice = currentpriceRepository.findAll();

        return currentprice;
    }

    //get coindesk API
    @GetMapping("/getAPI")
    public String getAPI() {

        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        RestTemplate r = new RestTemplate();
        String sr = r.getForObject(url, String.class);

        return sr;
    }

    // 轉換coindesk API 為 新API (含幣別中文名稱、更新時間)
    @GetMapping("/getNewAPI")
    public List<Currentprice> getNewAPI() {

        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        RestTemplate r = new RestTemplate();
        String sr = r.getForObject(url, String.class);

        JSONObject jsonObject = new JSONObject(sr);
        String chartName = (String) jsonObject.get("chartName");

        String symbol_EUR = jsonObject.getJSONObject("bpi").getJSONObject("EUR").getString("symbol");
        float rate_float_EUR = jsonObject.getJSONObject("bpi").getJSONObject("EUR").getFloat("rate_float");
        String code_EUR = jsonObject.getJSONObject("bpi").getJSONObject("EUR").getString("code");
        String rate_EUR = jsonObject.getJSONObject("bpi").getJSONObject("EUR").getString("rate");
        String description_EUR = jsonObject.getJSONObject("bpi").getJSONObject("EUR").getString("description");

        String symbol_GBP = jsonObject.getJSONObject("bpi").getJSONObject("GBP").getString("symbol");
        float rate_float_GBP = jsonObject.getJSONObject("bpi").getJSONObject("GBP").getFloat("rate_float");
        String code_GBP = jsonObject.getJSONObject("bpi").getJSONObject("GBP").getString("code");
        String rate_GBP = jsonObject.getJSONObject("bpi").getJSONObject("GBP").getString("rate");
        String description_GBP = jsonObject.getJSONObject("bpi").getJSONObject("GBP").getString("description");

        String symbol_USD = jsonObject.getJSONObject("bpi").getJSONObject("USD").getString("symbol");
        float rate_float_USD = jsonObject.getJSONObject("bpi").getJSONObject("USD").getFloat("rate_float");
        String code_USD = jsonObject.getJSONObject("bpi").getJSONObject("USD").getString("code");
        String rate_USD = jsonObject.getJSONObject("bpi").getJSONObject("USD").getString("rate");
        String description_USD = jsonObject.getJSONObject("bpi").getJSONObject("USD").getString("description");

        Date date1 = new Date();
        Timestamp nousedate = new Timestamp(date1.getTime());

        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateStr = sdf.format(date);

        List<Currentprice> currentprices = currentpriceRepository.findByEncu(code_EUR);
        Currentprice currentprice = new Currentprice();
        currentprice.setEncu(code_EUR);
        currentprice.setChcu(currentprices.get(0).getChcu());
        currentprice.setRate_float(rate_float_EUR);
        currentprice.setUpdate_time(dateStr);

        List<Currentprice> currentprices1 = currentpriceRepository.findByEncu(code_USD);
        Currentprice currentprice1 = new Currentprice();
        currentprice1.setEncu(code_USD);
        currentprice1.setChcu(currentprices1.get(0).getChcu());
        currentprice1.setRate_float(rate_float_USD);
        currentprice1.setUpdate_time(dateStr);

        List<Currentprice> currentprices2 = currentpriceRepository.findByEncu(code_GBP);
        Currentprice currentprice2 = new Currentprice();
        currentprice2.setEncu(code_GBP);
        currentprice2.setChcu(currentprices2.get(0).getChcu());
        currentprice2.setRate_float(rate_float_GBP);
        currentprice2.setUpdate_time(dateStr);

        List<Currentprice> s = new ArrayList<>();
        s.add(currentprice);
        s.add(currentprice1);
        s.add(currentprice2);

        return s;

    }

}
