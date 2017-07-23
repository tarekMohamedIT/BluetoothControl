package com.r3tr0.bluetoothcontrol.core;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.r3tr0.bluetoothcontrol.interfaces.BluetoothClientInterface;
import com.r3tr0.bluetoothcontrol.interfaces.BluetoothCore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarek on 7/22/17.
 */

public class BluetoothClient implements BluetoothCore, BluetoothClientInterface {

    public static final Integer STATE_ACTIVATE = 1;
    public static final Integer STATE_DEACTIVATE = 0;

    private BluetoothAdapter adapter;
    private BluetoothSocket bluetoothSocket;
    private String UUID;
    private boolean isConnected;

    public BluetoothClient(){
        this(BluetoothAdapter.getDefaultAdapter());
    }

    public BluetoothClient(BluetoothAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public boolean setBluetoothState(int state){
        if (state == STATE_ACTIVATE && adapter.isEnabled()) return false;
        else if (state == STATE_ACTIVATE && !adapter.isEnabled()){
            adapter.enable();
            return true;
        }

        else if (state == STATE_DEACTIVATE && !adapter.isEnabled()) return false;
        else if (state == STATE_DEACTIVATE && adapter.isEnabled()){
            adapter.disable();
            return true;
        }

        return false;
    }

    @Override
    public List<BluetoothDevice> getPairedDevicesList(){
        if (adapter == null) adapter = BluetoothAdapter.getDefaultAdapter();
        if (!adapter.isEnabled()) setBluetoothState(STATE_ACTIVATE);

        return new ArrayList<>(adapter.getBondedDevices());
    }

    @Override
    public int getBluetoothState() {
        return adapter.isEnabled() ? 1 : 0;
    }

    @Override
    public boolean connectToDevice(BluetoothDevice device) {
        try {
            bluetoothSocket = device.createRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
            bluetoothSocket.connect();
            isConnected = true;
            return bluetoothSocket != null && bluetoothSocket.isConnected();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isConnected = false;
        return false;
    }

    @Override
    public boolean disconnect() {
        if (bluetoothSocket != null && bluetoothSocket.isConnected()) try {
            bluetoothSocket.close();
            bluetoothSocket = null;
            isConnected = false;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void send(char character) {

    }

    @Override
    public void send(String string) {

    }

    @Override
    public void send(int integer) {

    }
}
