package cn.netkiller.elastic;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
//        Settings settings = Settings.builder()
//				.put("cluster.name", "elasticsearch") //集群名称
//				.put("client.transport.sniff", true) //自动嗅探
//				.put("discovery.type", "zen")
//				.put("discovery.zen.minimum_master_nodes", 1)
//				.put("discovery.zen.ping_timeout", "500ms")
//				.put("discovery.initial_state_timeout", "500ms")
//				.build();
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        
		try {
			Client client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("119.29.241.95"), 9300));
		
			SearchResponse response = client.prepareSearch("information").setTypes("article")
					.setFetchSource(new String[] { "id", "title", "description", "image", "ctime" }, null)
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addSort("ctime", SortOrder.DESC)
//					// .setQuery(QueryBuilders.termQuery("title", "description")) // Query
//					// .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18)) // Filter
					.setFrom(0).setSize(20).setExplain(true).get();
			
//			SearchRequestBuilder searchRequestBuilder = client.prepareSearch("information").setTypes("article").setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addSort("ctime", SortOrder.DESC);

//			searchRequestBuilder.setFetchSource(new String[] { "id", "title", "description", "image", "ctime" }, null);
//			if (tags != null && !tags.equals("")) {
//				// logger.info(tags);
//				searchRequestBuilder.setQuery(QueryBuilders.matchQuery("tags", tags));
//			}
//			searchRequestBuilder.setPostFilter(QueryBuilders.termQuery("site_id", "22")).setFrom(0).setSize(10).setExplain(true);

//			logger.info(searchRequestBuilder.toString());
//			SearchResponse response = searchRequestBuilder.get();
			
			
			System.out.println(response.getHits().getTotalHits());
			
			for (final SearchHit hit : response.getHits().getHits()) {
				
				System.out.println(hit.getSourceAsString());
			}
			
//			 
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
