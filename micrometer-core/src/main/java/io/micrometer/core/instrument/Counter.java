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
package io.micrometer.core.instrument;

import java.util.Collections;

/**
 * Used to measure the rate of change based on calls to increment.
 */
public interface Counter extends Meter {
    /**
     * Update the counter by one.
     */
    default void increment() {
        increment(1.0);
    }

    /**
     * Update the counter by {@code amount}.
     *
     * @param amount Amount to add to the counter.
     */
    void increment(double amount);

    /**
     * The cumulative count since this counter was created.
     */
    double count();

    @Override
    default Iterable<Measurement> measure() {
        return Collections.singletonList(new Measurement(this::count, Statistic.Count));
    }

    @Override
    default Type getType() {
        return Type.Counter;
    }

    interface Builder {
        Builder tags(Iterable<Tag> tags);

        default Builder tags(String... tags) {
            return tags(Tags.zip(tags));
        }

        Builder description(String description);

        Counter create();
    }
}
