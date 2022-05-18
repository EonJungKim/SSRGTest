package com.ppsoln.ssrgdecoder.dataType;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

/*
 *
 * 위성 궤도 보정 정보 클래스
 * 위성 궤도 오차 (Satellite Orbit Error) : 위성의 실제 궤도와 예측된 궤도 간의 방향별 오차
 *
 */
public class OrbitCorrectionData {

    public int gs;
    public int prn;             // 위성번호 (Pseudo Random Noise)
    public int iod;             // 궤도 오차 계산을 위한 방송궤도력의 식별자 정보 (Issue of data), 영역: 0~255
    public double radial;       // 방송궤도력에 대한 Radial 방향 궤도 보정, 단위: m, 영역: ±209.7151m
    public double alongTrack;   // 방송궤도력에 대한 Along-Track 방향 궤도 보정, 단위: m, 영역: ±209.7148m
    public double crossTrack;   // 방송궤도력에 대한 Cross-Track 방향 궤도 보정, 단위: m, 영역: ±209.7148m

    public OrbitCorrectionData(){
        gs = 0;
        prn = 0;
        iod = 0;
        radial = 0.0;
        alongTrack = 0.0;
        crossTrack = 0.0;
    }

    public OrbitCorrectionData(int _gs, int _prn, int _iod,
                               double _radial, double _alongTrack, double _crossTrack) {
        this.gs  = _gs;
        this.prn = _prn;
        this.iod = _iod;
        this.radial = _radial;
        this.alongTrack = _alongTrack;
        this.crossTrack = _crossTrack;
    }

    public OrbitCorrectionData(JSONObject _Stec){
        try {
            this.gs = _Stec.getInt("gs");
            this.prn = _Stec.getInt("prn");
            this.iod = _Stec.getInt("iod");
            this.radial = _Stec.getDouble("radial");
            this.alongTrack = _Stec.getDouble("alongTrack");
            this.crossTrack = _Stec.getDouble("crossTrack");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public String toString() {
        return gs + "\t" + prn + "\t" + iod + "\t" + radial + "\t" + alongTrack + "\t" + crossTrack + "\t\n";
    }
}
