package com.bool.kotlinstudy;

import android.content.ClipData;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.bool.kotlinstudy.grammar.LambdaBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型
 * @author  TianMingming
 * @date  2020/1/7 15:57
 */

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        initData();
        initLambda();
        initLambdaTwo();
        initBase();
    }



    private void initBase() {
        //lambda 表达式引用方法
        //有时候我们不是必须要自己重写某个匿名内部类的方法，我们可以利用 lambda表达式的接口快速指向一个已经被实现的方法。
        // 语法：  方法归属者::方法名 静态方法的归属者为类名，普通方法归属者为对象

        LambdaBaseActivity.ReturnOneParam lambda1 = a -> doubleNum(a);
        System.out.println(lambda1.method(3));

        //lambda2 引用了已经实现的 doubleNum 方法
        LambdaBaseActivity.ReturnOneParam lambda2 = JavaActivity::doubleNum;
        System.out.println(lambda2.method(3));

        JavaActivity exe = new JavaActivity();

        //lambda4 引用了已经实现的 addTwo 方法
        LambdaBaseActivity.ReturnOneParam lambda4 = exe::addTwo;
        System.out.println(lambda4.method(2));


        //一般我们需要声明接口，该接口作为对象的生成器，通过 类名::new 的方式来实例化对象，
        // 然后调用方法返回对象。
        ItemCreatorBlankConstruct creator = () -> new ItemBean();
        Object item = creator.getItem();

        ItemCreatorBlankConstruct creator2 = ItemBean::new;
        Object item2 = creator2.getItem();

        ItemCreatorParamContruct creator3 = ItemBean::new;
        Object item3 = creator3.getItem(112, "鼠标", 135.99);


        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(2 + ":" + i);
            }
        });
        t.start();


    }


    /**
     * 要求
     * 1.参数数量和类型要与接口中定义的一致
     * 2.返回值类型要与接口中定义的一致
     */
    public static int doubleNum(int a) {
        return a * 2;
    }

    public int addTwo(int a) {
        return a + 2;
    }



    /**
     * 代码简化
     *    ① 可以不写参数类型
     *    ② 只有一个参数可以省略小括号
     *    ③ 方法体只有一条语句，可以省略大括号
     *    ④
     */
    private void initLambdaTwo() {
        //1.简化参数类型，可以不写参数类型，但是必须所有参数都不写
        LambdaBaseActivity.NoReturnMultiParam lamdba1 = (a, b) -> {
            System.out.println("简化参数类型");
        };
        lamdba1.method(1, 2);

        //2.简化参数小括号，如果只有一个参数则可以省略参数小括号
        LambdaBaseActivity.NoReturnOneParam lambda2 = a -> {
            System.out.println("简化参数小括号");
        };
        lambda2.method(1);

        //3.简化方法体大括号，如果方法条只有一条语句，则可以胜率方法体大括号
        LambdaBaseActivity.NoReturnNoParam lambda3 = () -> System.out.println("简化方法体大括号");
        lambda3.method();

        //4.如果方法体只有一条语句，并且是 return 语句，则可以省略方法体大括号
        LambdaBaseActivity.ReturnOneParam lambda4 = a -> a+3;
        System.out.println(lambda4.method(5));

        LambdaBaseActivity.ReturnMultiParam lambda5 = (a, b) -> a+b;
        System.out.println(lambda5.method(1, 1));

    }

    /**
     * jdk 1.8 lambda新特性
     */
    private void initLambda() {
        //无参无返回
        LambdaBaseActivity.NoReturnNoParam noReturnNoParam = () -> {
            System.out.println("NoReturnNoParam");
        };
        noReturnNoParam.method();

        //一个参数无返回
        LambdaBaseActivity.NoReturnOneParam noReturnOneParam = (int a) -> {
            System.out.println("NoReturnOneParam param:" + a);
        };
        noReturnOneParam.method(6);

        //多个参数无返回
        LambdaBaseActivity.NoReturnMultiParam noReturnMultiParam = (int a, int b) -> {
            System.out.println("NoReturnMultiParam param:" + "{" + a +"," + + b +"}");
        };
        noReturnMultiParam.method(6, 8);

        //无参有返回值
        LambdaBaseActivity.ReturnNoParam returnNoParam = () -> {
            System.out.print("ReturnNoParam");
            return 1;
        };

        int res = returnNoParam.method();
        System.out.println("return:" + res);

        //一个参数有返回值
        LambdaBaseActivity.ReturnOneParam returnOneParam = (int a) -> {
            System.out.println("ReturnOneParam param:" + a);
            return 1;
        };

        int res2 = returnOneParam.method(6);
        System.out.println("return:" + res2);

        //多个参数有返回值
        LambdaBaseActivity.ReturnMultiParam returnMultiParam = (int a, int b) -> {
            System.out.println("ReturnMultiParam param:" + "{" + a + "," + b +"}");
            return 1;
        };

        int res3 = returnMultiParam.method(6, 8);
        System.out.println("return:" + res3);


    }





    private void initData() {
        List<String> strs = new ArrayList<String>();
        strs.add("0");
        strs.add("1");
        /**通配符上界*/
        List<? extends Object> objs = strs;
        objs.get(0); // 可以获取
        //objs.add(1); // 但是添加的时候报错


        List<String> strs1 = new ArrayList<String>();
        strs1.add("0");
        /**t通配符下界*/
        List<? super String> objs1 = strs;
        objs1.add("1");
        objs1.set(0, "2");
        // 得到Object类型，如果想要String 还需要强转
        Object s = objs.get(0);

        
    }





    interface ItemCreatorBlankConstruct {
        ItemBean getItem();
    }
    interface ItemCreatorParamContruct {

        ItemBean getItem(int id, String name, double price);
    }

    class  ItemBean {
        int id;String name; double price;

        public ItemBean() {
        }

        public ItemBean(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }
}
