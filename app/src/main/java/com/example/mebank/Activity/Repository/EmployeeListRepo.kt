package com.example.mebank.Activity.Repository

import com.example.mebank.Activity.Model.EmployeeListModel
import com.example.mebank.Activity.Network.Network

class EmployeeListRepo (val network:Network) {

    suspend fun refreshEmployeeList():List<EmployeeListModel>{
        val result:List<EmployeeListModel>
        try{
            result= network.fetchEmployeeList()
        }
        catch(cause:Throwable){
            throw  EmployeeListRefreshError("Unable to fetch list",cause)

        }

        return result

    }


}


class EmployeeListRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)