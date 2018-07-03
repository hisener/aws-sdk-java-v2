/*
 * Copyright 2010-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.core.client.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.Test;
import software.amazon.awssdk.core.internal.http.request.SlowExecutionInterceptor;
import software.amazon.awssdk.core.util.ImmutableMapParameter;

public class ClientOverrideConfigurationTest {

    @Test
    public void addingSameItemTwice_shouldOverride() {
        ClientOverrideConfiguration configuration = ClientOverrideConfiguration.builder()
                                                                               .header("value", "foo")
                                                                               .header("value", "bar")
                                                                               .advancedOption(SdkAdvancedClientOption.USER_AGENT_SUFFIX, "foo")
                                                                               .advancedOption(SdkAdvancedClientOption.USER_AGENT_SUFFIX, "bar")
                                                                               .build();

        assertThat(configuration.headers().get("value")).containsExactly("bar");
        assertThat(configuration.advancedOption(SdkAdvancedClientOption.USER_AGENT_SUFFIX).get()).isEqualTo("bar");
    }

    @Test
    public void settingCollection_shouldOverrideAddItem() {
        ClientOverrideConfiguration configuration = ClientOverrideConfiguration.builder()
                                                                               .header("value", "foo")
                                                                               .headers(ImmutableMapParameter.of("value", Arrays.asList("hello", "world")))
                                                                               .advancedOption(SdkAdvancedClientOption.USER_AGENT_SUFFIX, "test")
                                                                               .advancedOptions(new HashMap<>())
                                                                               .executionInterceptor(new SlowExecutionInterceptor())
                                                                               .executionInterceptors(new ArrayList<>())
                                                                               .build();

        assertThat(configuration.headers().get("value")).containsExactly("hello", "world");
        assertFalse(configuration.advancedOption(SdkAdvancedClientOption.USER_AGENT_SUFFIX).isPresent());
        assertTrue(configuration.executionInterceptors().isEmpty());
    }
}
