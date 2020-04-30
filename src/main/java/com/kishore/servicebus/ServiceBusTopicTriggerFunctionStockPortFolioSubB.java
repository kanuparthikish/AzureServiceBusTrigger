package com.kishore.servicebus;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import com.kishore.servicebus.vo.Course;
import com.kishore.servicebus.vo.StockPortFolio;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;
import com.microsoft.azure.functions.annotation.ServiceBusTopicTrigger;

/**
 * Azure Functions with HTTP Trigger.
 */
public class ServiceBusTopicTriggerFunctionStockPortFolioSubB {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("FunctionTopicStockPortSubB")
    public void run(
    		@ServiceBusTopicTrigger(name = "SUBBTopicItemListner", dataType="binary", topicName = "stockquoteprice",
            subscriptionName = "StockAgencyB", connection = "serviceBusConnection")  byte[] message,
          final ExecutionContext context){
    	 context.getLogger().info("Java @@ServiceBusTopicTrigger processed a message."+message);
         context.getLogger().info("Java @@ServiceBusTopicTrigger processed a messag "+message);
         try
         {
        	 ByteArrayInputStream in = new ByteArrayInputStream(message);
             context.getLogger().info("Java @@ServiceBusTopicTrigger processed a ByteArrayInputStream "+in);
             ObjectInputStream is = new ObjectInputStream(in);
             context.getLogger().info("Java @@ServiceBusTopicTrigger processed a is "+is);
             StockPortFolio stockPortFolio=(StockPortFolio)is.readObject();
             context.getLogger().info("Java @@ServiceBusTopicTrigger processed a stockPortFolio"+stockPortFolio.getStockSymbol());
             context.getLogger().info("Java @@ServiceBusTopicTrigger processed a stockPortFolio "+stockPortFolio);
             context.getLogger().info("Java @@ServiceBusTopicTrigger processed a stockPortFolio"+stockPortFolio.getStockSymbol());
         }
        catch(Exception e)
        {
        	context.getLogger().info("Exception"+e+"$$$$"+e.getMessage());
        }
    }
}
