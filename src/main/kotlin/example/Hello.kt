package example

import com.mitchellbosecke.pebble.PebbleEngine
import com.mitchellbosecke.pebble.loader.ClasspathLoader
import java.io.StringWriter
import java.util.HashMap

fun main(args: Array<String>) {
    val loader = ClasspathLoader()
    val engine = PebbleEngine.Builder().loader(loader).strictVariables(true).build()
    val compiledTemplate = engine.getTemplate("hello.html")

    val items = listOf(
            Item("Item 1", "$19.99", listOf("good", "item", "new"), listOf(Feature("New!"), Feature("Awesome!"))),
            Item("Item 2", "$29.99", listOf("old", "item", "bad"), listOf(Feature("Old."), Feature("Ugly.")))
    )

    val context = HashMap<String, Any>()
    context["items"] = items

    val writer = StringWriter()
    compiledTemplate.evaluate(writer, context)

    val output = writer.toString()
    println(output)
}

class Feature(val description: String)

class Item(val name: String, val price: String, val keywords: List<String>, val features: List<Feature>)