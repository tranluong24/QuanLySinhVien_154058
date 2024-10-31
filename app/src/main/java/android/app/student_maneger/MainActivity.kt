package android.app.student_maneger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSignUp = findViewById<Button>(R.id.btnSignIn)
        val btnList = findViewById<Button>(R.id.btnList)
        val btnBT = findViewById<Button>(R.id.btnBT1)

        btnSignUp.setOnClickListener{
            val intent = Intent(this, DangKyActivity::class.java)
            startActivity(intent)
        }

        btnList.setOnClickListener{
            val intent = Intent(this, DanhSachActivity::class.java)
            startActivity(intent)
        }

        btnBT.setOnClickListener{
            val intent = Intent(this, FilterNumber::class.java)
            startActivity(intent)
        }


    }
}