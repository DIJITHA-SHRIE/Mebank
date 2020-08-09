package com.example.mebank.Activity.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mebank.Activity.Model.EmployeeListModel
import com.example.mebank.Activity.Repository.EmployeeDetailRepo
import com.example.mebank.Activity.Repository.EmployeeListRefreshError
import com.example.mebank.Activity.Repository.EmployeeListRepo
import com.example.mebank.Activity.Util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class EmployeeDetailViewModel (private val repository: EmployeeDetailRepo) : ViewModel() {

    val mutableEmployeeDetailLiveData = MutableLiveData<EmployeeListModel>()

    companion object {
        val FACTORY = singleArgViewModelFactory(::EmployeeDetailViewModel)
    }

    fun onGetEmployeeDetail(){
        getEmployeeDetail()
    }



    fun getEmployeeDetail(){
        viewModelScope.launch{
            try{
                mutableEmployeeDetailLiveData.value = repository.refreshEmployeeDetail()
            }
            catch(error: EmployeeListRefreshError){
            }
            finally {

            }

        }
    }


}