package test

import javax.script.ScriptEngineManager


fun main() {
    val engineManager = ScriptEngineManager()
    println(engineManager.engineFactories.joinToString { it.engineName })
    engineManager.getEngineByName("kotlin")?.run { println(this); eval("println(\"byName\")") }
    engineManager.engineFactories[0].scriptEngine.eval("println(\"direct\")")
}
