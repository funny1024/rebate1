package com.cmsy.rebate.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import com.game.world.test.config.LocalSetting;

public class DateUtil {

    /**
     * 每秒多少毫秒
     */
    public static final long MS_PER_SECOND = 1000;
    /**
     * 每分钟多少毫秒
     */
    public static final long MS_PER_MINUTE = MS_PER_SECOND * 60;
    /**
     * 每小时多少毫秒
     */
    public static final long MS_PER_HOUR = MS_PER_MINUTE * 60;
    /**
     * 每天多少毫秒
     */
    public static final long MS_PER_DAY = MS_PER_HOUR * 24;

    public static final SimpleDateFormat dateFormat_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat dateFormat_HHmmss = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat dateFormat_yyyyMMdd_HHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat dateFormat_yyyyMM = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat dateFormat_HHmm = new SimpleDateFormat("HH:mm");

    // static {
    // dateFormat_yyyyMMdd.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    // dateFormat_HHmmss.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    // dateFormat_yyyyMMdd_HHmmss.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    // dateFormat_yyyyMM.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    // dateFormat_HHmm.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    // //设置默认时区
    // TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    // }

    /** 得到当前时间的小时数 */
    public static int getHour() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /** 得到当前时间的分钟数 */
    public static int getMinute() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MINUTE);
    }

    /** 得到当前时间的年份 */
    public static int getYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /** 得到当前时间的毫秒值 */
    public static long getTimer() {
        return System.currentTimeMillis();
    }

    /** 得到当前日期字符串，格式为：2009-07-16 */
    public static String getCurrentDate() {
        synchronized (dateFormat_yyyyMMdd) {
            return dateFormat_yyyyMMdd.format(new Date());
        }
    }

    /** 得到当前日期字符串，格式为：2009-07-16 */
    public static String getDate(Date d) {
        if (d == null)
            return "";
        synchronized (dateFormat_yyyyMMdd) {
            return dateFormat_yyyyMMdd.format(d);
        }
    }

    /** 得到当前的时间，格式为：23:05:38 */
    public static String getCurrentTime() {
        synchronized (dateFormat_HHmmss) {
            return dateFormat_HHmmss.format(new Date());
        }
    }

    /** 得到时间字符串，精确到秒，格式为：23:05:38 */
    public static String getCurrentTime(Date d) {
        synchronized (dateFormat_HHmmss) {
            return dateFormat_HHmmss.format(d);
        }
    }

    /** 得到时间字符串，精确到分，格式为：23:05 */
    public static String getTimeShort(Date d) {
        synchronized (dateFormat_HHmm) {
            return dateFormat_HHmm.format(d);
        }
    }
    /** 得到当前的日期和时间，格式为：2009-07-16 00:42:53 */
    public static String getCurrentDateTime() {
        synchronized (dateFormat_yyyyMMdd_HHmmss) {
            return dateFormat_yyyyMMdd_HHmmss.format(new Date());
        }
    }

    /** 根据格式化字符串得到格式化后的当前日期和时间字符串.format类似："yyyy-MM-dd HH:mm:ss" */
    public static String getCurrentDateTime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /** 通过毫秒数得到日期对象 */
    public static Date parseDate(long ms) {
        Date date = new Date();
        date.setTime(ms);
        return date;
    }

    /** 根据日期字符串得到日期对象，日期字符串dateString格式为dateFormat.转换失败返回null */
    public static Date parseDate(String dateString, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (Exception e) {
        }
        return date;
    }

    /** 根据日期字符串得到日期对象，日期字符串dateString格式为"yyyy-MM-dd HH:mm:ss" 转换失败返回null */
    public static Date parseDate(String dateString) {
        return parseDate(dateString, "yyyy-MM-dd HH:mm:ss");
    }

    /** 根据日期字符串得到日期对象，日期字符串timeShort格式为"HH:mm". 转换失败返回null */
    public static Date parseDateHHmm(String timeShort) {
        try {
            synchronized (dateFormat_HHmm) {
                return dateFormat_HHmm.parse(timeShort);
            }
        } catch (ParseException e) {
        }
        return null;
    }



    /**
     * 得到日期和时间字符串，格式为：2009-07-16 00:42:53
     */
    public static String getDateTime(Date d) {
        if (d == null)
            return "";
        synchronized (dateFormat_yyyyMMdd_HHmmss) {
            return dateFormat_yyyyMMdd_HHmmss.format(d);
        }
    }

    /**
     * 得到格式化后的日期和时间字符串<br>
     * format类似："yyyy-MM-dd HH:mm:ss"
     */
    public static String getDateTime(Date d, String format) {
        return new SimpleDateFormat(format).format(d);
    }

    /**
     * 得到d对应的月份字符串，格式为：2009-07
     */
    public static String getMonth(Date d) {
        synchronized (dateFormat_yyyyMM) {
            return dateFormat_yyyyMM.format(d);
        }
    }

    /**
     * 得到d所在周的星期1的日期字符串，格式为：2009-07-15<br>
     * 注意：按中国的习惯进行计算，每周开始是星期1，结束是星期天
     */
    public static String getWeekBegin(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        // 注意：星期天返回1，星期1返回2...
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            // 星期天特殊处理
            cal.add(Calendar.DATE, -6);
        } else {
            // 其余的正常处理
            cal.add(Calendar.DATE, -(dayOfWeek - 2));
        }
        synchronized (dateFormat_yyyyMMdd) {
            return dateFormat_yyyyMMdd.format(cal.getTime());
        }
    }

    /**
     * 得到d所在月的1号的日期字符串，格式为：2009-07-15
     */
    public static String getMonthBegin(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        synchronized (dateFormat_yyyyMMdd) {
            return dateFormat_yyyyMMdd.format(cal.getTime());
        }
    }

    /**
     * 得到时间d增加day天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAddDay(Date d, int day) {
        if (d == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 得到date(格式:必须是2009-07-15)所在周的星期1的日期字符串，格式为：2009-07-15<br>
     * 注意：按中国的习惯进行计算，每周开始是星期1，结束是星期天
     */
    public static String getWeekBegin(String date) {
        try {
            synchronized (dateFormat_yyyyMMdd) {
                return getWeekBegin(dateFormat_yyyyMMdd.parse(date));
            }
        } catch (ParseException e) {
        }
        return "";
    }

    /**
     * 得到d所在周的星期天的日期字符串，格式为：2009-07-15<br>
     * 注意：按中国的习惯进行计算，每周开始是星期1，结束是星期天
     */
    public static String getWeekEnd(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        // 注意：星期天返回1，星期1返回2...
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            // 星期天不需要处理
        } else {
            // 其余的正常处理
            cal.add(Calendar.DATE, (8 - dayOfWeek));
        }
        synchronized (dateFormat_yyyyMMdd) {
            return dateFormat_yyyyMMdd.format(cal.getTime());
        }
    }

    /**
     * 返回d是星期几，星期1返回1，星期天返回7
     */
    public static int getWeekDay(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        // 注意：星期天返回1，星期1返回2，星期6返回7，因此要特殊处理星期天
        int thisWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (thisWeekDay == 0)
            thisWeekDay = 7;
        return thisWeekDay;
    }

    /** 得到当前日期属于一年中的第几周 */
    public static int getWeekOfYear() {
        return getWeekOfYear(new Date());
    }

    /** 得到当前日期属于一年中的第几周 */
    public static int getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 返回当前时间是不是全部符合指定的多个限制条件，多个条件之间是and的关系
     *
     * @param timeLimitInDay
     *            每天内的限制时间，精确到分钟，为空表示不限制，如：["12:00-13:00",
     *            "20:00-21:00"]，满足任意1个即可
     * @param dayLimitInWeek
     *            每周内的限制天数，范围[1,7]，6表示星期6，为空表示不限制，如：[6,7]，满足任意1个即可
     * @param dateLimit
     *            限制为指定的日期，为空表示不限制，如：["2009-10-01","2009-10-02","2009-10-03"]，
     *            满足任意1个即可
     * @param betweenOpenTime
     *            开服时间到开服时间后的天数
     * @return 如果有任意一个条件不满足就返回false
     */
    public static boolean isRightTimeNow(ArrayList<String> timeLimitInDay, ArrayList<Integer> dayLimitInWeek,
                                         ArrayList<String> dateLimit, int betweenOpenTime, Date openTime) {
        Date d = new Date();
        // 判断时间
        if (timeLimitInDay != null && timeLimitInDay.size() > 0) {
            // 当前时间
            String thisTime = getTimeShort(d);
            boolean isEmptyList = true;
            boolean isRightTime = false;
            for (String timeRange : timeLimitInDay) {
                if (timeRange == null || timeRange.trim().length() == 0)
                    continue;
                isEmptyList = false;
                String[] timeArray = timeRange.split("-");
                if (timeArray.length < 2)
                    continue;
                // 在开始时间和结束时间之间
                if (thisTime.compareTo(timeArray[0]) >= 0 && thisTime.compareTo(timeArray[1]) <= 0) {
                    isRightTime = true;
                    break;
                }
            }
            if (!isEmptyList && !isRightTime)
                return false;
        }
        // 判断星期几
        if (dayLimitInWeek != null && dayLimitInWeek.size() > 0) {
            // 当前星期
            int thisWeekDay = getWeekDay(d);
            boolean isRightWeekDay = dayLimitInWeek.contains(thisWeekDay);
            if (!isRightWeekDay)
                return false;
        }
        // 判断日期
        if (dateLimit != null && dateLimit.size() > 0) {
            // 当前日期
            String thisDate = getDate(d);
            boolean isEmptyList = true;
            boolean isRightDate = false;
            for (String dateRange : dateLimit) {
                if (dateRange == null || dateRange.trim().length() == 0)
                    continue;
                isEmptyList = false;
                // 判断是单个日期还是日期段
                if (dateRange.indexOf("~") > -1) {
                    // 日期段
                    String[] dateArray = dateRange.split("~");
                    if (dateArray.length < 2)
                        continue;
                    // 在开始时间和结束时间之间
                    if (thisDate.compareTo(dateArray[0]) >= 0 && thisDate.compareTo(dateArray[1]) <= 0) {
                        isRightDate = true;
                        break;
                    }
                } else {
                    // 单个日期
                    if (thisDate.equals(dateRange)) {
                        isRightDate = true;
                        break;
                    }
                }
            }
            if (!isEmptyList && !isRightDate)
                return false;
        }
        // 开服时间的几天内
        if (betweenOpenTime > 0 && openTime != null) {
            String thisDate = getDate(d);
            String beforeDay = DateUtil.getDate(openTime);
            String endDay = getSpecifiedDayAfter(beforeDay, betweenOpenTime - 1);
            if (!(thisDate.compareTo(beforeDay) >= 0 && thisDate.compareTo(endDay) <= 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回当前时间是不是全部符合指定的多个限制条件，多个条件之间是and的关系
     *
     * @param timeLimitInDay
     *            每天内的限制时间，精确到分钟，为空表示不限制，如：["12:00-13:00",
     *            "20:00-21:00"]，满足任意1个即可
     * @param dayLimitInWeek
     *            每周内的限制天数，范围[1,7]，6表示星期6，为空表示不限制，如：[6,7]，满足任意1个即可
     * @param dateLimit
     *            限制为指定的日期，为空表示不限制，如：["2009-10-01","2009-10-02","2009-10-03"]，
     *            满足任意1个即可
     * @param betweenOpenTime
     *            开服时间到开服时间后的天数
     * @param openTime
     *            开服后的某一天
     * @return 每天时间段的开启时间间隔,如果其他时间不成立,那么返回0
     */
    public static long getLeftTimeInDay(ArrayList<String> timeLimitInDay, ArrayList<Integer> dayLimitInWeek,
                                        ArrayList<String> dateLimit, int betweenOpenTime, Date openTime) {
        Date d = new Date();

        // 判断星期几
        if (dayLimitInWeek != null && dayLimitInWeek.size() > 0) {
            // 当前星期
            int thisWeekDay = getWeekDay(d);
            boolean isRightWeekDay = dayLimitInWeek.contains(thisWeekDay);
            if (!isRightWeekDay) {
                return 0;
            }
        }
        // 判断日期
        if (dateLimit != null && dateLimit.size() > 0) {
            // 当前日期
            String thisDate = getDate(d);
            boolean isEmptyList = true;
            boolean isRightDate = false;
            for (String dateRange : dateLimit) {
                if (dateRange == null || dateRange.trim().length() == 0)
                    continue;
                isEmptyList = false;
                // 判断是单个日期还是日期段
                if (dateRange.indexOf("~") > -1) {
                    // 日期段
                    String[] dateArray = dateRange.split("~");
                    if (dateArray.length < 2)
                        continue;
                    // 在开始时间和结束时间之间
                    if (thisDate.compareTo(dateArray[0]) >= 0 && thisDate.compareTo(dateArray[1]) <= 0) {
                        isRightDate = true;
                        break;
                    }
                } else {
                    // 单个日期
                    if (thisDate.equals(dateRange)) {
                        isRightDate = true;
                        break;
                    }
                }
            }
            if (!isEmptyList && !isRightDate)
                return 0;
        }
        // 开服时间的几天内
        if (betweenOpenTime > 0 && openTime != null) {
            String thisDate = getDate(d);
            String beforeDay = DateUtil.getDate(openTime);
            String endDay = getSpecifiedDayAfter(beforeDay, betweenOpenTime - 1);
            if (!(thisDate.compareTo(beforeDay) >= 0 && thisDate.compareTo(endDay) <= 0)) {
                return 0;
            }
        }
        // 判断时间
        if (timeLimitInDay != null && timeLimitInDay.size() > 0) {
            // 当前时间
            String thisTime = getTimeShort(d);
            for (String timeRange : timeLimitInDay) {
                if (timeRange == null || timeRange.trim().length() == 0)
                    continue;
                String[] timeArray = timeRange.split("-");
                if (timeArray.length < 2)
                    continue;
                if (thisTime.compareTo(timeArray[0]) >= 0)
                    continue;
                // 在开始时间和结束时间之间
                if (!(thisTime.compareTo(timeArray[0]) >= 0 && thisTime.compareTo(timeArray[1]) <= 0)) {
                    Date lastTime = parseDate(getCurrentDate() + " " + timeArray[0] + ":00");
                    if (lastTime != null)
                        return lastTime.getTime() - d.getTime();
                    break;
                }
            }
        }
        return 0;
    }

    /**
     * 判断多个日期是不是有效日期
     *
     * @param timeLimitInDayTime
     *            格式:2012-09-24 16:39:05~2012-9-24 16:40:02
     * @return
     */
    public static boolean isRightTimeNow(ArrayList<String> timeLimitInDayTime) {
        if (timeLimitInDayTime == null || timeLimitInDayTime.size() == 0)
            return false;
        for (String dateTime : timeLimitInDayTime) {
            if (isRightTimeNow(dateTime))
                return true;
        }
        return false;
    }

    /**
     * 判断单个日期是不是有效日期
     *
     * @param timeLimitInDayTime
     *            格式:2012-09-24 16:39:05~2012-9-24 16:40:02
     * @return
     */
    public static boolean isRightTimeNow(String timeLimitInDayTime) {
        if (timeLimitInDayTime == null || timeLimitInDayTime.length() == 0)
            return false;
        String thisTime = getCurrentDateTime();
        // 日期段
        String[] dateArray = timeLimitInDayTime.split("~");
        if (dateArray.length < 2)
            return false;
        if (thisTime.compareTo(dateArray[0]) > 0 && thisTime.compareTo(dateArray[1]) < 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较两个日期是不是同一天,不比较时分秒,如果一个日期为null,那么返回false
     *
     * @param date1
     * @param date2
     * @return
     */
    @SuppressWarnings("deprecation")
    public static boolean isSameDate(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return false;
        return (date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth()
                && date1.getDate() == date2.getDate());
    }

    /**
     * 比较两个日期是不是同一天,不比较时分秒,如果一个日期为null,那么返回false
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(long date1, long date2) {
        if (date1 == date2)
            return true;
        Date d1 = new Date(date1);
        Date d2 = new Date(date2);
        return DateUtil.isSameDate(d1, d2);
    }

    /**
     * 得到两个时间之间相隔的天数
     * 标准时间从1970-1-1 08:00:00开始
     * 规律:
     * 8-24 24-48 48-72 72-96
     * 实际值却是:
     * 0-16 16-40 40-64 64-86
     * 所有,相当于减少了8个小时才开始计算的,这样规律就出来了
     * @param date1
     * @param date2
     * @return
     */
    public static long getBetweenDays(long date1, long date2) {
        long t1Begin = (date1 + DateUtil.MS_PER_HOUR * 8);
        long t2Begin = (date2 + DateUtil.MS_PER_HOUR * 8);
        long day1 = t1Begin / DateUtil.MS_PER_DAY;
        long day2 = t2Begin / DateUtil.MS_PER_DAY;
        return day2 - day1;
    }

    /**
     * 得到时间date1当天开始的时间毫秒数,返回的时间为:2016-06-08 00:00:00
     * @param date1
     * @return
     */
    public static long getDayBegin(long date1) {
        long t1Begin = (date1 + DateUtil.MS_PER_HOUR * 8);
        long totalDay = t1Begin / DateUtil.MS_PER_DAY;
        return totalDay * DateUtil.MS_PER_DAY - DateUtil.MS_PER_HOUR * 8;
    }

    /**
     * 得到时间date1当天开始的时间毫秒数+小时数,返回的时间为:2016-06-08 00:00:00
     * @param date1
     * @return
     */
    public static long getDayBeginHour(long date1, int hour) {
        return getDayBegin(date1) + DateUtil.MS_PER_HOUR * hour;
    }

    /**
     * 得到时间date1当天结束的时间毫秒数
     * @param date1
     * @return
     */
    public static long getDayEnd(long date1) {
        long t1Begin = (date1 + DateUtil.MS_PER_HOUR * 8);
        long totalDay = t1Begin / DateUtil.MS_PER_DAY;
        return totalDay * DateUtil.MS_PER_DAY + DateUtil.MS_PER_HOUR * 16 - 1;	//-1表示23:59:59
    }

    /**
     * 得到时间date1的小时数
     * @param date1
     * @return
     */
    public static int getTimeHour(long date1) {
        long t1Begin = (date1 + DateUtil.MS_PER_HOUR * 8);
        long totalHours = t1Begin / DateUtil.MS_PER_HOUR;
        return (int)(totalHours % 24);
    }

    /**
     * 检查时间checkTime1是否超过了checkTime的第day天的hours小时
     * @param checkTime
     * @param hour
     * @return
     */
    public static boolean isAfterDayHour(long checkTime, long checkTime1, int days, int hour) {
        //当前时间毫秒数
        long currentTime = System.currentTimeMillis();
        //间隔天数
        long betweenDays = DateUtil.getBetweenDays(checkTime, currentTime);
        //是否今天过去了
        if (betweenDays < days) {
            return false;
        } else {
            if (betweenDays == days && DateUtil.getTimeHour(currentTime) < hour) {
                return false;
            }
            //只要大于了,就表示肯定过了
        }
        return true;
    }

    /**
     * 根据格式化后的日期得到该日期的前beforeDay天数的日期字符串
     *
     * @param specifiedDay
     *            格式化后的时间字符串,eg: 2011-05-01
     * @param beforeDay
     * @return
     */
    public static String getSpecifiedDayBefore(String specifiedDay, int beforeDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            synchronized (dateFormat_yyyyMMdd) {
                date = dateFormat_yyyyMMdd.parse(specifiedDay);
            }
        } catch (ParseException e) {
        }
        if (date == null)
            return "";
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - beforeDay);
        String dayBefore = "";
        synchronized (dateFormat_yyyyMMdd) {
            dayBefore = dateFormat_yyyyMMdd.format(c.getTime());
        }
        return dayBefore;
    }

    /**
     * 根据格式化后的日期得到该日期的+afterDay天数的日期字符串
     *
     * @param specifiedDay
     *            格式化后的时间字符串,eg: 2011-05-01
     * @param afterDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay, int afterDay) {
        if (specifiedDay == null || specifiedDay.length() == 0)
            return "";
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            synchronized (dateFormat_yyyyMMdd) {
                date = dateFormat_yyyyMMdd.parse(specifiedDay);
            }
        } catch (ParseException e) {
        }
        if (date == null)
            return "";
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + afterDay);
        String dayAfter = "";
        synchronized (dateFormat_yyyyMMdd) {
            dayAfter = dateFormat_yyyyMMdd.format(c.getTime());
        }
        return dayAfter;
    }

    /**
     * 得到两个时间字符串之间的秒数
     */
    public static int getTimeValue(String timeStart, String timeEnd) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(parseDate(timeStart));
            c2.setTime(parseDate(timeEnd));
        } catch (Exception e) {
        }
        long nowMills = c1.getTimeInMillis();
        long setMills = c2.getTimeInMillis();
        return (int) ((setMills - nowMills) / 1000);
    }

    /**
     * 得到两日期相差天数
     */
    public static int compareDay(String byTime, String toTime) {
        Date openTime = (DateUtil.parseDate(byTime, "yyyy-MM-dd HH:mm:ss"));
        Date currTime = (DateUtil.parseDate(toTime, "yyyy-MM-dd HH:mm:ss"));
        if (openTime == null || currTime == null)
            return 0;
        long temp = currTime.getTime() - openTime.getTime();
        return (int) (temp / (1000 * 60 * 60 * 24));
    }

    /**
     * 得到当前时间到toTime之间的毫秒间隔,已经过去了返回下一天的这个时间的间隔,时间格式不正确,返回0
     *
     * @param toTime
     *            格式:21:00
     * @return 间隔时间,单位:毫秒
     */
    public static long getMSToTime(String toTime) {
        if (StringUtil.isEmpty(toTime))
            return 0;
        toTime = getCurrentDate() + " " + toTime + ":00";
        Date date = parseDate(toTime);
        if (date == null)
            return 0;
        // 如果当前时间大于toTime,那么取下一天的时间
        long result = date.getTime() - getTimer();
        if (result < 0)
            result += MS_PER_DAY;
        return date.getTime() - getTimer();
    }

    /** 得到两个日期之间的间隔天数 */
    public static int getBetweenDays(String date1, String date2) {
        if (StringUtil.isEmpty(date1) || StringUtil.isEmpty(date2))
            return 0;
        Date d1 = parseDate(date1, "yyyy-MM-dd");
        Date d2 = parseDate(date2, "yyyy-MM-dd");
        return getBetweenDays(d1, d2);
    }

    /** 得到两个日期之间的间隔天数 */
    public static int getBetweenDays(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return 0;
        return (int) ((date2.getTime() - date1.getTime()) / MS_PER_DAY);
    }


    public static void main(String[] args) {
//		String d1 = "1999-09-22";
//		String d2 = "2000-09-22";
//		int days = getBetweenDays(d1, d2);
//		System.out.println(days);
//		System.out.println(getTimeValue("2016-05-31 15:00:00", getCurrentDateTime()));
//		System.out.println(getTimeHour(System.currentTimeMillis()));

//		System.out.println("2016-07-06 12:00:00".compareTo(getCurrentDateTime()));
//		Date date = new Date();
//		date.setTime(1469188800000l);
//		date.setTime(1467806400000l);
//		System.out.println(date);
        //System.out.println(DateUtil.getMinute());

        System.out.println(getSpecifiedDayBefore(getCurrentDate(), 1));
    }

}
