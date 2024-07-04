package kopring.prac10_upload.domain

class Item (
    var id : Long? = null,
    val itemName: String,
    val attachFile : UploadFile,
    val imageFiles : List<UploadFile>
){
}