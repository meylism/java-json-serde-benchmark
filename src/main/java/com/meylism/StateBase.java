package com.meylism;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class StateBase {
    @Param({"mesh.json", "twitter.json"})
    public String resourceName;
}
