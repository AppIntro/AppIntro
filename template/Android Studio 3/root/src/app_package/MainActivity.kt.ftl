package ${packageName}

import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ${mainActivityName} : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val t = Thread(Runnable {
            //  Initialize SharedPreferences
            val getPrefs = PreferenceManager
                    .getDefaultSharedPreferences(baseContext)

            //  Create a new boolean and preference and set it to true
            val isFirstStart = getPrefs.getBoolean("firstStart", true)

            //  If the activity has never started before...
            if (isFirstStart) {

                //  Launch app intro
                val i = Intent(this@${mainActivityName}, ${className}::class.java)
                startActivity(i)

                //  Make a new preferences editor
                val e = getPrefs.edit()

                //  Edit preference to make it false because we don't want this to run again
                e.putBoolean("firstStart", false)

                //  Apply changes
                e.apply()
            }
        })

        // Start the thread
        t.start()
    }
}