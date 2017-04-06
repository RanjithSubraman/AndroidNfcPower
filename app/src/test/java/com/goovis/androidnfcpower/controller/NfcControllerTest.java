package com.goovis.androidnfcpower.controller;

import android.content.Context;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by ranjithsubramaniam on 4/6/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class NfcControllerTest {
    private NfcController nfcController;

    @Mock Context mockContext;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
