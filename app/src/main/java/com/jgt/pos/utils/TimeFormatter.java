package com.jgt.pos.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatter {
    public static String toDate(long timeStamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy HH:mm");
        return dateFormat.format(timeStamp);
    }

    public static long toTimestamp(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(date);
        return new Timestamp(date1.getTime()).getTime();
    }
}
