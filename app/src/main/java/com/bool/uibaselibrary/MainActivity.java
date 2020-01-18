package com.bool.uibaselibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bool.uibaselibrary.basics.BitActivity;
import com.bool.uibaselibrary.bean.Student;
import com.bool.uibaselibrary.utils.ByteUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBytes();
        initClick();
        initList();
    }

    /**
     * 比较两个集合
     *  负整数 	 位置排在前
     *  零 	 位置不变
     *  正整数 	 位置排在后
     *
     *         //降序
     *         //return o.age - this.age;
     *         //升序
     *         return this.age - o.age;
     */
    private void initList() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("张三", 20));
        list.add(new Student("李四", 10));
        Collections.sort(list, new Comparator<Student>() {
            /**
             *
             * @param student 当前对象   10
             * @param t1 比较对象        20    和集合添加的顺序有关
             * @return
             */
            @Override
            public int compare(Student student, Student t1) {
                int age1 = student.getAge();
                Log.e("initList", "当前值:" + age1);
                int age = t1.getAge();
                Log.e("initList", "当前值:" + age);
                int a =   age1 - age;
                Log.e("initList", "当前值:" + a);
                return   a;
            }
        });


        for (Student s : list) {
            Log.e("initList", "当前值:" + s.getName());
        }
    }

    private void initClick() {
        findViewById(R.id.act_main_tv_to_bit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BitActivity.class));
            }
        });
    }

    /**
     * 字节，string
     */
    private void initBytes() {
        String strByte = "DT EUP " + "\r\n";
        byte[] bytes = strByte.getBytes();
        printByte(bytes);      // 68 84 32 69 85 80 32 13 10 (ASCII码)
        String hello = new String(bytes);
        Log.e("MainActivity", "String结果:" + hello);

        String strByte1 = "123456789";
        byte[] bytes1 = strByte1.getBytes();
        printByte(bytes1); //49,50,51,52,53,54,55,56,57
        int a = 1;
        byte[] bytesInt = ByteUtils.intToByteArray(a);
        printByte(bytesInt);   // 0001 (4字节)

        byte[] by = new byte[]{'A', '1', 'a'};

        // DT VER\r\n
        //E/TAG: write byte :68,84,32,86,69,82,13,10,
        String strByteTwo = "DT VER\r\n";
        byte[] bytesTwo = strByteTwo.getBytes();
        printByte(bytesTwo);
        Log.e("MainActivity", "byte转String结果:" + ByteUtils.byteToString(bytesTwo));
        //4454205645520D0A   至于为什么这么转？没看出来干什么

    }

    /**
     * 打印byte数组
     *
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
