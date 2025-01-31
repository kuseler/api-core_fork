package com.educoach.chat;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/chat")
public class ChatResourceV1 {

    // Injects the Rest Client interface created before
    @RestClient
    ChatGptService chatGpt;

    // Injects some of the required properties by Chat GPT
    @ConfigProperty(name = "openai.model")
    String openaiModel;

    @ConfigProperty(name = "openai.key")
    String openaiKey;

    // Endpoint definition
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/completion")
    public String completion(String prompt) {

        return chatGpt.completion(getBearer(),

            ChatGptRequest.newRequest(openaiModel, prompt))

        .choices().toString();

    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/create-lesson")
    public String createLesson(String topic) {

        //tip, wie ein prompt aussehen könnte:
        //`Imagine yourself as a teacher in a German Middle school from grade ${grade}. Your thoughts right now are that you want to create an exercise over: ${topic} in the Subject ${subject} based on the Online Reference: ${reference}. The tasksheet should be A4 size big with text size 12 and should include different tasks to train the subject as a whole. Make more tasks. Just show the sources, A4 tasks and solution sheet. Make more tasks. Return the answer as a template string of the html styled version. for example <h1>Hello</h1> would be a heading in the response string. Answer in German and leave out unnessecary formal contents.`;
        var promt = String.format("""
            Imagine yourself as a teacher in a German Middle school from grade 12. 
        Your thoughts right now are that you want to create an lesson over: %s 
        Plan a lesson for this topic.
        Return the answer as a template string of the html styled version. 
        for example <h1>Hello</h1> would be a heading in the response string. 
        Answer in German and leave out unnessecary formal contents.""", topic);
                

        var response = chatGpt.completion(getBearer(),
            ChatGptRequest.newRequest(openaiModel, promt))
            .choices();
        
        return response.get(0).message().content();

    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/create-exam")
    public String createExam(String topic) {

        //tip, wie ein prompt aussehen könnte:
        //`Imagine yourself as a teacher in a German Middle school from grade ${grade}. Your thoughts right now are that you want to create an exercise over: ${topic} in the Subject ${subject} based on the Online Reference: ${reference}. The tasksheet should be A4 size big with text size 12 and should include different tasks to train the subject as a whole. Make more tasks. Just show the sources, A4 tasks and solution sheet. Make more tasks. Return the answer as a template string of the html styled version. for example <h1>Hello</h1> would be a heading in the response string. Answer in German and leave out unnessecary formal contents.`;
        var promt = String.format("""
            Imagine yourself as a teacher in a German Middle school from grade 5. 
        Your thoughts right now are that you want to create an exercise over: %s 
        The tasksheet should be A4 size big with text size 12 and should include different tasks to train the subject as a whole. 
        Make more tasks. Just show the sources, A4 tasks and solution sheet. 
        Make more tasks. Return the answer as a template string of the html styled version. 
        for example <h1>Hello</h1> would be a heading in the response string. 
        Answer in German and leave out unnessecary formal contents.""", topic);
                

        var response = chatGpt.completion(getBearer(),
            ChatGptRequest.newRequest(openaiModel, promt))
            .choices();
        
        return response.get(0).message().content();

    }





    // Builds the Bearer token

    private String getBearer() {

        return "Bearer " + openaiKey;

    }

}