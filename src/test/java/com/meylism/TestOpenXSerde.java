package com.meylism;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;
import org.openx.data.jsonserde.JsonSerDe;

import java.io.IOException;
import java.util.Properties;

public class TestOpenXSerde {
    @Test
    public void testMeshDeserialize() throws IOException, SerDeException {
        Text jsonText = BenchmarkUtils.loadJson("mesh.json");

        Properties props = new Properties();
        props.setProperty(serdeConstants.LIST_COLUMNS, BenchmarkConstants.MESH_COLUMN_NAME);
        props.setProperty(serdeConstants.LIST_COLUMN_TYPES, BenchmarkConstants.MESH_COLUMN_TYPE);

        JsonSerDe serde = new JsonSerDe();
        serde.initialize(new Configuration(), props);

        Object result = serde.deserialize(jsonText);
        Assert.assertNotNull(result);
    }

    @Test
    public void testMeshSerialize() throws IOException, SerDeException {
        Text jsonText = BenchmarkUtils.loadJson("mesh.json");

        Properties props = new Properties();
        props.setProperty(serdeConstants.LIST_COLUMNS, BenchmarkConstants.MESH_COLUMN_NAME);
        props.setProperty(serdeConstants.LIST_COLUMN_TYPES, BenchmarkConstants.MESH_COLUMN_TYPE);

        JsonSerDe serde = new JsonSerDe();
        serde.initialize(new Configuration(), props);

        Object result = serde.deserialize(jsonText);

        Object serialized = (Text) serde.serialize(result, serde.getObjectInspector());
        Assert.assertNotNull(serialized);
    }

    @Test
    public void testTwitterDeserialize() throws IOException, SerDeException {
        Text jsonText = BenchmarkUtils.loadJson("twitter.json");

        Properties props = new Properties();
        props.setProperty(serdeConstants.LIST_COLUMNS, BenchmarkConstants.TWITTER_COLUMN_NAME);
        props.setProperty(serdeConstants.LIST_COLUMN_TYPES, BenchmarkConstants.TWITTER_COLUMN_TYPE);

        JsonSerDe serde = new org.openx.data.jsonserde.JsonSerDe();
        serde.initialize(new Configuration(), props);

        Object result = serde.deserialize(jsonText);
        Assert.assertNotNull(result);
    }

}
