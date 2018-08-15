package com.wcccode;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class Topology1 {
    private static TopologyBuilder builder = new TopologyBuilder();
    public static void main(String[] args) {
        Config config = new Config();
        builder.setSpout("RandomSentence", new RandomSentenceSpout(), 2);
        builder.setBolt("WordNormalizer", new PrintBolt(), 2).shuffleGrouping("RandomSentence");
        config.setDebug(false);
        config.setMaxTaskParallelism(1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("wordcount", config, builder.createTopology());
    }
}
