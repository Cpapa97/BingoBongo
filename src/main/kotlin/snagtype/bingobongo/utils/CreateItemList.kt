package snagtype.bingobongo.utils

import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import snagtype.bingobongo.BingoBongo

class CreateItemList {
    companion object {
        fun getList(): MutableList<MutableList<String>> {
            val itemList = mutableListOf<MutableList<String>>()
            for (item in Registries.ITEM) {
                // every itemList element is a list of itemStrings for a particular itemID
                val itemStrings = mutableListOf<String>()
                val itemID = Registries.ITEM.getId(item) ?: continue // itemId format: "ModName:ItemName

                itemStrings.add(Parser.getItemName(itemID)) // element 0 = item name
                itemStrings.add(Parser.getItemModName(itemID)) //element 1 = mod name
                val itemStack = ItemStack(item)
                val tagList = itemStack.streamTags().toList() // gets a list of tags for each item
                BingoBongo.logger.info("item ID: $itemID")
                BingoBongo.logger.info("list of Tags: $tagList")
                for (tagListItem in tagList.listIterator()) {
                    itemStrings.add(Parser.getTagName(tagListItem)) // element 1 + x = associated tag names
                }
                itemList.add(itemStrings)
            }
            return itemList
        }
    }
}