package com.example.mebank.Activity.UI

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mebank.Activity.Adapter.EmployeeListAdapter
import com.example.mebank.Activity.Model.EmployeeListModel
import com.example.mebank.Activity.Network.getNetworkService
import com.example.mebank.Activity.Repository.EmployeeDetailRepo
import com.example.mebank.Activity.Repository.EmployeeListRepo
import com.example.mebank.Activity.ViewModel.EmployeeDetailViewModel
import com.example.mebank.Activity.ViewModel.EmployeeListViewModel
import com.example.mebank.R
import com.example.mebank.databinding.ActivityEmployeeDetailsBinding
import com.example.mebank.databinding.ActivityMainBinding

class EmployeeDetailsActivity : AppCompatActivity() {
    private lateinit var employeeDetailViewModel: EmployeeDetailViewModel
    private lateinit var binding: ActivityEmployeeDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_details)
        binding.setLifecycleOwner(this)

        val id: Int = intent.getIntExtra("id", 1)

        val repository = EmployeeDetailRepo(getNetworkService(), id)
        val viewModel = ViewModelProviders
            .of(
                this, EmployeeDetailViewModel.FACTORY(
                    repository
                )
            ).get(EmployeeDetailViewModel::class.java)

        viewModel.onGetEmployeeDetail()


        viewModel.mutableEmployeeDetailLiveData.observe(this) { value ->
            value?.let {

                val employeelist:EmployeeListModel = value

                val imageBytes = Base64.decode(employeelist.thumbImage, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                binding.detailImg.setImageBitmap(decodedImage)

            }
        }
    }
}