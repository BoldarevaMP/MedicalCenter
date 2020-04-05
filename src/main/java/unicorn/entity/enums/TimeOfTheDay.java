package unicorn.entity.enums;

public enum TimeOfTheDay {
    AM_12("00:00"),
    AM_1("01:00"),
    AM_2("02:00"),
    AM_3("03:00"),
    AM_4("04:00"),
    AM_5("05:00"),
    AM_6("06:00"),
    AM_7("07:00"),
    AM_8("08:00"),
    AM_9("09:00"),
    AM_10("10:00"),
    AM_11("11:00"),
    PM_12("12:00"),
    PM_1("13:00"),
    PM_2("14:00"),
    PM_3("15:00"),
    PM_4("16:00"),
    PM_5("17:00"),
    PM_6("18:00"),
    PM_7("19:00"),
    PM_8("20:00"),
    PM_9("21:00"),
    PM_10("22:00"),
    PM_11("23:00");

    private final String time;

    TimeOfTheDay(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return this.time;
    }
}
