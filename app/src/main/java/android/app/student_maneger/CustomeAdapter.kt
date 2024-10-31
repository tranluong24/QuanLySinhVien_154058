package android.app.student_maneger

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView

class CustomeAdapter(val activity: DanhSachActivity, val list: List<Student>): ArrayAdapter<Student>(activity, R.layout.list_item), Filterable {
    private var filteredList: MutableList<Student> = list.toMutableList()

    override fun getCount(): Int {
        return filteredList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contexts = activity.layoutInflater

        val rowView = contexts.inflate(R.layout.list_item, parent, false)

        val name = rowView.findViewById<TextView>(R.id.textName)
        val mssv = rowView.findViewById<TextView>(R.id.textMSSV)

        name.text = filteredList[position].name
        mssv.text = filteredList[position].MSSV

        return rowView
    }

    // Hàm để lọc danh sách
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val resultList = if (query.isNullOrEmpty()) {
                    list
                } else {
                    list.filter {
                        it.name.contains(query, ignoreCase = true) || it.MSSV.contains(query)
                    }
                }
                return FilterResults().apply {
                    values = resultList
                    Log.d("aaaa", resultList.size.toString())
                }
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredList = (p1?.values as? List<Student>)?.toMutableList() ?: mutableListOf()
                notifyDataSetChanged()
            }
        }
    }
}