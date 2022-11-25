package com.tangpusweetshop.exam_system.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    private volatile static CommonUtils commonUtils;

    private CommonUtils(){}

    public static CommonUtils getInstance(){
        if (commonUtils==null){
            synchronized (CommonUtils.class){
                if (commonUtils==null){
                    commonUtils=new CommonUtils();
                }
            }
        }
        return commonUtils;
    }

    public String generateTestId(){
        Date date=new Date();
        SimpleDateFormat ft=new SimpleDateFormat("yyyyMMddHHmmss");
        return "T"+ft.format(date);
    }
}
