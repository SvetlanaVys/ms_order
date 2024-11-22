package com.svysk.ms_order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"test-topic"})
@ActiveProfiles("local")
class MsOrderApplicationTests {

	@Test
	void contextLoads(@Autowired ApplicationArguments args) {
	}

}
