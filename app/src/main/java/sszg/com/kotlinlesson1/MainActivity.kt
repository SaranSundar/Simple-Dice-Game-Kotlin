package sszg.com.kotlinlesson1

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        diceImageView.setBackgroundResource(R.drawable.dice_roll_animation)
        val diceRollAnimation = diceImageView.background as AnimationDrawable
        val random = Random()

        rollDiceButton.setOnClickListener {
            "Rolling Dice".toast(applicationContext)
            diceRollAnimation.start()
            rollDiceButton.isEnabled = false
            val waitTime = random.nextInt(3000) + 1000L

            startCountdownTimer(waitTime,waitTime){
                diceRollAnimation.stop()
                rollDiceButton.isEnabled = true
                for (i in 0..diceRollAnimation.numberOfFrames) {
                    if (diceRollAnimation.getFrame(i) == diceRollAnimation.current) {
                        //"You rolled a ${i+1}".toast(applicationContext)
                        alert("${i + 1}") {
                            title = "You rolled a..."
                            isCancelable = false
                            positiveButton("Ok") {}
                        }.show()
                        break
                    }
                }
            }
        }
    }

    private fun startCountdownTimer(duration: Long, interval: Long, onFinish: () -> Unit){
        object : CountDownTimer(duration, interval) {
            override fun onFinish() {
                onFinish()
            }

            override fun onTick(millisUntilFinished: Long) {
                //To change body of created functions use File | Settings | File Templates.
            }

        }.start()
    }

    private fun Any.toast(context: Context) {
        Toast.makeText(context, this.toString(), Toast.LENGTH_LONG).show()
    }
}
