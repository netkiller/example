package cn.netkiller.example.ethereum.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class Netkiller extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b60408051908101604052600781527f64656661756c740000000000000000000000000000000000000000000000000060208201526000908051610056929160200190610060565b50600180556100fb565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100a157805160ff19168380011785556100ce565b828001600101855582156100ce579182015b828111156100ce5782518255916020019190600101906100b3565b506100da9291506100de565b5090565b6100f891905b808211156100da57600081556001016100e4565b90565b6103238061010a6000396000f3006060604052600436106100615763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166317d7de7c81146100665780636a5aa5ec146100f0578063c47f002714610108578063cc13962a14610159575b600080fd5b341561007157600080fd5b610079610181565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156100b557808201518382015260200161009d565b50505050905090810190601f1680156100e25780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156100fb57600080fd5b61010660043561022a565b005b341561011357600080fd5b61010660046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061022f95505050505050565b341561016457600080fd5b61016f600435610246565b60405190815260200160405180910390f35b61018961024d565b60008054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561021f5780601f106101f45761010080835404028352916020019161021f565b820191906000526020600020905b81548152906001019060200180831161020257829003601f168201915b505050505090505b90565b600155565b600081805161024292916020019061025f565b5050565b6001540190565b60206040519081016040526000815290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102a057805160ff19168380011785556102cd565b828001600101855582156102cd579182015b828111156102cd5782518255916020019190600101906102b2565b506102d99291506102dd565b5090565b61022791905b808211156102d957600081556001016102e35600a165627a7a723058202b664b28e76f288f990d718f1f6d47ce0a4225048861f8c2fb13b199ccc832e90029";

    protected Netkiller(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Netkiller(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> getName() {
        Function function = new Function("getName", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setNum(BigInteger n) {
        Function function = new Function(
                "setNum", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Int256(n)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setName(String _name) {
        Function function = new Function(
                "setName", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> addNum(BigInteger m) {
        Function function = new Function("addNum", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Int256(m)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<Netkiller> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Netkiller.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Netkiller> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Netkiller.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Netkiller load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Netkiller(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Netkiller load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Netkiller(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
