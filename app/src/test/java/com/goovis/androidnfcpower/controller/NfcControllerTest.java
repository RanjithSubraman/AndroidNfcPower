package com.goovis.androidnfcpower.controller;

import android.content.Context;
import android.nfc.NfcAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;

/**
 * Created by ranjithsubramaniam on 4/6/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class NfcControllerTest {
    private NfcController nfcController;

    @Mock Context mockContext;
    @Mock NfcAdapter mockNfcAdapter;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);

        nfcController = new NfcController(mockContext);
    }

    @Test
    public void testCheckNfcPowerStatus() {
        // We can't mock static class by using mockito, so we need power mockito.
        // doNothing().when(NfcAdapter.getDefaultAdapter(mockContext));
        assertFalse(nfcController.checkNfcPowerStatus(mockContext));
    }
}
