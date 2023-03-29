package thayduc.quanlydancu.demo.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;

public final class ChuanHoa {

    public String chuanHoaToanBo(String s){
        StringTokenizer s1 = new StringTokenizer(s);
        StringBuffer kq = new StringBuffer();
        while(s1.hasMoreTokens()) {
            String s2 = s1.nextToken();
            s2 = s2.toLowerCase();
            String s3 = s2.substring(0, 1);
            s2 = s2.replaceFirst(s3, s3.toUpperCase());
            kq.append(s2).append(" ");
        }
        String daChuanHoa =  String.valueOf(kq);
        return  daChuanHoa;
    }

    public String chuanHoaChuCaiDau(String s){
        String s3 = s.substring(0, 1);
        s = s.replaceFirst(s3, s3.toUpperCase());
        return  s;
    }


}
