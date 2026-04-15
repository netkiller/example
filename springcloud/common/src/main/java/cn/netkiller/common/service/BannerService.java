package cn.netkiller.common.service;

import cn.netkiller.common.domain.Banner;

public interface BannerService {

	Iterable<Banner> findByPosition(String position);

}
