package com.goovis.androidnfcpower;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.goovis.androidnfcpower.controller.NfcController;

/**
 * Created by Ranjith Subramaniam on 4/4/17.
 */

public class NfcMainActivity extends AppCompatActivity {

    private Switch nfcTurnOnOff;
    private NfcController nfcController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_main);

        nfcController = new NfcController(this.getApplicationContext());
        nfcTurnOnOff = (Switch)findViewById(R.id.turnonoff);

        nfcTurnOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    nfcController.enableNfcPower(true);
                    Toast.makeText(getApplicationContext(), "NFC is power ON",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    nfcController.enableNfcPower(false);
                    Toast.makeText(getApplicationContext(), "NFC is power OFF",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
