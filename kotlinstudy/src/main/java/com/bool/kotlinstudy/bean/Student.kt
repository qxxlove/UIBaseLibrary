package com.bool.kotlinstudy.bean

/**
 * List集合如何定义
 */
class Student : People() {

    val age: String = "8888"
    var sum: Int = 3
    var sex: Int = 0 // 0 女  1 男
    val isBoy: Boolean
        get() {
            return if (sex == 1) {
                true
            } else {
                false
            }
        }

    //初始化值会直接写入备用字段
    var counter = 0
        get() = field   // field可以理解为自己本身
        set(value) {
            if (value >= 0)
                field  = value
        }

    // 这种情况并不需要备用字段，所有不会生成备用字段
    val isEmpty: Boolean  = false
      //  get() = this.size == 0




}