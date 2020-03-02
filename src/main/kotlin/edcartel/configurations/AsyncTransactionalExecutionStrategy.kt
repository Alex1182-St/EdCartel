package edcartel.configurations

import graphql.ExecutionResult
import graphql.execution.AsyncExecutionStrategy
import graphql.execution.ExecutionContext
import graphql.execution.ExecutionStrategyParameters
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import javax.transaction.Transactional

@Service
class AsyncTransactionalExecutionStrategy : AsyncExecutionStrategy() {

    @Transactional
    override fun execute(

        executionContext: ExecutionContext,

        parameters: ExecutionStrategyParameters

    ) : CompletableFuture<ExecutionResult> = super.execute(executionContext, parameters)

}