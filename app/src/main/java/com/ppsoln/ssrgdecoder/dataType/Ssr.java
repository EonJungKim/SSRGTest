package com.ppsoln.ssrgdecoder.dataType;

import androidx.annotation.NonNull;

public class Ssr {

    public OrbitCorrectionData[] orbitCorrectionData;       // 위성 궤도 오차 보정정보
    public ClockCorrectionData[] clockCorrectionData;       // 위성 시계 오차 보정정보
    public SignalBiasData[] signalBiasData;                 // 위성 바이어스 오차 보정정보
    public TroposphereDelayData[] troposphereDelayData;     // 전리층 오차 지연 정보
    public IonosphereDelayData[] ionosphereDelayData;       // 대류권 오차 지연 정보

    @NonNull
    @Override
    public String toString() {
        String result = "";
        result += "\norbitCorrectionData : " + orbitCorrectionData.length;
        result += "\nclockCorrectionData : " + clockCorrectionData.length;
        result += "\nsignalBiasData : " + signalBiasData.length;
        result += "\ntroposphereDelayData : " + troposphereDelayData.length;
        result += "\nionosphereDelayData : " + ionosphereDelayData.length;
        return result;
    }
}
