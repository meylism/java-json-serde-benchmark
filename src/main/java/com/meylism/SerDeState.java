/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.meylism;

import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.io.Text;
import org.openjdk.jmh.annotations.*;


import java.io.*;
import java.util.Properties;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
public class SerDeState {
    @Param({"mesh.json"})
    private String resourceName;

    public Text jsonText;

    public Properties props;

    @Setup
    public void setup() throws Exception {
        System.out.println("!!!SETUP!!!");
        switch (resourceName) {
            case "mesh.json":
                props = new Properties();
                props.setProperty(serdeConstants.LIST_COLUMNS, "batches,colors,indices,influences,morphtargets," +
                        "normals,positions,tex0");
                props.setProperty(serdeConstants.LIST_COLUMN_TYPES,
                        "array<struct<indexrange:array<int>,usedbones:array<int>,vertexrange:array<int>>>," +
                        "array<int>," + "array<int>," + "array<array<double>>," + "struct<notKnown:int>,array<double>," +
                                "array<double>," + "array<double>".toLowerCase());
                break;
        }
        jsonText = loadJson(resourceName);
    }

    public Text loadJson(final String resourceName) throws IOException {
        final InputStream stream = this.getClass().getResourceAsStream("/" + resourceName);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        final String jsonText = reader.lines().collect(Collectors.joining("\n"));
        return new Text(jsonText);
    }
}
