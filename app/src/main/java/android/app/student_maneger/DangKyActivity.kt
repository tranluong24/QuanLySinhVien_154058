package android.app.student_maneger

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Locale

class DangKyActivity : AppCompatActivity() {

    private lateinit var edtDate : EditText

    private lateinit var spiTinh : Spinner
    private lateinit var spPhuong : Spinner
    private lateinit var spHuyen : Spinner
    private lateinit var signupBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky)

        val addressHelper = AddressHelper(resources)

        edtDate = findViewById(R.id.edtDate)

        signupBtn = findViewById(R.id.btnSignUp)
        spiTinh = findViewById(R.id.spinTinh)
        spHuyen = findViewById(R.id.spHuyen)
        spPhuong = findViewById(R.id.spPhuong)


        //Khoi tao cac spinner Dia chi
        val listProvider = addressHelper.getProvinces()
        var listHuyen : List<String> = ArrayList<String>()

        val adapterTinh = ArrayAdapter(this,android.R.layout.simple_spinner_item, listProvider)
        spiTinh.adapter = adapterTinh

        spiTinh.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listHuyen = addressHelper.getDistricts(listProvider[p2])
                val adapterHuyen = ArrayAdapter(this@DangKyActivity,android.R.layout.simple_spinner_item, listHuyen)
                spHuyen.adapter = adapterHuyen
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spHuyen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val listPhuong = addressHelper.getWards(listProvider[spiTinh.selectedItemPosition],listHuyen[p2])
                val adapterPhuong = ArrayAdapter(this@DangKyActivity,android.R.layout.simple_spinner_item, listPhuong)
                spPhuong.adapter = adapterPhuong
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        //-------------------------END khoi tao spinner----------------


        edtDate.setOnClickListener{
            showDatePickerDialog()
        }

        signupBtn.setOnClickListener{
            val edtName = findViewById<EditText>(R.id.edtName)
            val edtMssv = findViewById<EditText>(R.id.edtMSSV)
            val edtEmail = findViewById<EditText>(R.id.edtEmail)
            val edtSdt = findViewById<EditText>(R.id.edtSDT)
            val edtDate = findViewById<EditText>(R.id.edtDate)
            if(edtName.text.toString().isBlank()||
                edtMssv.text.toString().isBlank()||
                edtEmail.text.toString().isBlank()||
                edtSdt.text.toString().isBlank()||
                edtDate.text.toString().isBlank()){

                showAlert("Vui lòng điền đầy đủ thông tin")
                return@setOnClickListener
            }

            val radNam = findViewById<RadioButton>(R.id.radioNam)
            val radNu = findViewById<RadioButton>(R.id.radioNu)
            if(!radNam.isChecked && !radNu.isChecked){
                showAlert("Vui lòng điền đầy đủ thông tin")
                return@setOnClickListener
            }

            val checkTT = findViewById<CheckBox>(R.id.checkTheThao)
            val checkAN = findViewById<CheckBox>(R.id.checkAmNhac)
            val checkDA = findViewById<CheckBox>(R.id.checkDienAnh)
            var listHobby = ArrayList<String>()
            if(checkTT.isChecked){
                listHobby.add(checkTT.text.toString())
            }

            if(checkAN.isChecked){
                listHobby.add(checkAN.text.toString())
            }

            if(checkDA.isChecked){
                listHobby.add(checkDA.text.toString())
            }

            spiTinh.selectedItem

            val checkDieuKhoan = findViewById<CheckBox>(R.id.checkDieuKhoan)
            if(!checkDieuKhoan.isChecked){
                showAlert("Vui lòng đồng ý với các điều khoản đăng ký !")
                return@setOnClickListener
            }
            Toast.makeText(this, "Dang ky thanh cong !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

//            val student = Student(edtName.text.toString(), edtMssv.text.toString(),
//                edtEmail.text.toString(), edtSdt.text.toString(),
//                edtDate.text.toString(), true,spiTinh.selectedItem as String,
//                spiTinh.selectedItem as String,spiTinh.selectedItem as String,
//                listHobby)


        }

    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Định dạng ngày và hiển thị lên EditText
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                edtDate.setText(dateFormat.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    fun showAlert(msg: String) {
        // Tạo Builder cho AlertDialog
        val builder = AlertDialog.Builder(this)

        // Thiết lập tiêu đề và thông báo cho AlertDialog
        builder.setTitle("Thông báo")
        builder.setMessage(msg)

        // Thiết lập nút hủy bỏ (Cancel)
        builder.setNegativeButton("Cancel") { dialog, _ ->
            // Hành động khi nhấn nút Cancel
            dialog.dismiss() // Đóng AlertDialog
        }

        // Hiển thị AlertDialog
        val dialog = builder.create()
        dialog.show()
    }
}