package com.example.mebank.Activity.Repository

import com.example.mebank.Activity.Model.EmployeeListModel
import com.example.mebank.Activity.Network.Network

class EmployeeDetailRepo (val network: Network,val id:Int) {

    suspend fun refreshEmployeeDetail():EmployeeListModel{
        val result:EmployeeListModel
        try{
            result= network.fetchEmployeeDetail(id)
        }
        catch(cause:Throwable){
            throw  EmployeeDetailRefreshError("Unable to fetch list",cause)

        }

        return result

    }


}


class EmployeeDetailRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)