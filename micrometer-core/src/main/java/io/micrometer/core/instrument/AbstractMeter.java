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

public abstract class AbstractMeter implements Meter {
    private final String name;
    private final Iterable<Tag> tags;
    private final String description;

    public AbstractMeter(String name, Iterable<Tag> tags, String description) {
        this.name = name;
        this.tags = tags;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Iterable<Tag> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }
}
