package edcartel.configurations

import graphql.execution.ExecutionStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class GraphQLConfig(private val asyncTransactionalExecutionStrategy: AsyncTransactionalExecutionStrategy) {

    @Bean
    fun executionStrategies(): Map<String, ExecutionStrategy> {
        val executionStrategyMap = HashMap<String, ExecutionStrategy>()
        executionStrategyMap["queryExecutionStrategy"] = asyncTransactionalExecutionStrategy
        return executionStrategyMap
    }
}