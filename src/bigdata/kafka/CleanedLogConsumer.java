package bigdata.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import bigdata.util.Util;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;

public class CleanedLogConsumer {

	public static void main(String[] args) {
		Properties prop=new Properties();
		prop.put("auto.offset.reset", "largest");
		prop.put("zookeeper.connect","hanson02:2181,hanson03:2181,hanson04:2181");
		prop.put("group.id", "g1");
		prop.put("zookeeper.session.timeout.ms", "400");
        prop.put("zookeeper.sync.time.ms", "200");
        prop.put("auto.commit.interval.ms", "1000");
		
		//创建消费者配置对象
        ConsumerConfig conf = new ConsumerConfig(prop);
		//创建连接器
        ConsumerConnector conn= kafka.consumer.Consumer.createJavaConsumerConnector(conf);
		
		Map<String,Integer> topicCount=new HashMap<String,Integer>(); 
		topicCount.put("test3", 1);
		
		//key==topic
		Map<String,List<KafkaStream<byte[],byte[]>>> map=conn.createMessageStreams(topicCount);
		List<KafkaStream<byte[],byte[]>> list=map.get("test3");
		for(KafkaStream<byte[],byte[]> s:list){
			ConsumerIterator<byte[], byte[]> it=s.iterator();
			while(it.hasNext()){
				MessageAndMetadata<byte[], byte[]> entry=it.next();
				String msg=new String(entry.message());
				String path="/user/hanson/hive/warehouse"+Util.getDate(msg)+"/a.log";
				msg=cleanData(msg);
				if(msg!=null){
					writeLog(msg,path);
				}
				String key="Null";
				if(entry.key()!=null){
					key=new String(entry.key());
				}
				System.out.println(key+":"+msg);
			}
		}
		 if (conn != null) conn.shutdown();   //其实执行不到，因为上面的hasNext会block
	}
	
	/**
	 * 将原生数据写入hdfs
	 */
	public static void writeLog(String line,String path){
		try {
			Configuration conf=new Configuration();
			conf.set("fs.defaultFS", "hdfs://hanson01:8020");
			FileSystem fs=FileSystem.get(conf);
			FSDataOutputStream out=fs.append(new Path(path));
			out.writeChars(line+"\r\n");
			out.hsync();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String cleanData(String line){
		String[] arr=line.split(",");
		String requestLine=null;
		String[] requestSplit=null;
		String request=null;
		if(arr!=null&&arr.length>5){
			requestLine=arr[3];
			requestSplit=requestLine.split(" ");
			request=requestSplit[1];
			if(request.endsWith(".html")||request.endsWith(".htm")){
				return line;
			}
		}
		return null;
	}
}
