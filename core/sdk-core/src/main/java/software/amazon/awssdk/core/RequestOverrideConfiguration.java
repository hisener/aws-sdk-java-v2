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

package software.amazon.awssdk.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import software.amazon.awssdk.annotations.Immutable;
import software.amazon.awssdk.annotations.SdkPublicApi;
import software.amazon.awssdk.utils.CollectionUtils;

/**
 * Base per-request override configuration for all SDK requests.
 */
@Immutable
@SdkPublicApi
public abstract class RequestOverrideConfiguration {

    private final Map<String, List<String>> headers;

    private final Map<String, List<String>> rawQueryParameters;

    private final List<ApiName> apiNames;

    protected RequestOverrideConfiguration(Builder<?> builder) {
        this.headers = CollectionUtils.deepUnmodifiableMap(builder.headers(), () -> new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
        this.rawQueryParameters = CollectionUtils.deepUnmodifiableMap(builder.rawQueryParameters());
        this.apiNames = Collections.unmodifiableList(builder.apiNames());
    }

    /**
     * Optional additional headers to be added to the HTTP request.
     *
     * @return The optional additional headers.
     */
    public Map<String, List<String>> headers() {
        return headers;
    }

    /**
     * Optional additional query parameters to be added to the HTTP request.
     *
     * @return The optional additional query parameters.
     */
    public Map<String, List<String>> rawQueryParameters() {
        return rawQueryParameters;
    }

    /**
     * The optional names of the higher level libraries that constructed the request.
     *
     * @return The names of the libraries.
     */
    public List<ApiName> apiNames() {
        return apiNames;
    }

    /**
     * Create a {@link Builder} initialized with the properties of this {@code SdkRequestOverrideConfiguration}.
     *
     * @return A new builder intialized with this config's properties.
     */
    public abstract Builder<? extends Builder> toBuilder();

    public interface Builder<B extends Builder> {
        /**
         * Optional additional headers to be added to the HTTP request.
         *
         * @return The optional additional headers.
         */
        Map<String, List<String>> headers();

        /**
         * Add a single header to be set on the HTTP request.
         *
         * <p>
         * This overrides any values already configured with this header name in the builder.
         *
         * @param name The name of the header.
         * @param value The value of the header.
         * @return This object for method chaining.
         */
        default B header(String name, String value) {
            header(name, Collections.singletonList(value));
            return (B) this;
        }

        /**
         * Add a single header with multiple values to be set on the HTTP request.
         *
         * <p>
         * This overrides any values already configured with this header name in the builder.
         *
         * @param name The name of the header.
         * @param values The values of the header.
         * @return This object for method chaining.
         */
        B header(String name, List<String> values);

        /**
         * Add additional headers to be set on the HTTP request.
         *
         * <p>
         * This completely overrides any values currently configured in the builder.
         *
         * @param headers The set of additional headers.
         * @return This object for method chaining.
         */
        B headers(Map<String, List<String>> headers);

        /**
         * Optional additional query parameters to be added to the HTTP request.
         *
         * @return The optional additional query parameters.
         */
        Map<String, List<String>> rawQueryParameters();

        /**
         * Add a single query parameter to be set on the HTTP request.
         *
         * <p>
         * This overrides any values already configured with this query name in the builder.
         *
         * @param name The query parameter name.
         * @param value The query parameter value.
         * @return This object for method chaining.
         */
        default B rawQueryParameter(String name, String value) {
            rawQueryParameter(name, Collections.singletonList(value));
            return (B) this;
        }

        /**
         * Add a single query parameter with multiple values to be set on the HTTP request.
         *
         * <p>
         * This overrides any values already configured with this query name in the builder.
         *
         * @param name The query parameter name.
         * @param values The query parameter values.
         * @return This object for method chaining.
         */
        B rawQueryParameter(String name, List<String> values);

        /**
         * Configure query parameters to be set on the HTTP request.
         *
         * <p>
         * This completely overrides any query parameters currently configured in the builder.
         *
         * @param rawQueryParameters The set of additional query parameters.
         * @return This object for method chaining.
         */
        B rawQueryParameters(Map<String, List<String>> rawQueryParameters);

        /**
         * The optional names of the higher level libraries that constructed the request.
         *
         * @return The names of the libraries.
         */
        List<ApiName> apiNames();

        /**
         * Set the optional name of the higher level library that constructed the request.
         *
         * @param apiName The name of the library.
         *
         * @return This object for method chaining.
         */
        B apiName(ApiName apiName);

        /**
         * Set the optional name of the higher level library that constructed the request.
         *
         * @param apiNameConsumer A {@link Consumer} that accepts a {@link ApiName.Builder}.
         *
         * @return This object for method chaining.
         */
        B apiName(Consumer<ApiName.Builder> apiNameConsumer);

        /**
         * Create a new {@code SdkRequestOverrideConfiguration} with the properties set on this builder.
         *
         * @return The new {@code SdkRequestOverrideConfiguration}.
         */
        RequestOverrideConfiguration build();
    }

    protected abstract static class BuilderImpl<B extends Builder> implements Builder<B> {
        private Map<String, List<String>> headers = new HashMap<>();

        private Map<String, List<String>> rawQueryParameters = new HashMap<>();

        private List<ApiName> apiNames = new ArrayList<>();

        protected BuilderImpl() {
        }

        protected BuilderImpl(RequestOverrideConfiguration sdkRequestOverrideConfig) {
            this.headers = new HashMap<>(sdkRequestOverrideConfig.headers);
            this.rawQueryParameters = new HashMap<>(sdkRequestOverrideConfig.rawQueryParameters);
            this.apiNames = new ArrayList<>(sdkRequestOverrideConfig.apiNames);
        }

        @Override
        public Map<String, List<String>> headers() {
            return headers;
        }

        @Override
        public B header(String name, List<String> values) {
            headers.put(name, new ArrayList<>(values));
            return (B) this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public B headers(Map<String, List<String>> headers) {
            this.headers = headers;
            return (B) this;
        }

        @Override
        public Map<String, List<String>> rawQueryParameters() {
            return rawQueryParameters;
        }

        @Override
        public B rawQueryParameter(String name, List<String> values) {
            rawQueryParameters.put(name, new ArrayList<>(values));
            return (B) this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public B rawQueryParameters(Map<String, List<String>> rawQueryParameters) {
            this.rawQueryParameters = rawQueryParameters;
            return (B) this;
        }

        @Override
        public List<ApiName> apiNames() {
            return apiNames;
        }

        @Override
        @SuppressWarnings("unchecked")
        public B apiName(ApiName apiName) {
            this.apiNames.add(apiName);
            return (B) this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public B apiName(Consumer<ApiName.Builder> apiNameConsumer) {
            ApiName.Builder b = ApiName.builder();
            apiNameConsumer.accept(b);
            apiName(b.build());
            return (B) this;
        }
    }
}
