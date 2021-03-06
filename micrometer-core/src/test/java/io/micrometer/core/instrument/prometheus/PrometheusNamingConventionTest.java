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
package io.micrometer.core.instrument.prometheus;

import io.micrometer.core.instrument.Meter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jon Schneider
 */
class PrometheusNamingConventionTest {
    private PrometheusNamingConvention convention = new PrometheusNamingConvention();

    @Test
    void formatName() {
        assertThat(convention.name("123abc/{:id}水", Meter.Type.Gauge)).isEqualTo("m_123abc__:id__");
    }

    @Test
    void formatTagKey() {
        assertThat(convention.tagKey("123abc/{:id}水")).isEqualTo("m_123abc___id__");
    }

    @Test
    void unitsAreAppendedToTimers() {
        assertThat(convention.name("timer", Meter.Type.Timer)).isEqualTo("timer_duration_seconds");
    }

    @Test
    void unitsAreAppendedToDistributionSummaries() {
        assertThat(convention.name("response.size", Meter.Type.DistributionSummary, "bytes")).isEqualTo("response_size_bytes");
        assertThat(convention.name("summary", Meter.Type.DistributionSummary)).isEqualTo("summary");
    }

    @Test
    void dotNotationIsConvertedToSnakeCase() {
        assertThat(convention.name("gauge.size", Meter.Type.Gauge)).isEqualTo("gauge_size");
    }
}
