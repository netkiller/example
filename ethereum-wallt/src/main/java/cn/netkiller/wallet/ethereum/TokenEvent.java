//package cn.netkiller.wallet.ethereum;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//import org.web3j.abi.EventEncoder;
//import org.web3j.abi.EventValues;
//import org.web3j.abi.TypeReference;
//import org.web3j.abi.datatypes.Address;
//import org.web3j.abi.datatypes.Event;
//import org.web3j.abi.datatypes.generated.Uint256;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.DefaultBlockParameterName;
//import org.web3j.protocol.core.methods.response.EthFilter;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.protocol.ipc.UnixIpcService;
//
//public class TokenEvent extends Token {
//	private String contractAddress = null;
//	private String privateKey = null;
//
//	public TokenEvent(String contractAddress, String privateKey) throws IOException  {
//		 super(contractAddress, privateKey);
//		this.contractAddress = contractAddress;
//		this.privateKey = privateKey;
//		// web3 = Web3j.build(new HttpService("Http://localhost:8545"));
//		web3 = Web3j.build(new UnixIpcService("/Users/neo/Library/Ethereum/geth.ipc"));
//	}
//
//	/* event transfer(address indexed _from, address indexed _to, uint indexed value); */
//
//	public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
//		final Event event = new Event("Transfer", Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
//		}, new TypeReference<Address>() {
//		}), Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
//		}));
//		List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
//		ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
//		for (EventValues eventValues : valueList) {
//			TransferEventResponse typedResponse = new TransferEventResponse();
//			typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
//			typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
//			typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
//			responses.add(typedResponse);
//		}
//		return responses;
//	}
//
//	void listenEvent() {
//		Event event = new Event("transfer", Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
//		}), Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
//		}));
//		EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, this.contractAddress);
//		filter.addSingleTopic(EventEncoder.encode(event));
//		web3.ethLogObservable(filter).subscribe(new Action1<Log>() {
//			@Override
//			public void call(Log log) {
//				System.println(log.toString());
//			}
//		});
//	}
//
//	public static class TransferEventResponse {
//		public Address _from;
//
//		public Address _to;
//
//		public Uint256 _value;
//
//		public String _transactionHash;
//	}
//
//	public static class ApprovalEventResponse {
//		public Address _owner;
//
//		public Address _spender;
//
//		public Uint256 _value;
//
//		public String _transactionHash;
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
