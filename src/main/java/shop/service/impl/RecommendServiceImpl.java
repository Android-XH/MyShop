package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.RecommendExample;
import shop.mode.mapper.RecommendMapper;
import shop.service.RecommendService;
@Service
public class RecommendServiceImpl extends BaseServiceImpl<RecommendMapper, RecommendExample> implements RecommendService {
}
