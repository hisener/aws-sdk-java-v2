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

package software.amazon.awssdk.core.exception;

import software.amazon.awssdk.annotations.SdkPublicApi;

/**
 * Base class for all exceptions thrown by the SDK.
 *
 * @see SdkServiceException
 * @see SdkClientException
 */
@SdkPublicApi
public class SdkException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected SdkException(Builder builder) {
        super(builder.message(), builder.throwable());
    }

    /**
     * Specifies whether or not an exception can be expected to succeed on a retry.
     */
    public boolean retryable() {
        return false;
    }

    /**
     * Create a {@link SdkException.Builder} initialized with the properties of this {@code SdkException}.
     *
     * @return A new builder initialized with this config's properties.
     */
    public Builder toBuilder() {
        return new BuilderImpl(this);
    }

    /**
     * @return {@link Builder} instance to construct a new {@link SdkException}.
     */
    public static Builder builder() {
        return new BuilderImpl();
    }

    public interface Builder {
        /**
         * Specifies the exception that caused this exception to occur.
         *
         * @param t The exception that caused this exception to occur.
         * @return This object for method chaining.
         */
        Builder throwable(Throwable t);

        /**
         * The exception that caused this exception to occur.
         *
         * @return The exception that caused this exception to occur.
         */
        Throwable throwable();

        /**
         * Specifies the details of this exception.
         *
         * @param message The details of this exception.
         * @return This method for object chaining
         */
        Builder message(String message);

        /**
         * The details of this exception.
         *
         * @return Details of this exception.
         */
        String message();

        /**
         * Creates a new {@link SdkException} with the specified properties.
         *
         * @return The new {@link SdkException}.
         */
        SdkException build();
    }

    protected static class BuilderImpl implements Builder {

        protected Throwable throwable;
        protected String message;

        protected BuilderImpl() {}

        protected BuilderImpl(SdkException ex) {
            this.throwable = ex.getCause();
            this.message = ex.getMessage();
        }


        public Throwable getThrowable() {
            return throwable;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
        }

        @Override
        public Builder throwable(Throwable throwable) {
            this.throwable = throwable;
            return this;
        }

        @Override
        public Throwable throwable() {
            return throwable;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String message() {
            return message;
        }

        public SdkException build() {
            return new SdkException(this);
        }

    }
}
