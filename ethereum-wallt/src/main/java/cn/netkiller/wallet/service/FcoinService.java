package cn.netkiller.wallet.service;

import java.util.concurrent.Future;

import cn.netkiller.wallet.domain.fcoin.Crawler;

public interface FcoinService {

	void crawler();

	public Future<String> task(Crawler crawler);

	void send(int page, int size);

}
