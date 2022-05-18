package com.ppsoln.ssrgdecoder.dataType;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

/*
 *
 * 위성 시계 오차 보정 계수 정보 클래스
 * 위성 시계 오차 (Satellite Clock Error) : 위성에 탑재된 원자시계의 오차로부터 발생하는 오차
 *
 */
public class ClockCorrectionData {

    public int gs;
    public int prn;             // 위성번호 (Pseudo Random Noise)
    public double deltaClockC0; // 방송 위성시계의 보정을 위한 다항식 계수, 단위: m, 영역: ±209.7151m
    public double deltaClockC1; // 방송 위성시계의 보정을 위한 다항식 계수, 단위: m/s, 영역: ±1.048575m/s
    public double deltaClockC2; // 방송 위성시계의 보정을 위한 다항식 계수, 단위: m/s², 영역: ±1.34217726m/s²

    public ClockCorrectionData() {
        gs = 0;
        prn = 0;
        deltaClockC0 = 0;
        deltaClockC1 = 0;
        deltaClockC2 = 0;
    }

    public ClockCorrectionData(int _gs, int _prn, double _deltaClockC0, double _deltaClockC1, double _deltaClockC2){
        this.gs = _gs;
        this.prn = _prn;
        this.deltaClockC0 = _deltaClockC0;
        this.deltaClockC1 = _deltaClockC1;
        this.deltaClockC2 = _deltaClockC2;
    }

    public ClockCorrectionData(JSONObject satc){
        try {
            this.gs = satc.getInt("gs");
            this.prn = satc.getInt("prn");
            this.deltaClockC0 = satc.getDouble("deltaClockC0");
            this.deltaClockC1 = satc.getDouble("deltaClockC1");
            this.deltaClockC2 = satc.getDouble("deltaClockC2");
        } catch (JSONException e) {
            Log.e("ClockCorrectionData", e.getMessage());
        }
    }

    @NonNull
    @Override
    public String toString() {
        return gs + "\t" + prn + "\t" + deltaClockC0 + "\t" + deltaClockC1 + "\t" + deltaClockC2 + "\t\n";
    }
}
