package com.github.aliaksei_karaliou.vkclient.utils;

import android.content.Context;
import android.text.format.DateUtils;

import com.github.aliaksei_karaliou.vkclient.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    private static final String TODAY_TEMPLATE = "HH:mm";
    private static final String THIS_YEAR_TEMPLATE = "d MMM";
    private static final String DEFAULT_TEMPLATE = "d MMM yyyy";

    private static final long MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;

    private final Context pContext;

    public DateFormatter(final Context pPContext) {
        pContext = pPContext;
    }

    public String format(final Date pDate) {
        if (isToday(pDate)) {
            return formatToday(pDate);
        } else if (isYesterday(pDate)) {
            return formatYesterday(pDate);
        } else if (isThisYear(pDate)) {
            return formatThisYear(pDate);
        } else {
            return formatDefault(pDate);
        }
    }

    private boolean isToday(final Date pDate) {
        return DateUtils.isToday(pDate.getTime());
    }

    private boolean isYesterday(final Date pDate) {
        return DateUtils.isToday(pDate.getTime() + MILLISECONDS_IN_DAY);
    }

    private boolean isThisYear(final Date pDate) {
        final Calendar calendar = Calendar.getInstance();

        final int currentYear = calendar.get(Calendar.YEAR);

        calendar.setTime(pDate);
        final int dateYear = calendar.get(Calendar.YEAR);

        return currentYear == dateYear;
    }

    private String formatToday(final Date pDate) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TODAY_TEMPLATE, Locale.getDefault());

        return simpleDateFormat.format(pDate);
    }

    private String formatYesterday(final Date pDate) {
        return pContext.getString(R.string.yesterday);
    }

    private String formatThisYear(final Date pDate) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(THIS_YEAR_TEMPLATE, Locale.getDefault());

        return simpleDateFormat.format(pDate);
    }

    private String formatDefault(final Date pDate) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_TEMPLATE, Locale.getDefault());

        return simpleDateFormat.format(pDate);
    }

}
