package kopring.prac10_upload.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac10_upload.domain.Item
import kopring.prac10_upload.domain.ItemRepo
import kopring.prac10_upload.file.FileStore
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.net.MalformedURLException
import java.nio.charset.StandardCharsets

private val logger = KotlinLogging.logger {  }

@Controller
class ItemController(private val fileStore: FileStore, private val itemRepo : ItemRepo) {

    @GetMapping("/items/new")
    fun newItem (@ModelAttribute form : ItemForm?): String {
        return "item-form"
    }

    @PostMapping("/items/new")
    fun saveItem(@ModelAttribute form : ItemForm, redirectAttributes: RedirectAttributes): String {
        val attachFile = fileStore.storeFile(form.attachFile!!)
        val storeImageFiles = fileStore.storeFiles(form.imageFiles!!)

        // 데이터 베이스에 저장하기.
        val item = Item(itemName = form.itemName!!,
            attachFile = attachFile!!,
            imageFiles = storeImageFiles!!
            )

        itemRepo.save(item)
        redirectAttributes.addAttribute("itemId", item.id)

        return "redirect:/items/{itemId}"
    }

    @GetMapping("/items/{id}")
    fun items(@PathVariable id: Long, model : Model): String {
        val item = itemRepo.findById(id)
        model.addAttribute("item", item)
        return "item-view"
    }

    @ResponseBody
    @GetMapping("/images/{fileName}")
    fun downloadImage(@PathVariable fileName : String): Resource {
        return UrlResource("file:${fileStore.getFullPath(fileName)}")
    }

    @GetMapping("/attach/{itemId}")
    fun downloadFile(@PathVariable itemId : Long): ResponseEntity<Resource> {
        val item = itemRepo.findById(itemId)

        item?.let {
            val storeFileName = it.attachFile.storeFileName
            val uploadFileName = it.attachFile.uploadFileName

            val urlResource = UrlResource("file:${fileStore.getFullPath(storeFileName)}")

            logger.info { "uploadFileName : $uploadFileName" }

            val contentDisposition = ContentDisposition.attachment().filename(uploadFileName,StandardCharsets.UTF_8).build().toString()

            logger.info { "contentDisposition : $contentDisposition" }

            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource)

        }?: throw MalformedURLException()

    }

}