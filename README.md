# Benchmarking JSON SerDes

## SerDes

- CDH Json SerDe
  - Version: 2.1.1-cdh6.3.2
- [OpenX](https://github.com/rcongiu/Hive-JSON-Serde) Json SerDe
  - Version: 1.3.7.3

## Usage

1. Build: `mvn clean install`
2. Run:
   `java -jar target/benchmarks.jar`

For other options to have benchmarks run, refer to 
[JMH](https://github.com/openjdk/jmh) and 
[JMH Samples](https://github.com/openjdk/jmh/tree/master/jmh-samples/src/main/java/org/openjdk/jmh/samples).

## Notes

Take this benchmark with a caveat that `twitter.json` dataset cannot be deserialized as expected for an uknown reason.