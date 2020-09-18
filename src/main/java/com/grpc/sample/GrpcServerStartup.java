package com.grpc.sample;

import com.grpc.sample.service.impl.SampleGrpcServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GrpcServerStartup {
    private static final Logger LOGGER = Logger.getLogger(GrpcServerStartup.class.getName());
    int port = 8090;
    private Server server;

    private void start() throws IOException, InterruptedException {
        server = ServerBuilder.forPort(port)
                .addService(new SampleGrpcServiceImpl())
                .build()
                .start();
        LOGGER.info("GRPC server start on " + port);
        Runtime.getRuntime().addShutdownHook( new Thread() {
            @Override
          public void run() {
                System.err.println("**** Shutting down GRPC Server");
                try {
                    GrpcServerStartup.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
       });

    }


    private void stop() throws InterruptedException {
        if(null != server){
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }
    private void blockUnitShutDown() throws InterruptedException {
        if(null != server){
            server.awaitTermination();
        }
    }
    public static void main(String... args) throws IOException, InterruptedException {
        final GrpcServerStartup grpcServerStartup = new GrpcServerStartup();
        grpcServerStartup.start();
        grpcServerStartup.blockUnitShutDown();
    }


}
