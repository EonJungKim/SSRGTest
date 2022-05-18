package com.ppsoln.ssrgdecoder;


import android.util.Log;

import com.ppsoln.ssrgdecoder.dataType.Ssr;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CustomSSRClient implements Runnable {

    private Socket socket;
    private Ssr ssr;

    private String serverIp;
    private int port;
    private String agent;
    private String authorization;
    private String mountPoint;
    private String initMSG;

    SsrgDecoder ssrgDecoder;

    public CustomSSRClient() {
        serverIp = "RTS2.ngii.go.kr";
        port = 2101;
        agent = "NTRIP node-ssrg Client/0.1";
        authorization = "Basic " + toBase64("jek88888:ngii");
        mountPoint = "/SSR-SSRG";

        initMSG = "GET " + mountPoint + " HTTP/1.1\r\nUser-Agent:" + agent + "\r\nAuthorization: " + authorization + "\n\n";

        ssrgDecoder = new SsrgDecoder();
    }

    private String toBase64(String text) {
        byte[] targetBytes = text.getBytes();

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(targetBytes);

        return new String(encodedBytes);
    }

    public Ssr getSsr() {
        return ssr;
    }

    @Override
    public void run() {
        try {
            Socket client = new Socket(serverIp, port);
            OutputStream output = client.getOutputStream();
            output.write(initMSG.getBytes());

            String tmpGGA = "$GPGGA,065550.00,3550.25766807,N,12703.96535043,E,1,11,1.0,35.369,M,20.943,M,,*58\r\n";
            String cmd = "Ntrip-GGA: " + tmpGGA;
            cmd += "\r\n";

            output.write(cmd.getBytes());

            while (true) {
                byte[] buffer = new byte[1024*1024];
                InputStream input = client.getInputStream();

                int bytes = input.read(buffer);

                ssrgDecoder.rtcmSplitter(new ByteArrayInputStream(buffer));
                ssr = ssrgDecoder.ssrgParser();

                Log.e("Input data", ssr.toString());
            }
        } catch (Exception e) {
            Log.e("CustomSSRClient", e.getMessage());
        }
    }
}
