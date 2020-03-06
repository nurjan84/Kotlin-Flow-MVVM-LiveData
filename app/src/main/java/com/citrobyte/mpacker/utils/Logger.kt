package com.citrobyte.mpacker.utils

import android.util.Log
import com.citrobyte.mpacker.BuildConfig

class Logger {

    companion object {
        fun i(name: String, msg: String) {
            if (BuildConfig.SHOW_LOG) {
                Log.d(name, msg)
            }
        }

        fun i(s: String) {
            if (BuildConfig.SHOW_LOG) {
                val maxLogSize = 1000
                for (i in 0..s.length / maxLogSize) {
                    val start = i * maxLogSize
                    var end = (i + 1) * maxLogSize
                    end = if (end > s.length) s.length else end
                    Log.d("---------------Mine: ", s.substring(start, end))
                }
            }
        }
    }

}