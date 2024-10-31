package android.app.student_maneger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup

class FilterNumber : AppCompatActivity() {

    lateinit var edtNum : EditText
    lateinit var listNum: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_number)

        edtNum = findViewById(R.id.edtNum2)
        listNum = findViewById(R.id.listNum2)

        var listNumber = mutableListOf<Int>()
        var adapterNUm = ArrayAdapter(this,android.R.layout.simple_list_item_1,listNumber)
        listNum.adapter = adapterNUm

        edtNum.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(edtNum.text.toString().isEmpty()){
                    edtNum.setText("0")
                    return
                }
                listNumber.clear()
                val radGR = findViewById<RadioGroup>(R.id.radGroup)
                if(radGR.checkedRadioButtonId == R.id.radChan){
                    for(i in 1..edtNum.text.toString().toInt()){
                        if(i%2==0){
                            listNumber.add(i)
                        }
                    }
                    adapterNUm.notifyDataSetChanged()
                }else if(radGR.checkedRadioButtonId == R.id.radLe){
                    for(i in 1..edtNum.text.toString().toInt()){
                        if(i%2!=0){
                            listNumber.add(i)
                        }
                    }
                    adapterNUm.notifyDataSetChanged()
                }else if(radGR.checkedRadioButtonId == R.id.radCP){
                    for(i in 1..edtNum.text.toString().toInt()){
                        if((i*i)<=edtNum.text.toString().toInt()){
                            listNumber.add(i*i)
                        }
                    }
                    adapterNUm.notifyDataSetChanged()
                }else{
                    return
                }

            }
        })


    }
}