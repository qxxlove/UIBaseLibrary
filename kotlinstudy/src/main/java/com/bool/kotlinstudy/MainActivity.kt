package com.bool.kotlinstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bool.kotlinstudy.`interface`.Clicker
import com.bool.kotlinstudy.bean.*

/**
 *  Kotlin 入门
 * https://www.jianshu.com/p/fa48ae888879
 *  Kotlin学习手册（一）类与继承
 *  https://www.jianshu.com/p/234531a5a1a7
 *  Kotlin 实战
 *  https://juejin.im/post/5ec0b6bef265da7bf873ec79
 *  Kotlin系列之let、with、run、apply、also函数的使用
 *  https://blog.csdn.net/u013064109/article/details/78786646?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
 *  一文弄懂 kotlin lambda与高阶函数
 *   https://juejin.im/post/5e8893ab51882573744c5606
 *
 *   1. 常量
 *   2. 变量
 *   3. 非空
 *   4. 方法，类， 对象
 *   5. if ,for，
 *   6. 集合，数组
 *   7. kotlin 中的顶层函数也就是我们所说的工具类
 *      Java中调用Kotlin中定义顶层函数，一般是顶层文件名+"Kt"后缀作为静态函数的类名调用相应函数
         通过Kotlin中的@file: JvmName("自定义生成类名")注解就可以自动生成对应Java调用类名
 *      顶层属性即常量 类似于 psfs
 *
 *      扩展函数，属性
 *
 *   8. 中缀语法，扩展声明为成员
 *   9. 解构 声明实际上就是将对象中所有属性，解构成一组属性变量，而且这些变量可以单独使用
 *      实际上使用的是局部变量
 *      解构声明的对象类型一定是data class，普通的class是不会生成对应的component的方法。
 *
 *    10. 接口，继承   eg:  DoSomeThing, XueXue
 *         kotlin中 的方法默认都是 final ，如果需要子类继承就要特地标记 open 修饰符
 *         Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类
 *
 *    11. 主构造函数和次构造函数的使用，还需进一步  eg:girl
 *    12. 数据类 ，关键字：data
 *        数据类必须满足几个条件
 *           主构造函数需要至少有一个参数；
 *           主构造函数的所有参数需要标记为 val 或 var；
 *           数据类不能是抽象、开放、密封或者内部的；
 *            如：CarData

 *        13. 密封类
 *            基于枚举，高于枚举
 *            声明一个密封类，需要在类名前面添加 sealed 修饰符。虽然密封类也可以有子类，
 *            但是所有子类都必须在与密封类自身相同的文件中声明
 *            如： UserBean

 *        14. 使用 is 运算符检测一个表达式是否某类型的一个实例(类似于Java中的instanceof关键字)。
 *        15 泛型
 *           ZooBean
 *        16. 一元操作符    重载    和java 是一样的 ，需要注意kotlin 的写法
 *           +a 	   a.unaryPlus()
 *           -a 	   a.unaryMinus()
 *           !a 	   a.not()

 *           后缀自增：表示为操作数进行自增操作，其操作数为数值型。例如：a++
 *           后缀自减：表示为操作数进行自减操作，其操作数为数值型。例如：a--
 *           前缀自增：表示为操作数进行自增操作，其操作数为数值型。例如：++a
 *           前缀自减：表示为操作数进行自增操作，其操作数为数值型。例如：--a


 *
 *
 *         17. 枚举 实际开发中有什么用？
 *         18 . 抽象类
 *              抽象类，可以理解为类定义了一个模板。所有的子类都是根据这个模板是填充自己的代码。
 *              eg: AbsStudentClass
 *         19. 嵌套类 (内部类)
 *             声明一个内部类使用inner关键字
 *             匿名内部类
 *             局部类 同java
 *             静态类：
 *             
 *
 *
 *
 */
class MainActivity : AppCompatActivity(), Clicker {

    // public  static final boolean  = false ;
    /**常量  val <常量名> : <常量类型> = <初始值>
     * 显然这只是一个不可修改的变量，并不能称之为常量：
     *    val numA = 6  ==>  public final int numA = 6
     *    const val NUM_A = 6 = > public static final int numA = 6
     * */
    val sum = 4
    /**变量  var <变量名> : <变量类型> = <初始值>*/
    var strName: String = "3"
    var strAge = 4
    /**延迟初始化话*/
    lateinit var stndent: Student
    /**继承属性*/
    override var kCount: Int = 0
    /**set*/
    val set = hashSetOf(1, 2, 3)
    /**list*/
    val arr = arrayListOf(2, 3, 4, 5)
    /**hashmap*/
    val map = hashMapOf(1 to "A", 2 to "B")
    /**泛型*/
    var zooBean: ZooBean<Int> = ZooBean(1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initVar()
        //initStudent()
        // initNull()
        // initEnum()
        //initExtend()
         initData()

    }

