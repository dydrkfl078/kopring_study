package kopring.prac10_upload.domain

import org.springframework.stereotype.Repository

@Repository
class ItemRepo {

    companion object {
        val store = HashMap<Long, Item>()
        var sequence = 0L
    }

    fun save(item: Item) {
        item.id = ++sequence
        store[item.id!!] = item
    }

    fun findById(id: Long): Item? {
        return store[id]
    }
}