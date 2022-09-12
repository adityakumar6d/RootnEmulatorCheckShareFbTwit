package com.aditya.shareapp;

import android.os.Build;
import android.util.Log;

/**
 * Utility methods related to physical devies and emulators.
 */
public class DeviceUtil {

    public static boolean isEmulator() {

        Log.e("TAG", "isEmulator: " + Build.FINGERPRINT.toString() +
                "\n" + Build.MODEL + "\n" + Build.MANUFACTURER +
                "\n" + Build.BRAND + "\n" + Build.DEVICE + "\n" + Build.BOARD);

        return (Build.FINGERPRINT.startsWith("google/sdk_gphone_")
                && Build.FINGERPRINT.endsWith(":user/release-keys")
                && Build.MANUFACTURER == "Google" && Build.PRODUCT.startsWith("sdk_gphone_") && Build.BRAND == "google"
                && Build.MODEL.startsWith("sdk_gphone_"))
                //
                || Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                //bluestacks
                || "QC_Reference_Phone" == Build.BOARD && !"Xiaomi".equalsIgnoreCase(Build.MANUFACTURER) //bluestacks
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.HOST.startsWith("Build") //MSI App Player
                || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                || Build.PRODUCT == "google_sdk"
                // another Android SDK emulator check
                || SystemProperties.getProp("ro.kernel.qemu") == "1";
    }
}