import std.regex;
//import std.range;
import std.stdio;
import std.string;
import std.array;

void main()
{
	// nginx
	auto r = regex(`^(\S+) (\S+) (\S+) \[(.+)\] "([^"]+)" ([0-9]{3}) ([0-9]+) "([^"]+)" "([^"]+)" "([^"]+)"`);
	// apache2
	//auto r = regex(`^(\S+) (\S+) (\S+) \[(.+)\] "([^"]+)" ([0-9]{3}) ([0-9]+) "([^"]+)" "([^"]+)"`);
	foreach(line; stdin.byLine)
	{
		//writeln(line);
		//auto m = match(line, r);
		foreach(m; match(line, r)){
			//writeln(m.hit);
			auto c = m.captures;
			c.popFront();
			//writeln(c);
			/*
			SQL
			auto value = join(c, "\",\"");
			auto sql = format("insert into log(remote_addr,unknow,remote_user,time_local,request,status,body_bytes_sent,http_referer,http_user_agent,http_x_forwarded_for) value(\"%s\");", value );
			writeln(sql);
			*/
			// MongoDB
			string bson = format("db.access.save({
						'remote_addr': '%s',
						'remote_user': '%s',
						'time_local': '%s',
						'request': '%s',
						'status': '%s',
						'body_bytes_sent':'%s',
						'http_referer': '%s',
						'http_user_agent': '%s',
						'http_x_forwarded_for': '%s'
						})",
						c[0],c[2],c[3],c[4],c[5],c[6],c[7],c[8],c[9]
						);
			writeln(bson);
			
		}
	}

}
