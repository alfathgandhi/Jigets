package org.d3ifcool.catat

class Part{
    var nama : String?= null
    var line: String?=null
    var plane: String?=null
    var basic : String?=null
    var keterangan :String?=null
    var dibuat: String?=null
    var photo: String?=null
    var type:List<String>?=null

    constructor(){}

    constructor(nama: String?,plane:String?, line:String?, basic:String? ,profile:String?, dibuat:String?, keterangan:String?, type:List<String>?){
        this.plane = plane
        this.nama = nama
        this.line = line
        this.basic = basic
        this.keterangan = keterangan
        this.dibuat = dibuat
        this.photo = profile
        this.type = type

    }
}