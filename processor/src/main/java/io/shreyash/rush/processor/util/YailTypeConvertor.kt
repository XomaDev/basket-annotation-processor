package io.shreyash.rush.processor.util

import io.shreyash.rush.processor.block.HelperType
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement

/**
 * Returns a YAIL type from given [name] of a type.
 */
@Throws(IllegalStateException::class)
fun yailTypeOf(name: String, isHelper: Boolean): String = when (name) {
    "java.util.List" -> "list"
    "boolean" -> "boolean"
    "java.lang.String" -> "text"
    "java.util.Calendar" -> "InstantInTime"
    "float", "int", "double", "byte", "long", "short" -> "number"
    "com.google.appinventor.components.runtime.util.YailList" -> "list"
    "com.google.appinventor.components.runtime.util.YailObject" -> "yailobject"
    "com.google.appinventor.components.runtime.util.YailDictionary" -> "dictionary"
    else -> {
        if (isHelper) name + "Enum"
        else if (name.startsWith("com.google.appinventor.components.runtime.")) "component"
        else "any"
    }
}
