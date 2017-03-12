package ua.ddovgal.brainshareApi

import org.jooq.util.DefaultGeneratorStrategy
import org.jooq.util.Definition
import org.jooq.util.GeneratorStrategy
import org.jooq.util.TableDefinition

class JooqImprovedPrefixGeneratorStrategy : DefaultGeneratorStrategy() {
    override fun getJavaClassName(definition: Definition, mode: GeneratorStrategy.Mode): String {
        val prefix = if (mode == GeneratorStrategy.Mode.RECORD) "R"
        else if (mode == GeneratorStrategy.Mode.POJO) ""
        else if (definition is TableDefinition) "T"
        else ""
        return prefix + super.getJavaClassName(definition, mode)
    }
}