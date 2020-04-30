package com.kishore.servicebus;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import com.kishore.servicebus.vo.Course;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;

/**
 * Azure Functions with HTTP Trigger.
 */
public class ServiceBusQueueTriggerFunctionCourse {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("FunctionCourseQueue")
    public void run(
    		@ServiceBusQueueTrigger(name = "CourseQueueFirstListner", dataType="binary", queueName = "coursequeue", connection = "serviceBusConnection")  byte[] message,
            final ExecutionContext context) {
        context.getLogger().info("Java @ServiceBusQueueTrigger processed a messag course Id."+message);
        context.getLogger().info("Java @ServiceBusQueueTrigger processed a messag course Name."+message);
        try
        {
        ByteArrayInputStream in = new ByteArrayInputStream(message);
        ObjectInputStream is = new ObjectInputStream(in);
        Course course=(Course)is.readObject();
        context.getLogger().info("Object"+course.getCourseId());
        }
        catch(Exception e)
        {
        	context.getLogger().info("Exception"+e);
        }
    }
}
