package org.clxmm.autocode.learn.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {



    public static Calendar beginOfQuarter(Calendar calendar) {
        //noinspection MagicConstant
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println( calendar.get(Calendar.MONTH) / 3 * 3);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) / 3 * 3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        System.out.println(calendar.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        return calendar;
    }


    public static void main(String[] args) {
        beginOfQuarter(Calendar.getInstance());
    }












}
