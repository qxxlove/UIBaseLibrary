package com.bool.uibaselibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bool.uibaselibrary.utils.ByteUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBytes();
    }

    /**
     * 字节，string
     */
    private void initBytes() {
        String strByte = "DT EUP " + "\r\n";
        byte[] bytes = strByte.getBytes();
        printByte(bytes);      // 68 84 32 69 85 80 32 13 10
        String strByte1 = "123456789";
        byte[] bytes1 = strByte1.getBytes();
        printByte(bytes1); //49,50,51,52,53,54,55,56,57
        int a = 1;
        byte[] bytesInt = ByteUtils.intToByteArray(a);
        printByte(bytesInt);   // 0001 (4字节)
    }

    /**
     * 打印byte数组
     * @param bytes
     */
    private void printByte(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            stringBuffer.append(bytes[i] + ",");
        }
        Log.e("MainActivity", "结果" + stringBuffer.toString());
    }


}