    /**
     *一个延迟初始化的字符串数组变量
     */
    private val mTitles: Array<String> by lazy {
        arrayOf("sdf", "sdfsadfas", "adfsadfsd")
    }

    /**
     *  声明一个延迟初始化的字符串
     */
    private val mStr: String by lazy {
        "我是延迟初始化字符串变量"
    }

    /**
     * 数据类调用
     */
    fun initData() {
        var json = CarData(name = "ymc", age = 1)
        /**解构*/
        val (name, pwd) = json
        println("name = $name\tpwd = $pwd")
        /**修改属性*/
        val json1 = json.copy(age = 2)
        // 默认调用 User的 tostring（）
        println(json1)

        println(mTitles[1])
        println("集合：" + listOf(json))
        println("转换:" + json.to(2))
    }

    /**
     * 密封类的使用
     *      好处：
     *      有效的保护代码
     *     在使用when表达式 的时候，如果能够验证语句覆盖了所有情况，
     *     就不需要为该语句再添加一个else子句了。
     */
    fun eval(expr: UserBean): Double {
        return when (expr) {
            is UserBean.Const -> expr.number
            is UserBean.Sum -> eval(expr.e1) + eval(expr.e2)
            UserBean.NotANumber -> Double.NaN      // 什么作用 ？
        }
    }

    /**
     * 扩展函数
     *     kotlin 是 将他当做静态函数来看待的
     * 扩展属性 实际上就是提供某个属性访问的set,get方法
     */
    private fun initExtend() {
        println("Kotlin".lastData())
        indexx
    }

    /**
     * 枚举
     */
    fun getColor(color: Colors) {
        var a = 1
        when (a) {

        }
    }

    /**
     * 枚举
     *   ① 每个枚举常量都包含两个属性：name（枚举常量名）和ordinal（枚举常量位置）
     *   ② 提供了values()和valueOf()方法来检测指定的名称 与 枚举类中定义的任何枚举常量 是否匹配。
     *   自 Kotlin 1.1起，可以使用 enumValues<T>()和 enumValueOf<T>()函数以泛型的方式访问枚举类中的常量。
     *
     */
    private fun initEnum() {
        println("name = " + ColorTwos.RED.name + "\tordinal = " + ColorTwos.RED.ordinal)
        println("name = " + ColorTwos.WHITE.name + "\tordinal = " + ColorTwos.WHITE.ordinal)
        println("name = " + ColorTwos.BLACK.name + "\tordinal = " + ColorTwos.BLACK.ordinal)
        println("name = " + ColorTwos.GREEN.name + "\tordinal = " + ColorTwos.GREEN.ordinal)

        println(enumValues<ColorTwos>().joinToString { it.name })
        println(enumValueOf<ColorTwos>("RED"))

        /**若使用Color.valueOf("不存在的枚举常量")，则会抛出IllegalArgumentException 异常，
         * 即枚举变量不存在。
         * 若使用Color.values()[大于枚举常量位置]，则会抛出下标越界异常。*/
        println(ColorTwos.valueOf("RED"))
        println(ColorTwos.values()[0])
        println(ColorTwos.values()[1])
        println(ColorTwos.values()[2])
        println(ColorTwos.values()[3])
    }


    /**
     * if 语句
     */
    fun getMaxNumber(a: Int, b: Int) {
        if (a > b) a else b
    }

    /**
     * 获取和
     */
    fun getTotalNumber(a: Int, b: Int) = a + b

    /**
     * for 语句
     *
     * reversed() : 反序。即和初始化的顺序反过来。
    sorted() : 自然升序。
    sortedBy{} : 根据条件升序，即把不满足条件的放在前面，满足条件的放在后面
    sortedDescending() : 自然降序。
    sortedByDescending{} : 根据条件降序。和sortedBy{}相反
     */
    fun oneMethond(args: Array<String>) {
        for (arg in args) {
            print(arg)
        }

        //  检测某个数字是否在指定区间外
        var x: Int = 1
        var y: Int = 4
        if (x in 1..y - 1) { // x是否在 1到y-1的范围
            print("OK")
        }
        for (i in 1..100) {
        }  //  1到100范围
        for (i in 1 until 100) {
        } // 半开范围，不包括100，相当于[1,100)
        for (x in 2..10 step 2) {
        } // 每次加2，内容为 x , x+2 ,x+4 ...
        for (x in 10 downTo 1) {
        } // 倒序


        val langs = listOf("C", "C++", "Java", "Python", "JavaScript")
        // startsWith 给定的字串和自己定义的字串相比较，看是否是前缀相同
        langs.filter { it.startsWith("C") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }
    }

    /**
     *  if、is、 when 结合
     */
    fun descript(obj: Any): String = when (obj) {
        1 -> "one"
        "hello" -> "hello word"
        is Long -> "long type"
        !is String -> "is not String"
        else -> {
            "unknown type"
        }
    }


    /**
     * 初始化变量
     */
    private fun initVar() {
        strAge = 1
        strName = ""
        println("变量" + strAge)
        println("变量姓名：" + strName)
    }

