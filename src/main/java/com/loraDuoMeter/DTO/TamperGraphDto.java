package com.loraDuoMeter.DTO;

public class TamperGraphDto {
    private String date;
    private long waterCount;
    private long gasCount;

    public TamperGraphDto(String date, long waterCount, long gasCount) {
        this.date = date;
        this.waterCount = waterCount;
        this.gasCount = gasCount;
    }

    // Getters and Setters
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public long getWaterCount() { return waterCount; }
    public void setWaterCount(long waterCount) { this.waterCount = waterCount; }
    public long getGasCount() { return gasCount; }
    public void setGasCount(long gasCount) { this.gasCount = gasCount; }
}