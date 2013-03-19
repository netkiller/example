import std.regex;
//import std.range;
import std.stdio;
import std.string;
import std.array;

void main()
{
/*
        auto m = match("@abc#", regex(`(\w)(\w)(\w)`));
        auto c = m.captures;
	writeln(c);
        assert(c.pre == "@"); // Part of input preceeding match
        assert(c.post == "#"); // Immediately after match
        assert(c.hit == c[0] && c.hit == "abc"); // The whole match
        assert(c[2] =="b");
        assert(c.front == "abc");
        c.popFront();
        assert(c.front == "a");
        assert(c.back == "c");
        c.popBack();
        assert(c.back == "b");
        popFrontN(c, 2);
        assert(c.empty);
*/
      //auto r = regex(r"");
	auto r = regex(`^(\S+) (\S+) (\S+) \[(.+)\] "([^"]+)" ([0-9]{3}) ([0-9]+) "([^"]+)" "([^"]+)" "([^"]+)"`);
	foreach(line; stdin.byLine)
	{
        	// to get all subsequent matches.
		//writeln(line);
		//auto m = match(line, r);
		foreach(m; match(line, r)){
			//writeln(m.hit);
			auto c = m.captures;
			c.popFront();
			//writeln(c);
			auto value = join(c, "\",\"");
			auto sql = format("insert into log(remote_addr,unknow,remote_user,time_local,request,status,body_bytes_sent,http_referer,http_user_agent,http_x_forwarded_for) value(\"%s\");", value );
			writeln(sql);
		}
	}

}
