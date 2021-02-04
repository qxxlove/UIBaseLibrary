package com.bool.jetpackmvvm.room;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bool.jetpackmvvm.R;
import com.bool.jetpackmvvm.room.database.MyDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 *  1. Room相对来说就是对Sqlite上提供了一层封装
 *
 *  2. 一个Entity 代表一张表，而每张表都需要一个Dao对象，用于对表进行增/删/改/查。
 *     Room数据库在被实例化之后，我们就可以通过数据库实例得到Dao对象(Get Dao),
 *     进而通过Dao对象对表中的数据进行操作
 *
 *  3. 每次对数据库操作的时候都需要在工作线程中进行，
 *     这意味着，每次当数据库中的数据发送变化时，都需要开启一个工作线程进行查询。
 *     那么，能否在数据发生变化时，自动接收数据呢？ 通过LiveData就可以解决这个问题
 *
 *  4. 哪些数据是活的，是通过网络或者数据库获取的，就将它包装为：LiveData
 *
 *  5. 有时，您可能需要查询包含三个或更多表格的集合，这些表格之间互相关联。在这种情况下，
 *          您需要定义各个表之间的嵌套关系。
 *
 *      在音乐在线播放应用示例中，
 *      假设您想要查询所有用户、每个用户的所有播放列表 以及 每个用户的各个播放列表中包含的所有歌曲。
 *      用户与播放列表之间存在一对多关系，
 *      而播放列表与歌曲之间存在多对多关系。
 *      以下代码示例显示了代表这些实体以及播放列表与歌曲之间多对多关系的交叉引用表的类：
 *       User ,Playlist ,Song ,PlaylistSongCrossRef 实体类
 *
 *       注意：使用嵌套关系查询数据需要 Room 处理大量数据，可能会影响性能。因此，请在查询中尽量少用嵌套关系
 *
 *
 */


public class RoomActivity extends AppCompatActivity implements View.OnClickListener {


    private Button addBtn,delBtn,updateBtn,queryIdBtn,queryBtn;
    private EditText delId,updateId,updateName,queryId;
    private ListView mListView;
    //添加学生
    private  static  final  int ADD_STUDENT = 1001;
    //Handler
    private Handler handler = new Handler();
    //定义一个遍历
    private  int count  = 1;
    //存放学生基本信息集合
    private List<Student> mList = new ArrayList<>();
    //数据库对象
    private MyDatabase myDatabase;
    //ListView的Adapter
    private ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        initView();

//        现在我们就可以将增加、删除或修改代码中这一行代码queryStudentList()给删除了
//        运行程序，当对数据库进行增加、删除或修改操作时， public void onChanged(List students)方法会被自动调用，只需要在该方法中通知Adapter刷新数据即可
        StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        studentViewModel.getListLiveData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                if(mList.size()>0){
                    mList.clear();;
                }
                mList.addAll(students);
                adapter.setmList(mList);
            }
        });


    }

    //初始化控件
    private void initView() {

        //初始化数据库
        myDatabase = MyDatabase.getInstance(this);

        addBtn = findViewById(R.id.addBtn);
        delBtn = findViewById(R.id.delBtn);
        updateBtn = findViewById(R.id.updateBtn);
        queryIdBtn = findViewById(R.id.queryIdBtn);
        queryBtn = findViewById(R.id.queryBtn);

        delId = findViewById(R.id.delId);
        updateId = findViewById(R.id.updateId);
        updateName = findViewById(R.id.updateName);
        queryId = findViewById(R.id.queryId);

        //默认有一条数据防止报错
        mList.add(new Student("xiaoxin","20"));
        mListView = findViewById(R.id.listView);
        adapter = new ListAdapter(this,mList);
        mListView.setAdapter(adapter);

        //添加点击事件
        addBtn.setOnClickListener(this);
        delBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        queryIdBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //添加学生信息
            case R.id.addBtn:
                addStudent();
                break;
            //删除学生信息
            case R.id.delBtn:
                delStudent();
                break;
            //修改学生信息
            case R.id.updateBtn:
                updateStudent();
                break;
            //根据ID查询学旬信息
            case R.id.queryIdBtn:
                queryStudentById();
                break;
            //查询所有学生信息
            case R.id.queryBtn:
                queryStudentList();
                break;
        }
    }

    //根据ID查询好友
    private void queryStudentById() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String id = queryId.getText().toString().trim();
                if(TextUtils.isEmpty(id)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RoomActivity.this,
                                    "请输入查询id",Toast.LENGTH_SHORT).show();
                        }
                    });

                    return;
                }
                Student student = myDatabase.studentDao().getStudentById(Integer.valueOf(id));
                //查询之后，通知Adapter进行更新
                //如果之前的mList中有数据，先清空，毕竟我们只显示通过id查询到的数据
                if(mList.size()>0){
                    mList.clear();
                }
                mList.add(student);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //通知Adapter刷新
                        adapter.setmList(mList);
                    }
                });
            }
        }).start();
    }

    //修改学生
    private void updateStudent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String id = updateId.getText().toString().trim();
                String name = updateName.getText().toString().trim();
                if(TextUtils.isEmpty(id)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RoomActivity.this,
                                    "请输入修改id",Toast.LENGTH_SHORT).show();
                        }
                    });

                    return;
                }
                if(TextUtils.isEmpty(name)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RoomActivity.this,
                                    "请输入修改name",Toast.LENGTH_SHORT).show();
                        }
                    });

                    return;
                }

                myDatabase.studentDao().updateStudent(new Student(Integer.valueOf(id),name,20+""));
                //删除之后，在对数据进行查询一次
               // queryStudentList();
            }
        }).start();
    }

    //删除学生
    private void delStudent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String id = delId.getText().toString().trim();
                if(TextUtils.isEmpty(id)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RoomActivity.this,
                                    "请输入删除id",Toast.LENGTH_SHORT).show();
                        }
                    });

                    return;
                }

                myDatabase.studentDao().deleteStudent(new Student(Integer.valueOf(id)));
                //删除之后，在对数据进行查询一次
               // queryStudentList();
            }
        }).start();
    }

    //查询所有学生
    private void queryStudentList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mList = myDatabase.studentDao().getStudentList();
                //Adapter的更新需到主线程进行更新
                //用Handler发送到主线程进行更新
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //通知Adapter刷新
                        adapter.setmList(mList);
                    }
                });
            }
        }).start();
    }

    //添加学生信息
    private void addStudent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用count,为了区分添加的学生重复
                myDatabase.studentDao().insertStudent(new Student("xiaoxin"+count,20+count+""));
                count++;
               // queryStudentList();
            }
        }).start();
    }
}














