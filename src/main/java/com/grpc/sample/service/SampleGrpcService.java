package com.grpc.sample.service;

import com.grpc.sample.SampleGRPCApp;
import io.grpc.stub.StreamObserver;

public interface SampleGrpcService {
    void getMessage(SampleGRPCApp.gRPCRequest request, StreamObserver<SampleGRPCApp.gRPCResponse >response);
}
