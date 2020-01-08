package com.bool.kotlinstudy.bean

/**
 * 泛型
 * java 泛型 ;
 *      通配符上界，只能从中读取元素，不能添加元素，称为生产者(Producers)，用< ? extends T>表示。
        通配符下界，只能添加元素，不能直接读取下界类型的元素，称为消费者(Consumers)，用< ? super T>表示。
      < ? extends T>(T表示通配符的上界)，表示可以接收T以及T的子类参数，也就是说可以安全的读取到T的实例
       < ? super T>，其中T就表示通配符的下界。
 *
 *@author TianMingming
 *@date 2020/1/7 15:15
 */
class ZooBean<T>(t : T) {

    
}