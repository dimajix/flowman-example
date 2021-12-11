/*
 * Copyright 2019-2021 Kaya Kupferschmidt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dimajix.flowman.example.spec;

import com.dimajix.flowman.execution.Lifecycle;
import com.dimajix.flowman.execution.Phase;
import com.dimajix.flowman.testing.Runner;
import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;


public class JavaProjectTest {
    @Test
    public void testRunner(@TempDir File tempDir) {
        String proxyEnv = System.getenv("http_proxy");
        String proxyHost = "";
        String proxyPort = "-1";
        if (proxyEnv != null && proxyEnv.length() > 0) {
            Pattern pattern = Pattern.compile("([^:]+:\\/\\/)?([^:]*)(:(.*))?", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(proxyEnv);
            if (matcher.find()) {
                proxyHost = matcher.group(2);
                proxyPort = matcher.group(4);
            }
        }

        Runner runner = Runner.builder()
            .withProfile("test")
            .withProject(Resources.getResource("flows/weather/project.yml"))
            .withEnvironment("basedir", new File(tempDir, "weather").toString())
            .withConfig("spark.hadoop.fs.s3a.proxy.host",proxyHost)
            .withConfig("spark.hadoop.fs.s3a.proxy.port",proxyPort)
            .build();

        boolean result = runner.runJob("main", Collections.singletonList(Phase.ofString("build")), Collections.emptyMap());
        assertThat(result).isTrue();
        result = runner.runJob("main", Lifecycle.DEFAULT(), Collections.emptyMap());
        assertThat(result).isTrue();
    }
}
