package com.bool.jetpackmvvm.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

// 复合主键，还不知道有什么用？
// 也可以直接直接使用在属性名上
@Entity(primaryKeys = {"first_name", "last_name"})
public class User {

   // @PrimaryKey
    public String first_name;
   // @PrimaryKey
    public String last_name;
    public  int age;
    @PrimaryKey
    public long userId;
    /**所属区域*/
    private List<String> region;

    // 这个会生成几张表？
    @Embedded
    public Address address;




    /**嵌套类
     * 注意: 嵌套字段还可以包含其他嵌套字段。 也就是Address类也可以嵌套其他字段。
     * @Embedded有个prefix属性，当某个实体具体相同类型的多个嵌套字段，
     *  可以通过设置prefix属性确保每个列的唯一性。
     *  Room会将 提供的值 添加到  嵌套对象中 每个列名称 的开头。
     * */
    public class Address {
        public String street;
        public String state;
        public String city;

        @ColumnInfo(name = "post_code")
        public int postCode;
    }


}
