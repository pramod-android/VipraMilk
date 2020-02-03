package com.mywork.vipramilk.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "holiday_data_table")
public class HolidayData {

    @PrimaryKey()
    @NonNull
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    private int Id;

    @SerializedName("customer_id")
    @Expose
    @ColumnInfo(name = "customer_id")
    private int customerId;
    @SerializedName("month")
    @Expose
    @ColumnInfo(name = "month")
    private int month;
    @SerializedName("year")
    @Expose
    @ColumnInfo(name = "year")
    private int year;
    @SerializedName("day1")
    @Expose
    @ColumnInfo(name = "day1")
    private String day1;
    @SerializedName("day2")
    @Expose
    @ColumnInfo(name = "day2")
    private String day2;
    @SerializedName("day3")
    @Expose
    @ColumnInfo(name = "day3")
    private String day3;
    @SerializedName("day4")
    @Expose
    @ColumnInfo(name = "day4")
    private String day4;
    @SerializedName("day5")
    @Expose
    @ColumnInfo(name = "day5")
    private String day5;
    @SerializedName("day6")
    @Expose
    @ColumnInfo(name = "day6")
    private String day6;
    @SerializedName("day7")
    @Expose
    @ColumnInfo(name = "day7")
    private String day7;
    @SerializedName("day8")
    @Expose
    @ColumnInfo(name = "day8")
    private String day8;
    @SerializedName("day9")
    @Expose
    @ColumnInfo(name = "day9")
    private String day9;
    @SerializedName("day10")
    @Expose
    @ColumnInfo(name = "day10")
    private String day10;
    @SerializedName("day11")
    @Expose
    @ColumnInfo(name = "day11")
    private String day11;
    @SerializedName("day12")
    @Expose
    @ColumnInfo(name = "day12")
    private String day12;
    @SerializedName("day13")
    @Expose
    @ColumnInfo(name = "day13")
    private String day13;
    @SerializedName("day14")
    @Expose
    @ColumnInfo(name = "day14")
    private String day14;
    @SerializedName("day15")
    @Expose
    @ColumnInfo(name = "day15")
    private String day15;
    @SerializedName("day16")
    @Expose
    @ColumnInfo(name = "day16")
    private String day16;
    @SerializedName("day17")
    @Expose
    @ColumnInfo(name = "day17")
    private String day17;
    @SerializedName("day18")
    @Expose
    @ColumnInfo(name = "day18")
    private String day18;
    @SerializedName("day19")
    @Expose
    @ColumnInfo(name = "day19")
    private String day19;
    @SerializedName("day20")
    @Expose
    @ColumnInfo(name = "day20")
    private String day20;
    @SerializedName("day21")
    @Expose
    @ColumnInfo(name = "day21")
    private String day21;
    @SerializedName("day22")
    @Expose
    @ColumnInfo(name = "day22")
    private String day22;
    @SerializedName("day23")
    @Expose
    @ColumnInfo(name = "day23")
    private String day23;
    @SerializedName("day24")
    @Expose
    @ColumnInfo(name = "day24")
    private String day24;
    @SerializedName("day25")
    @Expose
    @ColumnInfo(name = "day25")
    private String day25;
    @SerializedName("day26")
    @Expose
    @ColumnInfo(name = "day26")
    private String day26;
    @SerializedName("day27")
    @Expose
    @ColumnInfo(name = "day27")
    private String day27;
    @SerializedName("day28")
    @Expose
    @ColumnInfo(name = "day28")
    private String day28;
    @SerializedName("day29")
    @Expose
    @ColumnInfo(name = "day29")
    private String day29;
    @SerializedName("day30")
    @Expose
    @ColumnInfo(name = "day30")
    private String day30;
    @SerializedName("day31")
    @Expose
    @ColumnInfo(name = "day31")
    private String day31;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    public String getDay5() {
        return day5;
    }

    public void setDay5(String day5) {
        this.day5 = day5;
    }

    public String getDay6() {
        return day6;
    }

    public void setDay6(String day6) {
        this.day6 = day6;
    }

    public String getDay7() {
        return day7;
    }

    public void setDay7(String day7) {
        this.day7 = day7;
    }

    public String getDay8() {
        return day8;
    }

    public void setDay8(String day8) {
        this.day8 = day8;
    }

    public String getDay9() {
        return day9;
    }

    public void setDay9(String day9) {
        this.day9 = day9;
    }

    public String getDay10() {
        return day10;
    }

    public void setDay10(String day10) {
        this.day10 = day10;
    }

    public String getDay11() {
        return day11;
    }

    public void setDay11(String day11) {
        this.day11 = day11;
    }

    public String getDay12() {
        return day12;
    }

    public void setDay12(String day12) {
        this.day12 = day12;
    }

    public String getDay13() {
        return day13;
    }

    public void setDay13(String day13) {
        this.day13 = day13;
    }

    public String getDay14() {
        return day14;
    }

    public void setDay14(String day14) {
        this.day14 = day14;
    }

    public String getDay15() {
        return day15;
    }

    public void setDay15(String day15) {
        this.day15 = day15;
    }

    public String getDay16() {
        return day16;
    }

    public void setDay16(String day16) {
        this.day16 = day16;
    }

    public String getDay17() {
        return day17;
    }

    public void setDay17(String day17) {
        this.day17 = day17;
    }

    public String getDay18() {
        return day18;
    }

    public void setDay18(String day18) {
        this.day18 = day18;
    }

    public String getDay19() {
        return day19;
    }

    public void setDay19(String day19) {
        this.day19 = day19;
    }

    public String getDay20() {
        return day20;
    }

    public void setDay20(String day20) {
        this.day20 = day20;
    }

    public String getDay21() {
        return day21;
    }

    public void setDay21(String day21) {
        this.day21 = day21;
    }

    public String getDay22() {
        return day22;
    }

    public void setDay22(String day22) {
        this.day22 = day22;
    }

    public String getDay23() {
        return day23;
    }

    public void setDay23(String day23) {
        this.day23 = day23;
    }

    public String getDay24() {
        return day24;
    }

    public void setDay24(String day24) {
        this.day24 = day24;
    }

    public String getDay25() {
        return day25;
    }

    public void setDay25(String day25) {
        this.day25 = day25;
    }

    public String getDay26() {
        return day26;
    }

    public void setDay26(String day26) {
        this.day26 = day26;
    }

    public String getDay27() {
        return day27;
    }

    public void setDay27(String day27) {
        this.day27 = day27;
    }

    public String getDay28() {
        return day28;
    }

    public void setDay28(String day28) {
        this.day28 = day28;
    }

    public String getDay29() {
        return day29;
    }

    public void setDay29(String day29) {
        this.day29 = day29;
    }

    public String getDay30() {
        return day30;
    }

    public void setDay30(String day30) {
        this.day30 = day30;
    }

    public String getDay31() {
        return day31;
    }

    public void setDay31(String day31) {
        this.day31 = day31;
    }
}
