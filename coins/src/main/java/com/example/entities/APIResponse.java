package com.example.entities;

import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class APIResponse  {
    private String result;
    private String documentation;
    @SerializedName("terms_of_use")
    private String termsOfUse;
    @SerializedName("time_last_update_unix")
    private long timeLastUpdateUnix;
    @SerializedName("time_last_update_utc")
    private String timeLastUpdateUTC;
    @SerializedName("time_next_update_unix")
    private long timeNextUpdateUnix;
    @SerializedName("time_next_update_utc")
    private String timeNextUpdateUTC;
    @SerializedName("base_code")
    private String baseCode;
    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static APIResponse fromJson(String json) {
        return new Gson().fromJson(json, APIResponse.class);
    }

    public Set<String> getAvailableCurrencies() {
        return conversionRates.keySet();
    }
}
