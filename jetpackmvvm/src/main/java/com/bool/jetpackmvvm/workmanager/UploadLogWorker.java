package com.bool.jetpackmvvm.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class UploadLogWorker extends Worker {

    public UploadLogWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    /**
     * 耗时的任务在doWork()方法中执行
     * @return 若执行成功，则返回Result.success()
     *         若执行失败，则返回Result.failure().
     *         若需要重新执行，则返回Result.retry();
     */
    @NonNull
    @Override
    public Result doWork() {
        //接收外界传递进行的数据
        String inputData = getInputData().getString("input_data");

        //任务执行完成后返回数据
        Data outputData = new Data.Builder().putString("out_put_data","Task Success").build();
        return Result.success(outputData);

    }
}
