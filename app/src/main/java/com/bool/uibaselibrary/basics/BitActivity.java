package com.bool.uibaselibrary.basics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bool.uibaselibrary.R;

/**
 * 位运算
 * https://mp.weixin.qq.com/s/0haD2epzqBXRuMbXMKP_xQ
 * @author  TianMingming
 * @date  2019/12/24 11:40
 */

public class BitActivity extends AppCompatActivity {

    private  int  a  = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bit);
        
   /*     bitOperation();
        andOperation();
        assignmentBit();
        UnsignedRightMoving();*/
        initBit();

    }

    /**
     *   (<<=)左移赋值
     *    其他同理
     *    1.先将5做左移三位的操作，得出结果为40(此步骤与<<一致)
     *    2.然后将最终结果重新赋值给a，即a为40
     */
    private void assignmentBit() {
        int a = 5;
        a <<= 3;  //a = a << 3
        
    }

    /**
     * 位与运算符
     * 1. 129转为二进制0000 0000 0000 0000 0000 0000 1000 0001，
     *   128转二进制为0000 0000 0000 0000 0000 0000 1000 0000
     *
     * 2.从最高位开始比较，同一位的两个值都为1则是1，否则为0，
     *  则最终结果为0000 0000 0000 0000 0000 0000 1000 0000 ，即128
     *
     * 位或运算符
     *  从最高位开始比较，同一位的两个值有一个为1则是1，否则为0，
     *  则最终结果为0000 0000 0000 0000 0000 0000 1000 0001即129
     *
     *  (~)位非运算符
     *    1.  37转为二进制为00000000 00000000 00000000 00100101
          2.~  操作是指如果每一位的值为0，则是1，如果为1，则是0，所以整体取反，
           结果为11111111 11111111 11111111 11011010
         3 由于当前获取的结果首位为1，代表值为负数，那么我们通过当前补码计算原码，首先将补码-1得到反码，
                                      则为11111111 11111111 11111111 11011001，然后整体取反，则为00000000 00000000 00000000 00100110 ，转为10进制为38，
                                     由于是负数，则最终结果为-38
           如果首位是0 ，则表示正数，原码，补码，反码，都是一样的

              ( ^ )位异或运算
             假设当前值为8，操作为8^11，具体操作如下：
         1.8转为二进制0000 0000 0000 0000 0000 0000 0000 1000 ，11转为二进制为0000 0000 0000 0000 0000 0000 0000 1011
         2.^运算符则是比较每一位的值是否一致，一致为0，否则为1，
           所以比较的结果为0000 0000 0000 0000 0000 0000 0000 0011 ，转为十进制结果为3

     *
     */
    private void andOperation() {
        Log.e("initBit", "位与运算符结果:"+(129 & 128));
        Log.e("initBit", "位或运算符结果:"+(129 | 128));
        Log.e("initBit", "位非运算符结果:"+(~37));
        Log.e("initBit", "位异或运算符结果:"+(8^11));

    }

    /**
     *  左移运算符
     *  ① 将5转为32比特位(int)的二进制，得出结果0000 0000 0000 0000 0000 0000 0000 0101 (不足补0位)
     *
     *  ② 将整体朝左移动三位，超过三十二位的高位舍弃(少舍弃一位，留下一位作为正负数的符号位，即正数最高位补0，负数最高位补1)，
     *     低位不足补0，则是0000 0000 0000 0000 0000 0000 0010 1 000 ，
     *     将当前二进制转换为十进制，则是40，所以最终计算的结果为40

     *  右移运算符
     *   假设当前值int a = 5，操作为5>>1，则是对5右移1位的操作，具体步骤如下:
     *   1.将5转为32比特位的二进制，结果为0000 0000 0000 0000 0000 0000 0000 0101
     *   2.将整体二进制的结果右移1位，如果本身为正数，首个最高位补0，
     *   负数首个最高位补1，其他高位补0，超过的低位舍弃，结果为00000 0000 0000 0000 0000 0000 0000 010，
     *   将这个值转为十进制为2，所以最终结果为2
     *
     * */
    private void bitOperation() {
         int c = a << 3 ;
         Log.e("initBit", "左移结果:"+c);
        int d = a >> 1 ;
         Log.e("initBit", "右移结果:"+d);
    }


    /**
     * 无符号右移
     * 1.原码为0000 0000 0000 0000 0000 0000 0000 0101
     *
     * 2.反码为1111 1111 1111 1111 1111 1111 1111 1010
     *
     * 3.补码为1111 1111 1111 1111 1111 1111 1111 1011
     *
     * 4.整体右移1位，高位补0，低位超过部分舍弃，
     * 则为0111 1111 1111 1111 1111 1111 1111 1101，转为十进制为2147483645 ，
     * 所以最终结果为2147483645
     *
     *  一定要分清楚无符号右移和右移操作的区别，右移位运算符>>，若操作的值为正，则在高位插入0；若值为负，则在高位插入1，
     *                                          右移补零操作符>>>，无论正负，都在高位插入0
      */
    public void UnsignedRightMoving (){
         int f = -5 >>> 1 ;
         Log.e("initBit", "无符号右移结果:"+f);
    }





    /**
     * 位运算
     */
    private void initBit() {
        Log.e("initBit", Integer.toHexString(0x7fffffff >> 4));
        Log.e("initBit", Integer.toHexString(0x8fffffff >> 4));
        Log.e("initBit", Integer.toHexString(0xffffffff >> 4));
        Log.e("initBit", Integer.toHexString(0x0fffffff >> 4));

        Log.e("initBit", Integer.toHexString(0x7fffffff << 4));
        Log.e("initBit", Integer.toHexString(0x8fffffff << 4));
        Log.e("initBit", Integer.toHexString(0xffffffff << 4));
        Log.e("initBit", Integer.toHexString(0x0fffffff << 4));

        Log.e("initBit", Integer.toHexString(0x7fffffff >>> 4));
        Log.e("initBit", Integer.toHexString(0x8fffffff >>> 4));
        Log.e("initBit", Integer.toHexString(0xffffffff >>> 4));
        Log.e("initBit", Integer.toHexString(0x0fffffff >>> 4));

        Log.e("initBit", "值："+0x7fffffff);
        Log.e("initBit", "值："+0x8fffffff);
        Log.e("initBit", "值："+0xffffffff );
        Log.e("initBit", "值："+0x0fffffff);


    }
}
