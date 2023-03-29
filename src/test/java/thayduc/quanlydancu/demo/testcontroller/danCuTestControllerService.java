package thayduc.quanlydancu.demo.testcontroller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import thayduc.quanlydancu.demo.controller.CuDanApi;
import thayduc.quanlydancu.demo.entity.CuDan;
import thayduc.quanlydancu.demo.service.serviceIpm.QuanLyCuDanServiceIpm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class danCuTestControllerService {

    @Mock
    QuanLyCuDanServiceIpm cuDanServiceIpm;
    @InjectMocks
    CuDanApi controller;
    @Test
    void getAll() throws ParseException {
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
        List<CuDan> expected= Arrays.asList(cuDan, cuDan1);
        Mockito.when(cuDanServiceIpm.findByAll()).thenReturn(expected);
        List<CuDan> actual= controller.getAll();
        //Assertions.assertArrayEquals( new CustomerModel[]{}, actual.toArray());
        Assertions.assertArrayEquals( expected.toArray(), actual.toArray());
    }

    @Test
    void get() throws Exception {
        CuDan cuDan = new CuDan();
        cuDan.setTen("Trần Trâm Anh");
        cuDan.setEmail("12345@gmail.com");
        cuDan.setTrangthai("Đã chuyển");
        cuDan.setCmt("1523652");
        cuDan.setSoDT("0345685220");
        Date date = new SimpleDateFormat("YYYY-MM-DD").parse("2020-10-10");
        cuDan.setNgaysinh(date);
//        Mockito.when(cuDanServiceIpm.findById(cuDan.getId())).thenReturn(cuDan);
//        CuDan actual=controller.;
//        //Assertions.assertEquals( null, actual);
//        Assertions.assertEquals( expected, actual);
    }



}
