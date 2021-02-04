package com.bool.jetpackmvvm.navigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bool.jetpackmvvm.R;

/**
 *
 * 参考 https://blog.csdn.net/qq_43404873/article/details/109506512
 *
 *   1. 嵌套导航图
 *      在程序中，对于对于多次复用界面，可以用新建一个navigation，
 *          在父布局(navigation)中通过include引入
 *
 *   2. 通用操作（全局操作）
 *      您可以使用全局操作来创建可由多个目的地(界面)共用的通用操作。
 *      例如,你可能想要不同目的地中的多个按钮(返回键)导航到同一应用主屏幕
 *
 *      作用： 无论你在哪个Fragment, 使用全局资源ID，都可以跳转到mainFragment
 *
 *   3. 遗留
 *      以下待学习实践
 *       Toolbar
 *       CollapsingToolbarLayout
 *       App bar 左侧的抽屉菜单(DrawLayout+NavigationView)
 *       底部菜单(BottomNavigation).
 *     
 *
 *
 */


public class NavHostActivity extends AppCompatActivity {

    //AppBarConfiguration用于App bar的配置，
    // NavController用于页面的导航和切换
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_host);

        //获取navController,通过MainActivity空白的NavHostFragment获取
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        //将导航图和AppBarConfiguration关联起来
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //在某些情况下，如果想指定多个顶级目的地。对于这样的情况，您可以改为将一组目的地 ID 传递给构造函数
        //appBarConfiguration = new AppBarConfiguration.Builder(R.id.mainFragment,R.id.settingFragment).build();
        //将AppBarConfiguration和NavController绑定起来
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);


        // 页面切换监听
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//                Log.i("true",controller.toString());
                Log.e("true",destination.getNavigatorName()+destination.getId());
                if(destination.getId() == R.id.leftFragment) {
                   // toolbar.setVisibility(View.GONE);
                   // bottomNavigationView.setVisibility(View.GONE);
                } else {
                   // toolbar.setVisibility(View.VISIBLE);
                   // bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    /**
     * 现在有了 NavigationUI组件，可以自动帮我们处理好跳转逻辑
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item,navController) ||
                super.onOptionsItemSelected(item);
    }

    /**
     * 处理返回按钮
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration) ||
                super.onSupportNavigateUp();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings,menu);
        return true;
    }

}