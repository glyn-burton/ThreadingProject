package com.example.threadingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.concurrent.thread
import kotlin.math.PI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        threadinPI()
        val asynchie = AsyncTaskClass()
        asynchie.execute()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister((this))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAsyncTaskEvent(event : AsyncTaskEvent){
        arrayOutput.text = event.resultMessage

    }

    fun threadinPI (){

        var pi = PI.toString()
        var twelfthpie : String = ""

        val thread = Thread(Runnable {
            // Account for the 3 and the decimal point
            for(i in 0 until 14)
            {
                twelfthpie += pi[i]


            }

            runOnUiThread{tvPiOutput.text = twelfthpie}

        })
        thread.start()



    }
}