    /**
     * 初始化实体类
     */
    private fun initStudent() {
        stndent = Student()
        println(stndent.age)
        println(stndent.sum)
        println(stndent.isBoy)
        stndent.counter = 3
        println(stndent.counter)


    }

    /**
     * 非空语句
    Evils其实不是一个操作符，而是evil的复数，而evil的意思在这里可以理解为屏蔽、安全的操作符，这样的操作符有三种：
    ?: 这个操作符表示在判断一个可空类型时，会返回一个我们自己设定好的默认值.
    !! 这个操作符表示在判断一个可空类型时，会显示的抛出空引用异常（NullPointException）.
    as? 这个操作符表示为安全的类型转换.

    因为在使用一个可空类型变量时，在该变量后面加上!!操作符，会显示的抛出NullPointException异常
    val testStr : String? = null
    println(testStr!!.length)

    // 会抛出ClassCastException异常
    val num1 : Int? = "Koltin" as Int
    println("nun1 = $num1")

    val num2 : Int? = "Koltin" as? Int
    println("nun2 = $num2)
    num2 = null
     */
    private fun initNull() {
        //类型后面加 ? 表示可为空
        var age: String? = null
        println(age)     // null
        //字段后面加 "!!" ，如果为null则抛出空指针异常
        // val ages = age!!.toInt()
        // println(ages)
        //字段后面加 ”？“  如果为null不做处理返回 null
        val ages1 = age?.toInt()
        println(ages1)    //  null
        //使用 ”?:“  表示age为null返回-1
        //当我们定义了一个可空类型的变量时，如果该变量不为空，则使用，反之使用另外一个不为空的值
        val ages2 = age?.toInt() ?: -1
        println(ages2)       // -1 

        println(getStringLengthOne(""))
        println(getStringLengthTwo(""))

        initLet()

    }

    /**
     * 双冒号使用：
     *     一般情况，我们调用当前类的方法 this 都是可省略的，这里之所以不可省略的原因是
          为了防止作用域混淆 ，
            :: 调用的函数如果是类的成员函数或者是扩展函数，必须使用限定符,比如this
               如果把 isOdd 写到 class 外部 (全局) 这里也是可以省略限定符。
     */
    fun isOdd(x: Int) = x % 2 != 0
    fun test() {
        var list = listOf(1, 2, 3, 4, 5)
        println(list.filter(this::isOdd))
    }


    /**
     * let 操作符
     * let操作符的作用：当时用符号?.验证的时候忽略掉null
       let的用法：变量?.let{ ... }
     */
    private fun initLet() {

        val arrTest: Array<Int?> = arrayOf(1, 2, null, 3, null, 5, 6, null)

        // 传统写法
        for (index in arrTest) {
            if (index == null) {
                continue
            }
            println("index => $index")
        }

        // let写法
        for (index in arrTest) {
            index?.let { println("index => $it") }
        }
    }


    /**
     * null 判断
     */
    fun Any?.toString(): String? {
        if (this == null) return "null"
        // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
        // 解析为 Any 类的成员函数
        return toString()
    }

    /**
     * 基础方法的定义：
     *  fun  方法名  参数  返回值
     *  注意冒号
     */
    fun main(strName: String): Int {
        return 0
    }


    /**
     * 有返回值
     */
    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    /**
     * 无返回值
     */
    fun min(a: Int, b: Int): Unit {
        if (a > b) a else b
    }


    /**
     * is 语句
     */
    fun getStringLengthOne(obj: Any): Int? {
        if (obj is String) {
            // `obj`在这个分支中自动转换为`String`类型
            return obj.length
        }
        // `obj`仍然是`Any`类型
        return null
    }

    fun getStringLengthTwo(obj: Any): Int? {
        // `obj`在这个分支中自动转换为`String`类型
        if (obj !is String) return null
        return obj.length
    }


    /**
     * 可边长
     * public void setData(Object... objects)。
     */
    fun vars(vararg args: Int) {
        for (arg in args) {
            print(arg)
        }
    }

    /**匿名函数*/
    val sumLambda: (Int, Int, Int) -> Int = { a, b, c -> a + b + c }

    /**基本 常见的方法定义*/
    fun sum(a: Int, b: Int, c: Int): Int {
        return a + b + c
    }


    /**
     * 获取String 最后一位
     *  分析：  我们想要扩展的类 或者接口名称，添加在函数名称的前面 ，这个 类的名称 称为 接收者类型，
     *          所以在本例中 String 就是接收者类型，(再调用处)而 “kotlin” 就是接收者对象
     *  原则：  扩展函数是 不希望你打破 原有类的封装性的，
     *          所以扩展函数是无法 访问到 私有的或者受保护的成员。
     */
    private fun String.lastData(): Char {
        return this.get(this.length - 1)
    }

    /**
     * 接口回调
     */
    override fun click() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
