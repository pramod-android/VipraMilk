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
    private int day1;
    @SerializedName("day2")
    @Expose
    @ColumnInfo(name = "day2")
    private int day2;
    @SerializedName("day3")
    @Expose
    @ColumnInfo(name = "day3")
    private int day3;
    @SerializedName("day4")
    @Expose
    @ColumnInfo(name = "day4")
    private int day4;
    @SerializedName("day5")
    @Expose
    @ColumnInfo(name = "day5")
    private int day5;
    @SerializedName("day6")
    @Expose
    @ColumnInfo(name = "day6")
    private int day6;
    @SerializedName("day7")
    @Expose
    @ColumnInfo(name = "day7")
    private int day7;
    @SerializedName("day8")
    @Expose
    @ColumnInfo(name = "day8")
    private int day8;
    @SerializedName("day9")
    @Expose
    @ColumnInfo(name = "day9")
    private int day9;
    @SerializedName("day10")
    @Expose
    @ColumnInfo(name = "day10")
    private int day10;
    @SerializedName("day11")
    @Expose
    @ColumnInfo(name = "day11")
    private int day11;
    @SerializedName("day12")
    @Expose
    @ColumnInfo(name = "day12")
    private int day12;
    @SerializedName("day13")
    @Expose
    @ColumnInfo(name = "day13")
    private int day13;
    @SerializedName("day14")
    @Expose
    @ColumnInfo(name = "day14")
    private int day14;
    @SerializedName("day15")
    @Expose
    @ColumnInfo(name = "day15")
    private int day15;
    @SerializedName("day16")
    @Expose
    @ColumnInfo(name = "day16")
    private int day16;
    @SerializedName("day17")
    @Expose
    @ColumnInfo(name = "day17")
    private int day17;
    @SerializedName("day18")
    @Expose
    @ColumnInfo(name = "day18")
    private int day18;
    @SerializedName("day19")
    @Expose
    @ColumnInfo(name = "day19")
    private int day19;
    @SerializedName("day20")
    @Expose
    @ColumnInfo(name = "day20")
    private int day20;
    @SerializedName("day21")
    @Expose
    @ColumnInfo(name = "day21")
    private int day21;
    @SerializedName("day22")
    @Expose
    @ColumnInfo(name = "day22")
    private int day22;
    @SerializedName("day23")
    @Expose
    @ColumnInfo(name = "day23")
    private int day23;
    @SerializedName("day24")
    @Expose
    @ColumnInfo(name = "day24")
    private int day24;
    @SerializedName("day25")
    @Expose
    @ColumnInfo(name = "day25")
    private int day25;
    @SerializedName("day26")
    @Expose
    @ColumnInfo(name = "day26")
    private int day26;
    @SerializedName("day27")
    @Expose
    @ColumnInfo(name = "day27")
    private int day27;
    @SerializedName("day28")
    @Expose
    @ColumnInfo(name = "day28")
    private int day28;
    @SerializedName("day29")
    @Expose
    @ColumnInfo(name = "day29")
    private int day29;
    @SerializedName("day30")
    @Expose
    @ColumnInfo(name = "day30")
    private int day30;
    @SerializedName("day31")
    @Expose
    @ColumnInfo(name = "day31")
    private int day31;

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

    public int getDay1() {
        return day1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
    }

    public int getDay2() {
        return day2;
    }

    public void setDay2(int day2) {
        this.day2 = day2;
    }

    public int getDay3() {
        return day3;
    }

    public void setDay3(int day3) {
        this.day3 = day3;
    }

    public int getDay4() {
        return day4;
    }

    public void setDay4(int day4) {
        this.day4 = day4;
    }

    public int getDay5() {
        return day5;
    }

    public void setDay5(int day5) {
        this.day5 = day5;
    }

    public int getDay6() {
        return day6;
    }

    public void setDay6(int day6) {
        this.day6 = day6;
    }

    public int getDay7() {
        return day7;
    }

    public void setDay7(int day7) {
        this.day7 = day7;
    }

    public int getDay8() {
        return day8;
    }

    public void setDay8(int day8) {
        this.day8 = day8;
    }

    public int getDay9() {
        return day9;
    }

    public void setDay9(int day9) {
        this.day9 = day9;
    }

    public int getDay10() {
        return day10;
    }

    public void setDay10(int day10) {
        this.day10 = day10;
    }

    public int getDay11() {
        return day11;
    }

    public void setDay11(int day11) {
        this.day11 = day11;
    }

    public int getDay12() {
        return day12;
    }

    public void setDay12(int day12) {
        this.day12 = day12;
    }

    public int getDay13() {
        return day13;
    }

    public void setDay13(int day13) {
        this.day13 = day13;
    }

    public int getDay14() {
        return day14;
    }

    public void setDay14(int day14) {
        this.day14 = day14;
    }

    public int getDay15() {
        return day15;
    }

    public void setDay15(int day15) {
        this.day15 = day15;
    }

    public int getDay16() {
        return day16;
    }

    public void setDay16(int day16) {
        this.day16 = day16;
    }

    public int getDay17() {
        return day17;
    }

    public void setDay17(int day17) {
        this.day17 = day17;
    }

    public int getDay18() {
        return day18;
    }

    public void setDay18(int day18) {
        this.day18 = day18;
    }

    public int getDay19() {
        return day19;
    }

    public void setDay19(int day19) {
        this.day19 = day19;
    }

    public int getDay20() {
        return day20;
    }

    public void setDay20(int day20) {
        this.day20 = day20;
    }

    public int getDay21() {
        return day21;
    }

    public void setDay21(int day21) {
        this.day21 = day21;
    }

    public int getDay22() {
        return day22;
    }

    public void setDay22(int day22) {
        this.day22 = day22;
    }

    public int getDay23() {
        return day23;
    }

    public void setDay23(int day23) {
        this.day23 = day23;
    }

    public int getDay24() {
        return day24;
    }

    public void setDay24(int day24) {
        this.day24 = day24;
    }

    public int getDay25() {
        return day25;
    }

    public void setDay25(int day25) {
        this.day25 = day25;
    }

    public int getDay26() {
        return day26;
    }

    public void setDay26(int day26) {
        this.day26 = day26;
    }

    public int getDay27() {
        return day27;
    }

    public void setDay27(int day27) {
        this.day27 = day27;
    }

    public int getDay28() {
        return day28;
    }

    public void setDay28(int day28) {
        this.day28 = day28;
    }

    public int getDay29() {
        return day29;
    }

    public void setDay29(int day29) {
        this.day29 = day29;
    }

    public int getDay30() {
        return day30;
    }

    public void setDay30(int day30) {
        this.day30 = day30;
    }

    public int getDay31() {
        return day31;
    }

    public void setDay31(int day31) {
        this.day31 = day31;
    }
}
