package kopring.prac10_upload.controller

import org.springframework.web.multipart.MultipartFile

data class ItemForm(
    val itemName : String?,
    val attachFile : MultipartFile?,
    val imageFiles : List<MultipartFile>?
)