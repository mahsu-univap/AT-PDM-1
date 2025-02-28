package com.example.aula01
import android.os.Build
import android . os . Bundle
import android . os . CountDownTimer
import android . os . VibrationEffect
import android . os . Vibrator
import android . widget . Button
import android . widget . EditText
import android . widget . TextView
import androidx.annotation.RequiresApi
import androidx . appcompat . app . AppCompatActivity

class MainActivity : AppCompatActivity () {
    private lateinit var timerText : TextView
    private lateinit var timeInput : EditText
    private lateinit var startButton : Button
    private var countDownTimer : CountDownTimer ? = null
    override fun onCreate ( savedInstanceState : Bundle ?) {
        super . onCreate ( savedInstanceState )
        setContentView (R. layout . activity_main )
        timerText = findViewById (R.id.timerText )
        timeInput = findViewById (R.id.timeInput )
        startButton = findViewById (R.id.startButton )
        startButton . setOnClickListener {
            val timeInMinutes = timeInput . text . toString (). toIntOrNull ()
            if ( timeInMinutes != null && timeInMinutes > 0) {
                startTimer ( timeInMinutes * 60 * 1000L )
            }
        }
    }
    private fun startTimer ( timeInMillis : Long ) {
        countDownTimer ?. cancel ()
        countDownTimer = object : CountDownTimer ( timeInMillis , 1000) {
            override fun onTick ( millisUntilFinished : Long ) {
                val minutes = millisUntilFinished / 60000
                val seconds = ( millisUntilFinished % 60000) / 1000
                timerText . text = String . format (" %02 d :%02 d", minutes , seconds )
            }



            @RequiresApi(Build.VERSION_CODES.O)
            override fun onFinish () {
                timerText . text = " 00:00 "
                vibratePhone ()
            }
        }. start ()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun vibratePhone () {
        val vibrator = getSystemService ( VIBRATOR_SERVICE ) as Vibrator
        val vibrationEffect = VibrationEffect.createOneShot (1000 , VibrationEffect.DEFAULT_AMPLITUDE )
        vibrator.vibrate ( vibrationEffect )
    }
}

