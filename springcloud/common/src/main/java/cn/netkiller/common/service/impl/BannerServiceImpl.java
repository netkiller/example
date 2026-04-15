package cn.netkiller.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.netkiller.common.domain.Banner;
import cn.netkiller.common.repository.BannerRepository;
import cn.netkiller.common.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerRepository bannerRepository;

//	public Banner getBanner(int id) {

//		return bannerRepository.findOne(id);
//	}

	public Iterable<Banner> findByPosition(String position) {

		return bannerRepository.findByPosition(position);
	}
}
