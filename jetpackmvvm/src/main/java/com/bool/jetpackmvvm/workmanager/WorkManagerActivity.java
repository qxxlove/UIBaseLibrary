package com.bool.jetpackmvvm.workmanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.bool.jetpackmvvm.R;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 * 使用场景：
 *    1. 针对的是不需要及时完成的任务
 *       例如: 发送应用程序日志、同步应用程序数据、备份用户数据等
 *    2. WorkManager能保证任务一定会被执行，即使应用程序当前不在运行中，
 *      甚至在设备重启过后，任务仍然会在适当的时刻被执行。
 *      这是因为WorkManager有自己的数据库，关于任务的所有信息和数据都保存在该数据库中。
 *      因此，只要任务交给了WorkManager，哪怕应用程序彻底退出，或者设备被重新启动，
 *      WorkManager依然能够保证完成你交给它的任务
 *
 *    3. WorkManager能依据设备的情况，选择不同的执行方案。在API Level 23以上的设备中，
 *       通过JobScheduler完成任务；
 *       在API Level 23以下的设备中，通过AlarmManager和Broadccast Receivers组合来完成任务。
 *       但无论采用哪种方案，任务最终都是交由Executor来执行。
 *
 *    4. 设置tag标签后，我们就可以根据该标签追踪任务的状态,也可以用来取消任务
 *       例如，WorkManager.cancelAllWorkByTag(String) 会取消带有特定标记的所有工作请求，
 *
 *    5. 调度分为：
 *         一次性工作的状态；(一次)
 *         定期工作的状态；  (无限循环)
 *
 *     6. 唯一性
 *       可确保同一时刻只有一个具体特定名称的工作实例.
 *       与 ID   不同的是，唯一名称是人类可读的，由开发者指定，而不是由 WorkManager 自动生成。
 *       与 标记 不同，唯一名称仅与一个工作实例相关联。
 *            WorkManager.enqueueUniqueWork()（用于一次性工作）
 *            WorkManager.enqueueUniquePeriodicWork()（用于定期工作）
 *
 *
 *
 *    注意：
 *    WorkManager不是一种新的工作线程，它的出现不是为了替代其他类型的工作线程。
 *    工作线程通常立即运行，并在任务执行完成后给用户反馈。而WorkManager不是即时的，它不能保证任务能立即得到执行
 *
 *
 */
public class WorkManagerActivity extends AppCompatActivity {

    private  WorkRequest uploadWorkRequest;
    private  UUID id ;
    private TextView tvTtansmission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);
        tvTtansmission = findViewById(R.id.tv_work_transmission);

        initWork();
        initLClick();



    }

    private void initLClick() {

        findViewById(R.id.tv_start_work).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将任务配置好之后，需要将其提交给系统，WorkManager.enqueue()方法用于将配置好的WorkRequest交给系统来执行
                WorkManager.getInstance(WorkManagerActivity.this).enqueue(uploadWorkRequest);
            }
        });


        findViewById(R.id.tv_end_work).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** 根据tag 取消任务
                 *  WorkManager.getWorkInfosByTag(String) 会返回一个 WorkInfo 对象列表，该列表可用于确定当前工作状态。
                 * */
                Operation uploadTag = WorkManager.getInstance(WorkManagerActivity.this).cancelAllWorkByTag("UploadTag");
                LiveData<Operation.State> state = uploadTag.getState();
                //取消所有任务
               // WorkManager.getInstance(WorkManagerActivity.this).cancelAllWork();
                //根据ID取消任务
               // WorkManager.getInstance(WorkManagerActivity.this).cancelWorkById(id);
            }
        });

        //任务在提交给系统后，可以通过WorkInfo获取任务的状态。
        // WorkInfo包含任务的id、tag、Worker对象传递过来的outputData,以及任务当前的状态。
        //SUCCEEDED、FAILED 和 CANCELLED 均表示此工作的终止状态。如果您的工作处于上述任何状态，
        // WorkInfo.State.isFinished() 都将返回 true。
        //有三种方式可以得到WorkInfo对象
        findViewById(R.id.tv_work_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //根据WorkRequest设置的Tag标签
                //WorkManager.getInstance(WorkManagerActivity.this).getWorkInfosByTag("");
                // 根据Id获取
                //WorkManager.getInstance(WorkManagerActivity.this).getWorkInfoById(id);
               // WorkManager.getInstance(WorkManagerActivity.this).getWorkInfosForUniqueWork("");

                //如果希望实时获知任务的状态，可以获取对应的LiveData方法
               // WorkManager.getInstance(WorkManagerActivity.this).getWorkInfosByTagLiveData("");
                WorkManager.getInstance(WorkManagerActivity.this).getWorkInfoByIdLiveData(id)
                        .observe(WorkManagerActivity.this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        workInfo.getId();
                        workInfo.getState();
                        workInfo.getProgress();
                        //WorkManager通过LiveData得到从Worker返回的数据
                        if(workInfo!=null && workInfo.getState() == WorkInfo.State.SUCCEEDED){
                            String outputData = workInfo.getOutputData().getString("out_put_data");
                            tvTtansmission.setText(outputData);
                        }

                        if (workInfo == null) {
                            return;
                        }
                        if (workInfo.getState() == WorkInfo.State.ENQUEUED) {
                            tvTtansmission.setText("任务入队");
                        }
                        if (workInfo.getState() == WorkInfo.State.RUNNING) {
                            tvTtansmission.setText("任务正在执行");
                        }
                        if (workInfo.getState() == WorkInfo.State.CANCELLED) {
                            tvTtansmission.setText("任务已经被取消了");
                        }
                        if (workInfo.getState() == WorkInfo.State.FAILED ) {
                            tvTtansmission.setText("任务执行失败了");
                        }
                    }
                });
                //WorkManager.getInstance(WorkManagerActivity.this).getWorkInfosForUniqueWorkLiveData("");
            }
        });


        /**
         * 工作唯一性
         * enqueueUniquePeriodicWork 方法：
         *     uniqueWorkName - 用于唯一标识工作请求的 String。
         *     existingWorkPolicy - 此 enum 可告知 WorkManager 如果已有使用该名称且尚未完成的唯一工作链，应执行什么操作。
         *     work - 要调度的 WorkRequest。
         */
        findViewById(R.id.tv_work_only).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PeriodicWorkRequest sendLogsWorkRequest = new
                        PeriodicWorkRequest.Builder(null, 24, TimeUnit.HOURS)
                        .setConstraints(new Constraints.Builder()
                                .setRequiresCharging(true)
                                .build()
                        )
                        .build();
