package com.goovis.androidnfcpower.controller;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Ranjith Subramaniam on 4/4/17.
 */

public class NfcController {
    private static final String TAG = "NfcController";
    private Context mAppContext;

    public NfcController(final Context appContext) {
        mAppContext = appContext;
    }

    public final boolean checkNfcPowerStatus() {
        return checkNfcPowerStatus(mAppContext);
    }

    public static boolean checkNfcPowerStatus(Context context) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        boolean enabled = false;

        if (nfcAdapter != null) {
            enabled = nfcAdapter.isEnabled();
        }

        return enabled;
    }

    public final boolean enableNfcPower(boolean isEnable) {
        Log.d(TAG, "enableNfcPower(" + isEnable + ")");

        return powerNfc(isEnable, mAppContext);
    }

    public static boolean powerNfc(boolean isOn, Context context) {
        boolean success = false;
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);

        if (nfcAdapter != null) {
            Class<?> NfcManagerClass;
            Method setNfcEnabled;

            try {
                NfcManagerClass = Class.forName(nfcAdapter.getClass().getName());
                setNfcEnabled = NfcManagerClass.getDeclaredMethod(isOn
                        ? "enable" : "disable");
                setNfcEnabled.setAccessible(true);
                success = (Boolean) setNfcEnabled.invoke(nfcAdapter);
            } catch (ClassNotFoundException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            } catch (NoSuchMethodException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            } catch (IllegalArgumentException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            } catch (IllegalAccessException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            } catch (InvocationTargetException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
        }

        return success;
    }
}
