package com.example.awslogservice;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spark.Request;
import spark.Response;

import static org.mockito.Mockito.*;

class LogServiceTest {

    @Mock
    private MongoDatabase mockDatabase;

    @Mock
    private MongoCollection<Document> mockCollection;

    private LogList logList;
    private LogService logService;
    private LogServiceFacade logServiceFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockDatabase.getCollection("logs")).thenReturn(mockCollection);
        logList = new LogList(mockDatabase);
        logService = new LogService();
        logServiceFacade = new LogServiceFacade();
    }

    @Test
    void addLogTest() {
        String date = "2024-03-13";
        String description = "Test log entry";
        logList.addLog(date, description);
        verify(mockCollection).insertOne(any(Document.class));
    }

    @Test
    void listLogsTest() {

        Request mockRequest = mock(Request.class);
        Response mockResponse = mock(Response.class);

        // Mocking behavior of request
        when(mockRequest.queryParams("msg")).thenReturn("Test message");

        // Mocking behavior of response
        doNothing().when(mockResponse).type(anyString());

        //logService.main(mockRequest, mockResponse);


        //verify(mockResponse).type("application/json");

    }

    @Test
    void getLogsTest() {

        Request mockRequest = mock(Request.class);
        Response mockResponse = mock(Response.class);

        when(mockRequest.queryParams("msg")).thenReturn("Test message");

        doNothing().when(mockResponse).type(anyString());

        //logServiceFacade.main(mockRequest, mockResponse);

        //verify(mockResponse).type("application/json");

    }
}