//                REPLACE:用新工作替换现有工作。
//                KEEP：保留现有工作，并忽略新工作
//                APPEND: 将新工作附加到现有工作的末尾。
//                现有工作将成为新工作的先决条件。如果现有工作变为 CANCELLED 或 FAILED 状态，新工作也会变为 CANCELLED 或 FAILED如果您希望无论现有工作的状态如何都运行新工作，请改用 APPEND_OR_REPLACE。
//                APPEND_OR_REPLACE:即使现有工作变为 CANCELLED 或 FAILED 状态，新工作仍会运行。
                WorkManager.getInstance(WorkManagerActivity.this).enqueueUniquePeriodicWork(
                        "sendLogs",
                        ExistingPeriodicWorkPolicy.KEEP,
                        sendLogsWorkRequest);
            }
        });

    }


    /**
     *  WorkRequest有两种实现方式:
     *     OneTimeWorkRequest和PeriodicWorkRequest，分别对应一次性任务和周期性任务。
     *     1. 一次性任务在任务成功执行后，便彻底结束。
     *     2.  周期性任务则会按照设定的时间定期执行。二者使用没有太大差别，
     *         需要注意的是，周期性任务的间隔时间不能少于15分钟
     */
    private void initWork() {
        //调度一次任务(无需任何配置)
        WorkRequest myWorkRequest = OneTimeWorkRequest.from(UploadLogWorker.class);

        //设置任务触发条件(工作约束)
//        BatteryNotLow	如果设置为 true，那么当设备处于“电量不足模式”时，工作不会运行。
//        RequiresCharging	如果设置为 true，那么工作只能在设备充电时运行。
//        DeviceIdle	如果设置为 true，则要求用户的设备必须处于空闲状态，才能运行工作。如果您要运行批量操作，否则可能会降低用户设备上正在积极运行的其他应用的性能，建议您使用此约束。
//        StorageNotLow	如果设置为 true，那么当用户设备上的存储空间不足时，工作不会运行。
        //例如: 我们可以设置当设备处于充电、网络已连接，且电池电量充足的状态下，才触发任务
        Constraints constraints = new Constraints.Builder()
                // .setRequiresChargint(true)
                //.setRequiredNetWorkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build();

        //WorkRequest可以通过setInputData()方法向Worker传递数据
        //Data只能用于传递一些小的基本类型的数据，且数据大小不能超过10KB
        Data inputData = new Data.Builder().putString("input_data","Hello World").build();


        //假如Worker线程的执行出现了异常（比如任务执行失败）,你希望一段时间后，重试该任务。
        // 那么你可以在Worker的doWork()方法中返回Result.retry(),
        // 系统会有默认的指数退避策略来帮你重试任务，
        // 也可以通过setBackoffCriteria()方法来自定义指数退避策略
        uploadWorkRequest =
                new OneTimeWorkRequest.Builder(UploadLogWorker.class)
                        // 配置任务
                        //设置触发条件
                        .setConstraints(constraints)
                        //任务 延时10s执行
                        .setInitialDelay(10, TimeUnit.SECONDS)
                        //设置指数退避算法
                        .setBackoffCriteria(BackoffPolicy.LINEAR,
                                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                                TimeUnit.MILLISECONDS)
                        .addTag("UploadTag")
                        .setInputData(inputData)
                        .build();


        @SuppressLint("RestrictedApi")
        Set<String> tags = uploadWorkRequest.getTags();
        id = uploadWorkRequest.getId();

        /**周期性请求*/
        PeriodicWorkRequest saveRequest =
                new PeriodicWorkRequest.Builder(null, 1, TimeUnit.HOURS)
                        //添加配置
                        .build();


        /**链式工作*/
       // 如果有一系列的任务需要按顺序执行，那么可以利用WorkManager.beginWith().then().then()…enqueue()的方式构建任务链。
        // 例如,在上传数据之前，可能需要先对数据进行压缩。
        /*WorkManager.getInstance(this)
                .beginWith()
                .then()
                .enqueue();*/

        /**组合任务*/
        //假设有更复杂的任务链，那么可以考虑使用WorkContinuation.combine()方法
//        OneTimeWorkRequest requestA = new OneTimeWorkRequest.Builder(ConbineWorkerA.class).build();
//        OneTimeWorkRequest requestB = new OneTimeWorkRequest.Builder(ConbineWorkerB.class).build();
//        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(ConbineWorkerC.class).build();
//        OneTimeWorkRequest requestD = new OneTimeWorkRequest.Builder(ConbineWorkerD.class).build();
//        OneTimeWorkRequest requestE = new OneTimeWorkRequest.Builder(ConbineWorkerE.class).build();
//         //A,B任务链
//        WorkContinuation continuation1 = WorkManager.getInstance().beginWith(requestA).then(requestB);
//        //C,D任务链
//        WorkContinuation continuation2 = WorkManager.getInstance().beginWith(requestC).then(requestD);
//        //合并上面两个任务链，在接入requestE任务，入队执行
//        WorkContinuation.combine(continuation1, continuation2).then(requestE).enqueue();

        /**work默认数据流 之间传递参数:只需要在各个work内定义好inputData和getInputData*/
        //在任务链中，比如我们有这样的需求，每个任务的数据相互依赖，下一个任务需要依赖上一个任务的输出。其实WorkManager会自动将上一个任务的输出流自动作为下一个任务的输入。
//        OneTimeWorkRequest requestA = new OneTimeWorkRequest.Builder(A.class).build();
//        OneTimeWorkRequest requestB = new OneTimeWorkRequest.Builder(B.class).build();
//        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(C.class).build();
//        WorkManager.getInstance().beginWith(requestA).then(requestB).then(requestC).enqueue();

        // 组合任务的数据流
//        WorkManager 提供两种不同类型的 InputMerger：
//        OverwritingInputMerger 会尝试将所有输入中的所有键添加到输出中。如果发生冲突，它会覆盖先前设置的键。
//        ArrayCreatingInputMerger 会尝试合并输入，并在必要时创建数组。
         //OverwritingInputMerger 是默认的合并方法。如果合并过程中没有键冲突，键的最新值将覆盖生成的输出数据中的所有先前版本。
         // ArrayCreatingInputMerger将每个键与数组配对。如果每个键都是唯一的，您会得到一系列一元数组。

      /*  OneTimeWorkRequest requestA = new OneTimeWorkRequest.Builder(A.class).build();
        OneTimeWorkRequest requestB = new OneTimeWorkRequest.Builder(B.class).build();
        // 设置合并规则ArrayCreatingInputMerger   C将会收一个数据 “akey”:[10,20]
        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(C.class)
                .setInputMerger(
                        ArrayCreatingInputMerger.class).build();

        //A任务链    发送putInt("akey", 10)
        WorkContinuation continuationA = WorkManager.getInstance().beginWith(requestA);
        //B任务链    发送 putInt("akey", 20)
        WorkContinuation continuationB = WorkManager.getInstance().beginWith(requestB);
        //合并上面两个任务链，在接入requestE任务，入队执行
        WorkContinuation continuation = WorkContinuation.combine(continuationA, continuationB).then(requestC).
                continuation.enqueue();*/
    }
}