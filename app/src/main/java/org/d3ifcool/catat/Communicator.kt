package org.d3ifcool.catat

interface Communicator {

    fun sendString(send: String)

    fun sendData(send:String,plane:String,basic:String,line:String,keterangan:String,photo:String,dibuat:List<String>,namaUser:String)






}