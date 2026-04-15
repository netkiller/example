//package cn.netkiller.ethereum.account;
//
//public class ParityAccount {
//	private static final String url = "http://172.16.0.1:8545/";
//	private static final Parity parity = Parity.build(new HttpService(url));
//
//	public String createAccount(String accountName, String password, AccountInfo
//			 accountInfo) { 
//				
//				
//				
//				try { 
//				 
//				 
//				 NewAccountIdentifier newAccountIdentifier =
//			 web3j.ethAccounts().send()..personalNewAccount(password).send(); if
//			 (newAccountIdentifier != null) { String accountId =
//			 newAccountIdentifier.getAccountId(); parity.personalSetAccountName(accountId,
//			 accountName);
//			 
//			 Map<String, Object> account = new HashMap<String, Object>();
//			 account.put(accountId, accountInfo); parity.personalSetAccountMeta(accountId,
//			 account);
//			 
//			 return accountId; } } catch (Exception e) { e.printStackTrace(); } return
//			 null; }
//
//	/*
//	 * public PersonalAccountsInfo.AccountsInfo getAccountInfo(String accountId) {
//	 * 
//	 * try { PersonalAccountsInfo personalAccountsInfo =
//	 * parity.personalAccountsInfo().send();
//	 * 
//	 * return personalAccountsInfo.getAccountsInfo().get(accountId); } catch
//	 * (Exception e) { e.printStackTrace(); } return null; }
//	 * 
//	 * public BigInteger getBalance(String accountId) { try { DefaultBlockParameter
//	 * defaultBlockParameter = new DefaultBlockParameterNumber(58); EthGetBalance
//	 * ethGetBalance = parity.ethGetBalance(accountId,
//	 * defaultBlockParameter).send(); if (ethGetBalance != null) { return
//	 * ethGetBalance.getBalance(); } } catch (Exception e) { e.printStackTrace(); }
//	 * return null; }
//	 * 
//	 * }
//	 */
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
