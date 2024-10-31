package android.app.student_maneger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView

class DanhSachActivity : AppCompatActivity() {

    lateinit var customeAdapter: CustomeAdapter
    lateinit var searchEdt: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach)

        val listViewStudent = findViewById<ListView>(R.id.listSinhVien)
        searchEdt = findViewById(R.id.edtSearch)


        var listStudent = mutableListOf<Student>()
        listStudent.add(Student("Nguyen Van A", "123456"))
        listStudent.add(Student("Tran Thi B","654321"))
        listStudent.add(Student("Le Van C","112233"))
        listStudent.add(Student("Pham Minh D", "223344"))
        listStudent.add(Student("Nguyen Thi E","334455"))
        listStudent.add(Student("Hoang Van F","445566"))
        listStudent.add(Student("Bui Thi G", "556677"))
        listStudent.add(Student("Vu Van H","667788"))
        listStudent.add(Student("Ngo Thi I", "778899"))
        listStudent.add(Student("Trinh Van J", "889900"))
        listStudent.add(Student("Pham Van K", "990011"))
        listStudent.add(Student("Tran Thi L", "101112"))
        listStudent.add(Student("Le Van M", "121314"))
        listStudent.add(Student("Nguyen Thi N", "141516"))
        listStudent.add(Student("Do Van O", "161718"))
        listStudent.add(Student("Ho Thi P", "181920"))
        listStudent.add(Student("Vo Van Q", "202122"))
        listStudent.add(Student("Tran Thi R", "222324"))
        listStudent.add(Student("Nguyen Van S", "242526"))
        listStudent.add(Student("Doan Thi T", "262728"))
        listStudent.add(Student("Phan Van U", "282930"))
        listStudent.add(Student("Cao Thi V", "303132"))
        listStudent.add(Student("Hoang Van W", "323334"))
        listStudent.add(Student("Truong Thi X", "343536"))
        listStudent.add(Student("Pham Van Y", "363738"))
        listStudent.add(Student("Ngo Thi Z", "383940"))
        listStudent.add(Student("Nguyen Van AA", "404142"))
        listStudent.add(Student("Le Thi BB", "424344"))
        listStudent.add(Student("Bui Van CC", "444546"))
        listStudent.add(Student("Vu Thi DD", "464748"))

        customeAdapter = CustomeAdapter(this, listStudent)
        listViewStudent.adapter = customeAdapter


        searchEdt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun afterTextChanged(p0: Editable?) {
                customeAdapter.clear()
                customeAdapter.filter.filter(p0.toString())
            }

        })
    }
}