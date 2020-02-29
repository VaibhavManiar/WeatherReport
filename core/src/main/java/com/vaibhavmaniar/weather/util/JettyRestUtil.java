package com.vaibhavmaniar.weather.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpContentResponse;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.io.ConnectionStatistics;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JettyRestUtil {

    private final HttpClient client;
    private final ObjectMapper mapper;

    public JettyRestUtil(ObjectMapper mapper,
                         int maxConnectionPerHost,
                         int connectTimeoutInMs,
                         int idleTimeoutInMs,
                         int maxRequestQueuePerDestination,
                         ExecutorService executorService,
                         ConnectionStatistics connectionStatistics) {
        this.client = createJettyClient(maxConnectionPerHost, connectTimeoutInMs, idleTimeoutInMs, maxRequestQueuePerDestination, executorService, connectionStatistics);
        this.mapper = mapper;
    }

    public JettyRestUtil(ObjectMapper mapper,
                         int maxConnectionPerHost,
                         int connectTimeoutInMs,
                         int idleTimeoutInMs,
                         int maxRequestQueuePerDestination) {
        this.client = createJettyClient(maxConnectionPerHost, connectTimeoutInMs, idleTimeoutInMs, maxRequestQueuePerDestination, Executors.newFixedThreadPool(10), null);
        this.mapper = mapper;
    }

    public <Req, Res> Res getAsync(Req req, Map<String, String> requestParams, String url, Response.CompleteListener responseListner) throws Exception {
        client.newRequest(url)
                .send(/*new Response.CompleteListener()
                {
                    @Override
                    public void onComplete(Result result) {
                        String data = ((HttpContentResponse) result.getResponse()).getContentAsString();
                        System.out.println(data);
                    }
                })*/ responseListner);

        return null;
    }

    public <Res> Res get(String url, Class<Res> resClass) {
        try {
            HttpContentResponse contentResponse = (HttpContentResponse) client.newRequest(url).method(HttpMethod.GET).header("WWW-Authenticate", "Basic").send();
            //HttpContentResponse contentResponse = (HttpContentResponse) client.GET(url);
            if(contentResponse.getStatus() == 200) {
                return mapper.readValue(contentResponse.getContent(), resClass);
            }
            throw new RuntimeException("Get call [" +url+ "] failed with status : " + contentResponse.getStatus() + " and error : " + contentResponse.getReason());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private HttpClient createJettyClient(int maxConnectionPerHost, int connectTimeoutInMs, int idleTimeoutInMs, int maxRequestQueuePerDestination, ExecutorService executorService, ConnectionStatistics connectionStatistics) {
        HttpClient httpClient = new HttpClient(new SslContextFactory.Client(true));
        httpClient.setMaxRequestsQueuedPerDestination(maxRequestQueuePerDestination);
        httpClient.setIdleTimeout((long)idleTimeoutInMs);
        httpClient.setMaxConnectionsPerDestination(maxConnectionPerHost);
        httpClient.setFollowRedirects(false);
        httpClient.setConnectTimeout((long)connectTimeoutInMs);
        httpClient.setExecutor(executorService);
        if (connectionStatistics != null) {
            httpClient.addBean(connectionStatistics);
        }
        try {
            httpClient.start();
            return httpClient;
        } catch (Exception var10) {
            throw new RuntimeException(var10);
        }
    }
}
