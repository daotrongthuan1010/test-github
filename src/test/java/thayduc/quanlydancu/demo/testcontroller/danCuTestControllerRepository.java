package thayduc.quanlydancu.demo.testcontroller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import thayduc.quanlydancu.demo.controller.DanCuController;
import thayduc.quanlydancu.demo.entity.CuDan;
import thayduc.quanlydancu.demo.repository.QuanLyDanCuRepository;
import thayduc.quanlydancu.demo.service.serviceIpm.QuanLyCuDanServiceIpm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = {DanCuController.class, QuanLyCuDanServiceIpm.class})
public class danCuTestControllerRepository {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    QuanLyDanCuRepository quanLyDanCuRepository;
    @Autowired
    DanCuController danCuController;
    @Test
    void getAll_by_CuDan() throws Exception {
        CuDan cuDan = new CuDan();
        cuDan.setTen("Trần Trâm Anh");
        cuDan.setEmail("12345@gmail.com");
        cuDan.setTrangthai("Đã chuyển");
        cuDan.setCmt("1523652");
        cuDan.setSoDT("0345685220");
        Date date = new SimpleDateFormat("YYYY-MM-DD").parse("2020-10-10");
        cuDan.setNgaysinh(date);
        CuDan cuDan1 = new CuDan();
        cuDan.setTen("Trần Trâm Anh");
        cuDan.setEmail("12345@gmail.com");
        cuDan.setTrangthai("Đã chuyển");
        cuDan.setCmt("15236532");
        cuDan.setSoDT("0345685220");
        Date date1 = new SimpleDateFormat("YYYY-MM-DD").parse("2020-10-10");
        cuDan.setNgaysinh(date1);

        List<CuDan> expected = Arrays.asList(cuDan, cuDan1);
        Mockito.when(quanLyDanCuRepository.findAll()).thenReturn(expected);
        List<CuDan> actual = quanLyDanCuRepository.findAll();
        Assertions.assertEquals(2, actual.size());
    }
//    @Test
//    void getAll_by_using_mockMvc() throws Exception {
//        CuDan cuDan = new CuDan();
//        cuDan.setTen("Trần Trâm Anh");
//        cuDan.setEmail("12345@gmail.com");
//        cuDan.setTrangthai("Đã chuyển");
//        cuDan.setCmt("1523652");
//        cuDan.setSoDT("0345685220");
//        Date date = new SimpleDateFormat("YYYY-MM-DD").parse("2020-10-10");
//        cuDan.setNgaysinh(date);
//        CuDan cuDan1 = new CuDan();
//        cuDan.setTen("Trần Trâm Anh");
//        cuDan.setEmail("12345@gmail.com");
//        cuDan.setTrangthai("Đã chuyển");
//        cuDan.setCmt("15236532");
//        cuDan.setSoDT("0345685220");
//        List<CuDan> expected = Arrays.asList(cuDan,cuDan1);
//        Mockito.when(quanLyDanCuRepository.findAll()).thenReturn(expected);
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/tim-kiem-cu-dan")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print()) // print the response context on screen
//                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isArray())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", org.hamcrest.Matchers.is(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trangThai", org.hamcrest.Matchers.is("Đã chuyển")))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andReturn();
//    }
//    @Test
//    @WithMockUser(username = "admin", authorities = { "ROLE_ADMIN" })
//    void get_CuDan_By_Id() throws Exception {
//        CuDan cuDan = new CuDan();
//        cuDan.setTen("Trần Trâm Anh");
//        cuDan.setEmail("12345@gmail.com");
//        cuDan.setTrangthai("Đã chuyển");
//        cuDan.setCmt("1523652");
//        cuDan.setSoDT("0345685220");
//        Date date = new SimpleDateFormat("YYYY-MM-DD").parse("2020-10-10");
//        cuDan.setNgaysinh(date);
//        Authentication auth = Mockito.mock(Authentication.class);
//        SecurityContext secCont = Mockito.mock(SecurityContext.class);
//        SecurityContextHolder.setContext(secCont);
//        Mockito.when(secCont.getAuthentication()).thenReturn(auth);
//        Mockito.when(auth.getName()).thenReturn("aName");
//
//        Mockito.when(quanLyDanCuRepository.findById(cuDan.getId())).thenReturn(Optional.of(cuDan));
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/customer/get/"+expected.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print()) // print the response context on screen
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", org.hamcrest.Matchers.is(expected.getId())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", org.hamcrest.Matchers.is(expected.getName())))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//    }

}
