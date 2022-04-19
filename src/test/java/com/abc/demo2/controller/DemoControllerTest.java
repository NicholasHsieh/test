package com.abc.demo2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
class DemoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    //1.測試呼叫查詢幣別對應表資料API，並顯示其內容。
    @Test
    public void select_Currency() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/select_Currency/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    //2.測試呼叫新增幣別對應表資料API
    @Test
    public void insertCurrent() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/insert_Currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"encu\" : \"KRW\",\n" +
                        "  \"chcu\" : \"韓幣\",\n" +
                        "  \"rate_float\" : 50465273.00,\n" +
                        "  \"update_time\" : \"2022/04/19 04:11:50\"\n" +
                        "}");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    //3.測試呼叫更新幣別對應表資料API，並顯示其內容。
    @Test
    public void updateCurrent() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/update_Currency/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"encu\" : \"USD\",\n" +
                        "  \"chcu\" : \"美金\",\n" +
                        "  \"rate_float\" : 39507.35,\n" +
                        "  \"update_time\" : \"2022/04/19 04:36:26\"\n" +
                        "}");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    //4. 測試呼叫刪除幣別對應表資料API。
    @Test
    public void deleteCurrentprice() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/delete_Currency/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    //5.測試呼叫coindesk API，並顯示其內容。
    @Test
    public void getAPI() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getAPI");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    //6.測試呼叫資料轉換的API，並顯示其內容。
    @Test
    public void getNEW_API() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .get("/getNewAPI");
            mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print());
    }




}