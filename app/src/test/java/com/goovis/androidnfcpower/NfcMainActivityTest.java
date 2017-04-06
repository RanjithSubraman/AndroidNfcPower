package com.goovis.androidnfcpower;

import android.content.Context;
import android.widget.Switch;

import com.goovis.androidnfcpower.controller.NfcController;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by ranjithsubramaniam on 4/6/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class NfcMainActivityTest {
    private NfcController nfcController;
    private NfcMainActivity nfcMainActivity;

    @Mock private Switch mockSwitch;
    @Mock Context mockContext;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);

        nfcController = new NfcController(mockContext);
    }


}
