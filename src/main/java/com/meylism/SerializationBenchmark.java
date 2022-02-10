package com.meylism;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.JsonSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SerializationBenchmark {
    @Benchmark
    public void benchmarkSerializers(SerializerState st, Blackhole bh) throws Exception {
        Object result;
        ObjectInspector oi;
        int a, b;
        a = b = 0;
        if(st.CDHSerDe != null) {
            oi = st.CDHSerDe.getObjectInspector();
            result = st.CDHSerDe.serialize(st.CDHSerDeResult, oi);
        } else {
            oi = st.OpenXSerDe.getObjectInspector();
            result = st.OpenXSerDe.serialize(st.OpenXSerDeResult, oi);
        }
        bh.consume(result);
    }

    public static void main(String[] args) throws RunnerException {
        /*Options opt = new OptionsBuilder()
                .include(SerializationBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();*/
        List<Integer> l = Arrays.asList(1, 2, 3);
        System.out.println(l);

    }
}
