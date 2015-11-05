 <h1>Programmatically Turn ON/OFF NFC on Android Device</h1>

I hope most of you know about NFC (Near Field Communication). It's a innovative version of RFID to read and write a small chunk of information by using electromagnetic field.

More information: http://developer.android.com/guide/topics/connectivity/nfc/index.html

In this post, I am going to explain how to turn ON/OFF NFC by using android program.

You may think, It's an easiest task but it's not easy. Because Google has some barriers on this power on/off operation. Obviously we know it is a default option on Android system setting menu. When we go programmatically to perform this operation we need to consider below steps.

The device should be a rooted device or system application acceptable devices.
We need to access set power ON/OFF API by Java reflection.
We need to specify the NFC permission as well as writing secure setting permission (This is acceptable only for system application).
We need to specify all below permissions in AndroidManifest.xml

uses-permission android:name="android.permission.NFC" 
android:name="android.permission.WRITE_SECURE_SETTINGS"
android:name="android.hardware.nfc" android:required="false"

Java method for turn on the device

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

Check the NFC power status by using below method.

public static boolean checkNfcPowerStatus(Context context) {
    NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
    boolean enabled = false;

    if (nfcAdapter != null) {
        enabled = nfcAdapter.isEnabled();
    }

    return enabled;
}