/*
问题描述： 内置数据库升级，定义的对象和表结构的定义存在问题(不一致)
          定义的Student 实体类也就是表结构，必须与数据库认为的表结构一致，否则就会报下面的错误。
01-30 08:42:48.162 4416-4526/com.bool.jetpackmvvm E/AndroidRuntime: FATAL EXCEPTION: Thread-350
        Process: com.bool.jetpackmvvm, PID: 4416
        java.lang.IllegalStateException:
        Migration didn't properly handle: student(com.bool.jetpackmvvm.room.Student).
        Expected: 预期
        TableInfo{name='student',
         columns ={sex=Column{name='sex', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'},
        name=Column{name='name', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'},
        id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='null'},
        age=Column{name='age', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}
        Found:    发现
        TableInfo{name='student',
        columns={sex=Column{name='sex', type='Integer', affinity='3', notNull=false, primaryKeyPosition=0, defaultValue='null'},
        name=Column{name='name', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'},
        id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='null'},
        age=Column{name='age', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}

        at androidx.room.RoomOpenHelper.onUpgrade(RoomOpenHelper.java:103)
        at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.onUpgrade(FrameworkSQLiteOpenHelper.java:177)
        at android.database.sqlite.SQLiteOpenHelper.getDatabaseLocked(SQLiteOpenHelper.java:256)
        at android.database.sqlite.SQLiteOpenHelper.getWritableDatabase(SQLiteOpenHelper.java:163)
        at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getWritableSupportDatabase(FrameworkSQLiteOpenHelper.java:145)
        at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.getWritableDatabase(FrameworkSQLiteOpenHelper.java:106)
        at androidx.room.RoomDatabase.inTransaction(RoomDatabase.java:476)
        at androidx.room.RoomDatabase.assertNotSuspendingTransaction(RoomDatabase.java:281)
        at com.bool.jetpackmvvm.room.StudentDao_Impl.getStudentList(StudentDao_Impl.java:130)
        at com.bool.jetpackmvvm.room.RoomActivity$4.run(RoomActivity.java:239)
        at java.lang.Thread.run(Thread.java:818)*/
