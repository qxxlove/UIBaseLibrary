package com.bool.jetpackmvvm.room.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.bool.jetpackmvvm.room.Student;
import com.bool.jetpackmvvm.room.StudentDao;

/**
 *  创建数据库
 *
 *    @Database标签用于告诉系统这是Room数据库对象。
 *    entities属性用于指定该数据库有哪些表，若需要建立多张表，则表名以逗号相隔开。
 *    version属性用于指定数据库版本号，后面数据库的升级正是依据版本号进行判断的
 *    数据库类需要继承自RoomDatabase，由于每次创建Database实例都会产生比较大的开销，
 *    所以我们使用的是单例模式。
 *    通过Room.databaseBuilder(): 生成Database对象，创建一个数据库。
 *    另外，我们之前创建的Dao对象，在此以抽象方法的形式返回。
 *
 *  升级
 *    在这种情况下，Room会先判断当前有没有直接从1到3的升级方案，
 *    如果有，就直接执行从1到3的升级方案，
 *    如果没有，那么Room会按照顺序先后执行Migration(1,2)、Migration(2,3)以完成升级。
 *
 *   方案：
 *         销毁与重建策略
 *         https://zhuanlan.zhihu.com/p/77408877
 *
 *    在Sqlite中修改表结构会比较麻烦。比如我们希望将Student表中的age字段类型从INTEGER改为TEXT。
 *
 *    面对此类需求，最好的方式就是采用“销毁与重建策略”，该策略大致分为以下几个步骤：
 *
 *      1.创建一张符合我们要求的临时表temp_Student
 *
 *      2.将数据从旧表Student拷贝至临时表temp_Student
 *
 *      3.删除旧表Student
 *
 *      4.将临时表temp_Student重命名为Student
 *
 *
 *    什么时候会触发   Migration ？
 *      答:  可以看schemas 文件夹下得文件：
 *           1.json 里面只有三个字段
 *           2.json 多了一个字段 sex ,说明升级成功。
 *           不论是新增字段，还是修改字段属性，都得参照上面的方案。
 *           如果是新增表，直接新建就可以了。
 *
 *
 *      fallbackToDestructiveMigration()
 *      该方法能够在出现升级异常时，重新创建数据表。需要注意的是，
 *      虽然应用程序不会崩溃，但由于数据表被重新创建，所有的数据也将会丢失.
 *
 *
 */
@Database(entities = {Student.class},version = 2)
public abstract  class MyDatabase extends RoomDatabase {

    //定义数据库名称
    private  static  final  String DATABASE_NAME = "my_db";

    private  static  MyDatabase databaseInstance;

    public  static  synchronized  MyDatabase getInstance(Context context){
        if(databaseInstance == null){
            databaseInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MyDatabase.class,
                    DATABASE_NAME)
                  //  .fallbackToDestructiveMigration()   (勿用)
                    .addMigrations(MIGRATION_1_2,MIGRATION_2_3)
                    //从assest/databases 目录下读取student.db
                   // .createFromAsset("databases/student.db")
                    .build();
            /**读取文件数据库*/
            /*Room.databaseBuilder( context.getApplicationContext(), MyDatabase.class, "Sample.db")
                    .createFromFile(new File("mypath"))
                    .build();*/
        }
        return  databaseInstance;
    }




    public  abstract StudentDao studentDao();


    /**
     * 升级类
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //执行与升级相关的操作
            //database.execSQL("ALTER TABLE student ADD COLUMN sex TEXT");

            database.execSQL("CREATE TABLE temp_Student (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "name TEXT," +
                    "age TEXT,sex TEXT)");
            database.execSQL("INSERT INTO temp_Student (id, name, age) " +
                    "SELECT id, name, age FROM Student");
            database.execSQL("DROP TABLE Student");
            database.execSQL("ALTER TABLE temp_Student RENAME TO Student");

        }
    };

    /**
     * 以此类推
     */
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //执行与升级相关的操作
        }
    };



    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
