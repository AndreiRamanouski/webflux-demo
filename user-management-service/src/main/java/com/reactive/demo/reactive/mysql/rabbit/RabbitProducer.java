package com.reactive.demo.reactive.mysql.rabbit;

import com.reactive.demo.HistoryModel;

public interface RabbitProducer {

    void produceMessage(HistoryModel historyModel);
}
