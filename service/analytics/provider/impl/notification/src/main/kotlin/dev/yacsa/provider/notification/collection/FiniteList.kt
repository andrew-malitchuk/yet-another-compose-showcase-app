package dev.yacsa.provider.notification.collection

class FiniteList<T>(private val size: Int) {
    private val list = mutableListOf<T>()

    fun add(item: T) {

        if (list.size < size) {
            list.add(item)
        } else {
            list.add(0, item)
            list.removeLast()
        }

    }

    override fun toString(): String {
        var result = ""
        list.forEach {
            result+="$it\n"
        }
        return result
    }

}