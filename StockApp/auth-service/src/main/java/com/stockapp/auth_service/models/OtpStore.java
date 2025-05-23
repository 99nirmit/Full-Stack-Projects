package com.stockapp.auth_service.models;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class OtpStore {

    private final Map<String, Otp> otpMap = new ConcurrentHashMap<>();

    private final ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();

    public void saveOtp(String phoneNumber, String otp, long ttlSeconds){
        long expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000);
        otpMap.put(phoneNumber, new Otp(otp, expiryTime));

        //Schedule Removal
        schedule.schedule(() -> otpMap.remove(phoneNumber), ttlSeconds, TimeUnit.SECONDS);
    }

    public boolean validOtp(String phoneNumber, String inputOtp){
        Otp entry = otpMap.get(phoneNumber);

        if(entry == null) return false;

        if(System.currentTimeMillis() > entry.getExpiryTime()){
            otpMap.remove(phoneNumber);
            return false;
        }

        return entry.getOtp().equals(inputOtp);
    }

    public void removeOtp(String phoneNUmber){
        otpMap.remove(phoneNUmber);
    }
}
