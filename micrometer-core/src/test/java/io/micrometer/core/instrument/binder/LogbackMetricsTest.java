/**
 * Copyright 2017 Pivotal Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.core.instrument.binder;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.micrometer.core.instrument.Statistic.Count;
import static org.assertj.core.api.Assertions.assertThat;

class LogbackMetricsTest {
    @Test
    /* FIXME */
    @Disabled("why is this flaky on CircleCI")
    void logbackLevelMetrics() {
        MeterRegistry registry = new SimpleMeterRegistry();
        new LogbackMetrics().bindTo(registry);

        assertThat(registry.find("logback.events").value(Count, 0.0).counter()).isPresent();

        Logger logger = LoggerFactory.getLogger("foo");
        logger.warn("warn");
        logger.error("error");

        assertThat(registry.find("logback.events").tags("level", "warn").value(Count, 1.0).counter()).isPresent();
    }
}
