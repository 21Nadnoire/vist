package com.anzuza.anzuza.Util;

import net.minidev.json.JSONObject;
import okhttp3.*;

import java.io.IOException;

public class SendRequestUtil {

    private static final String pindoToken = "eyJhbGciOiJub25lIn0.eyJpZCI6NjUxLCJyZXZva2VkX3Rva2VuX2NvdW50IjowfQ.";

    public static boolean sendPhoneNumberVerificationCode(String phoneNumber, String verificationCode) throws IOException {
        String message = "Your Visitor Management OTP is " + verificationCode;
        String messageSender = "Group One";

        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to",phoneNumber);
        jsonObject.put("text",message);
        jsonObject.put("sender", messageSender);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url("https://api.pindo.io/v1/sms/")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + pindoToken)
                .build();
        Response response = client.newCall(request).execute();
        return true;
    }
}
