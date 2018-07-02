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
 * Base type for all client exceptions thrown by the SDK.
 *
 * This exception is thrown when service could not be contacted for a response,
 * or when client is unable to parse the response from service.
 *
 * All exceptions that extend {@link SdkClientException} are assumed to be
 * not retryable.
 *
 * @see SdkServiceException
 */
@SdkPublicApi
public class SdkClientException extends SdkException {

    protected SdkClientException(Builder b) {
        super(b);
    }

    public static Builder builder() {
        return new BuilderImpl();
    }

    public interface Builder extends SdkException.Builder {

        @Override
        Builder message(String message);

        @Override
        Builder throwable(Throwable t);

        @Override
        SdkClientException build();
    }

    protected static class BuilderImpl extends SdkException.BuilderImpl implements Builder {

        @Override
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public Builder throwable(Throwable throwable) {
            this.throwable = throwable;
            return this;
        }

        @Override
        public SdkClientException build() {
            return new SdkClientException(this);
        }
    }
}
