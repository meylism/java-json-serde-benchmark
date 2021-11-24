package com.meylism;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.JsonSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.io.Text;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openx.data.jsonserde.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@State(Scope.Benchmark)
public class SerializerState extends StateBase {
    @Param({"CDH", "OpenX"})
    public String serde;
    public Text jsonText;
    ObjectInspector oi;

    public JsonSerDe CDHSerDe;
    public List<?> CDHSerDeResult;

    public org.openx.data.jsonserde.JsonSerDe OpenXSerDe;
    public JSONObject OpenXSerDeResult;

    Properties props = new Properties();
    @Setup
    public void setup() throws Exception {
        jsonText = BenchmarkUtils.loadJson(resourceName);
        switch (resourceName) {
            case "mesh.json":
                props.setProperty(serdeConstants.LIST_COLUMNS, BenchmarkConstants.MESH_COLUMN_NAME);
                props.setProperty(serdeConstants.LIST_COLUMN_TYPES, BenchmarkConstants.MESH_COLUMN_TYPE);
                break;
            case "twitter.json":
                props.setProperty(serdeConstants.LIST_COLUMNS, BenchmarkConstants.TWITTER_COLUMN_NAME);
                props.setProperty(serdeConstants.LIST_COLUMN_TYPES, BenchmarkConstants.TWITTER_COLUMN_TYPE);
                break;
        }
        switch (serde) {
            case "CDH":
                CDHSerDe = new JsonSerDe();
                CDHSerDe.initialize(new Configuration(), props, null);
                CDHSerDeResult = (List<?>) CDHSerDe.deserialize(jsonText);
                break;
            case "OpenX":
                OpenXSerDe = new org.openx.data.jsonserde.JsonSerDe();
                OpenXSerDe.initialize(new Configuration(), props);
                OpenXSerDeResult = (JSONObject) OpenXSerDe.deserialize(jsonText);
                break;
        }
    }
}
