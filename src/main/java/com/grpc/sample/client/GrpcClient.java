package com.grpc.sample.client;

import com.grpc.sample.SampleGRPCApp;
import com.grpc.sample.SampleGrpcServiceGrpc;
import com.grpc.sample.service.impl.SampleGrpcServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

public class GrpcClient {
    private static final Logger LOGGER = Logger.getLogger(SampleGrpcServiceImpl.class.getName());
    private final SampleGrpcServiceGrpc.SampleGrpcServiceBlockingStub blockingStub;
    private final static String serverURL = "localhost:8090";


    public GrpcClient(ManagedChannel channel) {
        this.blockingStub = SampleGrpcServiceGrpc.newBlockingStub(channel);
    }

    public void invokeGrpcSampleService() {
        SampleGRPCApp.gRPCRequest request = SampleGRPCApp
                .gRPCRequest
                .newBuilder()
                .setMessage("Request from GRPC Client")
                .build();
        SampleGRPCApp.gRPCResponse rpcResponse;
        rpcResponse = blockingStub.getMessage(request);
        LOGGER.info(rpcResponse.getMessage());

    }

    public static void main(String... args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverURL)
                .usePlaintext()
                .build();
        GrpcClient grpcClient = new GrpcClient(channel);
        grpcClient.invokeGrpcSampleService();


    }
}
