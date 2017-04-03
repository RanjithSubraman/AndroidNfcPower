 <h1>Programmatically Turn ON/OFF NFC on Android Device</h1>
I hope most of you know about NFC (Near Field Communication). It's an innovative version of RFID to read and write a small chunk of information by using the electromagnetic field.

More information: http://developer.android.com/guide/topics/connectivity/nfc/index.html

In this post, I am going to explain how to turn ON/OFF NFC by using the android APIs.

Google has some restrictions on Nfc power on/off for developers. Obviously, we know it is a user feature to turn on/off by android system setting menu. 

Follow the below steps:
The device should be a rooted device or system application acceptable device.
The developer needs to be accessed to handle power ON/OFF API by Java reflection.
The application should have NFC permission as well as writing secure setting permission.
We need to specify all below permissions in AndroidManifest.xml

<uses-permission android:name="android.permission.NFC" />
<uses-permission 
android:name="android.permission.WRITE_SECURE_SETTINGS" />
<uses-feature android:name="android.hardware.nfc" android:required="false" />

Implementation for turn on/off a device

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
