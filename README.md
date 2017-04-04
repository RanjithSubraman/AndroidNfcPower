<h1>Programmatically turn ON/OFF NFC in Android</h1>

I hope most of you know about NFC (Near Field Communication). It's an innovative version of RFID to read and write a small chunk of information by using the electromagnetic field.

More information: http://developer.android.com/guide/topics/connectivity/nfc/index.html

In this post, I am going to explain how to turn ON/OFF NFC by using the android APIs.

Google has some restrictions on Nfc power on/off for developers. Obviously, we know it is a user feature to turn on/off by android system setting menu.

<h6>Follow the below steps:</h6>
<li>The device should be a rooted device or system application acceptable device.</li>
<li>The developer needs to be accessed to handle power ON/OFF API by Java reflection.</li>
<li>The application should have NFC permission as well as writing secure setting permission.</li>

We need to specify all below permissions in AndroidManifest.xml
<pre lang='xml'>
     &lt;uses-permission android:name="android.permission.NFC" &gt;
     &lt;uses-permission android:name="android.permission.WRITE_SECURE_SETTINGS" &gt;
     &lt;uses-feature android:name="android.hardware.nfc" android:required="false" &gt;
</pre>

Implementation for turn on/off a device
<pre lang='java'>
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
</pre>

<h6>Check the NFC power status by using below method.</h6>

This is a public method, so we don't need to access this by using any reflection techniques. So simply you could call the method from an adapter object.
<pre lang='java'>
public static boolean checkNfcPowerStatus(Context context) {
    NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
    boolean enabled = false;

    if (nfcAdapter != null) {
        enabled = nfcAdapter.isEnabled();
    }

    return enabled;
}
</pre> 
