package software.amazon.awssdk.services.jsonprotocoltests.model;

import javax.annotation.Generated;
import software.amazon.awssdk.awscore.exception.AwsErrorDetails;
import software.amazon.awssdk.utils.builder.CopyableBuilder;
import software.amazon.awssdk.utils.builder.ToCopyableBuilder;

/**
 */
@Generated("software.amazon.awssdk:codegen")
public final class EmptyModeledException extends JsonProtocolTestsException implements
        ToCopyableBuilder<EmptyModeledException.Builder, EmptyModeledException> {
    private EmptyModeledException(BuilderImpl builder) {
        super(builder);
    }

    @Override
    public Builder toBuilder() {
        return new BuilderImpl(this);
    }

    public static Builder builder() {
        return new BuilderImpl();
    }

    public static Class<? extends Builder> serializableBuilderClass() {
        return BuilderImpl.class;
    }

    public interface Builder extends CopyableBuilder<Builder, EmptyModeledException>, JsonProtocolTestsException.Builder {
        @Override
        Builder awsErrorDetails(AwsErrorDetails awsErrorDetails);

        @Override
        Builder message(String message);

        @Override
        Builder requestId(String requestId);

        @Override
        Builder statusCode(int statusCode);

        @Override
        Builder throwable(Throwable throwable);
    }

    static final class BuilderImpl extends JsonProtocolTestsException.BuilderImpl implements Builder {
        private BuilderImpl() {
        }

        private BuilderImpl(EmptyModeledException model) {
            super(model);
        }

        @Override
        public BuilderImpl awsErrorDetails(AwsErrorDetails awsErrorDetails) {
            this.awsErrorDetails = awsErrorDetails;
            return this;
        }

        @Override
        public BuilderImpl message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public BuilderImpl requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        @Override
        public BuilderImpl statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        @Override
        public BuilderImpl throwable(Throwable throwable) {
            this.throwable = throwable;
            return this;
        }

        @Override
        public EmptyModeledException build() {
            return new EmptyModeledException(this);
        }
    }
}
