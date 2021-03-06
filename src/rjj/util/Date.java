package rjj.util;

import java.util.Calendar;
import java.util.Random;

public class Date {
    private int day, month, year;

    public Date(final int day, final int month, final int year) {
        if (!isValid(day, month, year))
            throw new IllegalArgumentException("Invalid date.");
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValid(final int day, final int month, final int year) {
        return year >= 0 && !(month < 1 || month > 12) && !(day < 1 || day > daysInMonth(month, year));
    }

    private static int daysInMonth(final int month, final int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1;
        }
    }

    private static boolean isLeapYear(final int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static Date today() {
        Calendar cal = Calendar.getInstance();
        return new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
    }

    public static Date fromString(final String date) {
        if (date == null)
            throw new NullPointerException("Date String is null.");
        try {
            String[] parts = date.split("\\D");
            int day = Integer.parseInt(parts[0]), month = Integer.parseInt(parts[1]), year = Integer.parseInt(parts[2]);
            return new Date(day, month, year);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can't build a new Date from the given String.");
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d", day, month, year);
    }

    public enum DayOfWeek {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

        public static final int size = values().length;
        private static final Random rand = new Random();

        public static DayOfWeek rand() {
            return values()[rand.nextInt(size)];
        }
    }
}
