package com.ppsoln.ssrgdecoder.dataType;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

/*
 *
 * 대류권 지연 오차 보정 정보 클래스
 * 대류권 오차 (Troposphere Error) : 항법신호가 대류권을 통과하면서 대기 중의 건조가스와 수증기로 인하여 발생하는 지연 오차
 *
 */
public class TroposphereDelayData {

    public int gs;
    public int latitude;    // 격자점 위치의 위도, 영역: ±90º
    public int longitude;   // 격자점 위치의 경도, 영역: ±180º
    public int height;      // 격자점 위치의 높이, 단위: m, 영역: ±8388.607m
    public double ztd;      // 대류권 지연량, 천정방향 총 지연량 (Zenith Total Delay), 단위: m, 영역: ±3.2767m
    public double zwd;      // 대류권 지연량, 천정방향 습윤 지연량 (Zenith Web Delay), 단위: m, 영역: ±0.8191m

    public TroposphereDelayData() {
        this.gs = 0;
        this.latitude = 0;
        this.longitude = 0;
        this.height = 0;
        this.ztd = 0;
        this.zwd =0;
    }

    public TroposphereDelayData(int _gs, int _latitude, int _longitude,
                                int _height, double _ztd, double _zwd) {
        this.gs = _gs;
        this.latitude = _latitude;
        this.longitude = _longitude;
        this.height = _height;
        this.ztd = _ztd;
        this.zwd = _zwd;
    }

    public TroposphereDelayData(JSONObject trop) {
        try {
            this.gs = trop.getInt("gs");
            this.latitude = trop.getInt("latitude");
            this.longitude = trop.getInt("longitude");
            this.height = trop.getInt("height");
            this.ztd = trop.getDouble("ztd");
            this.zwd = trop.getDouble("zwd");
        } catch (JSONException e) {
            Log.e("TroposphereDelayData", "error : " + e.getMessage());
        }
    }

    @NonNull
    @Override
    public String toString() {
        return gs + "\t" + latitude + "\t" + longitude + "\t" + height + "\t" + ztd + "\t" + zwd + "\t\n";
    }
}
