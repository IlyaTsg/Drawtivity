package com.ETU.api.utils;

import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.util.Base64;

@Component
public class ImageUtils {
    public Blob convertBase64ToBlob(String base64) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64);
            return new SerialBlob(decodedBytes);
        }
        catch (Exception e){
            return null;
        }
    }

    public String convertBlobToBase64(Blob blob) {
        byte[] bytes;
        try {
            bytes = blob.getBytes(1, (int) blob.length());
            return Base64.getEncoder().encodeToString(bytes);
        }
        catch (Exception e){
            return null;
        }
    }
}
