package cn.netkiller.ipfs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

public class Demo {
	IPFS ipfs = null;

	public Demo() throws IOException {
		// ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
		ipfs = new IPFS("/ip4/47.89.15.169/tcp/5001");
		System.out.println(ipfs.version());
	}

	public List<Multihash> local() throws IOException {
		// System.out.println(ipfs.refs.local().toString());
		return ipfs.refs.local();
	}

	public MerkleNode add(File file) throws IOException {
		NamedStreamable.FileWrapper fileWrapper = new NamedStreamable.FileWrapper(file);
		MerkleNode addResult = ipfs.add(fileWrapper).get(0);
		return addResult;
	}

	public List<MerkleNode> add(Path path) throws IOException {
		NamedStreamable file = new NamedStreamable.FileWrapper(path.toFile());
		List<MerkleNode> tree = ipfs.add(file);
		return tree;
	}

	public MerkleNode put() throws IOException {
		NamedStreamable.ByteArrayWrapper text = new NamedStreamable.ByteArrayWrapper("url", "http://www.netkiller.cn".getBytes());
		MerkleNode addResult = ipfs.add(text).get(0);
		return addResult;
	}

	public String get(String hash) throws IOException {
		Multihash filePointer = Multihash.fromBase58(hash);
		// ipfs.dag.
		byte[] fileContents = ipfs.cat(filePointer);

		String text = new String(fileContents, StandardCharsets.UTF_8);
		return text;
	}

	public MerkleNode mkdir(String name) throws IOException {
		NamedStreamable.DirWrapper dir = new NamedStreamable.DirWrapper(name, Arrays.asList());
		MerkleNode addResult = ipfs.add(dir).get(0);
		return addResult;
	}

	public List<MerkleNode> list(Multihash hash) throws IOException {
		List<MerkleNode> ls = ipfs.ls(hash);
		return ls;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Demo demo = new Demo();
			// MerkleNode add = demo.add(new File("/tmp/hello.txt"));
			MerkleNode add = demo.add(new File("/Users/neo/Downloads/u/1536896811406807.mp4"));

			System.out.println(add.toJSONString());

			Path path = Paths.get("src/test");
			List<MerkleNode> tree = demo.add(path);
			for (MerkleNode mn : tree) {
				System.out.println(mn.toJSONString());
			}

			MerkleNode put = demo.put();
			System.out.println(put.toJSONString());
			String text = demo.get(add.hash.toBase58());
			System.out.println(text);

			MerkleNode dir = demo.mkdir("/www/web.example.cn");

			System.out.println(dir.toJSONString());

			List<Multihash> list = demo.local();
			for (Multihash hash : list) {

				System.out.println(hash.toString());
				System.out.println(hash.toBase58());
				List<MerkleNode> ls = demo.list(hash);
				System.out.println(ls.get(0).toJSONString());
				// System.out.println(list.getHash().toString());
				System.out.println(demo.get(hash.toBase58()));
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
