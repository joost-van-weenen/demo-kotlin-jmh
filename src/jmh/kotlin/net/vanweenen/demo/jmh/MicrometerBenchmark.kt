package net.vanweenen.demo.jmh

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit


@Fork(value = 2)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class MicrometerBenchmark {

    @Benchmark
    open fun counterBenchmark(state: MyState) {
        state.registry.counter("micrometer.counter").increment()
    }

    @Benchmark
    open fun histogramBenchmark(state: MyState) {
        state.registry.summary("micrometer.summary").record(1000.0)
    }

    @Benchmark
    open fun gaugeBenchmark(state: MyState) {
        state.registry.gauge("micrometer.gauge", 5)
    }
}
