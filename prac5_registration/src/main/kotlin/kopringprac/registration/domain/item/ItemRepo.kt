package kopringprac.registration.domain.item

import org.springframework.stereotype.Repository

@Repository
class ItemRepo {

    companion object {
        val store = HashMap<Long, Item>()
        var sequence: Long = 0L
    }

    fun save(item: Item): Item {
        item.id = ++sequence
        item.id?.let { id -> store[id] = item }
        return item
    }

    fun findById(id: Long): Item? {
        return store[id]
    }

    fun findAll(): List<Item> {
        return store.values.toList()
    }

    fun update(id: Long, updateItem: Item) {
        val changeItem = findById(id)
        changeItem?.apply {
            itemName = updateItem.itemName
            price = updateItem.price
            quantity = updateItem.quantity
            store[id] = changeItem
            regions = updateItem.regions
            open = updateItem.open
        }
    }

    fun clearStore(){
        store.clear()
        sequence = 0L
    }
}
