package net.vanweenen.demo.jmh

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.openjdk.jmh.annotations.*

@State(Scope.Benchmark)
open class MyState {

    var registry: MeterRegistry = SimpleMeterRegistry()

    @Setup(Level.Trial)
    open fun doSetup() {
        registry = SimpleMeterRegistry()
    }

    @TearDown(Level.Trial)
    open fun doTearDown() {
        val counter = registry.counter("micrometer.counter")
        println("Counter: ${counter.count()}")

        val summary = registry.summary("micrometer.summary")
        println("Summary mean: ${summary.mean()}, max: ${summary.max()}")
    }
}
