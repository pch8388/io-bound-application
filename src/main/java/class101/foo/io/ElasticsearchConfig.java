package class101.foo.io;

import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {
	@Value("#{'${spring.data.elasticsearch.hosts}'.split(',')}")
	private List<String> hosts;

	@Value("${spring.data.elasticsearch.port}")
	private int port;

	@Bean
	public RestHighLevelClient getRestClient() {
		RestClientBuilder builder = RestClient.builder(hosts.stream()
			.map(host -> new HttpHost(host, port, "http")).toArray(HttpHost[]::new));
		return new RestHighLevelClient(builder);
	}
}
