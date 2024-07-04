package kopringprac.registration.domain.item

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ItemRepoTest{

     companion object {
         val itemRepo = ItemRepo()
     }

    @AfterEach
    fun clear() {
        itemRepo.clearStore()
    }

    @Test
    fun save(){
        //given
        val item = Item(itemName = "itemA", price = 1000, quantity = 10)

        //when
        itemRepo.save(item)

        //then
        itemRepo.findById(1)?.itemName shouldBe "itemA"
    }

    @Test
    fun findAll(){
        // given
        val itemA = Item(itemName = "itemA", price = 1000, quantity = 10)
        val itemB = Item(itemName = "itemB", price = 5000, quantity = 40)
        itemRepo.save(itemA)
        itemRepo.save(itemB)

        // when
        val result = itemRepo.findAll()

        // then
        result.size shouldBe 2
    }

    @Test
    fun update(){
        // given
        val itemA = Item(itemName = "itemA", price = 1000, quantity = 10)
        val itemB = Item(itemName = "itemB", price = 5000, quantity = 40)
        itemRepo.save(itemA)
        itemRepo.save(itemB)

        // when
        val updateItem = Item(itemName = "updateItem", price = 5000, quantity = 30)
        itemRepo.update(2,updateItem)
        val result = itemRepo.findById(2)

        // then
        result?.itemName shouldBe "updateItem"
    }
}