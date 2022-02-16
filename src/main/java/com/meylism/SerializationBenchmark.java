package com.meylism;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SerializationBenchmark {
    @Benchmark
    public void benchmarkSerializers(BenchmarkState st, Blackhole bh) throws Exception {
        Object result;
        ObjectInspector oi;
        if(st.CDHSerDe != null) {
            result = st.CDHSerDe.serialize(st.CDHSerDeResult, st.oi);
        } else {
            result = st.OpenXSerDe.serialize(st.OpenXSerDeResult, st.oi);
        }
        bh.consume(result);
    }
}
