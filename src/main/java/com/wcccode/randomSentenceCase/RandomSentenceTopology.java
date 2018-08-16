package com.wcccode.randomSentenceCase;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class RandomSentenceTopology {
    private static TopologyBuilder builder = new TopologyBuilder();
    public static void main(String[] args) {
        Config config = new Config();
        builder.setSpout("RandomSentence", new RandomSentenceSpout(), 2);
        builder.setBolt("WordNormalizer", new PrintBolt(), 2).shuffleGrouping("RandomSentence");
        config.setDebug(false);
        config.setMaxTaskParallelism(1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("wordcount", config, builder.createTopology());
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //结束拓扑
        cluster.killTopology("SequenceTest");

        cluster.shutdown();
    }
}
