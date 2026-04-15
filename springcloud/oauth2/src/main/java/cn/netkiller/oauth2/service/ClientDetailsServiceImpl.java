//package cn.netkiller.oauth2.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ClientDetailsServiceImpl implements ClientDetailsService {
//	@Autowired
//	PasswordEncoder passwordEncoder;
//
////	@Autowired
////	DasFeignService dasFeignService;
//
//	public ClientDetailsServiceImpl() {
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * 注意secret需要BCrypt加密，否则会报Encoded password does not look like BCrypt
//	 *
//	 * @param s
//	 * @return
//	 * @throws ClientRegistrationException
//	 */
//	@Override
//	public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
//
//		ApiResult<OauthClient> res = dasFeignService.getClientInfoByClientId(s);
//
//		if (res.getCode() != 1) {
//			Log.error(this, res.getMessage());
//			throw new ClientRegistrationException(s + "不存在");
//		}
//		OauthClient client = res.getData();
//		BaseClientDetails bcd = null;
//		bcd = new BaseClientDetails(s, client.getResourceIds() == null ? "" : client.getResourceIds(), client.getScope(), client.getAuthorizedGrantTypes(), client.getAuthorities() == null ? "" : client.getAuthorities());
//		if (!TextUtils.isBlank(client.getClientSecret())) {
//			bcd.setClientSecret(passwordEncoder.encode(client.getClientSecret()));
//		}
//
//		if (!TextUtils.isBlank(client.getAdditionalInformation())) {
//			Gson gson = new Gson();
//			Map<String, Object> map = new HashMap<String, Object>();
//			map = gson.fromJson(client.getAdditionalInformation(), map.getClass());
//			bcd.setAdditionalInformation(map);
//		}
//		if (!TextUtils.isBlank(client.getAutoapprove())) {
//			Set set = new HashSet<String>();
//			set.add(client.getAutoapprove());
//			bcd.setAutoApproveScopes(set);
//		}
//		if (!TextUtils.isBlank(client.getWebServerRedirectUri())) {
//			Set set = new HashSet<String>();
//			set.add(client.getWebServerRedirectUri());
//			bcd.setRegisteredRedirectUri(set);
//		}
//		if (client.getRefreshTokenValidity() != null) {
//			bcd.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
//		}
//		if (client.getAccessTokenValidity() != null) {
//			bcd.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
//		}
//		return bcd;
//	}
//}