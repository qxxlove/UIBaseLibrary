package com.bool.kotlinstudy.bean

/**
 *次构造函数(可以理解为构造函数的重载)
 *  如果类有一个主构造函数，每个次构造函数需要委托给主构造函数，
 *   可以  直接委托  或者  通过别的次构造函数 间接委托。委托到 同一个类的另一个构造函数  用 this  关键字即可
 *
 *  下面Boy 就是一个无主构造函数的类
 *@author TianMingming
 *@date 2020/1/7 13:57
 */
class Boy {

    /**子构造函数*/
    constructor(boy: Boy){
       
    }
}