package com.oneself.flink.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.StateBackend;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Preconditions;
import com.oneself.common.utils.PropertiesUtil;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Title: BaseFlink
 * @Author wen
 * @Date: 2022/7/31 17:29
 */
public abstract class BaseFlink {


    protected StreamExecutionEnvironment env;


    protected Properties properties;


    public void init(ParameterTool params) throws IOException {
        env = StreamExecutionEnvironment.getExecutionEnvironment();
        this.properties = PropertiesUtil.getProperties(getPropertiesName());
        Configuration configuration = new Configuration();
        configuration.addAllToProperties(properties);
        env.getConfig().setGlobalJobParameters(configuration);

        String parallelism = params.get("parallelism");
        if (StringUtils.isNotBlank(parallelism)) {
            env.setParallelism(Integer.valueOf(parallelism));
        }
        String restartStrategy = params.get("restartStrategy");
        if ("fixedDelayRestart".equals(restartStrategy)) {
            env.setRestartStrategy(RestartStrategies.fixedDelayRestart(
                    3,
                    Time.of(60, TimeUnit.SECONDS)
            ));
        } else if ("noRestart".equals(restartStrategy)) {
            env.setRestartStrategy(RestartStrategies.noRestart());
        } else if ("fallBackRestart".equals(restartStrategy)) {
            env.setRestartStrategy(RestartStrategies.fallBackRestart());
        } else {
            env.setRestartStrategy(RestartStrategies.failureRateRestart(
                    3,
                    Time.of(5, TimeUnit.MINUTES),
                    Time.of(60, TimeUnit.SECONDS)
            ));
        }

        String isLocal = params.get("isLocal");
        if (StringUtils.isBlank(isLocal)) {
            String isIncremental = params.get("isIncremental");
            Preconditions.checkNotNull(isIncremental, "isIncremental is null");
            StateBackend stateBackend;
            String hadoopIp = properties.getProperty("hadoopIp");
//            if ("isIncremental".equals(isIncremental)) {
//                //?????????????????????????????????hdfs??????????????????????????????hadoop???????????????????????????flink???hdfs???????????????????????????????????????hdfs???????????????????????????hadoop??????jar??????
//                stateBackend = new RocksDBStateBackend("hdfs:///home/intsmaze/flink/" + getJobName(), true);
//                env.setStateBackend(stateBackend);
//            } else if ("full".equals(isIncremental)) {
//                stateBackend = new RocksDBStateBackend("hdfs://" + hadoopIp + "/home/intsmaze/flink/" + getJobName(), false);
//                env.setStateBackend(stateBackend);
//            }

            env.enableCheckpointing(5000);
            env.getCheckpointConfig().setCheckpointTimeout(30000);
            env.getCheckpointConfig().setMinPauseBetweenCheckpoints(5000);
            env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
            env.getCheckpointConfig().setFailOnCheckpointingErrors(false);
            env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        }
    }




    protected DataStream<String> getKafkaSpout(String topic) {

        String bootstrapServers = properties.getProperty("bootstrap.servers", "");
        String port = properties.getProperty("kafka.offset.Port", "");

        String id = StringUtils.join(getJobName(), "-", topic);
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", bootstrapServers + ":" + port);
        properties.setProperty("group.id", id);
        System.out.println("-------------->>>>>>>>>>>>>>>>>> consumer name is :" + id);
        FlinkKafkaConsumer<String> stringFlinkKafkaConsumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), properties);
        return env.addSource(stringFlinkKafkaConsumer);
    }



    public abstract String getJobName();


    public abstract String getPropertiesName();


    public abstract void createTopology(StreamExecutionEnvironment builder) throws IOException;


    public void run(ParameterTool params) throws Exception {

        init(params);
        createTopology(env);

        String topoName = StringUtils.join(getJobName(), "-", new Date());
        env.execute(topoName);
    }

}
