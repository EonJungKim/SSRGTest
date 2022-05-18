package com.ppsoln.ssrgdecoder.dataType;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

/*
 *
 * 위성 바이어스 정보 클래스
 * 위성 바이어스 오차 (Satellite Bias Error) : 위성의 전송회로 하드웨어에서 발생하는 오차
 *
 */
public class SignalBiasData {

    public int gs;
    public int prn;             // 위성번호 (Pseudo Random Noise)
    public int signalIndex;     // GNSS 위성별 항법신호 구분 인덱스
    public double codeBias;     // 위성의 코드 신호에 대한 바이어스 보정, 단위: meter
    public double phaseBias;    // 위성의 위상 신호에 대한 바이어스 보정, 단위: meter

    public SignalBiasData() {
        this.gs = 0;
        this.prn = 0;
        this.signalIndex = 0;
        this.codeBias = 0;
        this.phaseBias = 0;
    }

    public SignalBiasData(int _gs, int _prn, int _signalIndex, double _codeBias, double _phaseBias) {
        this.gs = _gs;
        this.prn = _prn;
        this.signalIndex = _signalIndex;
        this.codeBias = _codeBias;
        this.phaseBias = _phaseBias;
    }

    public SignalBiasData(JSONObject satb) {
        try {
            this.gs = satb.getInt("gs");
            this.prn = satb.getInt("prn");
            this.signalIndex = satb.getInt("signalIndex");
            this.codeBias = satb.getDouble("codeBias");
            this.phaseBias = satb.getDouble("phaseBias");
        } catch(JSONException e) {
            //
        }
    }

    @NonNull
    @Override
    public String toString() {
        return gs + "\t" + prn + "\t" + signalIndex + "\t" + codeBias + "\t" + phaseBias + "\t\n";
    }
}
