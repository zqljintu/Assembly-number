package com.zql.app_ji.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public abstract class Ji_DateUtils {
    public static int getTheYearoncalendar(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return calendar.get(Calendar.YEAR);
    }
    public static int getTheMonthoncalendar(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return calendar.get(Calendar.MONTH)+1;
    }
    public static boolean iscurrentdate(String date){
        Calendar calendar=Calendar.getInstance();
        String currentdate=new String(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+1+"-"+calendar.get(Calendar.DAY_OF_MONTH));
        return currentdate.equals(date);
    }
    public static String getDateStringForUrl(String date){
        int[] step=new int[2];
        for (int i=0,j=0;i<date.length();i++){
            if (date.charAt(i)=='-'){
                step[j]=i;
                j++;
            }
        }
        int year=Integer.parseInt(date.substring(0,step[0]));
        int mounth=Integer.parseInt(date.substring(step[0]+1,step[1]));
        int day=Integer.parseInt(date.substring(step[1]+1,date.length()));
        String reStr=""+year;
        if (mounth>0&&mounth<10){
            reStr=reStr+"0"+mounth;
        }else {
            reStr=reStr+mounth;
        }
        if (day>0&&day<10){
            reStr=reStr+"0"+day;
        }else {
            reStr=reStr+day;
        }
        return reStr;
    }
    public static String getTomorrowDate(String nowday){
        Calendar calendar=Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(nowday);
        }catch (ParseException e){
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day1=calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day1+1);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }
}
