package com.r3tr0.bluetoothcontrol.interfaces;

import android.bluetooth.BluetoothDevice;

import java.util.List;

/**
 * Created by tarek on 7/23/17.
 */

public interface BluetoothCore {
    boolean setBluetoothState(int state);
    List<BluetoothDevice> getPairedDevicesList();
    int getBluetoothState();
}
