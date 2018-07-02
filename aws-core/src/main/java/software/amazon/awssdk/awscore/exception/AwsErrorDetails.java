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

package software.amazon.awssdk.awscore.exception;

import java.io.Serializable;
import java.util.Map;

import software.amazon.awssdk.annotations.SdkPublicApi;

@SdkPublicApi
public class AwsErrorDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String errorMessage;

    private final String errorCode;

    private final String serviceName;

    private final Map<String, String> headers;

    private final byte[] rawResponse;

    protected AwsErrorDetails(Builder b) {
        this.errorMessage = b.errorMessage();
        this.errorCode = b.errorCode();
        this.serviceName = b.serviceName();
        this.headers = b.headers();
        this.rawResponse = b.rawResponse();
    }

    /**
     * Returns the name of the service that sent this error response.
     *
     * @return The name of the service that sent this error response.
     */
    public String serviceName() {
        return serviceName;
    }

    /**
     * @return the human-readable error message provided by the service.
     */
    public String errorMessage() {
        return errorMessage;
    }

    /**
     * Returns the error code associated with the response.
     */
    public String errorCode() {
        return errorCode;
    }

    /**
     * Returns the response payload as bytes.
     */
    public byte[] rawResponse() {
        return rawResponse == null ? null : rawResponse.clone();
    }

    /**
     * Returns a map of HTTP headers associated with the error response.
     */
    public Map<String, String> headers() {
        return headers;
    }

    /**
     * @return {@link SdkException.Builder} instance to construct a new {@link AwsErrorDetails}.
     */
    public static Builder builder() {
        return new BuilderImpl();
    }

    /**
     * Create a {@link AwsErrorDetails.Builder} initialized with the properties of this {@code AwsErrorDetails}.
     *
     * @return A new builder initialized with this config's properties.
     */
    public Builder toBuilder() {
        return new BuilderImpl(this);
    }

    public static Class<? extends Builder> serializableBuilderClass() {
        return BuilderImpl.class;
    }

    public interface Builder {
        /**
         * Specifies the error message returned by the service.
         *
         * @param errorMessage The error message returned by the service.
         * @return This object for method chaining.
         */
        Builder errorMessage(String errorMessage);

        /**
         * The error message specified by the service.
         *
         * @return The error message specified by the service.
         */
        String errorMessage();

        /**
         * Specifies the error code returned by the service.
         *
         * @param errorCode The error code returned by the service.
         * @return This object for method chaining.
         */
        Builder errorCode(String errorCode);

        /**
         * The error code specified by the service.
         *
         * @return The error code specified by the service.
         */
        String errorCode();

        /**
         * Specifies the name of the service that returned this error.
         *
         * @param serviceName The name of the service.
         * @return This object for method chaining.
         */
        Builder serviceName(String serviceName);

        /**
         * The name of the service that returned this error.
         *
         * @return The name of the service that returned this error.
         */
        String serviceName();

        /**
         * Specifies the headers returned on the error response from the service.
         *
         * @param headers A map of headers from the response.
         * @return This object for method chaining.
         */
        Builder headers(Map<String, String> headers);

        /**
         * The headers returned on the response from the service.
         *
         * @return A map of headers from the response.
         */
        Map<String, String> headers();

        /**
         * Specifies raw http response from the service.
         *
         * @param rawResponse raw byte response from the service.
         * @return The object for method chaining.
         */
        Builder rawResponse(byte[] rawResponse);

        /**
         * The raw response from the service.
         *
         * @return The raw response from the service in a byte array.
         */
        byte[] rawResponse();

        /**
         * Creates a new {@link AwsErrorDetails} with the properties set on this builder.
         *
         * @return The new {@link AwsErrorDetails}.
         */
        AwsErrorDetails build();
    }

    protected static final class BuilderImpl implements Builder {

        private String errorMessage;
        private String errorCode;
        private String serviceName;
        private Map<String, String> headers;
        private byte[] rawResponse;

        private BuilderImpl() {}

        private BuilderImpl(AwsErrorDetails awsErrorDetails) {
            this.errorMessage = awsErrorDetails.errorMessage();
            this.errorCode = awsErrorDetails.errorCode();
            this.serviceName = awsErrorDetails.serviceName();
            this.headers = awsErrorDetails.headers();
            this.rawResponse = awsErrorDetails.rawResponse();
        }

        @Override
        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        @Override
        public String errorMessage() {
            return errorMessage;
        }

        @Override
        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        @Override
        public String errorCode() {
            return errorCode;
        }

        @Override
        public Builder serviceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        @Override
        public String serviceName() {
            return serviceName;
        }

        @Override
        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        @Override
        public Map<String, String> headers() {
            return headers;
        }

        @Override
        public Builder rawResponse(byte[] rawResponse) {
            this.rawResponse = rawResponse == null ? null : rawResponse.clone();
            return this;
        }

        @Override
        public byte[] rawResponse() {
            return rawResponse == null ? null : rawResponse.clone();
        }

        @Override
        public AwsErrorDetails build() {
            return new AwsErrorDetails(this);
        }
    }

}
