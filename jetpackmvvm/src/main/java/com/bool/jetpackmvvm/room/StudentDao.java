package com.bool.jetpackmvvm.room;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.bool.jetpackmvvm.room.bean.Book;
import com.bool.jetpackmvvm.room.bean.NameTuple;
import com.bool.jetpackmvvm.room.bean.PlaylistWithSongs;
import com.bool.jetpackmvvm.room.bean.SongWithPlaylists;
import com.bool.jetpackmvvm.room.bean.User;
import com.bool.jetpackmvvm.room.bean.UserAndLibrary;
import com.bool.jetpackmvvm.room.bean.UserWithPlaylists;
import com.bool.jetpackmvvm.room.bean.UserWithPlaylistsAndSongs;

import java.util.List;

@Dao
public interface StudentDao {

    //添加学生信息
    @Insert
    void insertStudent(Student student);

    //删除学生
    @Delete
    void deleteStudent(Student student);

    //修改学生
    @Update
    void updateStudent(Student student);

    //查询所有学生
    @Query("SELECT * FROM student")
    List<Student> getStudentList();


    //查询所有学生
    //使用LiveData将List<Student>包装起来
    @Query("SELECT * FROM student")
    LiveData<List<Student>> getLiveStudentList();

    //查询某个学生
    @Query("SELECT * FROM student WHERE id = :id")
    Student getStudentById(int id);

    //向 DAO 类添加一个方法，用于返回将父实体与子实体配对的数据类的所有实例。
    // 该方法需要 Room 运行两次查询，因此应向该方法添加 @Transaction 注释，
    // 以确保整个操作以原子方式执行
    @Transaction
    @Query("SELECT * FROM User")
    public List<UserAndLibrary> getUsersAndLibraries();

    // 同上
    @Transaction
    @Query("SELECT * FROM User")
    public List<UserWithPlaylists> getUsersWithPlaylists();

    @Transaction
    //@Query("SELECT * FROM Playlist")
    public List<PlaylistWithSongs> getPlaylistsWithSongs();

    @Transaction
    //@Query("SELECT * FROM Song")
    public List<SongWithPlaylists> getSongsWithPlaylists();

    @Transaction
    @Query("SELECT * FROM User")
    public List<UserWithPlaylistsAndSongs> getUsersWithPlaylistsAndSongs();

    // 支持将参数传递给查询
    //Room 会将 :minAge 绑定参数与 minAge 方法参数进行匹配。
    // Room 通过参数名称进行匹配。如果有不匹配的情况，则应用编译时会出现错误
    @Dao
    public interface MyDao {
        @Query("SELECT * FROM user WHERE age > :minAge")
        public User[] loadAllUsersOlderThan(int minAge);
    }

    //还可以在查询中传递多个参数或多次引用这些参数
    @Query("SELECT * FROM user WHERE first_name LIKE :search " +
            "OR last_name LIKE :search")
    public List<User> findUserWithName(String search);

    //返回列的子集
    @Query("SELECT first_name, last_name FROM user")
    public List<NameTuple> loadFullName();

    //部分查询可能要求您传入数量不定的参数，参数的确切数量要到运行时才知道。
    // 例如，您可能希望从部分区域中检索所有用户的相关信息。
    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
    public List<NameTuple> loadUsersFromRegions(List<String> regions);


    //直接光标访问
    //如果应用的逻辑要求直接访问返回行，您可以从查询中返回 Cursor 对象
    @Query("SELECT * FROM user WHERE age > :minAge LIMIT 5")
    public Cursor loadRawUsersOlderThan(int minAge);

    //如何执行表格联接以整合以下两个表格的信息：
    // 一个表格包含当前借阅图书的用户，
    // 另一个表格包含当前处于已被借阅状态的图书的数据。
    @Query("SELECT * FROM book " +
            "INNER JOIN loan ON loan.book_id = book.id " +
            "INNER JOIN user ON user.userId = loan.user_id " +
            "WHERE user.first_name LIKE :userName")
    public List<Book> findBooksBorrowedByNameSync(String userName);
}
