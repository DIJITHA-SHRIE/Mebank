package com.example.mebank.Activity.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mebank.Activity.Adapter.EmployeeListAdapter
import com.example.mebank.Activity.Model.EmployeeListModel
import com.example.mebank.Activity.Network.getNetworkService
import com.example.mebank.Activity.Repository.EmployeeListRepo
import com.example.mebank.Activity.ViewModel.EmployeeListViewModel
import com.example.mebank.R
import com.example.mebank.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var employeeListViewModel: EmployeeListViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.setLifecycleOwner(this)

        val repository = EmployeeListRepo(getNetworkService())
        val viewModel = ViewModelProviders
            .of(this, EmployeeListViewModel.FACTORY(repository
                )
            ).get(EmployeeListViewModel::class.java)

        viewModel.onGetEmployeeList()


        viewModel.mutableEmployeeListLiveData.observe(this){value ->
            value?.let{
                Log.i("EmployeeResponse",value.get(0).first_name)
                val employeelist:List<EmployeeListModel> = value

                try {
                    var adapter: EmployeeListAdapter = EmployeeListAdapter(employeelist,this!!)
                    binding.employeeListRecycle!!.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL, false
                    )
                    binding.employeeListRecycle.adapter = adapter
                } catch (e: Exception) {
                    Log.i("EmployeeAdapterExp",e.localizedMessage)
                }
            }

        }


    }
}