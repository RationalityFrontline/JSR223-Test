package test

import javax.script.ScriptEngineManager


fun main() {
    ScriptEngineManager().getEngineByName("kotlin").eval("println(\"TEST\")")
}
