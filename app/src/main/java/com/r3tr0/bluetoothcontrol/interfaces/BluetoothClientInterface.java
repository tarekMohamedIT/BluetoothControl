package com.r3tr0.bluetoothcontrol.interfaces;

import android.bluetooth.BluetoothDevice;

/**
 * Created by tarek on 7/23/17.
 */

public interface BluetoothClientInterface {
    boolean connectToDevice(BluetoothDevice device);
    boolean disconnect();
    void send(char character);
    void send(String string);
    void send(int integer);
}
