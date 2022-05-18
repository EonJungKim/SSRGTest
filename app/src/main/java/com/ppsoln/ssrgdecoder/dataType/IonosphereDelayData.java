package com.ppsoln.ssrgdecoder.dataType;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

/*
 *
 * 전리층 지연 오차 보정 정보 클래스
 * 전리층 오차 (Ionosphere Error) : 항법신호가 전리층 내에 분포한 자유 전자로 인해 받는 영향(굴절, 반사, 흡수 등)에 따른 오차
 *
*/
public class IonosphereDelayData {

    public int gs;
    public int latitude;    // 격자점 위치의 위도
    public int longitude;   // 격자점 위치의 경도
    public int height;      // 격자점 위치의 높이
    public int prn;         // 위성번호 (Pseudo Random Noise)
    public double stec;     // 전리층 지연량 오차 보정 값 (Slant Tec), 단위: TECU, 영역: ±5368.70911 TECU


    public IonosphereDelayData() {
        gs = 0;
        latitude = 0;
        longitude = 0;
        height = 0;
        prn = 0;
        stec = 0;
    }

    public IonosphereDelayData(int _gs, int _latitude, int _longitude,
                               int _height, int _prn, double _stec) {
        this.gs = _gs;
        this.latitude = _latitude;
        this.longitude = _longitude;
        this.height = _height;
        this.prn = _prn;
        this.stec = _stec;
    }

    public IonosphereDelayData(JSONObject stec) {
        try {
            this.gs = stec.getInt("gs");
            this.latitude = stec.getInt("latitude");
            this.longitude = stec.getInt("longitude");
            this.height = stec.getInt("height");
            this.prn = stec.getInt("prn");
            this.stec = stec.getDouble("stec");
        } catch (JSONException e) {
            Log.e("IonosphereDelayData", "error : " + e.getMessage());
        }
    }

    @NonNull
    @Override
    public String toString() {
        return gs + "\t" + latitude + "\t" + longitude + "\t" + height + "\t" + prn + "\t" + stec + "\t\n";
    }
}
