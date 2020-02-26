package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

public class Regions {
    @SerializedName("region_id") //we have add this if variable name in java is different from variable name in JSON
    private String regionId;
    private String region_name;
    public Regions(String regionId, String region_name) {
        this.regionId = regionId;
        this.region_name = region_name;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    @Override
    public String toString() {
        return "Regions{" +
                "regionId='" + regionId + '\'' +
                ", region_name='" + region_name + '\'' +
                '}';
    }
}
