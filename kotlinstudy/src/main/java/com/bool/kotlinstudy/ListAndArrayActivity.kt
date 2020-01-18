package com.bool.kotlinstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 *   Kotlin中的集合和其他语言不同的是，Kotlin集合可分为可变和不可变集合。
 *    在Kotlin中，集合类型包含三种类型：它们分别是：List、Set、Map,他们之间存在以下几个异同点：
 *
 * 它们都是接口，并不是实际的类。
 *   它们只实现了isEmpty()、size、contains()等函数以及属性。
 *    List<E>和Set<E>都继承至Collection<out E>接口,且Collection<out E>继承于Iterable<out T>接口。
 *   而Map<K,V>是独立出来的一个接口。这一点和Java相同。
 *    这三种集合类型分别有存在MutableList<E>、MutableSet<E>、MutableMap<K,V>接口，这些接口中提供了改变、操作集合的方法。例如add()、clear()、remove()等函数。
 *
 *   在定义集合类型变量的时候如果使用List<E>、Set<E>、Map<K,V>声明的时候该集合则是不可变集合，
 *   而使用MutableList<E>、MutableSet<E>、MutableMap<K,V>的时候该集合才是可变类型集合。
 *
 * 协变
 *   当一个集合赋值给另外一个集合时，这里以List<E>举例，如果两个集合的类型也就是E类型相同时，赋值是没有问题的。
 *    如果类型不同的情况，当E继承自M时。你就可以把List<E>赋值给List<M>了。这种情况称之为协变
 *    可变和不可变集合之间是可以互相转换的： toMutableList() ，toList()、toHashSet()、toSet()等等函数
 *    源码：  kotlin\collections\_Collections.kt
 *
 *   开发中常用的集合操作
 *   https://juejin.im/post/5b1f7699f265da6e155d5965

 */

class ListAndArrayActivity : AppCompatActivity() {

    val arr = arrayOf("1", "2", 3, 4, 5)
    val list1 = listOf(1, 2, "3", 4, "5")                // 随意创建
    val list2 = listOf<String>("1", "2", "3", "4", "5")  // 确定元素的值类型
    val list3 = listOf(arr)                          // 可传入一个数组

    val mutableList1 = mutableListOf(1, 2, "3", 4, "5")                // 随意创建
    val mutableList2 = mutableListOf<String>("1", "2", "3", "4", "5")  // 确定元素的值类型
    val mutableList3 = mutableListOf(arr)                          // 可传入一个数组
    lateinit var  mutableList: ArrayList<String>   // 这里的ArrayList<>和Java里面的ArrayList一致

    val set1 = setOf(1,2,"3","4","2",1,2,3,4,5)
    val mutableSet1 = mutableSetOf(1,2,"3","4","2",1,2,3,4,5)
    lateinit var  mutableSet2 : HashSet<String>  // 这里的HashSet<>和Java里面的HashSet<>一致

    // 以键值对的形式出现，键与值之间使用to
    // eg ： 当我们的键存在重复时，集合会过滤掉之前重复的元素。
    val map1 = mapOf("key1" to 2 , "key2" to 3)
    val map2 = mapOf<Int,String>(1 to "value1" , 2 to "value2")
    val mutableMap = mutableMapOf("key1" to 2 , "key1" to 3)
    val hashMap = hashMapOf("key1" to 2 , "key1" to 3)   // 同Java中的HashMap




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_and_array)
        initCanChangeList()

    }


    /**
     * 可变集合
     */
    fun initCanChangeList() {
        mutableList1.add("6")  // 添加元素
        mutableList1.add("7")
        mutableList1.remove(1)   // 删除某一元素

        // 遍历
        for (value in mutableList1) {
            print("$value \t")
        }

        mutableList1.clear()   // 清空集合

         /**set 集合
          * Set类型集合会把重复的元素去除掉。这一点和Java是不谋而合的
          * */
        for(value in set1){
            print("$value \t")
        }

        /**map 集合*/
        map2.forEach{
            key,value -> println("$key \t $value")
        }

    }
}
