package com.aws.kids.quiz.business.services;

import java.nio.ByteBuffer;

import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        AWSLambdaClient lambdaClient = new AWSLambdaClient();
        try {
        	 InvokeRequest invokeRequest = new InvokeRequest();
        	 invokeRequest.setFunctionName("KidsQuizDBLambda");
             invokeRequest.setPayload("{\"name\": \"user\",\"method\": \"GET\",\"values\": [{\"userID\": \"user1\"},{\"userID\": \"user2\"}]}");
             context.getLogger().log("Before Invoke");
             context.getLogger().log(invokeRequest.getFunctionName());
             ByteBuffer payload = lambdaClient.invoke(invokeRequest).getPayload();
             context.getLogger().log("After Inoke");
        }catch (Exception e) {
			// TODO: handle exception
        	context.getLogger().log(e.getMessage());
		}
        // TODO: implement your handler
        return "Hello from Lambda!";
    }

}
