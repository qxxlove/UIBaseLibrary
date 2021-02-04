package com.bool.jetpackmvvm.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


/***
 *     @Entity标签用于将Student类与Room中的数据表对应起来。
 *     tableName属性可以为数据表设置表名，若不设置，则表名与类名相同
 *     @PrimaryKey标签用于指定该字段作为表的主键
 *     @ColumnInfo标签可以设置该字段存储在数据库表中的名字，并指定字段的类型。
 *     @Ignore标签用来告诉Romm忽略该字段或方法。
 *     保留某个字段，Room 必须拥有该字段的访问权限。您可以将某个字段设为公开字段，也可以为其提供 getter 和 setter。这里就将其字段设为公开字段。
 *
 *     tableName：设置表名字。默认是类名字
 *     indices: 设置索引
 *     inheritSuperIndices: 父类的索引是否会自动被当前类继承
 *     primaryKeys: 设置主键
 *     foreignKeys: 设置外键
 *
 *     实体类具有复合主键，也就是两个主键?
 *
 *
 *
 *
 */

@Entity(tableName = "Student")
public class Student {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(name = "name",typeAffinity = ColumnInfo.TEXT)
    public  String name;

    @ColumnInfo(name= "age",typeAffinity = ColumnInfo.TEXT)
    public  String age;

    @ColumnInfo(name = "sex",typeAffinity = ColumnInfo.TEXT)
    public String sex;

    //Room默认会使用这个构造器操作数据

    public Student(int id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /*
     *   由于Room 只能识别和使用一个构造器，如果希望定义多个构造器
     *   可以使用Ignore标签，让Room忽略这个构造器
     *   不仅如此，@Ignore标签还可以用于字段
     *   Room不会持久化被@Ignore标签标记过的字段的数据
     * */

    @Ignore
    public  Student(String name,String age){
        this.name = name;
        this.age= age;
    }
    @Ignore
    public Student(int id) {
        this.id = id;
    }


}
