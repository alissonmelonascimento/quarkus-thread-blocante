package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/threads")
public class GreetingResource {

    @ConfigProperty(name = "max-thread")
    Integer maxThread;

    @ConfigProperty(name = "thread-sleep")
    Integer threadSleep;    

    Integer count = 0;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws InterruptedException {
        Integer myCount = null;

        synchronized(count){
            count = count + 1;
            myCount = count;
        }       

        System.out.println("Thread: "+myCount);

        if(myCount <= maxThread){
            System.out.println("Interrompendo thread: "+myCount);
            Thread.sleep(threadSleep);
            System.out.println("Liberando thread: "+myCount);
        }

        return "Retornando thread: "+myCount;
    }
}