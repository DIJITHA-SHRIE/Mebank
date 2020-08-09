package com.example.mebank.Activity.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mebank.Activity.Model.EmployeeListModel
import com.example.mebank.Activity.UI.EmployeeDetailsActivity
import com.example.mebank.databinding.EmployeelistadplayBinding

class EmployeeListAdapter (val items:List<EmployeeListModel>, val context: Context): RecyclerView.Adapter<EmployeeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = EmployeelistadplayBinding.inflate(inflater, parent, false)
        return EmployeeViewHolder(
            binding.root,
            binding
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(items.get(position),context)

    }


}
class EmployeeViewHolder constructor(itemView: View, binding: EmployeelistadplayBinding):
    RecyclerView.ViewHolder(itemView){

    private var mBinding: EmployeelistadplayBinding

    init {
        mBinding = binding
    }



    fun bind(getitems:EmployeeListModel,context: Context){
        mBinding.name.text = "${getitems.first_name},${getitems.last_name}"
        mBinding.gender.text = " ${getitems.gender}"
        mBinding.dob.text="${getitems.birth_date}"

        val imageBytes = Base64.decode(getitems.thumbImage, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        mBinding.img.setImageBitmap(decodedImage)


        itemView.setOnClickListener{
            val intent = Intent(context,EmployeeDetailsActivity::class.java)
            intent.putExtra("ID",getitems.id)
            context.startActivity(intent)
        }




    }



}