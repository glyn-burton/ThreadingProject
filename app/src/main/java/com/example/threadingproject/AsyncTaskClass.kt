package com.example.threadingproject

import android.os.AsyncTask
import android.util.Log
import org.greenrobot.eventbus.EventBus
import kotlin.random.Random

class AsyncTaskClass: AsyncTask<String, String, String>() {

    val largeArray = IntArray(1000)
    var arraySorted :String = ""
    override fun doInBackground(vararg params: String?): String {

        for (i in 0 until largeArray.size){

            largeArray[i] = Random.nextInt(0,200)
        }
        largeArray.sort()
        for (i in 0 until largeArray.size){

            arraySorted += largeArray[i].toString() + ", "

        }

        return arraySorted
    }


    override fun onPreExecute(){
        super.onPreExecute()

    }

    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)

    }

    //Runs on Main Thread
    //Reports the results of the task
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        EventBus.getDefault().post(AsyncTaskEvent(result?:"No Result"))
    }
}