package com.grpc.sample.service.impl;

import com.grpc.sample.SampleGRPCApp;
import com.grpc.sample.SampleGrpcServiceGrpc;
import com.grpc.sample.service.SampleGrpcService;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class SampleGrpcServiceImpl extends SampleGrpcServiceGrpc.SampleGrpcServiceImplBase  {
  private static final Logger LOGGER = Logger.getLogger(SampleGrpcServiceImpl.class.getName());
    @Override
    public void getMessage(SampleGRPCApp.gRPCRequest request, StreamObserver<SampleGRPCApp.gRPCResponse> response) {
        LOGGER.info("gRPC request " + request.getMessage());
        SampleGRPCApp.gRPCResponse  gRPCResponse = SampleGRPCApp.
                        gRPCResponse.newBuilder()
                .setMessage("Response from gRPC service")
                .build();
        response.onNext(gRPCResponse);
        response.onCompleted();
    }
}
