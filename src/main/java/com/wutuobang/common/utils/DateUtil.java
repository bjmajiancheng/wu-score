package com.wutuobang.common.utils;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//迁移来自com.davdian.erp.utils.DateUtil
public class DateUtil {

    /**
     * 获取SimpleDateFormat
     *
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date     日期
     * @param dateType 日期格式
     * @return 数值
     */
    private static int getInteger(Date date, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateType);
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期字符串
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = StringToDate(date, dateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = DateToString(myDate, dateStyle);
        }
        return dateString;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期
     */
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 获取精确的日期
     *
     * @param timestamps 时间long集合
     * @return 日期
     */
    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();

        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = { timestamps.get(i), timestamps.get(j) };
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    // 如果timestamps的size为2，这是差值只有一个，因此要给默认值
                    minAbsoluteValue = absoluteValues.get(0);
                }
                for (int i = 0; i < absoluteValues.size(); i++) {
                    for (int j = i + 1; j < absoluteValues.size(); j++) {
                        if (absoluteValues.get(i) > absoluteValues.get(j)) {
                            minAbsoluteValue = absoluteValues.get(j);
                        } else {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
                    } else if (absoluteValues.size() == 1) {
                        // 当timestamps的size为2，需要与当前时间作为参照
                        long dateOne = timestampsLastTmp[0];
                        long dateTwo = timestampsLastTmp[1];
                        if ((Math.abs(dateOne - dateTwo)) < 100000000000L) {
                            timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
                        } else {
                            long now = new Date().getTime();
                            if (Math.abs(dateOne - now) <= Math.abs(dateTwo - now)) {
                                timestamp = dateOne;
                            } else {
                                timestamp = dateTwo;
                            }
                        }
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }

        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (StringToDate(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }

    /**
     * 获取日期字符串的日期风格。失敗返回null。
     *
     * @param date 日期字符串
     * @return 日期风格
     */
    public static DateStyle getDateStyle(String date) {
        DateStyle dateStyle = null;
        Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
        List<Long> timestamps = new ArrayList<Long>();
        for (DateStyle style : DateStyle.values()) {
            Date dateTmp = StringToDate(date, style.getValue());
            if (dateTmp != null) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        dateStyle = map.get(getAccurateDate(timestamps).getTime());
        return dateStyle;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date StringToDate(String date) {
        DateStyle dateStyle = null;
        return StringToDate(date, dateStyle);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date     日期字符串
     * @param parttern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String parttern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(parttern).parse(date);
            } catch (Exception e) {
            }
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date      日期字符串
     * @param dateStyle 日期风格
     * @return 日期
     */
    public static Date StringToDate(String date, DateStyle dateStyle) {
        Date myDate = null;
        if (dateStyle == null) {
            List<Long> timestamps = new ArrayList<Long>();
            for (DateStyle style : DateStyle.values()) {
                Date dateTmp = StringToDate(date, style.getValue());
                if (dateTmp != null) {
                    timestamps.add(dateTmp.getTime());
                }
            }
            myDate = getAccurateDate(timestamps);
        } else {
            myDate = StringToDate(date, dateStyle.getValue());
        }
        return myDate;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date     日期
     * @param parttern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String parttern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(parttern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date      日期
     * @param dateStyle 日期风格
     * @return 日期字符串
     */
    public static String DateToString(Date date, DateStyle dateStyle) {
        String dateString = null;
        if (dateStyle != null) {
            dateString = DateToString(date, dateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 数字类型obj转格式化字符串
     *
     * @param obj       10位int Object
     * @param dateStyle 日期格式
     */
    public static String intObj2FmtDateStr(Object obj, DateStyle dateStyle) {
        String res = "未知";
        if (obj == null) return res;

        String intStr = obj.toString().trim();
        if (StringUtils.isEmpty(intStr)) return res;

        int seconds;
        try {
            seconds = Integer.valueOf(intStr);
            if (seconds < 0) return res;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return res;
        }

        return DateToString(new Date(1000L * seconds), dateStyle);
    }

    /**
     * 秒数转字符串时间
     *
     * @param seconds
     * @param dateStyle
     * @return
     */
    public static String IntToDateString(Integer seconds, DateStyle dateStyle) {
        return IntToDateString(Long.valueOf(seconds * 1000L), dateStyle);
    }

    /**
     * 毫秒数转字符串时间
     *
     * @param millionSeconds
     * @param dateStyle
     * @return
     */
    public static String IntToDateString(Long millionSeconds, DateStyle dateStyle) {
        return DateToString(new Date(millionSeconds), dateStyle);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date     旧日期字符串
     * @param parttern 新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String parttern) {
        return StringToString(date, null, parttern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date      旧日期字符串
     * @param dateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle dateStyle) {
        return StringToString(date, null, dateStyle);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddParttern 旧日期格式
     * @param newParttern  新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddParttern, String newParttern) {
        String dateString = null;
        if (olddParttern == null) {
            DateStyle style = getDateStyle(date);
            if (style != null) {
                Date myDate = StringToDate(date, style.getValue());
                dateString = DateToString(myDate, newParttern);
            }
        } else {
            Date myDate = StringToDate(date, olddParttern);
            dateString = DateToString(myDate, newParttern);
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {
        String dateString = null;
        if (olddDteStyle == null) {
            DateStyle style = getDateStyle(date);
            dateString = StringToString(date, style.getValue(), newDateStyle.getValue());
        } else {
            dateString = StringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static String addMonth(String date, int yearAmount) {
        return addInteger(date, Calendar.MONTH, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int yearAmount) {
        return addInteger(date, Calendar.MONTH, yearAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期字符串
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date   日期字符串
     * @param amount 增加数量。可为负数
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int amount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date   日期
     * @param amount 增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int amount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date   日期字符串
     * @param amount 增加数量。可为负数
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int amount) {
        return addInteger(date, Calendar.MINUTE, amount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date   日期
     * @param amount 增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int amount) {
        return addInteger(date, Calendar.MINUTE, amount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date   日期字符串
     * @param amount 增加数量。可为负数
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int amount) {
        return addInteger(date, Calendar.SECOND, amount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date   日期
     * @param amount 增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int amount) {
        return addInteger(date, Calendar.SECOND, amount);
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期字符串
     * @return 年份
     */
    public static int getYear(String date) {
        return getYear(StringToDate(date));
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期字符串
     * @return 月份
     */
    public static int getMonth(String date) {
        return getMonth(StringToDate(date));
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH);
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期字符串
     * @return 天
     */
    public static int getDay(String date) {
        return getDay(StringToDate(date));
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期字符串
     * @return 小时
     */
    public static int getHour(String date) {
        return getHour(StringToDate(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 分钟
     */
    public static int getMinute(String date) {
        return getMinute(StringToDate(date));
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date) {
        return getSecond(StringToDate(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }

    /**
     * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static String getDate(String date) {
        return StringToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date   待转换字符串
     * @param format 日期格式
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date, String format) {
        return getDate(date, format, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date   日期对象
     * @param format 日期格式
     * @param defVal 转换失败时的默认返回值
     * @return 转换后的日期对象
     */
    public static Date getDate(String date, String format, Date defVal) {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(format)) return null;
        Date d;
        try {
            d = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            d = defVal;
        }
        return d;
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 时间
     */
    public static String getTime(String date) {
        return StringToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        return DateToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期字符串
     * @return 星期
     */
    public static Week getWeek(String date) {
        Week week = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = StringToDate(date, dateStyle);
            week = getWeek(myDate);
        }
        return week;
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期
     * @return 星期
     */
    public static Week getWeek(Date date) {
        Week week = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (weekNumber) {
            case 0:
                week = Week.SUNDAY;
                break;
            case 1:
                week = Week.MONDAY;
                break;
            case 2:
                week = Week.TUESDAY;
                break;
            case 3:
                week = Week.WEDNESDAY;
                break;
            case 4:
                week = Week.THURSDAY;
                break;
            case 5:
                week = Week.FRIDAY;
                break;
            case 6:
                week = Week.SATURDAY;
                break;
        }
        return week;
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差天数
     */
    public static int getIntervalDays(String date, String otherDate) {
        return getIntervalDays(StringToDate(date), StringToDate(otherDate));
    }

    /**
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差天数
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long time1 = cal.getTimeInMillis();
        cal.setTime(otherDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取两个日期中的以天的拆分
     *
     * @param start
     * @param end
     * @param dateStyle
     * @return
     */
    public static List<String> getDatesOfDayBetweenDates(Date start, Date end, DateStyle dateStyle) {
        if (start.after(end)) {
            return null;
        }
        if (dateStyle == null) dateStyle = DateStyle.YYYY_MM_DD_HH_MM_SS;
        List<String> list = new ArrayList<String>();
        int interval = getIntervalDays(start, end);
        interval = Math.abs(interval);
        for (int i = 0; i <= interval; i++) {
            list.add(DateToString(start, dateStyle));
            start = addDay(start, 1);
        }
        return list;
    }

    /**
     * 获取两个日期中的以天的拆分
     *
     * @param start
     * @param end
     * @param dateStyle
     * @return
     */
    public static List<String> getDatesOfDayBetweenDates(String start, String end, DateStyle dateStyle) {
        return getDatesOfDayBetweenDates(StringToDate(start), StringToDate(end), dateStyle);
    }

    public static Date getMonthFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static Date getMonthFirstDay(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(StringToDate(date));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static Date getCurrentMonthFirstDay() {
        return getMonthFirstDay(new Date());
    }

    public static List<String> getDatesOfMonthBetweenDates(Date start, Date end, DateStyle dateStyle) {
        if (start.after(end)) {
            return null;
        }
        if (dateStyle == null) dateStyle = DateStyle.YYYY_MM_DD;
        List<String> list = new ArrayList<String>();
        do {
            Calendar cal = Calendar.getInstance();
            cal.setTime(start);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.getTime();
            list.add(DateToString(cal.getTime(), dateStyle));
            start = addMonth(start, 1);
        } while (addMonth(end, 1).after(start));
        return list;
    }

    public static List<String> getDatesOfMonthBetweenDates(String start, String end, DateStyle dateStyle) {
        return getDatesOfMonthBetweenDates(StringToDate(start), StringToDate(end), dateStyle);
    }

    public static Date getCurrentYearFirstMonthFirstDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return cal.getTime();
    }

    public static String getTimeLengthBetweenSeconds(Integer seconds) {
        if (seconds <= 0) {
            return "-";
        }
        long day = seconds / (24 * 3600);
        long hour = seconds % (24 * 3600) / 3600;
        long minute = seconds % 3600 / 60;
        long second = seconds % 60 / 60;
        return (day > 0 ? (day + "天") : "") + (hour > 0 ? (hour + "小时") : "") + (minute > 0 ? (minute + "分钟") : "") + (
                second > 0 ?
                        (second + "秒") :
                        "");
    }

    public static Date getToday() {
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTime();
    }

    public static String getTodayString(DateStyle dateStyle) {
        return DateToString(getToday(), dateStyle);
    }

    public static int getNowInt() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public static int getTodayZeroTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (int) (c.getTimeInMillis() / 1000);
    }

    /**
     * 获取今天最晚时间信息; 23:59:59
     *
     * @return
     */
    public static int getTodayLastTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    public static Date getYestoday() {
        Calendar current = Calendar.getInstance();
        Calendar yestoday = Calendar.getInstance();
        yestoday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yestoday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yestoday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
        yestoday.set(Calendar.HOUR_OF_DAY, 0);
        yestoday.set(Calendar.MINUTE, 0);
        yestoday.set(Calendar.SECOND, 0);
        yestoday.set(Calendar.MILLISECOND, 0);
        return yestoday.getTime();
    }

    /**
     * @param @return 设定文件
     * @return Date    返回类型
     * @throws
     * @Title: getNextDayZeroTime
     * @Description: 获得明晨零点的时间
     */
    public static Date getNextDayZeroTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * @param @return 设定文件
     * @return Date    返回类型
     * @throws
     * @Title: getNextDayZeroTime
     * @Description: 获得指定时间明晨零点的时间
     */
    public static Date getNextDayZeroTime(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * @param @param  time
     * @param @return 设定文件
     * @return Date    返回类型
     * @throws
     * @Title: getTheDayZeroTime
     * @Description: 获取指定时间当日凌晨时间
     */
    public static Date getTheDayZeroTime(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 0);
        return cal.getTime();
    }

    /**
     * @param @param  time
     * @param @return 设定文件
     * @return Date    返回类型
     * @throws
     * @Title: getTheDayZeroTime
     * @Description: 获取指定时间当日凌晨时间
     */
    public static Date getTheDayZeroTime(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 0);
        return cal.getTime();
    }

    /**
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: getNextYearMonth
     * @Description: 获取下个月份，格式为yyyyMM
     */
    public static String getNextYearMonth() {
        Calendar current = Calendar.getInstance();
        current.set(Calendar.MONTH, current.get(Calendar.MONTH) + 1);
        DateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(current.getTime());
    }

    /**
     * 付款时间格式化
     **/
    public static final String PAY_TIME_FMT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 十位时间戳转指定格日期格式字符串
     *
     * @param seconds 10位时间戳
     * @param format  格式化字符串
     * @return 没做参数校验, 若传入参数不正确, 返回莫名数据
     */
    public static String stamp2str(int seconds, String format) {
        if (StringUtils.isBlank(format)) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date((long) seconds * 1000));
    }

    /**
     * 获得服务器当前时间
     *
     * @return 10位时间戳
     */
    public static int currentSec() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取当前时间的月份第一天
     *
     * @param date
     * @return
     */
    public static Long getMonthFirstDayStamp(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取当前时间的月份最后一天
     *
     * @param date
     * @return
     */
    public static Long getMonthLastDayStamp(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 获取下月的第一天时间戳信息
     *
     * @param date
     * @return
     */
    public static Long getNextMonthFirstDayStamp(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取下月的最后时刻时间戳信息
     *
     * @param date
     * @return
     */
    public static Long getNextMonthLastDayStamp(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 获取下月的特定天时间戳信息
     *
     * @param date
     * @return
     */
    public static Long getNextMonthSpecificDayStamp(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, day - 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 根据日期获取下月时间
     *
     * @param date
     * @return
     */
    public static Long getNextMonthDayStamp(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTimeInMillis();
    }

    /**
     * 获取昨天凌晨0:00:00日期时间戳
     *
     * @param date
     * @return
     */
    public static int getYesterdayZeroStamp(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    /**
     * 获取昨天最晚时间戳信息; 23:59:59
     *
     * @param date
     * @return
     */
    public static int getYesterdayLastStamp(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000) - 1;
    }

    /**
     * 获取昨天凌晨0:00:00日期时间
     *
     * @param date
     * @return
     */
    public static Date getYesterdayZeroDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取昨天最晚时间信息; 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getYesterdayLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static String currDayStamp() {
        return IntToDateString(currentSec(), DateStyle.YYYYMMDD);
    }

    public static void main(String args[]) {


        /*Date date = StringToDate("2016-11-16 03:00:00", DateStyle.YYYY_MM_DD_HH_MM_SS);
        long timeStamp = getNextMonthSpecificDayStamp(date, 16);*/
        /*int zero = getYesterdayZeroStamp(date);
        int lastTime = getYesterdayLastStamp(date);

        System.out.println("zero:"+zero+";lastTime:"+lastTime);

        long timestamp = getNextMonthLastDayStamp(date);
        String time  = IntToDateString(timestamp, DateStyle.YYYY_MM_DD_HH_MM_SS);
        System.out.println(time);*/
        /*String time  = IntToDateString(timeStamp, DateStyle.YYYY_MM_DD_HH_MM_SS);
        System.out.println(time);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 32);
        System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        String time1  = IntToDateString(cal.getTimeInMillis(), DateStyle.YYYY_MM_DD_HH_MM_SS);
        System.out.println(time1);*/

        Date date = StringToDate("2016-11-01 03:00:00", DateStyle.YYYY_MM_DD_HH_MM_SS);
        /*Date currDate = new Date();
        currDate = DateUtil.getYesterdayLastDate(date);
        String time1  = IntToDateString(currDate.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS);
        System.out.println(time1);*/

        date = DateUtil.getYesterdayLastDate(date);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(IntToDateString((int) (DateUtil.getMonthFirstDayStamp(ca.getTime()) / 1000),
                DateStyle.YYYY_MM_DD_HH_MM_SS));
        System.out.println(IntToDateString((int) (DateUtil.getMonthLastDayStamp(ca.getTime()) / 1000),
                DateStyle.YYYY_MM_DD_HH_MM_SS));

    }
}
