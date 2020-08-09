package com.example.mebank.Activity.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mebank.Activity.Model.EmployeeListModel
import com.example.mebank.Activity.Repository.EmployeeListRefreshError
import com.example.mebank.Activity.Repository.EmployeeListRepo
import com.example.mebank.Activity.Util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class EmployeeListViewModel(private val repository: EmployeeListRepo) : ViewModel() {

    val mutableEmployeeListLiveData = MutableLiveData<List<EmployeeListModel>>()

    companion object {
        val FACTORY = singleArgViewModelFactory(::EmployeeListViewModel)
    }

    fun onGetEmployeeList(){
        getEmployeeList()
    }



    fun getEmployeeList(){
        viewModelScope.launch{
            try{
                mutableEmployeeListLiveData.value = repository.refreshEmployeeList()
            }
            catch(error: EmployeeListRefreshError){
            }
            finally {

            }

        }
    }


}