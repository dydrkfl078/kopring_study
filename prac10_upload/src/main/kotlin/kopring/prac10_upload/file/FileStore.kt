package kopring.prac10_upload.file

import kopring.prac10_upload.domain.Item
import kopring.prac10_upload.domain.UploadFile
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*

@Component
class FileStore {

    @Value("\${file.dir}")
    private lateinit var path : String

    fun getFullPath(itemName: String) : String{
        return path + itemName
    }

    fun storeFile(multipartFile: MultipartFile) : UploadFile? {
        multipartFile.originalFilename?.let {
            val fileName = it

            val ext = extractExt(fileName)
            val storeFileName = createStoreFileName(ext)
            multipartFile.transferTo(File(getFullPath(storeFileName)))

            return UploadFile(fileName, storeFileName)

        }?: return null
    }

    fun storeFiles(multipartFileList : List<MultipartFile>) : List<UploadFile>? {
        val storeFileResult = mutableListOf<UploadFile>()
        multipartFileList.forEach {
            if (!it.isEmpty) {
                storeFileResult.add(storeFile(it)!!)
            }
        }

        return storeFileResult
    }

    private fun extractExt(fileName: String): String {
        val pos = fileName.lastIndexOf(".")
        val ext = fileName.substring(pos + 1)
        return ext
    }

    private fun createStoreFileName(ext: String): String {
        val uuid = UUID.randomUUID().toString()
        return "$uuid.$ext"
    }
}